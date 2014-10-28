package at.bmlvs.NDMS.domain.connectors;

public class SSHTest {

	public static void main(String[] args) {
		SSHConnector ssh = new SSHConnector();
//		System.out.println(ssh.getSshFingerprint());
		
		Thread th = new Thread(ssh);
		th.start();
		ssh.connect();
		ssh.sendCmd("enable\n"
				+ "gwd_2014\n"
				+ "show run | include interface\n"
				+ "      ");
		ssh.disconnect();
		th.interrupt();
		System.out.println(ssh.getOutput());
	}

}
