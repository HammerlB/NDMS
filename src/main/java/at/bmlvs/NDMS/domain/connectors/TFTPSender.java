package at.bmlvs.NDMS.domain.connectors;

import java.io.File;

import at.bmlvs.NDMS.domain.connectors.TFTPServer.ServerMode;
import at.bmlvs.NDMS.domain.connectors.CustomExceptions.TFTPException;
import at.bmlvs.NDMS.linker.SnapshotToPathLinker;
import at.bmlvs.NDMS.service.ServiceFactory;

public class TFTPSender {

	private String readDir, writeDir, fingerprint, snapshotToSend;
	private ServerMode mode;
	private SnapshotToPathLinker stpl;
	private TFTPServer tftps;
	private SSHConnector ssh;

	public TFTPSender() throws Exception {
		this.readDir = ServiceFactory.getAppConfig().getNDMS_DEFAULT_PATH_APP()
				+ "\\"
				+ ServiceFactory.getAppConfig()
						.getNDMS_DEFAULT_PATH_SNAPSHOT_DIRECTORY();
		this.writeDir = ServiceFactory.getAppConfig().getNDMS_DEFAULT_PATH_APP()
				+ "\\"
				+ ServiceFactory.getAppConfig()
						.getNDMS_DEFAULT_PATH_SNAPSHOT_DIRECTORY();
		this.mode = ServerMode.GET_ONLY;
		this.stpl = new SnapshotToPathLinker(null, null);
		this.tftps = new TFTPServer(new File(readDir), new File(writeDir), mode);
		this.fingerprint = "UNDEFINED";
		this.snapshotToSend = "UNDEFINED";
	}

	private void connect() {
		tftps.run();
	}

	private void send() throws TFTPException {
		if (snapshotToSend != "UNDEFINED" && fingerprint != "UNDEFINED") {
		} else if (snapshotToSend == "UNDEFINED") {
			throw new TFTPException("The snapshot to send is undefined");
		} else if (fingerprint == "UNDEFINED") {
			throw new TFTPException(
					"The fingerprint for the snapshot is undefined");
		} else {
			throw new TFTPException("Unknown Error");
		}
	}

	public void playSnapshot(String fullName, SSHConnector ssh) throws TFTPException {
		this.snapshotToSend = fullName;
		this.ssh=ssh;
		setActiveConnection(ssh);
		connect();
		send();
		disconnect();
	}

	public void setActiveConnection(SSHConnector ssh) {
		this.fingerprint = ssh.getSSHFingerprint();
	}

	public void setActiveConnection(String sshFingerprint) {
		this.fingerprint = sshFingerprint;
	}

	public void disconnect() {
		tftps.shutdown();
	}

	public String getReadDir() {
		return readDir;
	}

	public void setReadDir(String readDir) {
		this.readDir = readDir;
	}

	public String getWriteDir() {
		return writeDir;
	}

	public void setWriteDir(String writeDir) {
		this.writeDir = writeDir;
	}

	public ServerMode getMode() {
		return mode;
	}

	public void setMode(ServerMode mode) {
		this.mode = mode;
	}

	public SnapshotToPathLinker getStpl() {
		return stpl;
	}

	public void setStpl(SnapshotToPathLinker stpl) {
		this.stpl = stpl;
	}

	public TFTPServer getTftps() {
		return tftps;
	}

	public void setTftps(TFTPServer tftps) {
		this.tftps = tftps;
	}
}
