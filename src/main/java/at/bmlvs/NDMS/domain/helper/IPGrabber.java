package at.bmlvs.NDMS.domain.helper;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import at.bmlvs.NDMS.domain.connectors.SSHConnector;

public class IPGrabber {
	public static void main(String[] args) {
		try {
			Enumeration e = NetworkInterface.getNetworkInterfaces();
			while (e.hasMoreElements()) {
				NetworkInterface n = (NetworkInterface) e.nextElement();
				Enumeration ee = n.getInetAddresses();
				while (ee.hasMoreElements()) {
					InetAddress i = (InetAddress) ee.nextElement();
					if (i instanceof Inet4Address) {
						System.out.println(i.getHostAddress());
					}
				}
			}

			SSHConnector ssh = new SSHConnector("192.168.1.12", "Herkel",
					"gwdH_2014", "gwd_2014");
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
	}
}
