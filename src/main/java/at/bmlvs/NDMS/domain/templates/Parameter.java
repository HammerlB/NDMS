package at.bmlvs.NDMS.domain.templates;

import java.io.Serializable;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("Parameter")
@SuppressWarnings("serial")
public class Parameter implements Serializable
{
	@XStreamAsAttribute
	private Command parent;
	
	@XStreamAsAttribute
	private int id;
	@XStreamAsAttribute
	private String name;
	@XStreamAsAttribute
	private String alias;
	@XStreamAsAttribute
	private String type;
	@XStreamAsAttribute
	private Value value;
	@XStreamAsAttribute
	private boolean used;
	@XStreamAsAttribute
	private boolean useName;
	
	@XStreamAlias("DefaultValues")
	private ParameterList defaultValues;
	
	@XStreamOmitField
	private BooleanProperty activated;
	
	public Parameter(Command parent, int id, String name, String alias, String type, Value value, boolean used, boolean useName, ParameterList parameterList)
	{
		setParent(parent);
		
		setId(id);
		setName(name);
		setAlias(alias);
		setType(type);
		
		if(parameterList != null && parameterList.size() > 0)
		{
			setDefaultValues(parameterList);
		}
		else
		{
			setDefaultValues(new ParameterList(this));
			getDefaultValues().add(new Value("", ""));
		}
		
		setValue(value);
		setUsed(used);
		setUseName(useName);
	}

	public Command getParent()
	{
		return parent;
	}

	public void setParent(Command parent)
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

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public Value getValue()
	{
		return value;
	}

	public void setValue(Value value)
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

	public ParameterList getDefaultValues()
	{
		return defaultValues;
	}

	public void setDefaultValues(ParameterList defaultValues)
	{
		this.defaultValues = defaultValues;
	}

	public final BooleanProperty activatedProperty()
	{
		if (activated == null)
		{
			activated = new SimpleBooleanProperty();
		}

		return activated;
	}

	public String getParameterOutput()
	{
		if(getValue() != null)
		{
			if(getValue().equals(""))
			{
				setValue(getDefaultValues().getSelected());
			}
			
			if(isUseName())
			{
				return getName() + " " + getValue();
			}
		}
		
		return getValue().getValue();
	}
	
	public String getDefaultValue()
	{	
		if(getDefaultValues().size() > 0 && getDefaultValues().getSelected() != null)
		{
			return getDefaultValues().getSelected().getValue();
		}
		
		return "";
	}
}