package at.bmlvs.NDMS.domain;

import at.bmlvs.NDMS.domain.connectors.ThreadSSH;

public class ThreadSSHTest {

	public static void main(String[] args) {
		ThreadSSH ssh = new ThreadSSH("192.168.1.12", "Herkel", "gwdH_2014");
		ssh.start();
		while (!ssh.isConnected()) {
			System.out.println("Waiting to connect!");
		}
		ssh.doSendCMD("enable\n" + "gwd_2014\n" + "conf t\n"
				+ "int f0/1\n" + "desc 12345\n"
				+ "int f0/2\n" + "desc 12345\n"
				+ "int f0/3\n" + "desc 12345\n"
				+ "int f0/4\n" + "desc 12345\n"
				+ "int f0/5\n" + "desc 12345\n"
				+ "int f0/6\n" + "desc 12345\n"
				+ "int f0/7\n" + "desc 12345\n"
				+ "int f0/8\n" + "desc 12345\n"
				+ "int f0/9\n" + "desc 12345\n"
				+ "int f0/10\n" + "desc 12345\n"
				+ "int f0/11\n" + "desc 12345\n"
				+ "int f0/12\n" + "desc 12345\n"
				+ "int f0/13\n" + "desc 12345\n"
				+ "int f0/14\n" + "desc 12345\n"
				+ "int f0/15\n" + "desc 12345\n"
				+ "int f0/16\n" + "desc 12345\n"
				+ "int f0/18\n" + "desc 12345\n"
				+ "int f0/19\n" + "desc 12345\n"
				+ "int f0/20\n" + "desc 12345\n"
				+ "int f0/21\n" + "desc 12345\n"
				+ "int f0/22\n" + "desc 12345\n"
				+ "int f0/23\n" + "desc 12345\n"
				+ "int f0/24\n" + "desc 12345\n"
				+ "int f0/25\n" + "desc 12345\n"
				+ "int f0/26\n" + "desc 12345\n"
				+ "int f0/27\n" + "desc 12345\n"
				+ "int f0/28\n" + "desc 12345\n"
				+ "int f0/29\n" + "desc 12345\n"
				+ "int f0/30\n" + "desc 12345\n"
				+ "end\n");
		try {
		    Thread.sleep(1000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
//		ssh.doSendCMD("enable\n" + "gwd_2014\n" + "conf t\n"
//				+ "int f0/26\n" + "desc 12345\n"
//				+ "end\n");
//		try {
//		    Thread.sleep(1000);                 //1000 milliseconds is one second.
//		} catch(InterruptedException ex) {
//		    Thread.currentThread().interrupt();
//		}
//		ssh.doSendCMD("enable\n" + "gwd_2014\n" + "conf t\n"
//				+ "int f0/27\n" + "desc 12345\n"
//				+ "end\n");
//		try {
//		    Thread.sleep(1000);                 //1000 milliseconds is one second.
//		} catch(InterruptedException ex) {
//		    Thread.currentThread().interrupt();
//		}
		// for(int i = 0;i<5;i++)
		// ssh.doSendCMDConfigMode("hostname GWD-SW"+i+"\n", "gwd_2014");
		ssh.doDisconnect();
	}

}