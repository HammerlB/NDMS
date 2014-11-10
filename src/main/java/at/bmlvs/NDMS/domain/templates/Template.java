package at.bmlvs.NDMS.domain.templates;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Template implements Serializable
{
	private String version;
	private String os_version;
	private String device_type;
	
	private ArrayList<Snippet> snippets;
	
	public Template(String version, String os_version, String device_type)
	{
		setVersion(version);
		setOs_version(os_version);
		setDevice_type(device_type);
		
		setSnippets(new ArrayList<Snippet>());
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public String getOs_version()
	{
		return os_version;
	}

	public void setOs_version(String os_version)
	{
		this.os_version = os_version;
	}

	public String getDevice_type()
	{
		return device_type;
	}

	public void setDevice_type(String device_type)
	{
		this.device_type = device_type;
	}

	public ArrayList<Snippet> getSnippets()
	{
		return snippets;
	}

	public void setSnippets(ArrayList<Snippet> snippets)
	{
		this.snippets = snippets;
	}
	
	public void sortSnippets()
	{
		//Needs to be implemented.
	}
}