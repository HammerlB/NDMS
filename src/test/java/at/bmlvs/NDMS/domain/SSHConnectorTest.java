package at.bmlvs.NDMS.domain;

import java.util.ArrayList;

import at.bmlvs.NDMS.domain.connectors.SSHConnector;
import at.bmlvs.NDMS.domain.connectors.SSHConnectorLight;

public class SSHConnectorTest {

	public static void main(String[] args) {
		SSHConnectorLight ssh = new SSHConnectorLight("192.168.1.12", "Herkel",
				"gwdH_2014", "gwd_2014");
		try {
			ssh.connect();
			ArrayList<String> a = new ArrayList<String>();
			for (int i = 1; i <= 48; i++) {
				a.add("int f0/" + i + "\ndesc trololol");
			}
			ssh.sendMultipleCMD(a);

			ssh.disconnect();
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