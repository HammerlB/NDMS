package at.bmlvs.NDMS.domain.connectors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import at.bmlvs.NDMS.domain.connectors.CustomExceptions.SSHException;

public class SSHConnectorLight {
	private ConnectionSSH ssh;
	private volatile boolean connected;
	private boolean somethingToSend, disconnect, readerStarted, reload;
	private int progress, counter;
	private String cmdToSend, enablePass;
	private volatile String connectionException;
	private Thread reader;
	private CopyOnWriteArrayList<String> cmd;
	
	public SSHConnectorLight(String host, String user, String sshPass,
			String enablePass) {
		ssh = new ConnectionSSH(host, user, sshPass);
		this.enablePass = enablePass;
		connected = false;
		somethingToSend = false;
		readerStarted = false;
		reload = false;
		counter = 1;
		disconnect = false;
		cmd = new CopyOnWriteArrayList<String>();
		reader = new Thread(ssh);
		counter=0;
	}
	
	public void connect() throws Exception {
		if (!connected) {
			ssh.connect();
			this.connected = true;
			System.out.println("Connected!");
		} else {
			System.out.println("Already connected!");
		}
	}
	
	private void sendCMD(String cmd) {
		try {
			ssh.sendCmd(cmd);
			if(!reader.isAlive()){
				reader.start();
				System.out.println("Reader started!");
			}
		} catch (IOException e) {
			System.err.println("SSH: Error in sending process!");
		}
		counter++;
		System.err.println("Sended!!("+counter+")");
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
			sendCMD(txt+"\n");
		}
		sendEnd();
	}

	public void sendEnd() {
		sendCMD("end\n");
	}
	
	public void reloadWithoutWrite() {
		sendCMD("enable\n" + enablePass + "\n" + "reload\nno\n\n");
		System.err.println("Reload was issued!!! (without write)");
		System.err.println("Disconnected due reload...");
		System.err.println("Please reconnect when device is avaliable again!");
	}

	public void reloadWithWrite() {
		sendCMD("enable\n" + enablePass + "\n" + "reload\nyes\n\n");
		System.err.println("Reload was issued!!! (with write)");
		System.err.println("Disconnected due reload...");
		System.err.println("Please reconnect when device is avaliable again!");
	}

	public void disconnect() throws Exception {
		if (connected){
			ssh.disconnectReader();
			ssh.disconnect();
		}
	}

	public void prepareSnapshot() {
		sendCMD("enable\n"
				+ enablePass
				+ "\ncopy run flash:snapshot.txt\n\n\nconf t\ntftp flash:snapshot.txt\nend\n");
	}
	
	public void playSnapshot(String fullName){
		sendCMD("enable\n"
				+ enablePass
				+ "\ncopy tftp:snapshotToPlay.txt start\n\n\nconf t\ntftp flash:snapshot.txt\nend\n");
	}

//	public void checkProgress(int i) {
//		this.progress = 100 / cmd.size() * i;
//	}
}
