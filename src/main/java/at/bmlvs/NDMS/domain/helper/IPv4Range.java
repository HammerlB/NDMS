package at.bmlvs.NDMS.domain.helper;

import java.util.ArrayList;

public class IPv4Range
{
	private IPv4Address startAddress;
	private IPv4Address endAddress;
	
	public IPv4Range(IPv4Address startAddress, IPv4Address endAddress)
	{
		setStartAddress(startAddress);
		setEndAddress(endAddress);
	}

	public IPv4Address getStartAddress()
	{
		return startAddress;
	}

	public void setStartAddress(IPv4Address startAddress)
	{
		this.startAddress = startAddress;
	}

	public IPv4Address getEndAddress()
	{
		return endAddress;
	}

	public void setEndAddress(IPv4Address endAddress)
	{
		this.endAddress = endAddress;
	}
	
	public ArrayList<String> getAllAddressesAsStringArray(boolean onlyalive)
	{
		ArrayList<String> addresses = new ArrayList<String>();
		
		if(getStartAddress().getFourth_octet() <= getEndAddress().getFourth_octet())
		{
			for(int i = getStartAddress().getFourth_octet(); i <= getEndAddress().getFourth_octet(); i++)
			{
				String address = getStartAddress().getFirst_octet() + "." + getStartAddress().getSecond_octet() + "." + getStartAddress().getThird_octet() + "." + i;
				
				if(onlyalive)
				{
					try
					{
						if(IPv4Address.isAlive(address))
						{
							addresses.add(address);
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					addresses.add(address);
				}
			}
		}
		
		return addresses;
	}
}
