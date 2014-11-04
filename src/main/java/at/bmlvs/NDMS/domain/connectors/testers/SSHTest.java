package at.bmlvs.NDMS.domain.connectors.testers;

import at.bmlvs.NDMS.domain.connectors.SSHConnector;

public class SSHTest {

	public static void main(String[] args) {
		SSHConnector ssh = new SSHConnector("192.168.1.12", "Herkel", "gwdH_2014");
//		System.out.println(ssh.getSshFingerprint());
		
//		Thread th = new Thread(ssh);
		ssh.connect();
		ssh.sendCmd("enable\n"
				+ "gwd_2014\n"
				+ "conf t\n"
				+ "hostname test123\n");
//		th.start();
//		ssh.createOutput(new byte[16384]);
//		ssh.disconnect();
//		th.interrupt();
//		System.out.println(ssh.getOutput());
	}

}