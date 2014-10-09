package at.bmlvs.NDMS.domain.helper;

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
}