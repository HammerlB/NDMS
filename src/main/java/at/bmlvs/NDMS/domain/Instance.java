package at.bmlvs.NDMS.domain;

import java.util.ArrayList;

import at.bmlvs.NDMS.domain.helper.UUIDGenerator;

public class Instance
{
	private String UUID;
	
	private ArrayList<Device> devices;

	public Instance()
	{
		setUUID(UUIDGenerator.generateUUID());
		setDevices(new ArrayList<Device>());
	}

	public String getUUID()
	{
		return UUID;
	}

	public void setUUID(String uUID)
	{
		UUID = uUID;
	}

	public ArrayList<Device> getDevices()
	{
		return devices;
	}

	public void setDevices(ArrayList<Device> devices)
	{
		this.devices = devices;
	}
}
