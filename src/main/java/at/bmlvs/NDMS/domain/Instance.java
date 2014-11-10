package at.bmlvs.NDMS.domain;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.Tab;
import at.bmlvs.NDMS.domain.connectors.SNMPConnector;
import at.bmlvs.NDMS.domain.connectors.SSHConnector;
import at.bmlvs.NDMS.domain.helper.SNMPParser;
import at.bmlvs.NDMS.domain.helper.UUIDGenerator;
import at.bmlvs.NDMS.service.ServiceFactory;

public class Instance extends Tab
{
	private String UUID;
	private String name;
	private String fingerprint;
	private String management_ip;
	
	private SSHConnector sshConnector;
	private SNMPConnector snmpConnector;
	
	private ArrayList<Interface> interfaces;
	
	public Instance(String name, String fingerprint, String management_ip, SSHConnector sshConnector, SNMPConnector snmpConnector)
	{
		setUUID(UUIDGenerator.generateUUID());
		setName(name);
		setFingerprint(fingerprint);
		setManagement_ip(management_ip);
		setSshConnector(sshConnector);
		setSnmpConnector(snmpConnector);
		setText(getName());
		setInterfaces(new ArrayList<Interface>());
	}

	public String getUUID()
	{
		return UUID;
	}

	public void setUUID(String uUID)
	{
		UUID = uUID;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getFingerprint()
	{
		return fingerprint;
	}

	public void setFingerprint(String fingerprint)
	{
		this.fingerprint = fingerprint;
	}

	public String getManagement_ip()
	{
		return management_ip;
	}

	public void setManagement_ip(String management_ip)
	{
		this.management_ip = management_ip;
	}

	public ArrayList<Interface> getInterfaces()
	{
		return interfaces;
	}

	public void setInterfaces(ArrayList<Interface> interfaces)
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

	public SSHConnector getSshConnector() {
		return sshConnector;
	}

	public void setSshConnector(SSHConnector sshConnector) {
		this.sshConnector = sshConnector;
	}

	public void populateInstance()
	{
		try
		{
			setName(getSnmpConnector().walk(ServiceFactory.getPersistenceService().getAppconfig().getElement().getSNMP_SWNAME(), false, true).get(0));
			setText(getName());
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
			for(String output: getSnmpConnector().walk(ServiceFactory.getPersistenceService().getAppconfig().getElement().getSNMP_ALLVLANINFORMATION(), true, false))
			{
				try
				{
					String[] parts = output.split("\\:");
					
					if(parts.length > 1)
					{
						vlans.add(parts[0]);
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			
			for(String output: getSnmpConnector().walk(ServiceFactory.getPersistenceService().getAppconfig().getElement().getSNMP_BRIDGEIFDESCR(), true, false))
			{
				try
				{
					String[] parts = output.split("\\:");
					
					if(parts.length > 1)
					{
						if(!parts[1].contains("Null") && !parts[1].equals("1") && !parts[1].contains("Vlan"))
						{
							Interface interf = new Interface(parts[0]);
							interf.setPortname(parts[1]);
							interf.setPortnameshort(SNMPParser.convertPortnameToPortshortname(parts[1]));
							getInterfaces().add(interf);
						}
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			
			for(String output: getSnmpConnector().walk(ServiceFactory.getPersistenceService().getAppconfig().getElement().getSNMP_BRIDGEIFSTATUS(), true, false))
			{
				try
				{
					String[] parts = output.split("\\:");
					
					for(Interface interf: getInterfaces())
					{
						if(parts.length > 1)
						{
							if(interf.getPortid().equals(parts[0]))
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
			
			for(String output: getSnmpConnector().walk(ServiceFactory.getPersistenceService().getAppconfig().getElement().getSNMP_VLANTRUNKPORTDYNAMICSTATUS(), true, false))
			{
				try
				{
					String[] parts = output.split("\\:");
					
					for(Interface interf: getInterfaces())
					{
						if(parts.length > 1)
						{
							if(interf.getPortid().equals(parts[0]))
							{
								if(parts[1].contains("1"))
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
			
			for(String output: getSnmpConnector().walk(ServiceFactory.getPersistenceService().getAppconfig().getElement().getSNMP_CDPINTERFACEENABLE(), true, false))
			{
				try
				{
					String[] parts = output.split("\\:");
					
					for(Interface interf: getInterfaces())
					{
						if(parts.length > 1)
						{
							if(interf.getPortid().equals(parts[0]))
							{
								if(parts[1].contains("1"))
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
			
			for(String vlan: vlans)
			{
				for(String output: getSnmpConnector().walkWithDynamicCommunityString(ServiceFactory.getPersistenceService().getAppconfig().getElement().getSNMP_DOT1DBASEPORTIFINDEX(), getSnmpConnector().getCommunityString() + "\\@" + vlan, true, false))
				{
					try
					{
						String[] parts = output.split("\\:");
						
						for(Interface interf: getInterfaces())
						{
							if(parts.length > 1)
							{
								if(interf.getPortid().equals(parts[1]))
								{
									interf.setPortidshort(parts[0]);
									
									if(interf.isTrunkstatus() != true)
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
					catch(Exception e)
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
		Thread t = new Thread()
		{
		     public void run()
		     {
		    	while(true)
		    	{
		    		try
					{
						System.out.println("CHECKIF: " + getSnmpConnector().walk(".1.3.6.1.2.1.2.2.1.1", false, true));
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
		    	}
		     }
		};
		
		t.start();
	}

	@Override
	public String toString()
	{
		return "Instance [UUID=" + UUID + ", name=" + name + ", fingerprint="
				+ fingerprint + ", management_ip=" + management_ip + "]";
	}
}