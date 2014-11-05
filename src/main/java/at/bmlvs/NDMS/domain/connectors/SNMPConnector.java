package at.bmlvs.NDMS.domain.connectors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.Null;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;
import org.snmp4j.util.TreeEvent;
import org.snmp4j.util.TreeUtils;

public class SNMPConnector
{
	private String host;
	private String communityString;

	private Snmp SNMPClient;

	public SNMPConnector(String host, String communityString)
	{
		setHost(host);
		setCommunityString(communityString);

		try
		{
			connect();
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	public String getHost()
	{
		return host;
	}

	public void setHost(String host)
	{
		this.host = host;
	}

	public String getCommunityString()
	{
		return communityString;
	}

	public void setCommunityString(String communityString)
	{
		this.communityString = communityString;
	}

	public Snmp getSNMPClient()
	{
		return SNMPClient;
	}

	public void setSNMPClient(Snmp sNMPClient)
	{
		SNMPClient = sNMPClient;
	}

	public void connect() throws IOException
	{
		TransportMapping transportMapping = new DefaultUdpTransportMapping();
		setSNMPClient(new Snmp(transportMapping));

		// Do not forget this line!
		transportMapping.listen();
	}

	// Since snmp4j relies on asynch req/resp we need a listener
	// for responses which should be closed
	public void disconnect() throws IOException
	{
		SNMPClient.close();
	}

	public String getAsString(OID oid) throws IOException
	{
		ResponseEvent event = get(new OID[] { oid });
		return event.getResponse().get(0).getVariable().toString();
	}

	public void getAsString(OID oids, ResponseListener listener)
	{
		try
		{
			SNMPClient.send(getPDU(new OID[] { oids }), getTarget(), null,
					listener);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<String> walk(String oids, boolean showonlylastoidoctet, boolean showonlyvalue) throws IOException
	{
		ArrayList<String> toreturn = new ArrayList<String>();
		
		OID oid = null;
		try
		{
			oid = new OID(oids);
		}
		catch (RuntimeException ex)
		{
			System.out.println("OID is not specified correctly.");
			System.exit(1);
		}

		TreeUtils treeUtils = new TreeUtils(SNMPClient, new DefaultPDUFactory());
		List<TreeEvent> events = treeUtils.getSubtree(getTarget(), oid);
		if (events == null || events.size() == 0)
		{
			System.out.println("No result returned.");
			System.exit(1);
		}

		// Get snmpwalk result.
		for (TreeEvent event : events)
		{
			if (event != null)
			{
				if (event.isError())
				{
					System.err.println("oid [" + oid + "] "
							+ event.getErrorMessage());
				}

				VariableBinding[] varBindings = event.getVariableBindings();
				if (varBindings == null || varBindings.length == 0)
				{
					toreturn.add("No result returned.");
				}
				for (VariableBinding varBinding : varBindings)
				{
					if(showonlyvalue == true)
					{
						toreturn.add("" + varBinding.getVariable());
					}
					else
					{
						if(showonlylastoidoctet == true)
						{
							String val = "" + varBinding.getOid();
							String[] parts = val.split("\\.");
							toreturn.add(parts[parts.length-1] + " : " + varBinding.getVariable());
						}
						else
						{
							toreturn.add(varBinding.getOid() + " : "
									+ varBinding.getVariable().getSyntaxString()
									+ " : " + varBinding.getVariable());
						}
					}
				}
			}
		}
		
		return toreturn;
	}

	private PDU getPDU(OID oids[])
	{
		PDU pdu = new PDU();
		for (OID oid : oids)
		{
			pdu.add(new VariableBinding(oid));
		}

		pdu.setType(PDU.GET);
		return pdu;
	}

	public ResponseEvent get(OID oids[]) throws IOException
	{
		ResponseEvent event = SNMPClient.send(getPDU(oids), getTarget(), null);
		if (event != null)
		{
			return event;
		}
		throw new RuntimeException("GET timed out");
	}

	private Target getTarget()
	{
		Address targetAddress = GenericAddress.parse(getHost());
		CommunityTarget target = new CommunityTarget();
		target.setCommunity(new OctetString(getCommunityString()));
		target.setAddress(targetAddress);
		target.setRetries(3);
		target.setTimeout(1500);
		target.setVersion(SnmpConstants.version2c);
		return target;
	}

	/**
	 * Normally this would return domain objects or something else than this...
	 */
	public List<List<String>> getTableAsStrings(OID[] oids)
	{
		TableUtils tUtils = new TableUtils(SNMPClient, new DefaultPDUFactory());

		@SuppressWarnings("unchecked")
		List<TableEvent> events = tUtils
				.getTable(getTarget(), oids, null, null);

		List<List<String>> list = new ArrayList<List<String>>();
		for (TableEvent event : events)
		{
			if (event.isError())
			{
				throw new RuntimeException(event.getErrorMessage());
			}
			List<String> strList = new ArrayList<String>();
			list.add(strList);
			for (VariableBinding vb : event.getColumns())
			{
				strList.add(vb.getVariable().toString());
			}
		}
		return list;
	}

	public String extractSingleString(ResponseEvent event)
	{
		return event.getResponse().get(0).getVariable().toString();
	}
}