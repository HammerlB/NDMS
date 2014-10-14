package at.bmlvs.NDMS.persistence.specific;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class WhenUsingTXTConfigurationDAO
{
	private static TXTConfigurationDAO dao = new TXTConfigurationDAO();
	private static File file = new File("Test.txt");
	private static String data = "This is test data for an integration test.";

	@Test
	public void ensureWriteWorksProperly()
	{
		dao.write(data, file.getAbsolutePath());

		String check = perfectRead(file.getAbsolutePath());

		assertThat(check, equalTo(data));
		assertThat(check, notNullValue());

		file.delete();
	}

	@Test
	public void ensureReadWorksProperly()
	{
		perfectWrite(file.getAbsolutePath(), data);

		String check = dao.read(file.getAbsolutePath());

		check = check.substring(0, check.length() - 1);

		assertThat(check, notNullValue());
		assertThat(check, equalTo(data));

		file.delete();
	}

	private void perfectWrite(String path, String input)
	{
		FileWriter fileWriter = null;
		BufferedWriter bufferWriter = null;

		try
		{
			fileWriter = new FileWriter(path, false);
			bufferWriter = new BufferedWriter(fileWriter);
			bufferWriter.write(input);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				bufferWriter.close();
				fileWriter.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String perfectRead(String path)
	{
		StringBuilder result = new StringBuilder();
		BufferedReader br = null;

		try
		{
			br = new BufferedReader(new FileReader(path));
			String line = null;

			while ((line = br.readLine()) != null)
			{
				result.append(line);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				br.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result.toString();
	}
}
