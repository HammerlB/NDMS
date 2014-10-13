package at.bmlvs.NDMS.domain.templates;

import java.util.ArrayList;

public class Templates
{
	private ArrayList<Template> templates;
	
	public Templates()
	{
		setTemplates(new ArrayList<Template>());
	}

	public ArrayList<Template> getTemplates()
	{
		return templates;
	}

	public void setTemplates(ArrayList<Template> templates)
	{
		this.templates = templates;
	}
}
