package at.bmlvs.NDMS.domain;

import at.bmlvs.NDMS.domain.connectors.TFTPConnector;

public class TFTPConnectorTest {

	public static void main(String[] args) {
		TFTPConnector tftp = new TFTPConnector("192.168.1.12", "remconf.txt", "config.text");
		tftp.connect();
		tftp.receive();
		tftp.disconnect();
	}
}