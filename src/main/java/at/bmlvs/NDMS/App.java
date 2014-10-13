package at.bmlvs.NDMS;

import at.bmlvs.NDMS.domain.connectors.SSHConnector;

/**
 * Hello world!
 *
 */
public class App
{
	public static void main(String[] args)
	{
		SSHConnector sshcon = new SSHConnector("192.168.1.12", "Herkel", "gwdH_2014", "2", 22);
		sshcon.connect();
	}
}
