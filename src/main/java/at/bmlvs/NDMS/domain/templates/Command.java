package at.bmlvs.NDMS.domain.templates;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.CheckBox;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("Command")
@SuppressWarnings("serial")
public class Command implements Serializable
{
	private int id;
	private String name;
	private String alias;
	private String type;
	private boolean hidden;
	private boolean appendParameters;
	
	@XStreamOmitField
	private BooleanProperty activated;
	
	@XStreamAlias("Parameters")
	private ArrayList<Parameter> parameters;
	
	@XStreamOmitField
	private CheckBox checkbox;
	
	public Command(String name, String alias, String type, boolean appendParameters)
	{
		setActivated(true);
		
		setId(id);
		setName(name);
		setAlias(alias);
		setType(type);
		
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
	
	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
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

	public ArrayList<Parameter> getParameters()
	{
		return parameters;
	}

	public void setParameters(ArrayList<Parameter> parameters)
	{
		this.parameters = parameters;
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
		for(Parameter parameter: getParameters())
		{
			parameter.setActivated(false);
		}
	}
	
	public void activateChildren()
	{
		for(Parameter parameter: getParameters())
		{
			parameter.setActivated(true);
		}
	}
}