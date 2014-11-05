package at.bmlvs.NDMS.domain;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.Tab;
import at.bmlvs.NDMS.domain.connectors.SNMPConnector;
import at.bmlvs.NDMS.domain.connectors.SSHConnector;
import at.bmlvs.NDMS.domain.helper.UUIDGenerator;

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

	public SSHConnector getSshConnector()
	{
		return sshConnector;
	}

	public void setSshConnector(SSHConnector sshConnector)
	{
		this.sshConnector = sshConnector;
	}
	
	public SNMPConnector getSnmpConnector()
	{
		return snmpConnector;
	}

	public void setSnmpConnector(SNMPConnector snmpConnector)
	{
		this.snmpConnector = snmpConnector;
	}

	public void populateInstance()
	{
		try
		{
			getSnmpConnector().walk(".1.3.6.1.2.1.1.5", false, true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void populateInterfaces()
	{
		try
		{
			getSnmpConnector().walk(".1.3.6.1.2.1.2.2.1.1", true, false);
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
}