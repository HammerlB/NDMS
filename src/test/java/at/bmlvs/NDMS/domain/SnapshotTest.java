package at.bmlvs.NDMS.domain;

import java.io.File;
import java.io.ObjectInputStream.GetField;

import at.bmlvs.NDMS.domain.connectors.SSHConnector;
import at.bmlvs.NDMS.domain.connectors.TFTPConnector;
import at.bmlvs.NDMS.domain.snapshots.Snapshot;
import at.bmlvs.NDMS.linker.SnapshotToPathLinker;
import at.bmlvs.NDMS.service.PersistenceService;
import at.bmlvs.NDMS.service.ServiceFactory;

public class SnapshotTest {

	public static void main(String[] args) {
		
		ServiceFactory.setPersistenceService(new PersistenceService());
		ServiceFactory.setAppConfig(ServiceFactory.getPersistenceService().getAppconfig().getElement());
		SSHConnector ssh = new SSHConnector("192.168.1.12", "Herkel", "gwdH_2014", "gwd_2014");
		
		
		TFTPConnector tftp = new TFTPConnector("192.168.1.12","tralal.txt","snapshot.txt");
		try {
			ssh.connect();
			ssh.start();
			ssh.doPrepareSnapshot();
			tftp.setSSHFingerprint(ssh.getSSHFingerprint());
			tftp.takeSnapshot("name", "desc");
//			tftp.connectAndReceive();
//			tftp.scanSnapshots();
			for(int i = 0;i<tftp.getSnapshotsList().getSnapshots().size();i++){
				System.out.println(tftp.getSnapshotsList().getSnapshots().get(i).getElement().getFullName());
			}
			ssh.doDisconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String[] s = ssh.getSSHFingerprint().split(":");
//		File f = new File("snapshots/"+s[1]);
//		System.out.println(f.mkdirs());
//		System.out.println(s[1]);

		

		
//		SnapshotToPathLinker s = new SnapshotToPathLinker(ss, ServiceFactory
//				.getAppConfig().getNDMS_DEFAULT_PATH_APP()
//				+ "\\"
//				+ ServiceFactory.getAppConfig()
//						.getNDMS_DEFAULT_PATH_SNAPSHOT_DIRECTORY()
//				+ "\\FINGERPRINT\\" + ss.getName());
//		System.out.println(s.getPath());
	}
}
