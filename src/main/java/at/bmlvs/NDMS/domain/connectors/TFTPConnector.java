/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package at.bmlvs.NDMS.domain.connectors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.apache.commons.net.finger.FingerClient;
import org.apache.commons.net.tftp.TFTP;
import org.apache.commons.net.tftp.TFTPClient;

import at.bmlvs.NDMS.domain.connectors.TFTPServer.ServerMode;
import at.bmlvs.NDMS.domain.snapshots.Snapshot;
import at.bmlvs.NDMS.domain.snapshots.Snapshots;
import at.bmlvs.NDMS.linker.SnapshotToPathLinker;
import at.bmlvs.NDMS.service.ServiceFactory;

/***
 * This is an example of a simple Java tftp client. Notice how all of the code
 * is really just argument processing and error handling.
 * <p>
 * Usage: tftp [options] hostname localfile remotefile hostname - The name of
 * the remote host localfile - The name of the local file to send or the name to
 * use for the received file remotefile - The name of the remote file to receive
 * or the name for the remote server to use to name the local file being sent.
 * options: (The default is to assume -r -b) -s Send a local file -r Receive a
 * remote file -a Use ASCII transfer mode -b Use binary transfer mode
 * <p>
 ***/
public class TFTPConnector extends FileTransferConnector {
	private TFTPClient tftpc;

	private int transfermode;
	private int timeout;

	private String localfile;
	private String remotefile;

	private boolean ascii_transfermode;
	private boolean binary_transfermode;

	private Snapshots snapshots;
	private SnapshotToPathLinker stpl;

	private String localPath;

	private String sshfingerprint;

	/*
	 * "Usage: tftp [options] hostname localfile remotefile\n\n" +
	 * "hostname   - The name of the remote host\n" +
	 * "localfile  - The name of the local file to send or the name to use for\n"
	 * + "\tthe received file\n" +
	 * "remotefile - The name of the remote file to receive or the name for\n" +
	 * "\tthe remote server to use to name the local file being sent.\n\n" +
	 * "options: (The default is to assume -r -b)\n" +
	 * "\t-s Send a local file\n" + "\t-r Receive a remote file\n" +
	 * "\t-a Use ASCII transfer mode\n" + "\t-b Use binary transfer mode\n";
	 */
	public TFTPConnector(String host) throws Exception {
		super(host);
		setLocalPath(ServiceFactory.getAppConfig().getNDMS_DEFAULT_PATH_APP()
				+ "\\"
				+ ServiceFactory.getAppConfig()
						.getNDMS_DEFAULT_PATH_SNAPSHOT_DIRECTORY());
		snapshots = new Snapshots();
		stpl = new SnapshotToPathLinker(null,null);
		setLocalfile(localfile);
		setRemotefile(remotefile);
		setTftpc(new TFTPClient());
		setTransfermode(TFTP.ASCII_MODE);
		setTimeout(60000);
	}

	@Override
	public void connect() throws Exception {
		tftpc.setDefaultTimeout(getTimeout());

		// Open local socket
		tftpc.open();
	}

	@Override
	public void disconnect() throws Exception {
		tftpc.close();
	}

	@Override
	public void receive() throws Exception {
		// We haven't closed the local file yet.
		boolean closed = false;

		FileOutputStream output = null;

		File dir = new File(this.localPath + "\\" + sshfingerprint);
		File file = new File(this.localPath + "\\" + sshfingerprint + "\\" + stpl.getElement().getFullName());
		setRemotefile("snapshot.txt");
		// If file exists, don't overwrite it.
		if (file.exists()) {
			System.out.println("This shouldn't have happened! (file exists)");
		}

		// Try to open local file for writing
		try {
			output = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			dir.mkdirs();
			file.createNewFile();
			output = new FileOutputStream(file);
		}

		// Try to receive remote file via TFTP
		tftpc.receiveFile(getRemotefile(), getTransfermode(), output, getHost());
		// Close local socket and output file
		disconnect();

		if (output != null) {
			output.close();
		}
	}
	
	private void deleteSnapshotInFileSystem(Snapshot s, SSHConnector ssh){
		setSSHFingerprint(ssh.getSSHFingerprint());
		File file = new File(this.localPath + "\\" + sshfingerprint + "\\" + s.getFullName());
		if (!s.getName().equals(null)) {
			file.delete();
			System.out.println("Snapshot sucessfully deleted!");
		} else {
			System.err.println("Snapshot doesn't exist!");
		}
	}

	private void connectAndReceive() throws Exception {
		connect();
		receive();
	}

	public void takeSnapshot(String name, String desc, SSHConnector ssh) throws Exception {
		setSSHFingerprint(ssh.getSSHFingerprint());
		ssh.prepareSnapshot();
		System.out.println("Waiting for Snapshot to take!");
		Thread.sleep(5000);
		Snapshot s = new Snapshot(name, desc);
		stpl = new SnapshotToPathLinker(s, localPath + "\\" + sshfingerprint
				+ "\\" + s.getFullName());
		ServiceFactory.getPersistenceService().getSnapshots().add(stpl);
//		setLocalfile(s.getFullName()+".txt");
		connectAndReceive();
	}
	
	public void deleteSnapshot(String fullName, SSHConnector ssh) throws Exception{
		Snapshot s = new Snapshot(null,null);
		for(int i = 0;i<ServiceFactory.getPersistenceService().getSnapshots().size();i++){
			if(ServiceFactory.getPersistenceService().getSnapshots().get(i).getElement().getFullName().equals(fullName)){
				s = ServiceFactory.getPersistenceService().getSnapshots().get(i).getElement();
				ServiceFactory.getPersistenceService().getSnapshots().remove(i);
				break;
			}
		}
		deleteSnapshotInFileSystem(s, ssh);
	}

	public void scanSnapshots() {
		File dir = new File(localPath + "\\" + sshfingerprint);
		ServiceFactory.getPersistenceService().getSnapshots().clear();
		for (File file : dir.listFiles()) {
			if (file.getName().endsWith(".txt")) {
				Snapshot s = new Snapshot(file.getName().split("_")[0],"Snapshot from "+ file.getName().split("_")[1]+"_"+file.getName().split("_")[2], "_"+file.getName().split("_")[1]+"_"+file.getName().split("_")[2]+"_");
				SnapshotToPathLinker stpl = new SnapshotToPathLinker(s, file.getAbsolutePath());
				ServiceFactory.getPersistenceService().getSnapshots().add(stpl);
			}
		}
	}

	public ObservableList<SnapshotToPathLinker> scanSnapshotsFrom(
			String fingerprint) {
		File dir = new File(localPath + "\\" + fingerprint);
		ObservableList<SnapshotToPathLinker> list = FXCollections
				.observableArrayList();
		for (File file : dir.listFiles()) {
			if (file.getName().endsWith(".txt")) {
				Snapshot s = new Snapshot(file.getName().split("_")[0],"Snapshot from "+ file.getName().split("_")[1]+"_"+file.getName().split("_")[2], "_"+file.getName().split("_")[1]+"_"+file.getName().split("_")[2]+"_");
				SnapshotToPathLinker stpl = new SnapshotToPathLinker(s, file.getAbsolutePath());
				list.add(stpl);
			}
		}
		return list;
	}
	
	private void takeInitialSnapshot(SSHConnector ssh) throws Exception{
		ssh.prepareSnapshot();
		Snapshot s = new Snapshot(null,null);
		takeSnapshot("Initial","Initial Snapshot from "+s.getDatetime(),ssh);
	}

	public boolean isAscii_transfermode() {
		return ascii_transfermode;
	}

	public void setAscii_transfermode(boolean ascii_transfermode) {
		this.ascii_transfermode = ascii_transfermode;
	}

	public boolean isBinary_transfermode() {
		return binary_transfermode;
	}

	public void setBinary_transfermode(boolean binary_transfermode) {
		this.binary_transfermode = binary_transfermode;
	}

	public TFTPClient getTftpc() {
		return tftpc;
	}

	public void setTftpc(TFTPClient tftpc) {
		this.tftpc = tftpc;
	}

	public int getTransfermode() {
		return transfermode;
	}

	public void setTransfermode(int transfermode) {
		this.transfermode = transfermode;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getLocalfile() {
		return localfile;
	}

	public void setLocalfile(String localfile) {
		this.localfile = localfile;
	}

	public String getRemotefile() {
		return remotefile;
	}

	public void setRemotefile(String remotefile) {
		this.remotefile = remotefile;
	}

	public Snapshots getSnapshotsList() {
		return snapshots;
	}

	public void setSnapshotsList(Snapshots snapshots) {
		this.snapshots = snapshots;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public SnapshotToPathLinker getStpl() {
		return stpl;
	}

	public void setStpl(SnapshotToPathLinker stpl) {
		this.stpl = stpl;
	}

	public String getSSHFingerprint() {
		return sshfingerprint;
	}

	public void setSSHFingerprint(String sshfingerprint) {
		this.sshfingerprint = sshfingerprint;
	}
}