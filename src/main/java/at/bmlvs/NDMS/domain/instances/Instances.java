package at.bmlvs.NDMS.domain.instances;

import java.util.ArrayList;

import javafx.scene.control.TabPane;

public class Instances extends TabPane
{
	private ArrayList<InstanceOnline> instancesOnline;
	private ArrayList<InstanceOffline> instancesOffline;

	public Instances()
	{
		setInstancesOnline(new ArrayList<InstanceOnline>());
	}

	public ArrayList<InstanceOnline> getInstancesOnline()
	{
		return instancesOnline;
	}

	public void setInstancesOnline(ArrayList<InstanceOnline> instancesOnline)
	{
		this.instancesOnline = instancesOnline;
	}
	
	public void addSingleOfflineInstance(String name)
	{
		
	}
	
	public void addSingleOnlineInstance(InstanceOnline instance)
	{
		getInstancesOnline().add(instance);
		getTabs().add(getInstancesOnline().indexOf(instance), getInstancesOnline().get(getInstancesOnline().indexOf(instance)));
	}
}