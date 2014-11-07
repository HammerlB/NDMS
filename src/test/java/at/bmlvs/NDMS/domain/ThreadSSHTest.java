package at.bmlvs.NDMS.domain;

import at.bmlvs.NDMS.domain.connectors.ThreadSSH;

public class ThreadSSHTest {

	public static void main(String[] args) {
		ThreadSSH ssh = new ThreadSSH("192.168.1.12", "Herkel", "gwdH_2014");
		ssh.start();
//		while (!ssh.isConnected()) {
//			try {
//			    Thread.sleep(1000);                 //1000 milliseconds is one second.
//			} catch(InterruptedException ex) {
//			    Thread.currentThread().interrupt();
//			}
//		}
		ssh.doSendCMDConfigMode("hostname haha", "gwd_2014");
		ssh.doSendCMDConfigMode("hostname trorlrol", "gwd_2014");
		ssh.doSendCMDConfigMode("hostname trorlrll", "gwd_2014");
		ssh.doSendCMDConfigMode("hostname sadsdsa", "gwd_2014");
//		for(int i = 0;i<100;i++){
//			ssh.doSendCMDConfigMode("hostname sadsdsa"+i, "gwd_2014");
//		}
		String cmd="";
		for(int i = 1;i<=48;i++){
			cmd+="int f0/"+i+"\ndesc f0/"+i+"\n";
		}
		ssh.doSendCMDConfigMode(cmd,"gwd_2014");
		try {
		    Thread.sleep(3000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		ssh.doSendCMDConfigMode("int f0/48\ndesc tralala","gwd_2014");
		try {
		    Thread.sleep(10000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		ssh.doSendCMDConfigMode("int f0/45\ndesc geh bitte","gwd_2014");
		try {
		    Thread.sleep(5000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		ssh.doSendCMDConfigMode("int f0/40\ndesc zumama","gwd_2014");
		try {
		    Thread.sleep(5000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		ssh.doDisconnect();
		
//		ssh.doSendCMD("enable\n" + "gwd_2014\n" + "conf t\n"
//				+ "int f0/26\n" + "desc 12345\n"
//				+ "end\n");
//		try {
//		    Thread.sleep(1000);                 //1000 milliseconds is one second.
//		} catch(InterruptedException ex) {
//		    Thread.currentThread().interrupt();
//		}
//		ssh.doSendCMD("enabe\n" + "gwd_2014\n" + "conf t\n"
//				+ "int f0/27\n" + "desc 12345\n"
//				+ "end\n");
//		try {
//		    Thread.sleep(1000);                 //1000 milliseconds is one second.
//		} catch(InterruptedException ex) {
//		    Thread.currentThread().interrupt();
//		}
		// for(int i = 0;i<5;i++)
		// ssh.doSendCMDConfigMode("hostname GWD-SW"+i+"\n", "gwd_2014");
		
	}

}