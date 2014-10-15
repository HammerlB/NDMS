package at.bmlvs.NDMS.domain.connectors;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.ReaderInputStream;
import org.apache.commons.io.output.WriterOutputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.transport.ConsoleKnownHostsKeyVerification;
import com.sshtools.j2ssh.transport.InvalidHostFileException;

/**
 * 
 * @author GWDH
 *
 */
public class SSHConnector extends TerminalConnector {
	private String version;
	private int port;
	private SshClient ssh;
	private PasswordAuthenticationClient pwd;
	private SessionChannelClient session;
	private String host;
	private String user;
	private String pass;
	private InputStream in;
	private OutputStream out;
	private String outputfinal;

	public SSHConnector() {
		super();
		this.ssh = new SshClient();
		this.pwd = new PasswordAuthenticationClient();
		setVersion("");
		setPort(0);
		setHost("192.168.1.12");
		setUser("Herkel");
		setPass("gwdH_2014");
	}

	public SSHConnector(String host, String user, String pass, String version,
			int port) {
		super(host, user, pass);
		this.ssh = new SshClient();
		this.pwd = new PasswordAuthenticationClient();
		setVersion(version);
		setPort(port);
		setHost(host);
		setUser(user);
		setPass(pass);
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
		pwd.setUsername(this.user);
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
		pwd.setPassword(this.pass);
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

	public String getSshFingerprint() {
		return ssh.getServerHostKey().getFingerprint();
	}

	public void sendCmd(String cmd) {
		try {
			out.write(cmd.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createOutput(byte[] buffer,int maxn) {
		int read;
		try {
			read = in.read(buffer);
			outputfinal = null;
			for (int i=0;(read = in.read(buffer)) > 0&&i<maxn;i++) {
				String output = new String(buffer, 0, read);
				outputfinal += output;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getOutput(){
		return this.outputfinal;
	}

	public void connect() {
		try {
			ssh.connect(host, new ConsoleKnownHostsKeyVerification());

			int result = ssh.authenticate(pwd);
			ssh.getServerHostKey();

			if (result == AuthenticationProtocolState.FAILED)
				System.out.println("The authentication failed");

			if (result == AuthenticationProtocolState.PARTIAL)
				System.out.println("The authentication succeeded but another"
						+ "authentication is required");

			if (result == AuthenticationProtocolState.COMPLETE)
				System.out.println("The authentication is complete");

			this.session = ssh.openSessionChannel();
			session.startShell();
			in = session.getInputStream();
			out = session.getOutputStream();
		} catch (InvalidHostFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// public void connect() {
		// try {
		// // http://www.jcraft.com/jsch/examples/Shell.java.html
		// //
		// http://stackoverflow.com/questions/782178/how-do-i-convert-a-string-to-an-inputstream-in-java
		// JSch jsch = new JSch();
		// Session session = jsch.getSession(getUsername(), getHost(),
		// getPort());
		// session.setPassword(getPassword());
		// session.setConfig("StrictHostKeyChecking", "no");
		// session.connect(30000);
		// Channel channel = session.openChannel("shell");
		// channel.setInputStream(input);
		// channel.setOutputStream(output);
		// channel.connect(3 * 1000);
		// // channel.disconnect();
		// } catch (Exception e) {
		// System.err.println(e.getMessage());
		// }
		// }
}