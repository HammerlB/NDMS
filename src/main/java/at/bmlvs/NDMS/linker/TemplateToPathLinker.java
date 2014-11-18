package at.bmlvs.NDMS.linker;

import java.io.Serializable;

import at.bmlvs.NDMS.domain.templates.Template;

@SuppressWarnings("serial")
public class TemplateToPathLinker extends Linker<Template, String> implements Serializable
{
	private boolean changed;
	
	public TemplateToPathLinker(Template element, String path)
	{
		super(element, path);
		
		setChanged(false);
	}

	public boolean isChanged()
	{
		return changed;
	}

	public void setChanged(boolean changed)
	{
		this.changed = changed;
	}
}