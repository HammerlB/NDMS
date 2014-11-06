package at.bmlvs.NDMS.domain;

import at.bmlvs.NDMS.domain.connectors.ThreadSSH;

public class ThreadSSHTest {

	public static void main(String[] args) {
		ThreadSSH ssh = new ThreadSSH("192.168.1.12","Herkel","gwdH_2014");
		ssh.start();
		while(!ssh.isConnected()){
			System.out.println("Waiting to connect!");
		}
		ssh.doSendCMD("enable\n" + "gwd_2014\n" + "conf t\n"
					+ "hostname test32423\n"
					+ "hostname test123\n"
					+ "hostname asd45545aassa\n"
					+ "exit\n");
		ssh.doSendCMD("enable\n" + "gwd_2014\n" + "conf t\n"
				+ "hostname tesqwrwqt32423\n"
				+ "hostname testqweqwer123\n"
				+ "hostname asd4554qwe5\n"
				+ "exit\n");
		ssh.doSendCMD("enable\n" + "gwd_2014\n" + "conf t\n"
				+ "hostname testqwe32423\n"
				+ "hostname testqw123\n"
				+ "hostname da723asd847\n"
				+ "exit\n");
		ssh.doSendCMDConfigMode("hostname GWD-SW3\n", "gwd_2014");
//		ssh.doDisconnect();
	}

}