package at.bmlvs.NDMS.persistence.specific;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import at.bmlvs.NDMS.domain.snapshots.Snapshot;
import at.bmlvs.NDMS.persistence.general.SnapshotDAO;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@SuppressWarnings("serial")
public class XMLSnapshotDAO implements SnapshotDAO, Serializable
{
	public void write(Snapshot element, String path)
	{
		FileOutputStream fs = null;

		try
		{
			XStream xs = new XStream();
			xs.alias("snapshot", Snapshot.class);

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

	public void delete(Snapshot element, String path)
	{
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("finally")
	public Snapshot read(String path)
	{
		FileInputStream fs = null;

		XStream xs = new XStream(new DomDriver());
		Snapshot element = null;

		try
		{
			fs = new FileInputStream(path);
			xs.alias("snapshot", Snapshot.class);

			element = (Snapshot) xs.fromXML(fs);
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

	public void update(Snapshot newelement, Snapshot oldelement, String path)
	{
		// TODO Auto-generated method stub
		
	}
}