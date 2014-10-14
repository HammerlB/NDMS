package at.bmlvs.NDMS.linker;

import java.io.Serializable;

import at.bmlvs.NDMS.domain.configs.Configuration;

@SuppressWarnings("serial")
public class ConfigurationToPathLinker extends Linker<Configuration, String> implements Serializable
{
	public ConfigurationToPathLinker(Configuration element, String path)
	{
		super(element, path);
	}
}