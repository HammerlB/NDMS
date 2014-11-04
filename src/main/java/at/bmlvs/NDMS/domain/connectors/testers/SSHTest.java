package at.bmlvs.NDMS.domain.connectors.testers;

import at.bmlvs.NDMS.domain.connectors.SSHConnector;

public class SSHTest {

	public static void main(String[] args) {
		SSHConnector ssh = new SSHConnector("192.168.1.12", "Herkel", "gwdH_2014");
		ssh.connect();
		
		System.out.println(ssh.getSSHFingerprint());
//		ssh.sendCmd("enable\n"
//				+ "gwd_2014\n"
//				+ "conf t\n"
//				+ "hostname test123\n");

	}

}
