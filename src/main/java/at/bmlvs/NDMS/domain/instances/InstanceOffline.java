package at.bmlvs.NDMS.domain.instances;

public class InstanceOffline extends Instance
{
	private int portcount;
	
	public InstanceOffline(String name, int portcount)
	{
		super(name);
		
		setPortcount(portcount);
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
		setText(getName());
	}

	@Override
	public void populateInterfaces()
	{
		for(int i = 1; i < getPortcount(); i++)
		{
			Interface interf = new Interface("" + i);
			interf.setText("Fa0" + "/" + interf.getPortid());
			
			getInterfaces().add(interf);
		}
	}
}