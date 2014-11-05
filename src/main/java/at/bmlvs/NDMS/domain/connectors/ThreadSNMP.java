package at.bmlvs.NDMS.domain.connectors;

public class ThreadSNMP extends Thread{

	private SNMPConnector snmp;
	private boolean isConnected;
	private boolean somethingToSend;
	private String cmdToSend;

	public ThreadSNMP(String host, String communityString) {
		snmp = new SNMPConnector(host, communityString);
		isConnected = false;
		somethingToSend = false;
	}

	public void run() {
		try {
			if (!isConnected) {
				try {
					snmp.connect();
					setConnected(true);
				} catch (Exception e) {
					System.out.println("SNMP: " + e.getMessage()+"\n"+"Reason: "+ e.getCause());
				}
			}
//			if (somethingToSend){
//				sendCMD(cmdToSend);
//				sleep(100);
//				setSomethingToSend(false);
//			}
			
		} catch (InterruptedException e) {
			System.out.println("SNMP: " + e.getMessage()+"\n"+"Reason: "+ e.getCause());
		}
	}

	public void sendCMD(String cmd) {
		snmp.sendCmd(cmd);
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
