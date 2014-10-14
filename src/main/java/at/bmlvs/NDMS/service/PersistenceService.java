package at.bmlvs.NDMS.service;

import java.util.ArrayList;

import at.bmlvs.NDMS.linker.TemplateToPathLinker;
import at.bmlvs.NDMS.persistence.specific.XMLTemplateDAO;

public class PersistenceService
{
	private ArrayList<TemplateToPathLinker> templates;
	
	private XMLTemplateDAO tdao = new XMLTemplateDAO();
	
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

	public XMLTemplateDAO getTdao()
	{
		return tdao;
	}

	public void setTdao(XMLTemplateDAO tdao)
	{
		this.tdao = tdao;
	}
	
	public void saveTemplates()
	{
		for(TemplateToPathLinker t: getTemplates())
		{
			tdao.write(t.getElement(), t.getPath());
		}
	}
	
	public TemplateToPathLinker loadTemplates(String path)
	{
		return new TemplateToPathLinker(tdao.read(path), path);
	}
}
