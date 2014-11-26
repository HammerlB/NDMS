package at.bmlvs.NDMS.domain.templates;

public class Value
{
	private String alias;
	private String value;
	
	public Value(String alias, String value)
	{
		setAlias(alias);
		setValue(value);
	}

	public String getAlias()
	{
		return alias;
	}

	public void setAlias(String alias)
	{
		this.alias = alias;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
}