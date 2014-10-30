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
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;

public class SNMPConnector
{
	private String host;

	private Snmp SNMPClient;

	public SNMPConnector(String host)
	{
		setHost(host);
		
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
		target.setCommunity(new OctetString("public"));
		target.setAddress(targetAddress);
		target.setRetries(2);
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