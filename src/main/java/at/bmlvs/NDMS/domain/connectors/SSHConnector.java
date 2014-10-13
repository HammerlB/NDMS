package at.bmlvs.NDMS.domain.connectors;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * 
 * @author GWDH
 *
 */
public class SSHConnector extends TerminalConnector
{
	private String version;
	private int port;

	public SSHConnector()
	{
		super();
		setVersion("");
		setPort(0);
	}

	public SSHConnector(String host, String username, String password,
			String version, int port)
	{
		super(host, username, password);
		setVersion(version);
		setPort(port);
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public int getPort()
	{
		return port;
	}

	public void setPort(int port)
	{
		this.port = port;
	}

	public void connect()
	{
		try
		{
			//http://www.jcraft.com/jsch/examples/Shell.java.html
			//http://stackoverflow.com/questions/782178/how-do-i-convert-a-string-to-an-inputstream-in-java
			JSch jsch = new JSch();
			Session session=jsch.getSession(getUsername(), getHost(), getPort());
			session.setPassword(getPassword());
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(30000);
			Channel channel=session.openChannel("shell");
			channel.setInputStream(System.in);
			channel.setOutputStream(System.out);
			channel.connect(3*1000);
			//channel.disconnect();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void disconnect()
	{
		
	}
}