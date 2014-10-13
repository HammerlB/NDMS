package at.bmlvs.NDMS.domain.templates;

import java.util.ArrayList;

public class Section
{
	private String name;
	
	private ArrayList<Command> commands;
	
	public Section(String name)
	{
		setName(name);
		
		setCommands(new ArrayList<Command>());
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public ArrayList<Command> getCommands()
	{
		return commands;
	}

	public void setCommands(ArrayList<Command> commands)
	{
		this.commands = commands;
	}
}
