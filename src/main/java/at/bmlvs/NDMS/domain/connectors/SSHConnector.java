package at.bmlvs.NDMS.domain.connectors;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class SSHConnector extends Thread {
	private ConnectionSSH ssh;
	private volatile boolean connected;
	private boolean somethingToSend, disconnect, readerStarted, reload,
			running, fPause;
	private int progress, counter;
	private String cmdToSend, enablePass;
	private volatile String connectionException;
	private Thread reader;
	private CopyOnWriteArrayList<String> cmd;

	public SSHConnector(String host, String user, String sshPass,
			String enablePass) {
		ssh = new ConnectionSSH(host, user, sshPass);
		this.enablePass = enablePass;
		connected = false;
		somethingToSend = false;
		readerStarted = false;
		reload = false;
		running = true;
		fPause = false;
		counter = 1;
		disconnect = false;
		cmd = new CopyOnWriteArrayList<String>();
		reader = new Thread(ssh);
	}

	@Override
	public void run() {
		this.checkMainThread();
	}

	public void checkMainThread() {
		while (true) {
			if (somethingToSend) {
				try {
					for (int i = 0; i < cmd.size(); i++) {
						sendCMD(cmd.get(i));
						if (!readerStarted) {
							reader.start();
							System.out.println("Reader Started!");
							readerStarted = true;
						}
						checkProgress(i);
						System.err.println("Sended!!" + "(" + counter + ")");
						// System.out.println("Command:"+cmd.get(i));
						counter++;
						if (reload) {
							System.out.println("Reload Requested!");
							reload = false;
							sleep(10000);
						} else {
							sleep(1000);
						}
					}
					cmd.clear();
					this.somethingToSend = false;
				} catch (Exception e) {
					System.err.println("SSH: " + e.getMessage());
					break;
				}
			}
			if (disconnect) {
				try {
					System.out.println("Trying to disconnect...");
					sleep(5000);
					reader.interrupt();
					ssh.disconnect();
					System.out.println("Disconnected!");
					break;
				} catch (Exception e) {
					System.err.println("SSH: " + e.getMessage());
				}
			}
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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

	public CopyOnWriteArrayList<String> getCmd() {
		return cmd;
	}

	public void setCmd(CopyOnWriteArrayList<String> cmd) {
		this.cmd = cmd;
	}

	private void sendCMD(String cmd) {
		ssh.sendCmd(cmd);
	}

	public void doSendCMD(String cmd) {
		this.cmd.add(cmd);
		this.somethingToSend = true;
	}

	public void doSendSingleCMDConfigMode(String cmd) {
		this.cmd.add("enable\n" + enablePass + "\n" + "conf t\n" + cmd + "\n"
				+ "end\n");
		this.somethingToSend = true;
	}

	public void doSetConfigMode() {
		this.cmd.add("enable\n" + enablePass + "\n" + "conf t\n");
		this.somethingToSend = true;
	}

	public void doSetEnableMode() {
		this.cmd.add("enable\n" + enablePass + "\n");
		this.somethingToSend = true;
	}

	public void doSendMultipleCMD(ArrayList<String> cmds) {
		String cmd = "";
		doSetConfigMode();
		for (String txt : cmds) {
			cmd += txt + "\n";
		}
		doSendCMD(cmd);
		doSendEnd();
		this.somethingToSend = true;
	}

	public void doSendEnd() {
		this.cmd.add("end\n");
		this.somethingToSend = true;
	}

	public void doReloadWithoutWrite() {
		// this.cmd.add("RELOAD1");
		this.cmd.add("enable\n" + enablePass + "\n" + "reload\nno\n\n");
		this.somethingToSend = true;
		this.reload = true;
	}

	public void doReloadWithWrite() {
		// this.cmd.add("RELOAD2");
		this.cmd.add("enable\n" + enablePass + "\n" + "reload\nyes\n\n");
		this.somethingToSend = true;
		this.reload = true;
	}

	public void doDisconnect() {
		if (isConnected())
			this.disconnect = true;
	}

	public void checkProgress(int i) {
		this.progress = 100 / cmd.size() * i;
	}

	public String getSSHFingerprint() {
		return ssh.getSSHFingerprint();
	}

	public ConnectionSSH getSSHConnection() {
		return ssh;
	}

	public void setSSHConnection(ConnectionSSH ssh) {
		this.ssh = ssh;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public boolean isSomethingToSend() {
		return somethingToSend;
	}

	public void setSomethingToSend(boolean somethingToSend) {
		this.somethingToSend = somethingToSend;
	}

	public boolean isDisconnect() {
		return disconnect;
	}

	public void setDisconnect(boolean disconnect) {
		this.disconnect = disconnect;
	}

	public boolean isReaderStarted() {
		return readerStarted;
	}

	public void setReaderStarted(boolean readerStarted) {
		this.readerStarted = readerStarted;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public String getCmdToSend() {
		return cmdToSend;
	}

	public void setCmdToSend(String cmdToSend) {
		this.cmdToSend = cmdToSend;
	}

	public Thread getReader() {
		return reader;
	}

	public ConnectionSSH getSsh() {
		return ssh;
	}

	public void setSsh(ConnectionSSH ssh) {
		this.ssh = ssh;
	}

	public boolean isReload() {
		return reload;
	}

	public void setReload(boolean reload) {
		this.reload = reload;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public String getEnablePass() {
		return enablePass;
	}

	public void setEnablePass(String enablePass) {
		this.enablePass = enablePass;
	}

	public String getConnectionException() {
		return connectionException;
	}

	public void setConnectionException(String connectionException) {
		this.connectionException = connectionException;
	}

	public void setReader(Thread reader) {
		this.reader = reader;
	}

	public void pause() {
		fPause = true;
	}

	public void proceed() {
		fPause = false;
		notify();
	}
}
