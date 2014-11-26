package at.bmlvs.NDMS.domain;

import java.util.ArrayList;

import at.bmlvs.NDMS.domain.connectors.SSHConnector;

public class SSHConnectorTest {

	public static void main(String[] args) {
		SSHConnector ssh = new SSHConnector("192.168.1.11", "Herkel",
				"gwdH_2014", "gwd_2014");
		try {
			ssh.connect(true);
			ssh.sendEnableMode();
//			ssh.sendConfigMode();
			ssh.expectWaitForIt("(config)#");
			ssh.disconnect();
			System.exit(0);
//			ssh.sendMultipleCMD(a);
//			ssh.sendConfigMode();
//			ssh.sendSingleCMD("hostname GWD-SW3");
//			ssh.sendEnd();
//			ssh.saveRunningConfig();
//			ssh.reloadWithWrite();
//			ssh.reloadWithoutWrite();
//			ssh.disconnect();
			// try {
			// Thread.sleep(10000); // 1000 milliseconds is one second.
			// } catch (InterruptedException ex) {
			// Thread.currentThread().interrupt();
			// }
			// try {
			// Thread.sleep(5000); // 1000 milliseconds is one second.
			// } catch (InterruptedException ex) {
			// Thread.currentThread().interrupt();
			// }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}