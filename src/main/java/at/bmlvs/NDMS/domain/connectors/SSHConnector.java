package at.bmlvs.NDMS.domain.connectors;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class SSHConnector extends Thread {
	private ConnectionSSH ssh;
	private volatile boolean isConnected;
	private boolean somethingToSend, disconnect, readerStarted,
			wantFingerprint;
	private int progress;
	private String cmdToSend, fingerprint;
	private Thread reader;
	private CopyOnWriteArrayList<String> cmd;
	private final Object lock = new Object();


	public SSHConnector(String host, String user, String pass) {
		ssh = new ConnectionSSH(host, user, pass);
		isConnected = false;
		somethingToSend = false;
		readerStarted = false;
		cmd = new CopyOnWriteArrayList<String>();
		reader = new Thread(ssh);
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
						checkProgress(i);
						System.out.println("Sended!!");
						sleep(1000);
					}
					cmd.clear();

					if (!readerStarted) {
						reader.start();
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
					reader.interrupt();
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

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public void doSendCMD(String cmd) {
		this.cmd.add(cmd);
		this.somethingToSend = true;

	}

	public void doSendSingleCMDConfigMode(String cmd, String enablePass) {
		this.cmd.add("enable\n" + enablePass + "\n" + "conf t\n" + cmd + "\n"
				+ "end\n");
		this.somethingToSend = true;
	}

	public void doSetConfigMode(String enablePass) {
		this.cmd.add("enable\n" + enablePass + "\n" + "conf t\n");
		this.somethingToSend = true;
	}

	public void doSetEnableMode(String enablePass) {
		this.cmd.add("enable\n" + enablePass + "\n" + "conf t\n");
		this.somethingToSend = true;
	}

	public void doSendMultipleCMD(ArrayList<String> cmds, String enablePass) {
		for (String cmd : cmds) {
			doSetConfigMode(enablePass);
			doSendCMD(cmd);
		}
	}

	public void doReloadWithoutWrite(String enablePass) {
		this.cmd.add("enable\n" + enablePass + "\n" + "do reload\nno\n\n");
	}

	public void doReloadWithWrite(String enablePass) {
		this.cmd.add("enable\n" + enablePass + "\n" + "do reload\nyes\n\n");
	}

	public void doDisconnect() {
		this.disconnect = true;
	}

	public void checkProgress(int i) {
		this.progress = 100 / cmd.size() * i;
	}

	public String getSSHFingerprint() {
		return ssh.getSSHFingerprint();
	}
}
