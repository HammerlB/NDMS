package at.bmlvs.NDMS.domain.templates;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("Template")
@SuppressWarnings("serial")
public class Template implements Serializable
{
	@XStreamAsAttribute
	private String name;
	@XStreamAsAttribute
	private String version;
	@XStreamAsAttribute
	private String os_version;
	@XStreamAsAttribute
	private String device_type;

	@XStreamOmitField
	private BooleanProperty activated;

	@XStreamAlias("Snippets")
	private ArrayList<Snippet> snippets;

	@XStreamOmitField
	private StringProperty output;

	@XStreamOmitField
	private CheckBox checkbox;

	public Template(String name, String version, String os_version, String device_type)
	{
		setCheckbox(new CheckBox());
		
		setActivated(true);
		
		setName(name);
		setVersion(version);
		setOs_version(os_version);
		setDevice_type(device_type);

		setSnippets(new ArrayList<Snippet>());
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public String getOs_version()
	{
		return os_version;
	}

	public void setOs_version(String os_version)
	{
		this.os_version = os_version;
	}

	public String getDevice_type()
	{
		return device_type;
	}

	public void setDevice_type(String device_type)
	{
		this.device_type = device_type;
	}

	public ArrayList<Snippet> getSnippets()
	{
		return snippets;
	}

	public void setSnippets(ArrayList<Snippet> snippets)
	{
		this.snippets = snippets;
	}

	public final String getOutput()
	{
		if (output != null)
		{
			return output.get();
		}

		return null;
	}

	public final void setOutput(String output)
	{
		this.outputProperty().set(output);
	}

	public final StringProperty outputProperty()
	{
		if (output == null)
		{
			output = new SimpleStringProperty(null);
		}

		return output;
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

	public void sortSnippets()
	{
		// Needs to be implemented.
	}

	// public String receiveTemplateOutputAsString()
	// {
	// String receivedOutput = "";
	//
	// for(Snippet snippet: getSnippets())
	// {
	// if(snippet.isActivated() == true)
	// {
	// for(Section section: snippet.getSections())
	// {
	// if(section.isActivated() == true)
	// {
	// for(Command command: section.getCommands())
	// {
	// if(command.isActivated() == true)
	// {
	// receivedOutput += command.getName();
	//
	// for(Parameter parameter: command.getParameters())
	// {
	// receivedOutput += " " + parameter.getParameterOutput();
	// }
	//
	// receivedOutput += "\n";
	// }
	// }
	// }
	// }
	// }
	// }
	//
	// setOutput(receivedOutput);
	//
	// return receivedOutput;
	// }

	public String receiveTemplateOutputAsString()
	{
		String receivedOutput = "";

		for (Snippet snippet : getSnippets())
		{
			for (Section section : snippet.getSections())
			{
				for (Command command : section.getCommands())
				{
					if (command.isActivated() == true)
					{
						receivedOutput += command.getName();

						for (Parameter parameter : command.getParameters())
						{
							receivedOutput += " " + parameter.getParameterOutput();
						}

						receivedOutput += "\n";
					}
				}
			}
		}

		setOutput(receivedOutput);

		return receivedOutput;
	}

	// public ArrayList<String> receiveTemplateOutputAsArrayList()
	// {
	// ArrayList<String> receivedOutput = new ArrayList<String>();
	//
	// for (Snippet snippet : getSnippets())
	// {
	// if (snippet.isActivated() == true)
	// {
	// for (Section section : snippet.getSections())
	// {
	// if (section.isActivated() == true)
	// {
	// for (Command command : section.getCommands())
	// {
	// if (command.isActivated() == true)
	// {
	// String cmd = "";
	// cmd += command.getName();
	//
	// for (Parameter parameter : command.getParameters())
	// {
	// cmd += " " + parameter.getParameterOutput();
	// }
	//
	// receivedOutput.add(cmd);
	// }
	// }
	// }
	// }
	// }
	// }
	//
	// return receivedOutput;
	// }

	public ArrayList<String> receiveTemplateOutputAsArrayList()
	{
		ArrayList<String> receivedOutput = new ArrayList<String>();

		for (Snippet snippet : getSnippets())
		{
			for (Section section : snippet.getSections())
			{
				for (Command command : section.getCommands())
				{
					if (command.isActivated() == true)
					{
						String cmd = "";
						cmd += command.getName();

						for (Parameter parameter : command.getParameters())
						{
							cmd += " " + parameter.getParameterOutput();
						}

						receivedOutput.add(cmd);
					}
				}
			}
		}

		return receivedOutput;
	}

	public void deactivateChildren()
	{
		for (Snippet snippet : getSnippets())
		{
			snippet.getCheckbox().setSelected(false);
			snippet.deactivateChildren();
		}
	}

	public void activateChildren()
	{
		for (Snippet snippet : getSnippets())
		{
			snippet.getCheckbox().setSelected(true);
			snippet.activateChildren();
		}
	}

	public String getFullName()
	{
		return getDevice_type() + "-" + getName() + "-" + getVersion() + "-" + getOs_version();
	}
}