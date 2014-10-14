package at.bmlvs.NDMS.persistence.specific;

import java.io.Serializable;

import at.bmlvs.NDMS.persistence.general.AppConfigDAO;
import at.bmlvs.NDMS.service.AppConfig;

@SuppressWarnings("serial")
public class XMLAppConfigDAO implements AppConfigDAO, Serializable
{
	public void write(AppConfig element, String path)
	{
		// TODO Auto-generated method stub
	}

	public void delete(AppConfig element, String path)
	{
		// TODO Auto-generated method stub
	}

	public AppConfig read(String path)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void update(AppConfig newelement, AppConfig oldelement, String path)
	{
		// TODO Auto-generated method stub
	}
}