package at.bmlvs.NDMS.domain.helper;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class IPv4Address
{
	private int first_octet;
	private int second_octet;
	private int third_octet;
	private int fourth_octet;

	public IPv4Address(String ipv4address)
	{
		convertAndSetIPv4AddressToOctets(ipv4address);
	}

	public int getFirst_octet()
	{
		return first_octet;
	}

	public void setFirst_octet(int first_octet)
	{
		this.first_octet = first_octet;
	}

	public int getSecond_octet()
	{
		return second_octet;
	}

	public void setSecond_octet(int second_octet)
	{
		this.second_octet = second_octet;
	}

	public int getThird_octet()
	{
		return third_octet;
	}

	public void setThird_octet(int third_octet)
	{
		this.third_octet = third_octet;
	}

	public int getFourth_octet()
	{
		return fourth_octet;
	}

	public void setFourth_octet(int fourth_octet)
	{
		this.fourth_octet = fourth_octet;
	}

	public void convertAndSetIPv4AddressToOctets(String ipv4address)
	{
		String[] octetsString = ipv4address.split("\\.");

		if (octetsString.length == 4)
		{
			setFirst_octet(Integer.parseInt(octetsString[0]));
			setSecond_octet(Integer.parseInt(octetsString[1]));
			setThird_octet(Integer.parseInt(octetsString[2]));
			setFourth_octet(Integer.parseInt(octetsString[3]));
		}
	}

	public String getIPv4Address()
	{
		return getFirst_octet() + "." + getSecond_octet() + "."
				+ getThird_octet() + "." + getFourth_octet();
	}

	public static boolean isAlive(String ipv4address)
	{
		try
		{
			String command;

			if (System.getProperty("os.name").toLowerCase()
					.startsWith("windows"))
			{
				// For Windows
				command = "ping -n 2 " + ipv4address;
			}
			else
			{
				// For Linux and OSX
				command = "ping -c 2 " + ipv4address;
			}

			Process proc = Runtime.getRuntime().exec(command);
			StreamGobbler outputGobbler = new StreamGobbler(
					proc.getInputStream(), "OUTPUT");
			outputGobbler.start();

			proc.waitFor();
			return checkAvailability(outputGobbler.getOutputLines());

		}
		catch (Exception e)
		{
			
		}

		return false;
	}

	private static boolean checkAvailability(List<String> outputLines)
	{

		for (String line : outputLines)
		{
			if (line.contains("unreachable"))
			{
				return false;
			}
			if (line.contains("TTL="))
			{
				return true;
			}
		}
		return false;

	}
}
