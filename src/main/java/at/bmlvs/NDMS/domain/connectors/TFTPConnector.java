package at.bmlvs.NDMS.domain.connectors;


public class TFTPConnector extends Thread {

	private String host, localFile, remoteFile, fingerPrint;
	private ConnectionTFTP tftp;
	private boolean toSend,toReceive,disconnect,firstConnect;

	public TFTPConnector(String host, String localFile, String remoteFile) {
		this.host = host;
		this.localFile = localFile;
		this.remoteFile = remoteFile;
		this.toSend=false;
		this.toReceive=false;
		this.disconnect=false;
		this.firstConnect=true;
		fingerPrint=null;
	}

	public void run() {
		firstConnect();
		sendReceiveDisconnect();
	}
	
	public void sendReceiveDisconnect(){
		while(true){
			if(toSend){
				tftp.send();
				toSend=false;
			}
			if(toReceive){
				tftp.receive();
				toSend=false;
			}
			if(disconnect){
				tftp.disconnect();
				disconnect=false;
				break;
			}
		}
	}
	
	public void firstConnect(){
		tftp.receive();
	}
	
	public void checkFingerprint(){
		
	}
	
	public void doReceive(String local,String remote){
		this.toReceive=true;
	}
	
	public void doSend(String local,String remote){
		this.toSend=true;
	}
	
	public void doDisconnect(){
		this.disconnect=true;
	}
}
