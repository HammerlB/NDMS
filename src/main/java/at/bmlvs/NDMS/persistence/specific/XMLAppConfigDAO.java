package at.bmlvs.NDMS.persistence.specific;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import at.bmlvs.NDMS.persistence.general.AppConfigDAO;
import at.bmlvs.NDMS.service.AppConfig;

@SuppressWarnings("serial")
public class XMLAppConfigDAO implements AppConfigDAO, Serializable
{
	public void write(AppConfig element, String path)
	{
		FileOutputStream fs = null;

		try
		{
			XStream xs = new XStream();
			xs.alias("appconfig", AppConfig.class);

			try
			{
				fs = new FileOutputStream(path);
				xs.toXML(element, fs);
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (fs != null)
			{
				try
				{
					fs.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public void delete(AppConfig element, String path)
	{
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("finally")
	public AppConfig read(String path)
	{
		FileInputStream fs = null;

		XStream xs = new XStream(new DomDriver());
		AppConfig element = null;

		try
		{
			fs = new FileInputStream(path);
			xs.alias("appconfig", AppConfig.class);

			element = (AppConfig) xs.fromXML(fs);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (fs != null)
			{
				try
				{
					fs.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			return element;
		}
	}

	public void update(AppConfig newelement, AppConfig oldelement, String path)
	{
		// TODO Auto-generated method stub
	}
}