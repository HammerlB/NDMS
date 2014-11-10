package at.bmlvs.NDMS.domain;

import javafx.scene.control.Button;

public class Interface extends Button
{
	private String portid;
	private String portidshort;
	private String portname;
	private String portnameshort;
	private String type;
	private String portstatus;
	private boolean trunkstatus;
	private boolean cdpstatus;
	private boolean cdpneighbor;
	private boolean port_8021X;
	private String vlan;
	
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

	public String getPortnameshort()
	{
		return portnameshort;
	}

	public void setPortnameshort(String portnameshort)
	{
		this.portnameshort = portnameshort;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getPortstatus()
	{
		return portstatus;
	}

	public void setPortstatus(String portstatus)
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

	public String getVlan()
	{
		return vlan;
	}

	public void setVlan(String vlan)
	{
		this.vlan = vlan;
	}
	
	@Override
	public String toString()
	{
		return "Interface [portid=" + portid + ", portidshort=" + portidshort
				+ ", portname=" + portname + ", portnameshort=" + portnameshort + ", type=" + type + ", portstatus="
				+ portstatus + ", trunkstatus=" + trunkstatus + ", cdpstatus="
				+ cdpstatus + ", cdpneighbor=" + cdpneighbor + ", port_8021X="
				+ port_8021X + ", vlan=" + vlan + "]";
	}
}