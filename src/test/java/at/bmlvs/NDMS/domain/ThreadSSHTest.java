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
				+ "int f0/25\n" + "desc 12345\n"
				+ "end\n");
		try {
		    Thread.sleep(1000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		ssh.doSendCMD("enable\n" + "gwd_2014\n" + "conf t\n"
				+ "int f0/26\n" + "desc 12345\n"
				+ "end\n");
		try {
		    Thread.sleep(1000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		ssh.doSendCMD("enable\n" + "gwd_2014\n" + "conf t\n"
				+ "int f0/27\n" + "desc 12345\n"
				+ "end\n");
		try {
		    Thread.sleep(1000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		// for(int i = 0;i<5;i++)
		// ssh.doSendCMDConfigMode("hostname GWD-SW"+i+"\n", "gwd_2014");
		ssh.doDisconnect();
	}

}