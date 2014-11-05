package at.bmlvs.NDMS.domain;

import at.bmlvs.NDMS.domain.connectors.ThreadSSH;

public class ThreadSSHTest {

	public static void main(String[] args) {
		ThreadSSH ssh = new ThreadSSH("192.168.1.12","Herkel","gwdH_2014");
		ssh.start();
		ssh.setCmdToSend("enable\n" + "gwd_2014\n" + "conf t\n"
					+ "hostname SW1\n");
		ssh.setSomethingToSend(true);
	}

}
