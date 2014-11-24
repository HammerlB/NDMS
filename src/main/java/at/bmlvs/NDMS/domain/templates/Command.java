package at.bmlvs.NDMS.domain.templates;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Command implements Serializable
{
	private int id;
	private String name;
	private String alias;
	private boolean hidden;
	private boolean appendParameters;
	
	private ArrayList<Parameter> parameters;
	
	public Command(String name, String alias, boolean appendParameters)
	{
		setId(id);
		setName(name);
		setAlias(alias);
		
		if(alias.equals(""))
		{
			setHidden(true);
		}
		else
		{
			setHidden(false);
		}
		
		setAppendParameters(appendParameters);
		
		setParameters(new ArrayList<Parameter>());
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

	public boolean isHidden()
	{
		return hidden;
	}

	public void setHidden(boolean hidden)
	{
		this.hidden = hidden;
	}

	public boolean isAppendParameters()
	{
		return appendParameters;
	}

	public void setAppendParameters(boolean appendParameters)
	{
		this.appendParameters = appendParameters;
	}

	public ArrayList<Parameter> getParameters()
	{
		return parameters;
	}

	public void setParameters(ArrayList<Parameter> parameters)
	{
		this.parameters = parameters;
	}
}