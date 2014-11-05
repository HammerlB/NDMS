package at.bmlvs.NDMS.domain.helper;

public class SNMPParser
{
	public static boolean matchSNMPOutput(String search, String value)
	{
		String[] parts = value.split("\\:");

		if(parts[0].equals(search))
		{
			
		}
		
		return false;
	}
}
