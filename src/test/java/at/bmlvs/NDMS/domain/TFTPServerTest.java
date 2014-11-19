package at.bmlvs.NDMS.domain;

import at.bmlvs.NDMS.domain.connectors.SSHConnector;
import at.bmlvs.NDMS.domain.connectors.TFTPConnector;
import at.bmlvs.NDMS.domain.connectors.TFTPSender;
import at.bmlvs.NDMS.service.PersistenceService;
import at.bmlvs.NDMS.service.ServiceFactory;

public class TFTPServerTest {

	public static void main(String[] args) throws Exception {
		ServiceFactory.setPersistenceService(new PersistenceService());
		ServiceFactory.setAppConfig(ServiceFactory.getPersistenceService()
				.getAppconfig().getElement());
		
		SSHConnector ssh = new SSHConnector("192.168.1.11", "Herkel", "gwdH_2014", "gwd_2014");
		ssh.connect();
		ssh.start();
		TFTPConnector tftpc = new TFTPConnector("192.168.1.11");
		TFTPSender tftps = new TFTPSender();
		tftpc.takeSnapshot("asd", "hello", ssh);
		tftps.playSnapshot("asd", ssh);
		ssh.doDisconnect();
	}

}
