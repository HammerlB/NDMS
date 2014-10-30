package at.bmlvs.NDMS.domain.connectors;

public class SSHTest {

	public static void main(String[] args) {
		SSHConnector ssh = new SSHConnector();
//		System.out.println(ssh.getSshFingerprint());
		
		Thread th = new Thread(ssh);
//		th.start();
		ssh.connect();
		ssh.sendCmd("ping 192.168.1.12\n");
		ssh.createOutput(new byte[16384]);
		ssh.disconnect();
//		th.interrupt();
//		System.out.println(ssh.getOutput());
	}

}
