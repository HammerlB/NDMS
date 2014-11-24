package at.bmlvs.NDMS.domain.templates;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Parameter implements Serializable
{
	private int id;
	private String name;
	private String alias;
	private String type;
	private String defaultValue;
	private String value;
	private boolean used;
	private boolean useName;
	
	public Parameter(int id, String name, String alias, String type, String defaultValue, String value, boolean used, boolean useName)
	{
		setId(id);
		setName(name);
		setAlias(alias);
		setType(type);
		setDefaultValue(defaultValue);
		setValue(value);
		setUsed(used);
		setUseName(useName);
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getAlias()
	{
		return alias;
	}

	public void setAlias(String alias)
	{
		this.alias = alias;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
	
	/**
	 * @return the used
	 */
	public boolean isUsed()
	{
		return used;
	}

	/**
	 * @param used the used to set
	 */
	public void setUsed(boolean used)
	{
		this.used = used;
	}

	public boolean isUseName()
	{
		return useName;
	}

	public void setUseName(boolean useName)
	{
		this.useName = useName;
	}

	public String getParameterOutput()
	{
		if(isUseName())
		{
			return getName() + " " + getValue();
		}
		
		return getValue();
	}

	public String getDefaultValue()
	{
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}
}