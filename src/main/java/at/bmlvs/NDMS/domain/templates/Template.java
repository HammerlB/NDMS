package at.bmlvs.NDMS.domain.templates;

public class Template
{
	private String version;
	private String os_version;
	private String device_type;
	
	public Template()
	{
		
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
}
