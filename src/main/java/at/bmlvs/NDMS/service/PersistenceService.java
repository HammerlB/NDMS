package at.bmlvs.NDMS.service;

import java.io.File;
import java.util.ArrayList;

import at.bmlvs.NDMS.domain.helper.Filewalker;
import at.bmlvs.NDMS.linker.AppConfigToPathLinker;
import at.bmlvs.NDMS.linker.SnapshotToPathLinker;
import at.bmlvs.NDMS.linker.TemplateToPathLinker;
import at.bmlvs.NDMS.persistence.specific.XMLAppConfigDAO;
import at.bmlvs.NDMS.persistence.specific.XMLSnapshotDAO;
import at.bmlvs.NDMS.persistence.specific.XMLTemplateDAO;

public class PersistenceService
{
	private ArrayList<TemplateToPathLinker> templates;
	private ArrayList<SnapshotToPathLinker> snapshots;
	private AppConfigToPathLinker appconfig;

	private XMLTemplateDAO tdao = new XMLTemplateDAO();
	private XMLAppConfigDAO cdao = new XMLAppConfigDAO();
	private XMLSnapshotDAO sdao = new XMLSnapshotDAO();

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
		
		setTemplates(new ArrayList<TemplateToPathLinker>());
		
		if ((new File("templates")).exists())
		{
			setTemplates(loadAllTemplates(getAppconfig().getElement().getNDMS_DEFAULT_PATH_APP() + "\\" + getAppconfig().getElement().getNDMS_DEFAULT_PATH_TEMPLATE_DIRECTORY()));
		}
		
		setSnapshots(new ArrayList<SnapshotToPathLinker>());
		
		if ((new File("snapshots")).exists())
		{
			setSnapshots(loadAllSnapshots(getAppconfig().getElement().getNDMS_DEFAULT_PATH_APP() + "\\" + getAppconfig().getElement().getNDMS_DEFAULT_PATH_SNAPSHOT_DIRECTORY()));
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

	public void saveTemplate(TemplateToPathLinker template)
	{
		tdao.write(template.getElement(), template.getPath());
	}
	
	public ArrayList<TemplateToPathLinker> loadAllTemplates(String pathToDirectory)
	{
		ArrayList<TemplateToPathLinker> templates = new ArrayList<TemplateToPathLinker>();
		
		for(String path : Filewalker.walk(pathToDirectory))
		{
			templates.add(loadTemplate(path));
		}
		
		return templates;
	}
	
	public ArrayList<SnapshotToPathLinker> loadAllSnapshots(String pathToDirectory)
	{
		ArrayList<SnapshotToPathLinker> snapshots = new ArrayList<SnapshotToPathLinker>();
		
		for(String path : Filewalker.walk(pathToDirectory))
		{
			snapshots.add(loadSnapshot(path));
		}
		
		return snapshots;
	}
	
	public void saveAllTemplates()
	{
		for(TemplateToPathLinker template : getTemplates())
		{
			saveTemplate(template);
		}
	}

	public TemplateToPathLinker loadTemplate(String path)
	{
		TemplateToPathLinker template = new TemplateToPathLinker(tdao.read(path), path);;
		
		getTemplates().add(template);
		
		return template;
	}
	
	public SnapshotToPathLinker loadSnapshot(String path)
	{
		SnapshotToPathLinker snapshot = new SnapshotToPathLinker(sdao.read(path), path);
		
		getSnapshots().add(snapshot);
		
		return snapshot;
	}

	public void saveAppConfig()
	{
		cdao.write(getAppconfig().getElement(), getAppconfig().getPath());
	}

	public AppConfigToPathLinker loadAppConfig(String path)
	{
		return new AppConfigToPathLinker(cdao.read(path), path);
	}
	
	public ArrayList<SnapshotToPathLinker> getSnapshots()
	{
		return snapshots;
	}

	public void setSnapshots(ArrayList<SnapshotToPathLinker> snapshots)
	{
		this.snapshots = snapshots;
	}

	public XMLSnapshotDAO getSdao()
	{
		return sdao;
	}

	public void setSdao(XMLSnapshotDAO sdao)
	{
		this.sdao = sdao;
	}
	
	public void saveSnapshot(SnapshotToPathLinker snapshot)
	{
		sdao.write(snapshot.getElement(), snapshot.getPath());
	}
	
	public void saveAllSnapshots()
	{
		for(SnapshotToPathLinker snapshot : getSnapshots())
		{
			saveSnapshot(snapshot);
		}
	}
}