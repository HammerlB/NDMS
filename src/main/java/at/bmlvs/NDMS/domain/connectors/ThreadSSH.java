package at.bmlvs.NDMS.domain.connectors;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadSSH extends Thread {

	private SSHConnector ssh;
	private boolean isConnected;
	private boolean somethingToSend;
	private String cmdToSend;
	private Thread t;
	private boolean disconnect;
	private boolean readerStarted;
	private CopyOnWriteArrayList<String> cmd;

	public ThreadSSH(String host, String user, String pass) {
		ssh = new SSHConnector(host, user, pass);
		isConnected = false;
		somethingToSend = false;
		readerStarted = false;
		cmd = new CopyOnWriteArrayList<String>();
		t = new Thread(ssh);
	}

	@Override
	public void run() {
		if (!isConnected) {
			try {
				ssh.connect();
				setConnected(true);
				System.out.println("Connected!");
			} catch (Exception e) {
				System.out.println("SSH: " + e.getMessage() + "\n" + "Reason: "
						+ e.getCause());
			}
		}
		while (true) {
			if (somethingToSend) {
				try {
					for (int i = 0; i < cmd.size(); i++) {
						sendCMD(cmd.get(i));
						System.out.println("Sended!!");
						sleep(1000);
					}
					cmd.clear();

					if (!readerStarted) {
						t.start();
						System.out.println("Reader Started!");
						readerStarted = true;
					}
					setSomethingToSend(false);
				} catch (Exception e) {
					System.out.println("SSH: " + e.getMessage() + "\n"
							+ "Reason: " + e.getCause());
				}
			}
			if (disconnect) {
				try {
					System.out.println("Trying to disconnect...");
					sleep(3000);
					t.interrupt();
					ssh.disconnect();
					System.out.println("Disconnected!");
					break;
				} catch (Exception e) {
					System.out.println("SSH: " + e.getMessage() + "\n"
							+ "Reason: " + e.getCause());
				}
			}
		}
	}

	private void sendCMD(String cmd) {
		ssh.sendCmd(cmd);
		// try {
		// out.write(cmd.getBytes());
		//
		// byte[] buffer = new byte[cmd.getBytes().length];
		// int read = 0;
		// for (int i = 0; i < 100; i++) {
		// read = in.read(buffer);
		// }
		// } catch (IOException e) {
		// System.out.println("sendCMD: " + e.getMessage());
		// }
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

	public void doSendCMD(String cmd) {

		this.cmd.add(cmd);
		this.somethingToSend = true;

	}

	public void doSendCMDConfigMode(String cmd, String enablePass) {
		this.cmd.add("enable\n" + enablePass + "\n" + "conf t\n" + cmd + "\n"
				+ "exit\n");
		this.somethingToSend = true;

	}

	public void doDisconnect() {
		this.disconnect = true;
	}
}
