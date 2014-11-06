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
	
	public static String convertPortnameToPortshortname(String portname)
	{
		String portnameshort = portname;
		
		if(portname.contains("FastEthernet"))
		{
			portnameshort = portname.replace("FastEthernet", "fa");
		}
		else if(portname.contains("GigabitEthernet"))
		{
			portnameshort = portname.replace("GigabitEthernet", "gi");
		}
		else if(portname.contains("Ethernet"))
		{
			portnameshort = portname.replace("Ethernet", "e");
		}
		
		return portnameshort;
	}
}
