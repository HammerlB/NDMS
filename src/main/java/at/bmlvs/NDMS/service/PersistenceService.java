package at.bmlvs.NDMS.service;

import java.io.File;
import java.util.ArrayList;

import at.bmlvs.NDMS.domain.configs.Configuration;
import at.bmlvs.NDMS.linker.AppConfigToPathLinker;
import at.bmlvs.NDMS.linker.ConfigurationToPathLinker;
import at.bmlvs.NDMS.linker.DatatypesToPathLinker;
import at.bmlvs.NDMS.linker.TemplateToPathLinker;
import at.bmlvs.NDMS.persistence.specific.TXTConfigurationDAO;
import at.bmlvs.NDMS.persistence.specific.XMLAppConfigDAO;
import at.bmlvs.NDMS.persistence.specific.XMLDatatypesDAO;
import at.bmlvs.NDMS.persistence.specific.XMLTemplateDAO;

public class PersistenceService
{
	private ArrayList<TemplateToPathLinker> templates;
	private AppConfigToPathLinker appconfig;
	private ArrayList<ConfigurationToPathLinker> configurations;
	private DatatypesToPathLinker datatypes;

	private XMLTemplateDAO tdao = new XMLTemplateDAO();
	private XMLAppConfigDAO cdao = new XMLAppConfigDAO();
	private TXTConfigurationDAO fdao = new TXTConfigurationDAO();
	private XMLDatatypesDAO ddao = new XMLDatatypesDAO();

	public PersistenceService()
	{
		if ((new File("config.xml")).exists())
		{
			setAppconfig(loadAppConfig("config.xml"));
		}
		else
		{
			setAppconfig(new AppConfigToPathLinker(new AppConfig(), "config.xml"));
			saveAppConfig();
		}
	}

	public ArrayList<TemplateToPathLinker> getTemplates()
	{
		return templates;
	}

	public void setTemplates(ArrayList<TemplateToPathLinker> templates)
	{
		this.templates = templates;
	}

	public AppConfigToPathLinker getAppconfig()
	{
		return appconfig;
	}

	public void setAppconfig(AppConfigToPathLinker appconfig)
	{
		this.appconfig = appconfig;
	}

	public ArrayList<ConfigurationToPathLinker> getConfigurations()
	{
		return configurations;
	}

	public void setConfigurations(
			ArrayList<ConfigurationToPathLinker> configurations)
	{
		this.configurations = configurations;
	}

	public DatatypesToPathLinker getDatatypes()
	{
		return datatypes;
	}

	public void setDatatypes(DatatypesToPathLinker datatypes)
	{
		this.datatypes = datatypes;
	}

	public XMLTemplateDAO getTdao()
	{
		return tdao;
	}

	public void setTdao(XMLTemplateDAO tdao)
	{
		this.tdao = tdao;
	}

	public XMLAppConfigDAO getCdao()
	{
		return cdao;
	}

	public void setCdao(XMLAppConfigDAO cdao)
	{
		this.cdao = cdao;
	}

	public TXTConfigurationDAO getFdao()
	{
		return fdao;
	}

	public void setFdao(TXTConfigurationDAO fdao)
	{
		this.fdao = fdao;
	}

	public XMLDatatypesDAO getDdao()
	{
		return ddao;
	}

	public void setDdao(XMLDatatypesDAO ddao)
	{
		this.ddao = ddao;
	}

	public void saveTemplates()
	{
		for (TemplateToPathLinker t : getTemplates())
		{
			tdao.write(t.getElement(), t.getPath());
		}
	}

	public TemplateToPathLinker loadTemplates(String path)
	{
		TemplateToPathLinker template = new TemplateToPathLinker(tdao.read(path), path);;
		getTemplates().add(template);
		
		return template;
	}

	public void saveAppConfig()
	{
		cdao.write(getAppconfig().getElement(), getAppconfig().getPath());
	}

	public AppConfigToPathLinker loadAppConfig(String path)
	{
		return new AppConfigToPathLinker(cdao.read(path), path);
	}
	
	public void saveConfigurations()
	{
		for (ConfigurationToPathLinker c : getConfigurations())
		{
			fdao.write(c.getElement().getData(), c.getPath());
		}
	}

	public ConfigurationToPathLinker loadConfigurations(String path)
	{
		ConfigurationToPathLinker config = new ConfigurationToPathLinker(new Configuration(fdao.read(path)), path);;
		getConfigurations().add(config);
		
		return config;
	}
	
	public void saveDatatypes()
	{
		ddao.write(getDatatypes().getElement(), getDatatypes().getPath());
	}

	public DatatypesToPathLinker loadDatatypes(String path)
	{
		return new DatatypesToPathLinker(ddao.read(path), path);
	}
}