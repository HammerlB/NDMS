package at.bmlvs.NDMS.domain.connectors;

import java.io.IOException;

public abstract class TerminalConnector
{
	private String host;
	private String username;
	private String password;
	
	public TerminalConnector()
	{
		this("", "", "");
	}
	
	public TerminalConnector(String host, String username, String password)
	{
		setHost(host);
		setUsername(username);
		setPassword(password);
	}
	
	abstract void connect() throws Exception;
	
	abstract void disconnect() throws Exception;

	public String getHost()
	{
		return host;
	}

	public void setHost(String host)
	{
		this.host = host;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}