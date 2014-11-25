package at.bmlvs.NDMS.domain.templates;

import java.io.Serializable;
import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.CheckBox;

@XStreamAlias("Section")
@SuppressWarnings("serial")
public class Section implements Serializable
{
	private String name;
	
	@XStreamOmitField
	private BooleanProperty activated;
	
	@XStreamAlias("Commands")
	private ArrayList<Command> commands;
	
	@XStreamOmitField
	private CheckBox checkbox;
	
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
	
	public CheckBox getCheckbox()
	{
		return checkbox;
	}

	public void setCheckbox(CheckBox checkbox)
	{
		this.checkbox = checkbox;
	}

	public void deactivateChildren()
	{
		for(Command command: getCommands())
		{
			command.getCheckbox().setSelected(false);
			command.deactivateChildren();
		}
	}
	
	public void activateChildren()
	{
		for(Command command: getCommands())
		{
			command.getCheckbox().setSelected(true);
			command.activateChildren();
		}
	}
}