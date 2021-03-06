package at.bmlvs.NDMS.domain.instances;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import at.bmlvs.NDMS.domain.helper.UUIDGenerator;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;

public class Interface extends Button
{
	private StringProperty uuid;
	private StringProperty portid;
	private StringProperty portidshort;
	private StringProperty portname;
	private StringProperty portnameshort;
	private StringProperty portnameshortDisplay;
	private StringProperty portdescription;
	private StringProperty type;
	private StringProperty portstatus;
	private StringProperty vlan;
	private BooleanProperty trunkstatus;
	private BooleanProperty cdpstatus;
	private BooleanProperty cdpneighbor;
	private BooleanProperty port_8021X;
	
	public Interface(String portid)
	{
		setUUID(UUIDGenerator.generateUUID());
		setPortid(portid);
		setType("Unknown");
		setId(getUUID());
		setText(getPortnameshort());
		setPortdescription("None");
		
		setTooltip(new Tooltip());
		setTextForTooltip();
		
	}
	
	public final String getUUID()
	{
		if (uuid != null)
		{
			return uuid.get();
		}

		return null;
	}

	public final void setUUID(String uuid)
	{
		this.uuidProperty().set(uuid);
	}

	public final StringProperty uuidProperty()
	{
		if (uuid == null)
		{
			uuid = new SimpleStringProperty(null);
		}

		return uuid;
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
	
	public final String getPortdescription()
	{
		if (portdescription != null)
		{
			return portdescription.get();
		}

		return null;
	}

	public final void setPortdescription(String portdescription)
	{
		this.portdescriptionProperty().set(portdescription);
	}

	public final StringProperty portdescriptionProperty()
	{
		if (portdescription == null)
		{
			portdescription = new SimpleStringProperty(null);
		}

		return portdescription;
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
		setTextForTooltip();
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
		
		if(portnameshort.length() <= 5)
		{
			setPortnameshortDisplay(" " + portnameshort + " ");
		}
		else
		{
			setPortnameshortDisplay(portnameshort);
		}
	}

	public final StringProperty portnameshortProperty()
	{
		if (portnameshort == null)
		{
			portnameshort = new SimpleStringProperty(null);
		}

		return portnameshort;
	}
	
	public final String getPortnameshortDisplay()
	{
		if (portnameshortDisplay != null)
		{
			return portnameshortDisplay.get();
		}

		return null;
	}

	public final void setPortnameshortDisplay(String portnameshortDisplay)
	{
		this.portnameshortDisplayProperty().set(portnameshortDisplay);
		setText(getPortnameshortDisplay());
	}

	public final StringProperty portnameshortDisplayProperty()
	{
		if (portnameshortDisplay == null)
		{
			portnameshortDisplay = new SimpleStringProperty(null);
		}

		return portnameshortDisplay;
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
		setTextForTooltip();
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
		setTextForTooltip();
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
		setTextForTooltip();
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
		
		if(getPortstatus().equals("2"))
		{
			type = "Offline";
			setStyle("-fx-base: #FFB2B2;");
		}
		else
		{
			if(getPort_8021X())
			{
				type = "802.1X-Client";
				setStyle("-fx-base: #b6e7c9;");
			}
			else if(getPort_8021X() == false && getVlan().equals(10))
			{
				type = "Client";
				setStyle("-fx-base: #3399FF;");
			}
			else if(getTrunkstatus())
			{
				type = "Network";
				setStyle("-fx-base: #CCFF99;");
			}
			else
			{
				setStyle("-fx-base: #CDCDC1;");
			}
		}
		
		setType(type);
		
		return type;
	}
	
	public void setTextForTooltip()
	{
		
		
		Platform.runLater(new Runnable()
		{
			public void run()
			{
				if(getTooltip() != null)
				{
					getTooltip().setFont(new Font("Arial", 20));
					getTooltip().setText("Portname: " + getPortname() + "\nType: " + getType() + "\nStatus: " + getPortstatus() + "\nVlan: " + getVlan() + "\nDescription:\n" + getPortdescription());
				}
			}
		});
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