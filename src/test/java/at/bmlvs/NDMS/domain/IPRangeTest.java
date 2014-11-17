package at.bmlvs.NDMS.domain;

import at.bmlvs.NDMS.domain.helper.IPv4Address;
import at.bmlvs.NDMS.domain.helper.IPv4Range;

public class IPRangeTest
{
	public static void main(String[] args)
	{
		IPv4Range range = new IPv4Range(new IPv4Address("192.168.1.8"), new IPv4Address("192.168.1.14"));
		
		for(String address: range.getAllAddressesAsStringArray(true))
		{
			System.out.println(address);
		}
	}
}
