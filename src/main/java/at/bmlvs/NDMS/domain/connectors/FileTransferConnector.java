package at.bmlvs.NDMS.domain.connectors;

public abstract class FileTransferConnector
{
	private String host;
	
	public FileTransferConnector(String host)
	{
		setHost(host);
	}

	public String getHost()
	{
		return host;
	}

	public void setHost(String host)
	{
		this.host = host;
	}
	
	abstract void connect() throws Exception;
	
	abstract void disconnect() throws Exception;
	
	abstract void receive() throws Exception;
}
