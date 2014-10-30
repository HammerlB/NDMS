package at.bmlvs.NDMS.domain.connectors;

public class SSHTest {

	public static void main(String[] args) {
		SSHConnector ssh = new SSHConnector();
//		System.out.println(ssh.getSshFingerprint());
		
//		Thread th = new Thread(ssh);
		ssh.connect();
		ssh.sendCmd("enable\n"
				+ "gwd_2014\n"
				+ "conf t\n"
				+ "hostname test123\n");
//		th.start();
//		ssh.createOutput(new byte[16384]);
//		ssh.disconnect();
//		th.interrupt();
//		System.out.println(ssh.getOutput());
	}

}
