package at.bmlvs.NDMS.domain;

import java.util.ArrayList;

import javafx.scene.control.Tab;
import at.bmlvs.NDMS.domain.connectors.SSHConnector;
import at.bmlvs.NDMS.domain.helper.UUIDGenerator;

public class Instance
{
	private String UUID;
	private String name;
	
	private SSHConnector sshConnector;
	private Tab instanceTab;
	
	private ArrayList<Device> devices;

	public Instance(String name)
	{
		setUUID(UUIDGenerator.generateUUID());
		setName(name);
		setSshConnector(new SSHConnector());
		setDevices(new ArrayList<Device>());
		
		setInstanceTab(new Tab());
	}

	public String getUUID()
	{
		return UUID;
	}

	public void setUUID(String uUID)
	{
		UUID = uUID;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public SSHConnector getSshConnector()
	{
		return sshConnector;
	}

	public void setSshConnector(SSHConnector sshConnector)
	{
		this.sshConnector = sshConnector;
	}

	public Tab getInstanceTab()
	{
		return instanceTab;
	}

	public void setInstanceTab(Tab instanceTab)
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
