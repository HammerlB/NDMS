package at.bmlvs.NDMS.service;

import java.util.ArrayList;

import at.bmlvs.NDMS.linker.TemplateToPathLinker;
import at.bmlvs.NDMS.persistence.specific.XMLTemplatesDAO;

public class PersistenceService
{
	private ArrayList<TemplateToPathLinker> templates;
	
	private XMLTemplatesDAO tdao = new XMLTemplatesDAO();
	
	public PersistenceService()
	{
		
	}

	public ArrayList<TemplateToPathLinker> getTemplates()
	{
		return templates;
	}

	public void setTemplates(ArrayList<TemplateToPathLinker> templates)
	{
		this.templates = templates;
	}

	public XMLTemplatesDAO getTdao()
	{
		return tdao;
	}

	public void setTdao(XMLTemplatesDAO tdao)
	{
		this.tdao = tdao;
	}
	
	public void saveTemplates()
	{
		for(TemplateToPathLinker t: getTemplates())
		{
			tdao.write(t.getElement(), getTemplates().getPath());
		}
	}
	
	public TemplateToPathLinker loadTemplates(String path)
	{
		return new TemplateToPathLinker(tdao.read(path), path);
	}
}
