package at.bmlvs.NDMS.domain.helper;

import java.io.File;
import java.util.ArrayList;

public class Filewalker
{
	public static ArrayList<String> walk(String path)
	{
		ArrayList<String> files = new ArrayList<String>();
		
		File root = new File(path);
		File[] list = root.listFiles();

		if (list != null)
		{
			for (File f : list)
			{
				if (f.isDirectory())
				{
					walk(f.getAbsolutePath());
				}
				else
				{
					files.add("" + f.getAbsoluteFile());
				}
			}
		}
		
		return files;
	}
}