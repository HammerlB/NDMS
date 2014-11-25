package at.bmlvs.NDMS.persistence.specific;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import at.bmlvs.NDMS.domain.templates.Snippet;
import at.bmlvs.NDMS.domain.templates.Template;
import at.bmlvs.NDMS.persistence.general.TemplateDAO;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;

@SuppressWarnings("serial")
public class XMLTemplateDAO implements TemplateDAO, Serializable
{
	public void write(Template element, String path)
	{
		FileOutputStream fs = null;

		try
		{
			XStream xs = new XStream();
			xs.autodetectAnnotations(true);

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

	public void delete(Template element, String path)
	{
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("finally")
	public Template read(String path)
	{
		FileInputStream fs = null;

		XStream xs = new XStream(new DomDriver());
		xs.autodetectAnnotations(true);
		Template element = null;

		try
		{
			fs = new FileInputStream(path);
			xs.alias("Template", Template.class);

			element = (Template) xs.fromXML(fs);
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

	public void update(Template newelement, Template oldelement, String path)
	{
		// TODO Auto-generated method stub
		
	}
}