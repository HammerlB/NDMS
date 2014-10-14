package at.bmlvs.NDMS;

import at.bmlvs.NDMS.domain.connectors.SSHConnector;
import at.bmlvs.NDMS.service.PersistenceService;
import at.bmlvs.NDMS.service.ServiceFactory;

public class App
{
	public static void main(String[] args)
	{
		SSHConnector sshcon = new SSHConnector("192.168.1.12", "Herkel", "gwdH_2014", "2", 22);
		sshcon.setInput(
				"enable\n"
				+ "gwd_2014\n"
				+ "show run | include interface\n"
				+ "         ");
		sshcon.connect();
		System.out.println(sshcon.getOutput());
		ServiceFactory.setPersistenceService(new PersistenceService());
		
		//Test
		//ServiceFactory.getPersistenceService().setTemplates(new Tem);
	}
}
