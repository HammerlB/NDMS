package at.bmlvs.NDMS.linker;

import java.io.Serializable;

import at.bmlvs.NDMS.service.AppConfig;
import at.bmlvs.NDMS.service.ServiceFactory;

@SuppressWarnings("serial")
public class AppConfigToPathLinker extends Linker<AppConfig, String> implements
		Serializable
{
	public AppConfigToPathLinker(AppConfig element, String path)
	{
		super(element, path);

		setPath(ServiceFactory.getAppConfig().getNDMS_DEFAULT_PATH_APP()
				+ ServiceFactory.getAppConfig()
						.getNDMS_DEFAULT_PATH_APPCONFIG());
	}
}