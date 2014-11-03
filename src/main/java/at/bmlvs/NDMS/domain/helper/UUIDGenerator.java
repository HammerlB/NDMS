package at.bmlvs.NDMS.domain.helper;

import java.util.Random;
import java.util.UUID;

public class UUIDGenerator
{
	public static String generateUUID()
	{
		String uuid = "";

		try
		{
			uuid += UUID.randomUUID();
		}
		catch (Exception e)
		{
			uuid += "INVALID UUID";
		}

		return uuid;
	}
	
	public static int generateIntegerUUID()
	{
		Random rand = new Random();
		int randomNum = rand.nextInt(Integer.MAX_VALUE) + 1;
		
		return randomNum;
	}
}