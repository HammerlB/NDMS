package at.bmlvs.NDMS.domain.templates;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

@SuppressWarnings("serial")
public class Template implements Serializable
{
	private String name;
	private String version;
	private String os_version;
	private String device_type;
	
	private ArrayList<Snippet> snippets;
	
	@XStreamOmitField
	private StringProperty output;
	
	public Template(String name, String version, String os_version, String device_type)
	{
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
	
	public void sortSnippets()
	{
		//Needs to be implemented.
	}
	
	public String receiveTemplateOutputAsString()
	{
		String receivedOutput = "";
		
		for(Snippet snippet: getSnippets())
		{
			for(Section section: snippet.getSections())
			{
				for(Command command: section.getCommands())
				{
					receivedOutput += command.getName();
					
					for(Parameter parameter: command.getParameters())
					{
						receivedOutput += " " + parameter.getParameterOutput();
					}
					
					receivedOutput += "\n";
				}
			}
		}
		
		setOutput(receivedOutput);
		
		return receivedOutput;
	}
	
	public ArrayList<String> receiveTemplateOutputAsArrayList()
	{
		ArrayList<String> receivedOutput = new ArrayList<String>();
		
		for(Snippet snippet: getSnippets())
		{
			for(Section section: snippet.getSections())
			{
				for(Command command: section.getCommands())
				{
					String cmd = "";
					cmd += command.getName();
					
					for(Parameter parameter: command.getParameters())
					{
						cmd += " " + parameter.getParameterOutput();
					}
					
					receivedOutput.add(cmd);
				}
			}
		}
		
		return receivedOutput;
	}
	
	public String getFullName()
	{
		return getDevice_type() + "-" + getName() + "-" + getVersion() + "-" + getOs_version();
	}
}