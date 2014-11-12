package at.bmlvs.NDMS.domain.templates;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Parameter implements Serializable
{
	private int id;
	private String name;
	private Datatype type;
	private String value;
	private boolean useName;
	
	public Parameter(int id, String name, Datatype type, String value)
	{
		setId(id);
		setName(name);
		setType(type);
		setValue(value);
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

	public Datatype getType()
	{
		return type;
	}

	public void setType(Datatype type)
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
}