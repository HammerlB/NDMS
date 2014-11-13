package at.bmlvs.NDMS.domain;

import java.io.ObjectInputStream.GetField;

import at.bmlvs.NDMS.domain.connectors.SSHConnector;
import at.bmlvs.NDMS.domain.connectors.TFTPConnector;
import at.bmlvs.NDMS.domain.snapshots.Snapshot;
import at.bmlvs.NDMS.linker.SnapshotToPathLinker;
import at.bmlvs.NDMS.service.PersistenceService;
import at.bmlvs.NDMS.service.ServiceFactory;

public class TFTPConnectorTest {

	public static void main(String[] args) {
		
		ServiceFactory.setPersistenceService(new PersistenceService());
		ServiceFactory.setAppConfig(ServiceFactory.getPersistenceService().getAppconfig().getElement());
		SSHConnector ssh = new SSHConnector("192.168.1.12", "Herkel", "gwdH_2014", "gwd_2014");
		
		Snapshot ss = new Snapshot("name", "desc");
		TFTPConnector tftp = new TFTPConnector("192.168.1.12","config.text","tralal");
		try {
			ssh.connect();
			tftp.setFingerprint(ssh.getSSHFingerprint());
			tftp.connect();
			tftp.receive();
			tftp.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		
//		SnapshotToPathLinker s = new SnapshotToPathLinker(ss, ServiceFactory
//				.getAppConfig().getNDMS_DEFAULT_PATH_APP()
//				+ "\\"
//				+ ServiceFactory.getAppConfig()
//						.getNDMS_DEFAULT_PATH_SNAPSHOT_DIRECTORY()
//				+ "\\FINGERPRINT\\" + ss.getName());
//		System.out.println(s.getPath());
	}
}
