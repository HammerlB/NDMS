package at.bmlvs.NDMS.domain.connectors;

import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

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
	private InetAddress localIP;

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
		this.fingerprint = "UNDEFINED";
		this.snapshotToSend = "UNDEFINED";
	}

	private void send() throws TFTPException, SocketException, UnknownHostException, InterruptedException {
		if (snapshotToSend != "UNDEFINED" && fingerprint != "UNDEFINED") {
			ssh.begin();
			ssh.playSnapshot(snapshotToSend);
			ssh.end();
		} else if (snapshotToSend == "UNDEFINED") {
			throw new TFTPException("The snapshot to send is undefined");
		} else if (fingerprint == "UNDEFINED") {
			throw new TFTPException(
					"The fingerprint for the snapshot is undefined");
		} else {
			throw new TFTPException("Unknown Error");
		}
	}

	public void playSnapshot(String fullName, SSHConnector ssh) throws TFTPException, IOException, InterruptedException {
		this.snapshotToSend = fullName;
		this.ssh = ssh;
		setActiveConnection(ssh);
		send();
		Thread.sleep(5000);
		disconnect();
	}

	public void setActiveConnection(SSHConnector ssh) throws IOException {
		this.fingerprint = ssh.getSSHFingerprint();
		this.tftps = new TFTPServer(new File(readDir+"\\"+fingerprint), new File(writeDir+"\\"+fingerprint), mode);
		this.tftps.setSocketTimeout(6000);
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
	
	public InetAddress getLocalAddress(){
		return tftps.getLocalAddress();
		
	}
}
