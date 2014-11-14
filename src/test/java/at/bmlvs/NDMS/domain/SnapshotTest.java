package at.bmlvs.NDMS.domain;

import java.io.File;
import java.io.ObjectInputStream.GetField;

import javax.swing.JOptionPane;

import at.bmlvs.NDMS.domain.connectors.SSHConnector;
import at.bmlvs.NDMS.domain.connectors.TFTPConnector;
import at.bmlvs.NDMS.domain.snapshots.Snapshot;
import at.bmlvs.NDMS.linker.SnapshotToPathLinker;
import at.bmlvs.NDMS.service.PersistenceService;
import at.bmlvs.NDMS.service.ServiceFactory;

public class SnapshotTest {

	public static void main(String[] args) {

		ServiceFactory.setPersistenceService(new PersistenceService());
		ServiceFactory.setAppConfig(ServiceFactory.getPersistenceService()
				.getAppconfig().getElement());
		SSHConnector ssh = new SSHConnector("192.168.1.12", "Herkel",
				"gwdH_2014", "gwd_2014");

		TFTPConnector tftp = new TFTPConnector("192.168.1.12");
		try {
			ssh.connect();
			ssh.start();
//			ssh.doPrepareSnapshot();
			tftp.setSSHFingerprint(ssh.getSSHFingerprint());
//			tftp.takeSnapshot("abc", "desc");
//			tftp.takeSnapshot("def", "desc");
//			tftp.takeSnapshot("ghi", "desc");
//			tftp.takeSnapshot("jkl", "desc");
//			tftp.takeSnapshot("mno", "desc");
//			tftp.takeSnapshot("pqr", "desc");
//			tftp.scanSnapshots();
//			System.out.println("Scan1:");
//			for (SnapshotToPathLinker s : tftp.getSnapshotsList().getSnapshots()) {
//				System.out.println(s.getElement().getFullName());
//				System.out.println(s.getElement().getDescription());
//			}
//			System.out.println("Scan2:");
//			for(SnapshotToPathLinker s : tftp.scanSnapshotsFrom(ssh.getSSHFingerprint())){
//				System.out.println(s.getElement().getFullName());
//				System.out.println(s.getElement().getDescription());
//			}
			tftp.scanSnapshots();
//			for (SnapshotToPathLinker s : tftp.getSnapshotsList().getSnapshots()) {
//				System.out.println(s.getElement().getFullName());
//				System.out.println(s.getElement().getDescription());
//			}
//			JOptionPane.showInputDialog("Trolololol");
			tftp.sendSnapshot("abc_14-11-2014_14-28-32_.txt");
//			ssh.doPlaySnapshot();
//			ssh.doReloadWithoutWrite();
			
			ssh.doDisconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
// String[] s = ssh.getSSHFingerprint().split(":");
// File f = new File("snapshots/"+s[1]);
// System.out.println(f.mkdirs());
// System.out.println(s[1]);

// SnapshotToPathLinker s = new SnapshotToPathLinker(ss, ServiceFactory
// .getAppConfig().getNDMS_DEFAULT_PATH_APP()
// + "\\"
// + ServiceFactory.getAppConfig()
// .getNDMS_DEFAULT_PATH_SNAPSHOT_DIRECTORY()
// + "\\FINGERPRINT\\" + ss.getName());
// System.out.println(s.getPath());
