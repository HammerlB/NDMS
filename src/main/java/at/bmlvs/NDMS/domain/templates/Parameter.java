package at.bmlvs.NDMS.domain.templates;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Parameter implements Serializable
{
	private int id;
	private String name;
	private String type;
	private String defaultValue;
	private String value;
	private boolean useName;
	
	public Parameter(int id, String name, String type, String defaultValue, String value, boolean useName)
	{
		setId(id);
		setName(name);
		setType(type);
		setDefaultValue(defaultValue);
		setValue(value);
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