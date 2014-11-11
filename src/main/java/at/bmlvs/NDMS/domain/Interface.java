package at.bmlvs.NDMS.domain;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

public class Interface extends Button
{
	private StringProperty portid;
	private StringProperty portidshort;
	private StringProperty portname;
	private StringProperty portnameshort;
	private StringProperty type;
	private StringProperty portstatus;
	private StringProperty vlan;
	private BooleanProperty trunkstatus;
	private BooleanProperty cdpstatus;
	private BooleanProperty cdpneighbor;
	private BooleanProperty port_8021X;
	
	public Interface(String portid)
	{
		setPortid(portid);
		setType("Unknown");
	}
	
	public final String getPortid()
	{
		if (portid != null)
		{
			return portid.get();
		}

		return null;
	}

	public final void setPortid(String portid)
	{
		this.portidProperty().set(portid);
	}

	public final StringProperty portidProperty()
	{
		if (portid == null)
		{
			portid = new SimpleStringProperty(null);
		}

		return portid;
	}
	
	public final String getPortidshort()
	{
		if (portidshort != null)
		{
			return portidshort.get();
		}

		return null;
	}

	public final void setPortidshort(String portidshort)
	{
		this.portidshortProperty().set(portidshort);
	}

	public final StringProperty portidshortProperty()
	{
		if (portidshort == null)
		{
			portidshort = new SimpleStringProperty(null);
		}

		return portidshort;
	}
	
	public final String getPortname()
	{
		if (portname != null)
		{
			return portname.get();
		}

		return null;
	}

	public final void setPortname(String portname)
	{
		this.portnameProperty().set(portname);
	}

	public final StringProperty portnameProperty()
	{
		if (portname == null)
		{
			portname = new SimpleStringProperty(null);
		}

		return portname;
	}
	
	public final String getPortnameshort()
	{
		if (portnameshort != null)
		{
			return portnameshort.get();
		}

		return null;
	}

	public final void setPortnameshort(String portnameshort)
	{
		this.portnameshortProperty().set(portnameshort);
	}

	public final StringProperty portnameshortProperty()
	{
		if (portnameshort == null)
		{
			portnameshort = new SimpleStringProperty(null);
		}

		return portnameshort;
	}
	
	public final String getType()
	{
		if (type != null)
		{
			return type.get();
		}

		return null;
	}

	public final void setType(String type)
	{
		this.typeProperty().set(type);
	}

	public final StringProperty typeProperty()
	{
		if (type == null)
		{
			type = new SimpleStringProperty(null);
		}

		return type;
	}
	
	public final String getPortstatus()
	{
		if (portstatus != null)
		{
			return portstatus.get();
		}

		return null;
	}

	public final void setPortstatus(String portstatus)
	{
		this.portstatusProperty().set(portstatus);
	}

	public final StringProperty portstatusProperty()
	{
		if (portstatus == null)
		{
			portstatus = new SimpleStringProperty(null);
		}

		return portstatus;
	}
	
	public final String getVlan()
	{
		if (vlan != null)
		{
			return vlan.get();
		}

		return null;
	}

	public final void setVlan(String vlan)
	{
		this.vlanProperty().set(vlan);
	}

	public final StringProperty vlanProperty()
	{
		if (vlan == null)
		{
			vlan = new SimpleStringProperty(null);
		}

		return vlan;
	}
	
	public final boolean getTrunkstatus()
	{
		if (trunkstatus != null)
		{
			return trunkstatus.get();
		}

		return false;
	}

	public final void setTrunkstatus(boolean trunkstatus)
	{
		this.trunkstatusProperty().set(trunkstatus);
	}

	public final BooleanProperty trunkstatusProperty()
	{
		if (trunkstatus == null)
		{
			trunkstatus = new SimpleBooleanProperty();
		}

		return trunkstatus;
	}
	
	public final boolean getCdpstatus()
	{
		if (cdpstatus != null)
		{
			return cdpstatus.get();
		}

		return false;
	}

	public final void setCdpstatus(boolean cdpstatus)
	{
		this.cdpstatusProperty().set(cdpstatus);
	}

	public final BooleanProperty cdpstatusProperty()
	{
		if (cdpstatus == null)
		{
			cdpstatus = new SimpleBooleanProperty();
		}

		return cdpstatus;
	}
	
	public final boolean getCdpneighbor()
	{
		if (cdpneighbor != null)
		{
			return cdpneighbor.get();
		}

		return false;
	}

	public final void setCdpneighbor(boolean cdpneighbor)
	{
		this.cdpneighborProperty().set(cdpneighbor);
	}

	public final BooleanProperty cdpneighborProperty()
	{
		if (cdpneighbor == null)
		{
			cdpneighbor = new SimpleBooleanProperty();
		}

		return cdpneighbor;
	}
	
	public final boolean getPort_8021X()
	{
		if (port_8021X != null)
		{
			return port_8021X.get();
		}

		return false;
	}

	public final void setPort_8021Xs(boolean port_8021X)
	{
		this.port_8021XProperty().set(port_8021X);
	}

	public final BooleanProperty port_8021XProperty()
	{
		if (port_8021X == null)
		{
			port_8021X = new SimpleBooleanProperty();
		}

		return port_8021X;
	}
	
	public String checkAndSetWhatType()
	{
		String type = "Unknown";
		
		if(getPort_8021X())
		{
			type = "Client";
		}
		else if(getTrunkstatus())
		{
			type = "Network";
		}
		
		setType(type);
		
		return type;
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