package at.bmlvs.NDMS.domain.helper;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import at.bmlvs.NDMS.domain.connectors.SSHConnector;

public class IPGrabber {
	public static String grab() throws SocketException, UnknownHostException{
		Enumeration e = NetworkInterface.getNetworkInterfaces();
		while (e.hasMoreElements()) {
			NetworkInterface n = (NetworkInterface) e.nextElement();
			Enumeration ee = n.getInetAddresses();
			while (ee.hasMoreElements()) {
				InetAddress i = (InetAddress) ee.nextElement();
				if (i instanceof Inet4Address && !(i.getHostAddress().equals("127.0.0.1"))) {
					return i.getHostAddress();
				}
			}
		}
		return null;
	}
}
