package at.bmlvs.NDMS.domain;

import java.util.ArrayList;

public class Device
{
	private String fingerprint;
	private String management_ip;
	
	private ArrayList<Interface> interfaces;
	
	public Device(String fingerprint, String management_ip)
	{
		setFingerprint(fingerprint);
		setManagement_ip(management_ip);
		
		setInterfaces(new ArrayList<Interface>());
	}

	public String getFingerprint()
	{
		return fingerprint;
	}

	public void setFingerprint(String fingerprint)
	{
		this.fingerprint = fingerprint;
	}

	public String getManagement_ip()
	{
		return management_ip;
	}

	public void setManagement_ip(String management_ip)
	{
		this.management_ip = management_ip;
	}

	public ArrayList<Interface> getInterfaces()
	{
		return interfaces;
	}

	public void setInterfaces(ArrayList<Interface> interfaces)
	{
		this.interfaces = interfaces;
	}
}
