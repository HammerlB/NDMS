package at.bmlvs.NDMS.domain;

import java.util.ArrayList;

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
	
	public void addMultipleInstances()
	{
		
	}
}