package at.bmlvs.NDMS.domain.templates;

import java.util.ArrayList;

public class Command
{
	private int id;
	private String name;
	
	private ArrayList<Parameter> parameters;
	
	public Command(String name)
	{
		setId(id);
		setName(name);
		
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

	public ArrayList<Parameter> getParameters()
	{
		return parameters;
	}

	public void setParameters(ArrayList<Parameter> parameters)
	{
		this.parameters = parameters;
	}
}