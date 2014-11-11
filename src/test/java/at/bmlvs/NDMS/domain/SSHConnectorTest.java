package at.bmlvs.NDMS.domain;

import java.util.ArrayList;

import at.bmlvs.NDMS.domain.connectors.SSHConnector;


public class SSHConnectorTest {

	public static void main(String[] args) {
		SSHConnector ssh = new SSHConnector("192.168.1.12", "Herkel", "gwdH_2014", "gwd_2014");
		ArrayList<String> a = new ArrayList<String>();
		ssh.start();
//		while (!ssh.isConnected()) {
//			try {
//			    Thread.sleep(1000);                 //1000 milliseconds is one second.
//			} catch(InterruptedException ex) {
//			    Thread.currentThread().interrupt();
//			}
//		}
//		ssh.doSendSingleCMDConfigMode("hostname haha", "gwd_2014");
//		ssh.doSendSingleCMDConfigMode("hostname trorlrol", "gwd_2014");
//		ssh.doSendSingleCMDConfigMode("hostname trorlrll", "gwd_2014");
//		ssh.doSendSingleCMDConfigMode("hostname sadsdsa", "gwd_2014");
//		for(int i = 0;i<100;i++){
//			ssh.doSendCMDConfigMode("hostname sadsdsa"+i, "gwd_2014");
//		}
		for(int i = 1;i<=48;i++){
			a.add("int f0/"+i+"\ndesc f0/"+i);
		}
		ssh.doSendMultipleCMD(a);
		
		ssh.doSendEnd();
		
		ssh.doReloadWithoutWrite();
		
//		try {
//		    Thread.sleep(3000);                 //1000 milliseconds is one second.
//		} catch(InterruptedException ex) {
//		    Thread.currentThread().interrupt();
//		}
//		ssh.doSendSingleCMDConfigMode("int f0/48\ndesc tralala","gwd_2014");
//		try {
//		    Thread.sleep(10000);                 //1000 milliseconds is one second.
//		} catch(InterruptedException ex) {
//		    Thread.currentThread().interrupt();
//		}
//		ssh.doSendSingleCMDConfigMode("int f0/45\ndesc geh bitte","gwd_2014");
//		try {
//		    Thread.sleep(5000);                 //1000 milliseconds is one second.
//		} catch(InterruptedException ex) {
//		    Thread.currentThread().interrupt();
//		}
//		ssh.doSendSingleCMDConfigMode("int f0/40\ndesc zumama","gwd_2014");
//		try {
//		    Thread.sleep(10000);                 //1000 milliseconds is one second.
//		} catch(InterruptedException ex) {
//		    Thread.currentThread().interrupt();
//		}
//		ssh.doSendEnd();
//		ssh.doReloadWithoutWrite("gwd_2014");
//		ssh.doDisconnect();
		
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