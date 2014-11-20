package at.bmlvs.NDMS.domain.connectors;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import com.sshtools.j2ssh.transport.IgnoreHostKeyVerification;

import at.bmlvs.NDMS.domain.connectors.CustomExceptions.SSHException;

public class SSHConnector {
	private ConnectionSSH ssh;
	private volatile boolean connected;
	private boolean somethingToSend, disconnect, readerStarted, reload;
	private int progress, counter;
	private String cmdToSend, enablePass;
	private Thread reader;
	private CopyOnWriteArrayList<String> cmd;

	public SSHConnector(String host, String user, String sshPass,
			String enablePass) {
		ssh = new ConnectionSSH(host, user, sshPass);
		this.enablePass = enablePass;
		connected = false;
		somethingToSend = false;
		readerStarted = false;
		reload = true;
		counter = 1;
		disconnect = false;
		cmd = new CopyOnWriteArrayList<String>();
		reader = new Thread(ssh);
		counter = 0;
	}

	public void connect(boolean withRetry) throws Exception {
		if (!connected&&withRetry) {
			try{
				ssh.connect();
			}catch(ConnectException e){
				try{
					System.out.println("Retry connecting... (timed out)");
					ssh.connect();
				}catch(ConnectException e1){
					System.out.println("Retry connecting... (timed out)");
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
		System.out.println("Sended!!(" + counter + ")");
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

	public void reloadWithoutWrite() {
		sendEnableMode();
		reload();
		reload1();
		System.err.println("Reload issued!!! (without write)");
		System.err.println("Disconnected due reload...");
		System.err.println("Please reconnect when device is avaliable again!");
	}

	public void reloadWithWrite() {
		saveRunningConfig();
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

	public void clearOutput() {

	}

	public void prepareSnapshot() {
		sendCMD("enable\n"
				+ enablePass
				+ "\ncopy run flash:snapshot.txt\n\n\nconf t\ntftp flash:snapshot.txt\nend\n");
	}

	public void playSnapshot(String fullName) {
		sendCMD("enable\n"
				+ enablePass
				+ "\ncopy tftp:snapshotToPlay.txt start\n\n\nconf t\ntftp flash:snapshot.txt\nend\n");
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
