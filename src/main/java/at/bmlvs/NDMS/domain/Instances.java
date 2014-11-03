package at.bmlvs.NDMS.domain;

import java.util.ArrayList;

import at.bmlvs.NDMS.domain.connectors.SSHConnector;

public class Instances
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
	
	public int addSingleOfflineInstance(String name)
	{
		Instance instance = new Instance(name);
		instance.setSshConnector(null);
		
		instance.getDevices().add(new Device(name, null));
		getInstances().add(instance);
		
		return 0;
	}
	
	public int addSingleOnlineInstance(String name)
	{
		Instance instance = new Instance(name);
		instance.getSshConnector().connect();
		
		System.out.println(instance.getSshConnector().getSshFingerprint());
		
//		instance.getDevices().add(new Device(instance.getSshConnector().getSshFingerprint(), name));
		getInstances().add(instance);
		
		return getInstances().indexOf(instance);
	}
	
	public int addMultipleOnlineInstances(String name)
	{
		return 0;
	}
}