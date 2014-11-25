package at.bmlvs.NDMS.domain.connectors;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import com.sshtools.j2ssh.transport.IgnoreHostKeyVerification;

import at.bmlvs.NDMS.domain.connectors.CustomExceptions.SSHException;
import at.bmlvs.NDMS.domain.helper.IPGrabber;

public class SSHConnector {
	private ConnectionSSH ssh;
	private volatile boolean connected;
	private int progress, counter;
	private String enablePass;
	private Thread reader;

	public SSHConnector(String host, String user, String sshPass,
			String enablePass) {
		ssh = new ConnectionSSH(host, user, sshPass);
		this.enablePass = enablePass;
		connected = false;
		counter = 1;
		reader = new Thread(ssh);
		counter = 0;
	}

	public void connect(boolean withRetry) throws Exception {
		if (!connected&&withRetry) {
			try{
				ssh.connect();
//				ssh.wantOutput();
			}catch(ConnectException e){
				try{
					System.out.println("Retry connecting... ("+e.getMessage()+")(1)");
					ssh.connect();
				}catch(ConnectException e1){
					System.out.println("Retry connecting... ("+e1.getMessage()+")(2)");
					ssh.connect();
				}
			}
			this.connected = true;
			System.out.println("Connected!");
			
		}else if(!connected&&!withRetry){
			ssh.connect();
		} else {
			System.out.println("Already connected!");
		}
	}

	private void sendCMD(String cmd) {
		try {
			ssh.sendCmd(cmd);
			if (!reader.isAlive()) {
				reader.start();
				System.out.println("Reader started!");
			}
		} catch (IOException e) {
			System.err.println("SSH: Error in sending process!");
		}
		counter++;
//		System.out.println("Sended!!(" + counter + ")");
	}

	public void sendSingleCMD(String cmd) {
		sendCMD(cmd + "\n");
	}

	public void sendSingleCMDConfigMode(String cmd) {
		sendCMD("enable\n" + enablePass + "\n" + "conf t\n" + cmd + "\n"
				+ "end\n");
	}

	public void sendConfigMode() {
		sendCMD("enable\n" + enablePass + "\n" + "conf t\n");
	}

	public void sendEnableMode() {
		sendCMD("enable\n" + enablePass + "\n");
	}

	public void sendMultipleCMD(ArrayList<String> cmds) {
		sendConfigMode();
		for (String txt : cmds) {
			sendCMD(txt + "\n");
		}
		sendEnd();
	}

	public void sendEnd() {
		sendCMD("end\n");
	}

	public void reloadWithoutWrite() throws InterruptedException {
		Thread.sleep(10000);
		sendEnableMode();
		reload1();
		reload();
		System.err.println("Reload issued!!! (without write)");
		System.err.println("Disconnected due reload...");
		System.err.println("Please reconnect when device is avaliable again!");
	}

	public void reloadWithWrite() throws InterruptedException {
		saveRunningConfig();
		Thread.sleep(10000);
		reload();
		System.err.println("Reload issued!!! (with write)");
		System.err.println("Disconnected due reload...");
		System.err.println("Please reconnect when device is avaliable again!");
	}

	private void reload() {
		sendCMD("enable\n" + enablePass + "\n" + "reload\n\n");
	}
	private void reload1() {
		sendCMD("enable\n" + enablePass + "\n" + "reload\nno\n\n");
	}

	public void saveRunningConfig() {
		sendCMD("enable\n" + enablePass + "\n" + "wr\n" + "end\n");
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	
	public void disconnect() throws Exception {
		if (connected) {
			ssh.disconnectReader();
			ssh.disconnect();
		}
	}

	public void prepareSnapshot() {
		begin();
		sendEnableMode();
		sendCMD("del flash:snapshot.txt\n\n");
		sendCMD("\ncopy run flash:snapshot.txt\n\n\nconf t\ntftp flash:snapshot.txt\nend\n");
		end();
	}

	public void playSnapshot(String fullName) throws SocketException, UnknownHostException, InterruptedException {
		begin();
		sendEnableMode();
		sendCMD("del flash:play.txt\n\n\ncopy tftp: flash:"
				+ "\n"+IPGrabber.grab()
				+ "\n"+fullName
				+ "\nplay.txt\n\n");
		Thread.sleep(4000);
		sendCMD("copy flash:play.txt start\n\n");
		Thread.sleep(4000);
		System.out.println("Snapshot played! Waiting for Reload...");
		end();
//		reloadWithoutWrite();
	}
	
	public boolean expect(String element){
		if(ssh.getOutput().matches("/Delete filename\\s\\Ssnapshot.txt\\S\\S/g")){
			return true;
		}else{
			return false;
		}
	}
	
	public void begin(){
		ssh.begin();
	}
	
	public void end(){
		ssh.end();
	}
	
	public String getSSHFingerprint() {
		return ssh.getSSHFingerprint().split(":")[1];
	}
	
	public ConnectionSSH getSSHConnection(){
		return ssh;
	}
	// public void checkProgress(int i) {
	// this.progress = 100 / cmd.size() * i;
	// }
}
