package at.bmlvs.NDMS.domain.templates;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.CheckBox;

@XStreamAlias("Snippet")
@SuppressWarnings("serial")
public class Snippet implements Serializable
{
	@XStreamAsAttribute
	private Template parent;
	
	@XStreamAsAttribute
	private String name;
	@XStreamAsAttribute
	private String prev;
	@XStreamAsAttribute
	private String next;
	
	@XStreamOmitField
	private BooleanProperty activated;

	@XStreamAlias("Sections")
	private ArrayList<Section> sections;
	
	@XStreamOmitField
	private CheckBox checkbox;

	public Snippet(Template parent, String name, String prev, String next)
	{
		setCheckbox(new CheckBox());
		getCheckbox().setAllowIndeterminate(true);
		
		setParent(parent);
		
		setActivated(true);
		
		setName(name);
		setPrev(prev);
		setNext(next);

		setSections(new ArrayList<Section>());
	}

	/**
	 * @return the parent
	 */
	public Template getParent()
	{
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Template parent)
	{
		this.parent = parent;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPrev()
	{
		return prev;
	}

	public void setPrev(String prev)
	{
		this.prev = prev;
	}

	public String getNext()
	{
		return next;
	}

	public void setNext(String next)
	{
		this.next = next;
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

	public ArrayList<Section> getSections()
	{
		return sections;
	}

	public void setSections(ArrayList<Section> sections)
	{
		this.sections = sections;
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
		for(Section section: getSections())
		{
			section.getCheckbox().setSelected(false);
			section.deactivateChildren();
		}
	}
	
	public void activateChildren()
	{
		for(Section section: getSections())
		{
			section.getCheckbox().setSelected(true);
			section.activateChildren();
		}
	}
	
	public boolean someElementsActivated()
	{
		boolean activated = false;
		
		for (Section section: getSections())
		{
			if(section.isActivated() == true)
			{
				activated = true;
				break;
			}
		}
		
		return activated;
	}
	
	private Object readResolve() throws ObjectStreamException
	{
		setCheckbox(new CheckBox());
		getCheckbox().setAllowIndeterminate(true);
		
		return this;
	}
}