package at.bmlvs.NDMS.domain.connectors;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ThreadSSH extends Thread {

	private SSHConnector ssh;
	private InputStream in;
	private OutputStream out;
	private boolean isConnected;
	private boolean somethingToSend;
	private String cmdToSend;

	public ThreadSSH(String host, String user, String pass) {
		ssh = new SSHConnector(host, user, pass);
	}

	@Override
	public void run() {
		try {
			if (!isConnected) {
				try {
					ssh.connect();
				} catch (Exception e) {
					System.out.println("ThreadConnectionError: " + e.getMessage());
				}
			}
			if (somethingToSend){
				sendCMD(cmdToSend);
				sleep(100);
				setSomethingToSend(false);
			}
		} catch (InterruptedException e) {
			System.out.println("ThreadInterrupted: " + e.getMessage());
		}
	}

	public void sendCMD(String cmd) {
		try {
			out.write(cmd.getBytes());

			byte[] buffer = new byte[cmd.getBytes().length];
			int read = 0;
			for (int i = 0; i < 100; i++) {
				read = in.read(buffer);
			}
		} catch (IOException e) {
			System.out.println("sendCMD: " + e.getMessage());
		}
	}

	public String getFingerprint() {
		return ssh.getSSHFingerprint();
	}

	public boolean isConnected() {
		return isConnected;
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	public boolean isSomethingToSend() {
		return somethingToSend;
	}

	public void setSomethingToSend(boolean somethingToSend) {
		this.somethingToSend = somethingToSend;
	}

	public String getCmdToSend() {
		return cmdToSend;
	}

	public void setCmdToSend(String cmdToSend) {
		this.cmdToSend = cmdToSend;
	}
}
