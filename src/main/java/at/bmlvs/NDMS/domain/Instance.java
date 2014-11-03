package at.bmlvs.NDMS.domain;

import java.util.ArrayList;

import at.bmlvs.NDMS.domain.helper.UUIDGenerator;
import at.bmlvs.NDMS.presentation.elements.InstanceTab;

public class Instance
{
	private String UUID;
	private InstanceTab instanceTab;
	
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

	public InstanceTab getInstanceTab()
	{
		return instanceTab;
	}

	public void setInstanceTab(InstanceTab instanceTab)
	{
		this.instanceTab = instanceTab;
	}

	public ArrayList<Device> getDevices()
	{
		return devices;
	}

	public void setDevices(ArrayList<Device> devices)
	{
		this.devices = devices;
	}

	@SuppressWarnings("unused")
	public void checkInterfacesOfDevices()
	{
		for(Device device: getDevices())
		{
			for(Interface interf: device.getInterfaces())
			{
				
			}
		}
	}
}
