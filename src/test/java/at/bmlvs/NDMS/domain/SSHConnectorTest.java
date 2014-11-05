package at.bmlvs.NDMS.domain;

import at.bmlvs.NDMS.domain.connectors.SSHConnector;

public class SSHConnectorTest {

	public static void main(String[] args) {
		SSHConnector ssh = new SSHConnector("192.168.1.12", "Herkel", "gwdH_2014");
		try {
			ssh.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		System.out.println(ssh.getSSHFingerprint());
//		ssh.sendCmd("enable\n"
//				+ "gwd_2014\n"
//				+ "conf t\n"
//				+ "hostname test123\n");

	}

}
