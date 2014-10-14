package at.bmlvs.NDMS.domain.configs;

public class Configuration
{
	private String data;
	
	public Configuration()
	{
		
	}
	
	public Configuration(String data)
	{
		this();
		setData(data);
	}

	public String getData()
	{
		return data;
	}

	public void setData(String data)
	{
		this.data = data;
	}
}