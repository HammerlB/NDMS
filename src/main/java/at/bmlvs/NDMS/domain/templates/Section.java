package at.bmlvs.NDMS.domain.templates;

import java.io.Serializable;
import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

@SuppressWarnings("serial")
public class Section implements Serializable
{
	private String name;
	
	@XStreamOmitField
	private BooleanProperty activated;
	
	private ArrayList<Command> commands;
	
	public Section(String name)
	{
		setActivated(true);
		
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
	
	public final boolean isActivated()
	{
		if (activated != null)
		{
			return activated.get();
		}

		return false;
	}

	public final void setActivated(boolean activated)
	{
		this.activatedProperty().set(activated);
	}

	public final BooleanProperty activatedProperty()
	{
		if (activated == null)
		{
			activated = new SimpleBooleanProperty();
		}

		return activated;
	}

	public ArrayList<Command> getCommands()
	{
		return commands;
	}

	public void setCommands(ArrayList<Command> commands)
	{
		this.commands = commands;
	}
	
	public void deactivateChildren()
	{
		for(Command command: getCommands())
		{
			command.setActivated(false);
		}
	}
	
	public void activateChildren()
	{
		for(Command command: getCommands())
		{
			command.setActivated(true);
		}
	}
}