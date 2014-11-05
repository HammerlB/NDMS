package at.bmlvs.NDMS.domain.connectors;

public class ThreadSSH extends Thread {

	private SSHConnector ssh;
	private boolean isConnected;
	private boolean somethingToSend;
	private String cmdToSend;
	private Thread t;

	public ThreadSSH(String host, String user, String pass) {
		ssh = new SSHConnector(host, user, pass);
		isConnected = false;
		somethingToSend = false;
		t = new Thread(ssh);
	}

	@Override
	public void run() {
		try {
			if (!isConnected) {
				try {
					ssh.connect();
					setConnected(true);
				} catch (Exception e) {
					System.out.println("SSH: " + e.getMessage()+"\n"+"Reason: "+ e.getCause());
				}
			}
			if (somethingToSend){
				sendCMD(cmdToSend);
				t.start();
				sleep(100);
				setSomethingToSend(false);
			}
		} catch (InterruptedException e) {
			System.out.println("SSH: " + e.getMessage()+"\n"+"Reason: "+ e.getCause());
		}
	}

	public void sendCMD(String cmd) {
		ssh.sendCmd(cmd);
//		try {
//			out.write(cmd.getBytes());
//
//			byte[] buffer = new byte[cmd.getBytes().length];
//			int read = 0;
//			for (int i = 0; i < 100; i++) {
//				read = in.read(buffer);
//			}
//		} catch (IOException e) {
//			System.out.println("sendCMD: " + e.getMessage());
//		}
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
