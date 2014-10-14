package at.bmlvs.NDMS.service;

import at.bmlvs.NDMS.linker.TemplatesToPathLinker;
import at.bmlvs.NDMS.persistence.xml.XMLTemplatesDAO;

public class PersistenceService
{
	private TemplatesToPathLinker templates;
	
	private XMLTemplatesDAO tdao = new XMLTemplatesDAO();
	
	public PersistenceService()
	{
		
	}

	public TemplatesToPathLinker getTemplates()
	{
		return templates;
	}

	public void setTemplates(TemplatesToPathLinker templates)
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
		tdao.write(getTemplates().getElement(), getTemplates().getPath());
	}
	
	public TemplatesToPathLinker loadTemplates(String path)
	{
		return new TemplatesToPathLinker(tdao.read(path), path);
	}
}
