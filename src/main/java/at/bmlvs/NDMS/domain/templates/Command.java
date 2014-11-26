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
	private Section parent;
	
	private int id;
	private String name;
	private String alias;
	private boolean hidden;
	private boolean appendParameters;
	
	@XStreamOmitField
	private BooleanProperty activated;
	
	@XStreamAlias("Parameters")
	private ArrayList<Parameter> parameters;
	
	@XStreamOmitField
	private CheckBox checkbox;
	
	public Command(Section parent, String name, String alias, boolean appendParameters)
	{
		setCheckbox(new CheckBox());
		
		setParent(parent);
		
		setActivated(true);
		
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

	/**
	 * @return the parent
	 */
	public Section getParent()
	{
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Section parent)
	{
		this.parent = parent;
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
		this.getCheckbox().setSelected(activated);
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
		if(checkbox == null)
		{
			setCheckbox(new CheckBox());
			getCheckbox().setSelected(isActivated());
		}
		
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