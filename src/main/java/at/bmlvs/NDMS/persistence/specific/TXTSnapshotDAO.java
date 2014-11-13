package at.bmlvs.NDMS.persistence.specific;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

import at.bmlvs.NDMS.domain.snapshots.Snapshot;

public class TXTSnapshotDAO
{
	public void write(Snapshot element, String path)
	{
		FileOutputStream fs = null;

		try
		{
			try
			{
				File file = new File(path);

				fs = new FileOutputStream(path);

				if (!file.exists())
				{
					file.createNewFile();
				}

				// get the content in bytes
//				byte[] contentInBytes = element.getContent().getBytes();

//				fs.write(contentInBytes);
//				fs.flush();
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

	public void delete(String element, String path)
	{
		// TODO Auto-generated method stub
	}

	public Snapshot read(String path)
	{
		File file = new File(path);

		if (file.exists())
		{
			FileInputStream fs = null;

			try
			{
				fs = new FileInputStream(path);

				StringBuilder inputStringBuilder = new StringBuilder();
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(fs, "ISO-8859-1"));
				String line = bufferedReader.readLine();
				
				while (line != null)
				{
					inputStringBuilder.append(line);
					inputStringBuilder.append('\n');
					line = bufferedReader.readLine();
				}
				
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				
//				Snapshot snapshot = new Snapshot(file.getParent(), file.getName(), sdf.format(file.lastModified()), inputStringBuilder.toString());

//				return snapshot;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					fs.close();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	public void update(String newelement, String oldelement, String path)
	{
		// TODO Auto-generated method stub
	}
}
