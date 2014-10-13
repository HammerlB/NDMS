package at.bmlvs.NDMS.persistence.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import at.bmlvs.NDMS.domain.templates.Template;
import at.bmlvs.NDMS.domain.templates.Templates;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@SuppressWarnings("serial")
public class XMLTemplatesDAO implements TemplatesDAO, Serializable
{
	public void write(Templates element, String path)
	{
		FileOutputStream fs = null;

		try
		{
			XStream xs = new XStream();
			xs.alias("templates", Templates.class);
			xs.alias("template", Template.class);

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

	public void delete(List<Template> element, String path)
	{
		// TODO Auto-generated method stub

	}

	@SuppressWarnings({ "unchecked", "finally" })
	public Templates read(String path)
	{
		FileInputStream fs = null;

		XStream xs = new XStream(new DomDriver());
		Templates element = null;

		try
		{
			fs = new FileInputStream(path);
			xs.alias("templates", Templates.class);
			xs.alias("template", Template.class);

			element = (Templates) xs.fromXML(fs);
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

	public void delete(Templates element, String path)
	{
		// TODO Auto-generated method stub
	}

	public void update(Templates newelement, Templates oldelement, String path)
	{
		// TODO Auto-generated method stub
	}
}