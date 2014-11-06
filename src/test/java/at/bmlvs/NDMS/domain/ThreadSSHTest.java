package at.bmlvs.NDMS.domain;

import at.bmlvs.NDMS.domain.connectors.ThreadSSH;

public class ThreadSSHTest {

	public static void main(String[] args) {
		ThreadSSH ssh = new ThreadSSH("192.168.1.12","Herkel","gwdH_2014");
		ssh.start();
		ssh.doSendCMD("enable\n" + "gwd_2014\n" + "conf t\n"
					+ "hostname test5\n");
//		ssh.doSendCMD("hostname test2\n");
		
		for(int i = 0;i<2000000;i++){
			if (i==1999999)
				ssh.interrupt();
		}
//		ssh.doDisconnect();
	}

}
