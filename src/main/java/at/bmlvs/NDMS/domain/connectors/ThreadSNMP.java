package at.bmlvs.NDMS.domain.connectors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.snmp4j.PDU;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.smi.OID;

public class ThreadSNMP extends Thread {

	private SNMPConnector snmp;
	private String host, communityString, oids;
	private boolean isCreated;
	private boolean disconnect;
	private boolean somethingToWalk;
	private boolean showonlylastoidoctet, showonlyvalue;

	public ThreadSNMP(String host, String communityString) {
		this.host = host;
		this.communityString = communityString;
		this.isCreated = false;
		this.disconnect = false;
	}

	public void run() {
		try {
			if (!isCreated) {
				try {
					snmp = new SNMPConnector(host, communityString);
					setCreated(true);
				} catch (Exception e) {
					System.out.println("SNMP: " + e.getMessage() + "\n"
							+ "Reason: " + e.getCause());
				}
			}

			if (somethingToWalk) {
				try {
					walk(oids, showonlylastoidoctet, showonlyvalue);
				} catch (IOException e) {
					System.out.println("SNMP: " + e.getMessage() + "\n"
							+ "Reason: " + e.getCause());
				} finally {
					somethingToWalk = false;
				}
			}

			if (disconnect) {
				try {
					snmp.disconnect();
				} catch (Exception e) {
					System.out.println("SNMP: " + e.getMessage() + "\n"
							+ "Reason: " + e.getCause());
				}
			}
			sleep(100);

		} catch (InterruptedException e) {
			System.out.println("SNMP: " + e.getMessage() + "\n" + "Reason: "
					+ e.getCause());
		}
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getCommunityString() {
		return communityString;
	}

	public void setCommunityString(String communityString) {
		this.communityString = communityString;
	}

	public boolean isDisconnect() {
		return disconnect;
	}

	public void setDisconnect(boolean disconnect) {
		this.disconnect = disconnect;
	}

	public boolean isCreated() {
		return isCreated;
	}

	public void setCreated(boolean isCreated) {
		this.isCreated = isCreated;
	}

	public String getAsString(OID oid) throws IOException {
		return snmp.getAsString(oid);
	}

	public void getAsString(OID oids, ResponseListener listener) {
		snmp.getAsString(oids, listener);
	}

	private ArrayList<String> walk(String oids, boolean showonlylastoidoctet,
			boolean showonlyvalue) throws IOException {
		return snmp.walk(oids, showonlylastoidoctet, showonlyvalue);
	}

	public ResponseEvent get(OID oids[]) throws IOException {
		return snmp.get(oids);
	}

	public List<List<String>> getTableAsStrings(OID[] oids) {
		return snmp.getTableAsStrings(oids);
	}

	public String extractSingleString(ResponseEvent event) {
		return snmp.extractSingleString(event);
	}

	public void doWalk(String oids, boolean showonlylastoidoctet,
			boolean showonlyvalue) {
		this.somethingToWalk = true;
		this.oids = oids;
		this.showonlylastoidoctet = showonlylastoidoctet;
		this.showonlyvalue = showonlyvalue;
	}
}