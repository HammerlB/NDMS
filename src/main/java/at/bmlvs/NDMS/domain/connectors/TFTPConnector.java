package at.bmlvs.NDMS.domain.connectors;

import at.bmlvs.NDMS.service.ServiceFactory;

public class TFTPConnector extends Thread {

	private String host, localFile, remoteFile;
	private ConnectionTFTP tftp;

	public TFTPConnector(String host, String localFile, String remoteFile) {
		this.host = host;
		this.localFile = localFile;
	}

	public void run() {
		while(ServiceFactory.getDomainService().getInstances().getInstances().get(0).getSshConnector().isConnected()==false){
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("SSH Connected!");
	}

}
