package at.bmlvs.NDMS.domain.templates;

import java.io.Serializable;
import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.CheckBox;

@XStreamAlias("Snippet")
@SuppressWarnings("serial")
public class Snippet implements Serializable
{
	private String name;
	private String prev;
	private String next;
	
	@XStreamOmitField
	private BooleanProperty activated;

	@XStreamAlias("Sections")
	private ArrayList<Section> sections;
	
	@XStreamOmitField
	private CheckBox checkbox;

	public Snippet(String name, String prev, String next)
	{
		setActivated(true);
		
		setName(name);
		setPrev(prev);
		setNext(next);

		setSections(new ArrayList<Section>());
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
}