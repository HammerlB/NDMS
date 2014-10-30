package at.bmlvs.NDMS.domain;

public class Interface
{
	private String portid;
	private String portidshort;
	private String portname;
	private String type;
	private boolean portstatus;
	private boolean trunkstatus;
	private boolean cdpstatus;
	private boolean cdpneighbor;
	private boolean port_8021X;
	private int vlan;
	
	public Interface(String portid)
	{
		setPortid(portid);
	}

	public String getPortid()
	{
		return portid;
	}

	public void setPortid(String portid)
	{
		this.portid = portid;
	}

	public String getPortidshort()
	{
		return portidshort;
	}

	public void setPortidshort(String portidshort)
	{
		this.portidshort = portidshort;
	}

	public String getPortname()
	{
		return portname;
	}

	public void setPortname(String portname)
	{
		this.portname = portname;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public boolean isPortstatus()
	{
		return portstatus;
	}

	public void setPortstatus(boolean portstatus)
	{
		this.portstatus = portstatus;
	}

	public boolean isTrunkstatus()
	{
		return trunkstatus;
	}

	public void setTrunkstatus(boolean trunkstatus)
	{
		this.trunkstatus = trunkstatus;
	}

	public boolean isCdpstatus()
	{
		return cdpstatus;
	}

	public void setCdpstatus(boolean cdpstatus)
	{
		this.cdpstatus = cdpstatus;
	}

	public boolean isCdpneighbor()
	{
		return cdpneighbor;
	}

	public void setCdpneighbor(boolean cdpneighbor)
	{
		this.cdpneighbor = cdpneighbor;
	}

	public boolean isPort_8021X()
	{
		return port_8021X;
	}

	public void setPort_8021X(boolean port_8021x)
	{
		port_8021X = port_8021x;
	}

	public int getVlan()
	{
		return vlan;
	}

	public void setVlan(int vlan)
	{
		this.vlan = vlan;
	}
	
	public void setAll(String portidshort, String portname, String type, 
			boolean portstatus, boolean trunkstatus, boolean cdpstatus, 
			boolean cdpneighbor, boolean port_8021X, int vlan)
	{
		setPortidshort(portidshort);
		setPortname(portname);
		setType(type);
		setPortstatus(portstatus);
		setTrunkstatus(trunkstatus);
		setCdpstatus(cdpstatus);
		setCdpneighbor(cdpneighbor);
		setPort_8021X(port_8021X);
		setVlan(vlan);
	}
}