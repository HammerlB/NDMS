package at.bmlvs.NDMS.domain;

import at.bmlvs.NDMS.domain.connectors.TerminalConnector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * 
 * @author GWDH
 *
 */
public class Jsch_connector{
	private String version;
	private int port;
	private String host,username,password;

	public Jsch_connector(String host, String username, String password,
			String version, int port) {
//		super(host, username, password);
		setHost(host);
		setUsername(username);
		setPassword(password);
		setVersion(version);
		setPort(port);
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	/**
  * 
  */
	public void connect() {
		try {
			// http://www.jcraft.com/jsch/examples/Shell.java.html
			// http://stackoverflow.com/questions/782178/how-do-i-convert-a-string-to-an-inputstream-in-java
			JSch jsch = new JSch();
			Session session = jsch.getSession(getUsername(), getHost(),
					getPort());
			session.setPassword(getPassword());
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(30000);
			Channel channel = session.openChannel("shell");
			channel.setInputStream(System.in);
			channel.setOutputStream(System.out);
			channel.connect(3 * 1000);
			// channel.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public static void main(String[] args){
		Jsch_connector jsch = new Jsch_connector("192.168.1.12", "Herkel", "gwdH_2014",
				"sd", 22);
		jsch.connect();
	}
}