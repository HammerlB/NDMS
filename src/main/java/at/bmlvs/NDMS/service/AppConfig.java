package at.bmlvs.NDMS.service;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class AppConfig implements Serializable
{
	// Serialization
	private String NDMS_DEFAULT_PATH_APP;
	private String NDMS_DEFAULT_PATH_APPCONFIG;
	private String NDMS_DEFAULT_PATH_TEMPLATE_SOURCE_DIRECTORY;
	private String NDMS_DEFAULT_PATH_TEMPLATE_USER_DIRECTORY;
	private String NDMS_DEFAULT_PATH_SNAPSHOT_DIRECTORY;
	private String NDMS_DEFAULT_XML_FILE_EXTENSION;
	private String NDMS_DEFAULT_TXT_FILE_EXTENSION;

	private ArrayList<String> NDMS_DEFAULT_PATH_TEMPLATES;
	private ArrayList<String> NDMS_DEFAULT_PATH_DATATYPES;
	private ArrayList<String> NDMS_DEFAULT_PATH_CONFIGS;
	private ArrayList<String> NDMS_DEFAULT_PATH_SNAPSHOTS;
	
	private String SNMP_SWNAME;
	private String SNMP_BRIDGEIFDESCR;
	private String SNMP_BRIDGEIFSTATUS;
	private String SNMP_BRIDGEALIAS;
	private String SNMP_BRIDGESHORTNAME;
	private String SNMP_DOT1DTPFDBADDRESS;
	private String SNMP_DOT1DTPFDBPORT;
	private String SNMP_VLANTRUNKPORTDYNAMICSTATUS;
	private String SNMP_CDPINTERFACEENABLE;
	private String SNMP_CDPNEIGHBORPLATFORM;
	private String SNMP_ALLVLANINFORMATION;
	private String SNMP_DOT1DBASEPORTIFINDEX;

	public AppConfig()
	{
		setDefaultSerialization();
	}

	public String getNDMS_DEFAULT_PATH_APP()
	{
		return NDMS_DEFAULT_PATH_APP;
	}

	public void setNDMS_DEFAULT_PATH_APP(String nDMS_DEFAULT_PATH_APP)
	{
		NDMS_DEFAULT_PATH_APP = nDMS_DEFAULT_PATH_APP;
	}

	public String getNDMS_DEFAULT_PATH_APPCONFIG()
	{
		return NDMS_DEFAULT_PATH_APPCONFIG;
	}

	public void setNDMS_DEFAULT_PATH_APPCONFIG(
			String nDMS_DEFAULT_PATH_APPCONFIG)
	{
		NDMS_DEFAULT_PATH_APPCONFIG = nDMS_DEFAULT_PATH_APPCONFIG;
	}

	public String getNDMS_DEFAULT_PATH_TEMPLATE_SOURCE_DIRECTORY()
	{
		return NDMS_DEFAULT_PATH_TEMPLATE_SOURCE_DIRECTORY;
	}

	public void setNDMS_DEFAULT_PATH_TEMPLATE_SOURCE_DIRECTORY(
			String nDMS_DEFAULT_PATH_TEMPLATE_SOURCE_DIRECTORY)
	{
		NDMS_DEFAULT_PATH_TEMPLATE_SOURCE_DIRECTORY = nDMS_DEFAULT_PATH_TEMPLATE_SOURCE_DIRECTORY;
	}

	public String getNDMS_DEFAULT_PATH_SNAPSHOT_DIRECTORY() {
		return NDMS_DEFAULT_PATH_SNAPSHOT_DIRECTORY;
	}

	public void setNDMS_DEFAULT_PATH_SNAPSHOT_DIRECTORY(
			String nDMS_DEFAULT_PATH_SNAPSHOT_DIRECTORY) {
		NDMS_DEFAULT_PATH_SNAPSHOT_DIRECTORY = nDMS_DEFAULT_PATH_SNAPSHOT_DIRECTORY;
	}

	public String getNDMS_DEFAULT_XML_FILE_EXTENSION()
	{
		return NDMS_DEFAULT_XML_FILE_EXTENSION;
	}

	public void setNDMS_DEFAULT_XML_FILE_EXTENSION(
			String nDMS_DEFAULT_XML_FILE_EXTENSION)
	{
		NDMS_DEFAULT_XML_FILE_EXTENSION = nDMS_DEFAULT_XML_FILE_EXTENSION;
	}

	public String getNDMS_DEFAULT_TXT_FILE_EXTENSION()
	{
		return NDMS_DEFAULT_TXT_FILE_EXTENSION;
	}

	public void setNDMS_DEFAULT_TXT_FILE_EXTENSION(
			String nDMS_DEFAULT_TXT_FILE_EXTENSION)
	{
		NDMS_DEFAULT_TXT_FILE_EXTENSION = nDMS_DEFAULT_TXT_FILE_EXTENSION;
	}

	public ArrayList<String> getNDMS_DEFAULT_PATH_TEMPLATES()
	{
		return NDMS_DEFAULT_PATH_TEMPLATES;
	}

	public void setNDMS_DEFAULT_PATH_TEMPLATES(
			ArrayList<String> nDMS_DEFAULT_PATH_TEMPLATES)
	{
		NDMS_DEFAULT_PATH_TEMPLATES = nDMS_DEFAULT_PATH_TEMPLATES;
	}

	public ArrayList<String> getNDMS_DEFAULT_PATH_CONFIGS()
	{
		return NDMS_DEFAULT_PATH_CONFIGS;
	}

	public void setNDMS_DEFAULT_PATH_CONFIGS(
			ArrayList<String> nDMS_DEFAULT_PATH_CONFIGS)
	{
		NDMS_DEFAULT_PATH_CONFIGS = nDMS_DEFAULT_PATH_CONFIGS;
	}

	public ArrayList<String> getNDMS_DEFAULT_PATH_SNAPSHOTS() {
		return NDMS_DEFAULT_PATH_SNAPSHOTS;
	}

	public void setNDMS_DEFAULT_PATH_SNAPSHOTS(
			ArrayList<String> nDMS_DEFAULT_PATH_SNAPSHOTS) {
		NDMS_DEFAULT_PATH_SNAPSHOTS = nDMS_DEFAULT_PATH_SNAPSHOTS;
	}

	public String getSNMP_SWNAME()
	{
		return SNMP_SWNAME;
	}

	public void setSNMP_SWNAME(String sNMP_SWNAME)
	{
		SNMP_SWNAME = sNMP_SWNAME;
	}

	public String getSNMP_BRIDGEIFDESCR()
	{
		return SNMP_BRIDGEIFDESCR;
	}

	public void setSNMP_BRIDGEIFDESCR(String sNMP_BRIDGEIFDESCR)
	{
		SNMP_BRIDGEIFDESCR = sNMP_BRIDGEIFDESCR;
	}

	public String getSNMP_BRIDGEIFSTATUS()
	{
		return SNMP_BRIDGEIFSTATUS;
	}

	public void setSNMP_BRIDGEIFSTATUS(String sNMP_BRIDGEIFSTATUS)
	{
		SNMP_BRIDGEIFSTATUS = sNMP_BRIDGEIFSTATUS;
	}

	public String getSNMP_BRIDGEALIAS()
	{
		return SNMP_BRIDGEALIAS;
	}

	public void setSNMP_BRIDGEALIAS(String sNMP_BRIDGEALIAS)
	{
		SNMP_BRIDGEALIAS = sNMP_BRIDGEALIAS;
	}

	public String getSNMP_BRIDGESHORTNAME()
	{
		return SNMP_BRIDGESHORTNAME;
	}

	public void setSNMP_BRIDGESHORTNAME(String sNMP_BRIDGESHORTNAME)
	{
		SNMP_BRIDGESHORTNAME = sNMP_BRIDGESHORTNAME;
	}

	public String getSNMP_DOT1DTPFDBADDRESS()
	{
		return SNMP_DOT1DTPFDBADDRESS;
	}

	public void setSNMP_DOT1DTPFDBADDRESS(String sNMP_DOT1DTPFDBADDRESS)
	{
		SNMP_DOT1DTPFDBADDRESS = sNMP_DOT1DTPFDBADDRESS;
	}

	public String getSNMP_DOT1DTPFDBPORT()
	{
		return SNMP_DOT1DTPFDBPORT;
	}

	public void setSNMP_DOT1DTPFDBPORT(String sNMP_DOT1DTPFDBPORT)
	{
		SNMP_DOT1DTPFDBPORT = sNMP_DOT1DTPFDBPORT;
	}

	public String getSNMP_VLANTRUNKPORTDYNAMICSTATUS()
	{
		return SNMP_VLANTRUNKPORTDYNAMICSTATUS;
	}

	public void setSNMP_VLANTRUNKPORTDYNAMICSTATUS(
			String sNMP_VLANTRUNKPORTDYNAMICSTATUS)
	{
		SNMP_VLANTRUNKPORTDYNAMICSTATUS = sNMP_VLANTRUNKPORTDYNAMICSTATUS;
	}

	public String getSNMP_CDPINTERFACEENABLE()
	{
		return SNMP_CDPINTERFACEENABLE;
	}

	public void setSNMP_CDPINTERFACEENABLE(String sNMP_CDPINTERFACEENABLE)
	{
		SNMP_CDPINTERFACEENABLE = sNMP_CDPINTERFACEENABLE;
	}

	public String getSNMP_CDPNEIGHBORPLATFORM()
	{
		return SNMP_CDPNEIGHBORPLATFORM;
	}

	public void setSNMP_CDPNEIGHBORPLATFORM(String sNMP_CDPNEIGHBORPLATFORM)
	{
		SNMP_CDPNEIGHBORPLATFORM = sNMP_CDPNEIGHBORPLATFORM;
	}

	public String getSNMP_ALLVLANINFORMATION()
	{
		return SNMP_ALLVLANINFORMATION;
	}

	public void setSNMP_ALLVLANINFORMATION(String sNMP_ALLVLANINFORMATION)
	{
		SNMP_ALLVLANINFORMATION = sNMP_ALLVLANINFORMATION;
	}

	public String getSNMP_DOT1DBASEPORTIFINDEX()
	{
		return SNMP_DOT1DBASEPORTIFINDEX;
	}

	public void setSNMP_DOT1DBASEPORTIFINDEX(String sNMP_DOT1DBASEPORTIFINDEX)
	{
		SNMP_DOT1DBASEPORTIFINDEX = sNMP_DOT1DBASEPORTIFINDEX;
	}
	
	public ArrayList<String> getNDMS_DEFAULT_PATH_DATATYPES()
	{
		return NDMS_DEFAULT_PATH_DATATYPES;
	}

	public void setNDMS_DEFAULT_PATH_DATATYPES(
			ArrayList<String> nDMS_DEFAULT_PATH_DATATYPES)
	{
		NDMS_DEFAULT_PATH_DATATYPES = nDMS_DEFAULT_PATH_DATATYPES;
	}

	public String getNDMS_DEFAULT_PATH_TEMPLATE_USER_DIRECTORY()
	{
		return NDMS_DEFAULT_PATH_TEMPLATE_USER_DIRECTORY;
	}

	public void setNDMS_DEFAULT_PATH_TEMPLATE_USER_DIRECTORY(
			String nDMS_DEFAULT_PATH_TEMPLATE_USER_DIRECTORY)
	{
		NDMS_DEFAULT_PATH_TEMPLATE_USER_DIRECTORY = nDMS_DEFAULT_PATH_TEMPLATE_USER_DIRECTORY;
	}

	/**
	 * Serialization default-configuration
	 */
	private void setDefaultSerialization()
	{
		setNDMS_DEFAULT_PATH_APP(System.getProperty("user.dir"));
		setNDMS_DEFAULT_PATH_APPCONFIG("appconfig.xml");
		setNDMS_DEFAULT_PATH_TEMPLATE_SOURCE_DIRECTORY("templates");
		setNDMS_DEFAULT_PATH_TEMPLATE_USER_DIRECTORY("custom");
		setNDMS_DEFAULT_PATH_SNAPSHOT_DIRECTORY("snapshots");
		setNDMS_DEFAULT_XML_FILE_EXTENSION(".xml");
		setNDMS_DEFAULT_TXT_FILE_EXTENSION(".txt");

		setNDMS_DEFAULT_PATH_TEMPLATES(new ArrayList<String>());
		setNDMS_DEFAULT_PATH_SNAPSHOTS(new ArrayList<String>());
		
		setSNMP_SWNAME(".1.3.6.1.2.1.1.5");
		setSNMP_BRIDGEIFDESCR(".1.3.6.1.2.1.2.2.1.2");
		setSNMP_BRIDGEALIAS(".1.3.6.1.2.1.31.1.1.1.18");
		setSNMP_BRIDGESHORTNAME("1.3.6.1.2.1.31.1.1.1.1");
		setSNMP_BRIDGEIFSTATUS(".1.3.6.1.2.1.2.2.1.8");
		setSNMP_DOT1DTPFDBADDRESS(".1.3.6.1.2.1.17.4.3.1.1");
		setSNMP_DOT1DTPFDBPORT(".1.3.6.1.2.1.17.4.3.1.2");
		setSNMP_VLANTRUNKPORTDYNAMICSTATUS(".1.3.6.1.4.1.9.9.46.1.6.1.1.14");
		setSNMP_CDPINTERFACEENABLE(".1.3.6.1.4.1.9.9.23.1.1.1.1.2");
		setSNMP_CDPNEIGHBORPLATFORM(".1.3.6.1.4.1.9.9.23.1.2.1.1.8");
		setSNMP_ALLVLANINFORMATION(".1.3.6.1.4.1.9.9.46.1.3.1.1.2");
		setSNMP_DOT1DBASEPORTIFINDEX(".1.3.6.1.2.1.17.1.4.1.2");
	}
}