package at.bmlvs.NDMS.domain;

import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.Tab;
import at.bmlvs.NDMS.domain.connectors.SNMPConnector;
import at.bmlvs.NDMS.domain.connectors.SSHConnector;
import at.bmlvs.NDMS.domain.helper.SNMPParser;
import at.bmlvs.NDMS.domain.helper.UUIDGenerator;
import at.bmlvs.NDMS.service.ServiceFactory;

public class Instance extends Tab
{
	private StringProperty uuid;
	private StringProperty name;
	private StringProperty fingerprint;
	private StringProperty management_ip;

	private SSHConnector sshConnector;
	private SNMPConnector snmpConnector;

	private ObservableList<Interface> interfaces;

	public Instance(String name, String fingerprint, String management_ip,
			SSHConnector sshConnector, SNMPConnector snmpConnector)
	{
		setUUID(UUIDGenerator.generateUUID());
		setName(name);
		setFingerprint(fingerprint);
		setManagement_IP(management_ip);
		setSshConnector(sshConnector);
		setSnmpConnector(snmpConnector);
		setText(getName());
		setInterfaces(FXCollections.observableArrayList());
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

	public final String getName()
	{
		if (name != null)
		{
			return name.get();
		}

		return null;
	}

	public final void setName(String name)
	{
		this.nameProperty().set(name);
	}

	public final StringProperty nameProperty()
	{
		if (name == null)
		{
			name = new SimpleStringProperty(null);
		}

		return name;
	}

	public final String getFingerprint()
	{
		if (fingerprint != null)
		{
			return fingerprint.get();
		}

		return null;
	}

	public final void setFingerprint(String fingerprint)
	{
		this.fingerprintProperty().set(fingerprint);
	}

	public final StringProperty fingerprintProperty()
	{
		if (fingerprint == null)
		{
			fingerprint = new SimpleStringProperty(null);
		}

		return fingerprint;
	}

	public final String getManagement_IP()
	{
		if (management_ip != null)
		{
			return management_ip.get();
		}

		return null;
	}

	public final void setManagement_IP(String management_ip)
	{
		this.management_ipProperty().set(management_ip);
	}

	public final StringProperty management_ipProperty()
	{
		if (management_ip == null)
		{
			management_ip = new SimpleStringProperty(null);
		}

		return management_ip;
	}

	public ObservableList<Interface> getInterfaces()
	{
		return interfaces;
	}

	public void setInterfaces(ObservableList<Interface> interfaces)
	{
		this.interfaces = interfaces;
	}

	public SNMPConnector getSnmpConnector()
	{
		return snmpConnector;
	}

	public void setSnmpConnector(SNMPConnector snmpConnector)
	{
		this.snmpConnector = snmpConnector;
	}

	public SSHConnector getSshConnector()
	{
		return sshConnector;
	}

	public void setSshConnector(SSHConnector sshConnector)
	{
		this.sshConnector = sshConnector;
	}

	public void populateInstance()
	{
		try
		{
			setName(getSnmpConnector().walk(
					ServiceFactory.getPersistenceService().getAppconfig()
							.getElement().getSNMP_SWNAME(), false, true).get(0));
			setText(getName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void checkInstance()
	{
		try
		{
			String name = getSnmpConnector().walk(
					ServiceFactory.getPersistenceService().getAppconfig()
							.getElement().getSNMP_SWNAME(), false, true).get(0);
			setName(name);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void populateInterfaces()
	{
		ArrayList<String> vlans = new ArrayList<String>();

		try
		{
			for (String output : getSnmpConnector().walk(
					ServiceFactory.getPersistenceService().getAppconfig()
							.getElement().getSNMP_ALLVLANINFORMATION(), true,
					false))
			{
				try
				{
					String[] parts = output.split("\\:");

					if (parts.length > 1)
					{
						vlans.add(parts[0]);
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}

			for (String output : getSnmpConnector().walk(
					ServiceFactory.getPersistenceService().getAppconfig()
							.getElement().getSNMP_BRIDGEIFDESCR(), true, false))
			{
				try
				{
					String[] parts = output.split("\\:");

					if (parts.length > 1)
					{
						if (!parts[1].contains("Null") && !parts[1].equals("1")
								&& !parts[1].contains("Vlan"))
						{
							Interface interf = new Interface(parts[0]);
							interf.setPortname(parts[1]);
							interf.setPortnameshort(SNMPParser
									.convertPortnameToPortshortname(parts[1]));
							getInterfaces().add(interf);
						}
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}

			for (String output : getSnmpConnector()
					.walk(ServiceFactory.getPersistenceService().getAppconfig()
							.getElement().getSNMP_BRIDGEIFSTATUS(), true, false))
			{
				try
				{
					String[] parts = output.split("\\:");

					for (Interface interf : getInterfaces())
					{
						if (parts.length > 1)
						{
							if (interf.getPortid().equals(parts[0]))
							{
								interf.setPortstatus(parts[1]);
							}
						}
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}

			for (String output : getSnmpConnector().walk(
					ServiceFactory.getPersistenceService().getAppconfig()
							.getElement().getSNMP_VLANTRUNKPORTDYNAMICSTATUS(),
					true, false))
			{
				try
				{
					String[] parts = output.split("\\:");

					for (Interface interf : getInterfaces())
					{
						if (parts.length > 1)
						{
							if (interf.getPortid().equals(parts[0]))
							{
								if (parts[1].contains("1"))
								{
									interf.setTrunkstatus(true);
								}
								else
								{
									interf.setTrunkstatus(false);
								}
							}
						}
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}

			for (String output : getSnmpConnector().walk(
					ServiceFactory.getPersistenceService().getAppconfig()
							.getElement().getSNMP_CDPINTERFACEENABLE(), true,
					false))
			{
				try
				{
					String[] parts = output.split("\\:");

					for (Interface interf : getInterfaces())
					{
						if (parts.length > 1)
						{
							if (interf.getPortid().equals(parts[0]))
							{
								if (parts[1].contains("1"))
								{
									interf.setCdpstatus(true);
								}
								else
								{
									interf.setCdpstatus(false);
								}
							}
						}
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}

			for (String vlan : vlans)
			{
				for (String output : getSnmpConnector()
						.walkWithDynamicCommunityString(
								ServiceFactory.getPersistenceService()
										.getAppconfig().getElement()
										.getSNMP_DOT1DBASEPORTIFINDEX(),
								getSnmpConnector().getCommunityString() + "@"
										+ vlan, true, false))
				{
					try
					{
						String[] parts = output.split("\\:");

						for (Interface interf : getInterfaces())
						{
							if (parts.length > 1)
							{
								if (interf.getPortid().equals(parts[1]))
								{
									interf.setPortidshort(parts[0]);

									if (interf.getTrunkstatus() != true)
									{
										interf.setVlan(vlan);
									}
									else
									{
										interf.setVlan("All");
									}
								}
							}
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void populateAll()
	{
		populateInstance();
		populateInterfaces();
	}

	public void checkInterfaces()
	{
		ArrayList<String> vlans = new ArrayList<String>();

		try
		{
			for (String output : getSnmpConnector().walk(
					ServiceFactory.getPersistenceService().getAppconfig()
							.getElement().getSNMP_ALLVLANINFORMATION(), true,
					false))
			{
				try
				{
					String[] parts = output.split("\\:");

					if (parts.length > 1)
					{
						vlans.add(parts[0]);
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}

			for (String output : getSnmpConnector()
					.walk(ServiceFactory.getPersistenceService().getAppconfig()
							.getElement().getSNMP_BRIDGEIFSTATUS(), true, false))
			{
				try
				{
					String[] parts = output.split("\\:");

					for (Interface interf : getInterfaces())
					{
						if (parts.length > 1)
						{
							if (interf.getPortid().equals(parts[0]))
							{
								interf.setPortstatus(parts[1]);
							}
						}
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}

			for (String output : getSnmpConnector().walk(
					ServiceFactory.getPersistenceService().getAppconfig()
							.getElement().getSNMP_VLANTRUNKPORTDYNAMICSTATUS(),
					true, false))
			{
				try
				{
					String[] parts = output.split("\\:");

					for (Interface interf : getInterfaces())
					{
						if (parts.length > 1)
						{
							if (interf.getPortid().equals(parts[0]))
							{
								if (parts[1].contains("1"))
								{
									interf.setTrunkstatus(true);
								}
								else
								{
									interf.setTrunkstatus(false);
								}
							}
						}
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}

			for (String output : getSnmpConnector().walk(
					ServiceFactory.getPersistenceService().getAppconfig()
							.getElement().getSNMP_CDPINTERFACEENABLE(), true,
					false))
			{
				try
				{
					String[] parts = output.split("\\:");

					for (Interface interf : getInterfaces())
					{
						if (parts.length > 1)
						{
							if (interf.getPortid().equals(parts[0]))
							{
								if (parts[1].contains("1"))
								{
									interf.setCdpstatus(true);
								}
								else
								{
									interf.setCdpstatus(false);
								}
							}
						}
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}

			for (String vlan : vlans)
			{
				for (String output : getSnmpConnector()
						.walkWithDynamicCommunityString(
								ServiceFactory.getPersistenceService()
										.getAppconfig().getElement()
										.getSNMP_DOT1DBASEPORTIFINDEX(),
								getSnmpConnector().getCommunityString() + "@"
										+ vlan, true, false))
				{
					try
					{
						String[] parts = output.split("\\:");

						for (Interface interf : getInterfaces())
						{
							if (parts.length > 1)
							{
								if (interf.getPortid().equals(parts[1]))
								{
									interf.setPortidshort(parts[0]);

									if (interf.getTrunkstatus() != true)
									{
										interf.setVlan(vlan);
									}
									else
									{
										interf.setVlan("All");
									}
								}
							}
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}

			for (Interface interf : getInterfaces())
			{
				interf.checkAndSetWhatType();
			}
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}

		/*
		 * Thread t = new Thread() { public void run() { while(true) { try {
		 * System.out.println("CHECKIF: " +
		 * getSnmpConnector().walk(".1.3.6.1.2.1.2.2.1.1", false, true)); }
		 * catch (IOException e) { e.printStackTrace(); } } } };
		 * 
		 * t.start();
		 */
	}


	@Override
	public String toString()
	{
		return "Instance [UUID=" + uuid + ", name=" + name + ", fingerprint="
				+ fingerprint + ", management_ip=" + management_ip + "]";
	}
}