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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.apache.commons.net.finger.FingerClient;
import org.apache.commons.net.tftp.TFTP;
import org.apache.commons.net.tftp.TFTPClient;

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
	public TFTPConnector(String host) {
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
	public void send() throws Exception {
		// We're sending a file
		FileInputStream input = null;

		// Try to open local file for reading
		input = null;
		File file = new File(this.localPath + "\\" + sshfingerprint + "\\" + stpl.getElement().getFullName());
		
		setRemotefile("snapshotToPlay.txt");
		
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			System.err.println("This Snapshot don't exists!");
		}
		
		// Try to send local file via TFTP
		tftpc.sendFile(getRemotefile(), getTransfermode(), input, getHost());

		// Close local socket and input file
		disconnect();
		if (input != null) {
			input.close();
		}
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

	private void connectAndReceive() throws Exception {
		connect();
		Thread.sleep(4000);
		receive();
	}

	private void connectAndSend() throws Exception {
		connect();
		send();
	}

	public void takeSnapshot(String name, String desc, SSHConnector ssh) throws Exception {
		setSSHFingerprint(ssh.getSSHFingerprint());
		ssh.doPrepareSnapshot();
		Snapshot s = new Snapshot(name, desc);
		stpl = new SnapshotToPathLinker(s, localPath + "\\" + sshfingerprint
				+ "\\" + s.getFullName());
		ServiceFactory.getPersistenceService().getSnapshots().add(stpl);
//		setLocalfile(s.getFullName()+".txt");
		connectAndReceive();
	}
	
	private void deleteSnapshot(Snapshot s){
		ServiceFactory.getPersistenceService().getSnapshots().remove(s);
	}
	
	public void deleteSnapshot(String fullName){
		Snapshot snapshotToDelete = new Snapshot(null,null);
		for(SnapshotToPathLinker s : ServiceFactory.getPersistenceService().getSnapshots()){
			if(s.getElement().getFullName().equals(fullName)){
				snapshotToDelete=s.getElement();
			}
		}
		deleteSnapshot(snapshotToDelete);
	}
	
//	public void sendSnapshot(String fullName) throws Exception{
// 		String s = snapshots.getSnapshots().get(0).getElement().getFullName();
//		for(int i = 0; i<snapshots.getSnapshots().size();i++){
//			if(snapshots.getSnapshots().get(i).getElement().getFullName().equals(fullName)){
//				this.stpl = snapshots.getSnapshots().get(i);
//				System.out.println(this.stpl.getElement().getFullName());
//				break;
//			}
//		}
//		connectAndSend();
//	}

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

	// public void doCreateSnapshot(String name,String description) throws
	// Exception{
	// setCurrentsnapshot(new Snapshot(name,description));
	// snapshots.createSnapshot(currentsnapshot);
	// connect();
	// setRemotefile("snapshot.txt");
	// setLocalfile(currentsnapshot.getRelativePath());
	// receive();
	// }
	//
	// public void doCreateSnapshot(Snapshot s) throws Exception{
	// setCurrentsnapshot(s);
	// snapshots.createSnapshot(currentsnapshot);
	// connect();
	// setRemotefile("snapshot.txt");
	// setLocalfile(currentsnapshot.getRelativePath());
	// receive();
	// }
	//
	// public void initialSnapshot() throws Exception{
	// setCurrentsnapshot(new
	// Snapshot("initial","This is the initial Snapshot"));
	// if(!snapshots.checkSnapshot(currentsnapshot)){
	// doCreateSnapshot(currentsnapshot);
	// }
	// }

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