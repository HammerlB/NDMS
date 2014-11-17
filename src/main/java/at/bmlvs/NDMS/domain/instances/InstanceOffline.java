package at.bmlvs.NDMS.domain.instances;

public class InstanceOffline extends Instance
{
	private int portcount;
	
	public InstanceOffline(String name, int portcount)
	{
		super(name);
	}
	
	public int getPortcount()
	{
		return portcount;
	}

	public void setPortcount(int portcount)
	{
		this.portcount = portcount;
	}

	@Override
	public void populateInstance()
	{
		
	}

	@Override
	public void populateInterfaces()
	{
		for(int i = 0; i < getPortcount(); i++)
		{
			getInterfaces().add(new Interface("" + i));
		}
		
	}
}