package at.bmlvs.NDMS.domain.connectors;

public class ThreadSSH extends Thread {

	private SSHConnector ssh;
	private boolean isConnected;
	private boolean somethingToSend;
	private String cmdToSend;
	private Thread t;
	private boolean disconnect;
	private boolean readerStarted;

	public ThreadSSH(String host, String user, String pass) {
		ssh = new SSHConnector(host, user, pass);
		isConnected = false;
		somethingToSend = false;
		readerStarted = false;
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
				System.out.println("SSH: " + e.getMessage() + "\n"
						+ "Reason: " + e.getCause());
			}
		}
		while (true) {
			if (somethingToSend) {
				try {
					sendCMD(cmdToSend);
					System.out.println("Sended!!");
					if (!readerStarted){
						t.start();
						System.out.println("Reader Started!");
						readerStarted=true;
					}
					setSomethingToSend(false);
				} catch (Exception e) {
					System.out.println("SSH: " + e.getMessage() + "\n"
							+ "Reason: " + e.getCause());
				}
			}
			if (disconnect) {
				t.stop();
				try {
					ssh.disconnect();
					disconnect=false;
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
		this.cmdToSend = cmd;
		this.somethingToSend = true;
	}
	
	public void doSendCMDConfigMode(String cmd,String enablePass){
		this.cmdToSend = "enable\n"+enablePass+"\n"+"conf t\n"+cmd+"exit\n";
		this.somethingToSend = true;
		
	}

	public void doDisconnect() {
		this.disconnect = true;
	}
}
