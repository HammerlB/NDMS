package at.bmlvs.NDMS.domain.snapshots;

import at.bmlvs.NDMS.domain.connectors.SSHConnector;

public class Snapshot {
	private String sshKey;
	private String switchtype;
	
	public Snapshot(){
		this.sshKey = SSHConnector.getSSHFingerprint();
	}
	
	public void createSnapshot(){
		
	}
}
