package at.bmlvs.NDMS.persistence.specific;

import java.io.Serializable;

import at.bmlvs.NDMS.domain.configs.Configuration;
import at.bmlvs.NDMS.persistence.general.ConfigurationDAO;

@SuppressWarnings("serial")
public class TXTConfigurationDAO implements ConfigurationDAO, Serializable
{
	public void write(Configuration element, String path)
	{
		// TODO Auto-generated method stub
	}

	public void delete(Configuration element, String path)
	{
		// TODO Auto-generated method stub
	}

	public Configuration read(String path)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Configuration newelement, Configuration oldelement,
			String path)
	{
		// TODO Auto-generated method stub
	}
}