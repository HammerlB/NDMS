package at.bmlvs.NDMS.domain.connectors;

public class SSHTest {

	public static void main(String[] args) {
		SSHConnectorNew sshc = new SSHConnectorNew("192.168.1.12", "Herkel",
				"gwdH_2014");
		System.out.println(sshc.execCmd("enable"));
		System.out.println(sshc.execCmd("gwd_2014"));
		System.out.println(sshc.execCmd("show run | include interface"));
	}
}
