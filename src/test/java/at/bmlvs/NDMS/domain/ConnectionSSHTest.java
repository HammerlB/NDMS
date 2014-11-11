package at.bmlvs.NDMS.domain;

import at.bmlvs.NDMS.domain.connectors.ConnectionSSH;

public class ConnectionSSHTest {

	public static void main(String[] args) {
		ConnectionSSH ssh = new ConnectionSSH("192.168.1.12", "Herkel", "gwdH_2014");
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
