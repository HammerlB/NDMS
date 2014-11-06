package at.bmlvs.NDMS.domain;

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
	
	public void addSingleOfflineInstance(String name)
	{
		
	}
	
	public void addSingleOnlineInstance(Instance instance)
	{
		getInstances().add(instance);
		getTabs().add(getInstances().indexOf(instance), getInstances().get(getInstances().indexOf(instance)));
	}
	
	public void addMultipleOnlineInstances(Instance[] instances)
	{
		for(Instance inst: instances)
		{
			addSingleOnlineInstance(inst);
		}
	}
}