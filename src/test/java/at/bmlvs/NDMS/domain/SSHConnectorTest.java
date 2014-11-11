package at.bmlvs.NDMS.domain;

import java.util.ArrayList;

import at.bmlvs.NDMS.domain.connectors.SSHConnector;


public class SSHConnectorTest {

	public static void main(String[] args) {
		SSHConnector ssh = new SSHConnector("192.168.1.13", "Herkel", "gwdH_2014", "gwd_2014");
		ArrayList<String> a = new ArrayList<String>();
		ssh.start();
		for(int i = 1;i<=48;i++){
			a.add("int f0/"+i+"\ndesc f0/"+i);
		}
		ssh.doSendMultipleCMD(a);
		
//		try {
//			Thread.sleep(3000); // 1000 milliseconds is one second.
//		} catch (InterruptedException ex) {
//			Thread.currentThread().interrupt();
//		}
		
//		ssh.doReloadWithoutWrite();
		ssh.doDisconnect();
	}

}