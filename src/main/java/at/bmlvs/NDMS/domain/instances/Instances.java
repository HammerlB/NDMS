package at.bmlvs.NDMS.domain.instances;

import java.util.ArrayList;

import javafx.scene.control.TabPane;

public class Instances extends TabPane
{
	private ArrayList<Instance> instances;

	public Instances()
	{
		setInstances(new ArrayList<Instance>());
	}

	public ArrayList<Instance> getInstances()
	{
		return instances;
	}

	public void setInstances(ArrayList<Instance> instances)
	{
		this.instances = instances;
	}
	
	public void addSingleInstance(InstanceOnline instance)
	{
		getInstances().add(instance);
		getTabs().add(getInstances().indexOf(instance), getInstances().get(getInstances().indexOf(instance)));
	}
	
	public void convertSingleOfflineInstanceToOnlineInstance(InstanceOffline instanceOffline, String ipv4address)
	{
		for(Instance inst: getInstances())
		{
			if(inst.equals(instanceOffline))
			{
				InstanceOnline instanceOnline = new InstanceOnline(name, fingerprint, management_ip, sshConnector, tftpConnector, snmpConnector)
				String id = inst.getId();
				
				break;
			}
		}
	}
}