package at.bmlvs.NDMS.domain;

import java.util.ArrayList;

public class Devices
{
	private ArrayList<Device> devices;
	
	public Devices()
	{
		setDevices(new ArrayList<Device>());
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
