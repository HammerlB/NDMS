package at.bmlvs.NDMS.persistence.specific;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import at.bmlvs.NDMS.domain.templates.Datatype;
import at.bmlvs.NDMS.domain.templates.Datatypes;
import at.bmlvs.NDMS.persistence.general.DatatypesDAO;

@SuppressWarnings("serial")
public class XMLDatatypesDAO implements DatatypesDAO, Serializable
{
	public void write(Datatypes element, String path)
	{
		FileOutputStream fs = null;

		try
		{
			XStream xs = new XStream();
			xs.alias("datatypes", Datatypes.class);
			xs.alias("datatype", Datatype.class);

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
	public void delete(Datatypes element, String path)
	{
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("finally")
	public Datatypes read(String path)
	{
		FileInputStream fs = null;

		XStream xs = new XStream(new DomDriver());
		Datatypes element = null;

		try
		{
			fs = new FileInputStream(path);
			xs.alias("datatypes", Datatypes.class);
			xs.alias("datatype", Datatype.class);

			element = (Datatypes) xs.fromXML(fs);
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

	public void update(Datatypes newelement, Datatypes oldelement, String path)
	{
		// TODO Auto-generated method stub
	}
}