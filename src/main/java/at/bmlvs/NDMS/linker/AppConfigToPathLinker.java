package at.bmlvs.NDMS.linker;

import java.io.Serializable;

import at.bmlvs.NDMS.service.AppConfig;

@SuppressWarnings("serial")
public class AppConfigToPathLinker extends Linker<AppConfig, String> implements
		Serializable
{
	public AppConfigToPathLinker(AppConfig element, String path)
	{
		super(element, path);
	}
}