package at.bmlvs.NDMS.domain.connectors;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
	// private PasswordAuthenticationClient pwd;
	private SessionChannelClient session;
	private String host;
	private String user;
	private String pass;
	// private InputStream in;
	private OutputStream out;

	// private String outputfinal;
	// private byte[] buffer = new byte[1024];

	public SSHConnector() {
		super();
		this.ssh = new SshClient();
		// this.pwd = new PasswordAuthenticationClient();
		this.version = "";
		this.port = 0;
		this.host = "192.168.1.12";
		this.user = "Herkel";
		this.pass = "gwdH_2014";
	}

	public SSHConnector(String host, String user, String pass, String version,
			int port) {
		super(host, user, pass);
		this.ssh = new SshClient();
		// this.pwd = new PasswordAuthenticationClient();
		this.version = version;
		this.port = port;
		this.host = host;
		this.user = user;
		this.pass = pass;
	}

	public String getSshFingerprint() {
		return ssh.getServerHostKey().getFingerprint();
	}

	public void sendCmd(String cmd) {
		try {
			OutputStream out = session.getOutputStream();
			out.write(cmd.getBytes());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// public void createOutput(byte[] buffer) {
	// int read;
	// try {
	// read = in.read(buffer);
	// outputfinal = "";
	// // for (int i = 0; (read = in.read(buffer)) > 0 && i < maxn; i++) {
	// String output = new String(buffer, 0, read);
	// System.out.println(output);
	// // outputfinal += output;
	// // }
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	@Override
	public void connect() {
		SshClient ssh = new SshClient();
		try {
			ssh.connect("192.168.1.12", new ConsoleKnownHostsKeyVerification());

			PasswordAuthenticationClient pwd = new PasswordAuthenticationClient();

			pwd.setUsername(user);
			pwd.setPassword(pass);

			int result = ssh.authenticate(pwd);
			ssh.getServerHostKey();

			if (result == AuthenticationProtocolState.FAILED)
				System.out.println("The authentication failed");

			if (result == AuthenticationProtocolState.PARTIAL)
				System.out.println("The authentication succeeded but another"
						+ "authentication is required");

			if (result == AuthenticationProtocolState.COMPLETE)
				System.out.println("The authentication is complete");
			SessionChannelClient session = ssh.openSessionChannel();
			session.startShell();
			// COMMANDS
			OutputStream out = session.getOutputStream();
			InputStream in = session.getInputStream();
			String cmd = "enable\n" + "gwd_2014\n" + "conf t\n"
					+ "hostname test1\n";
			out.write(cmd.getBytes());
			byte buffer[] = new byte[1024];
			int read = 0;
			String outputfinal="Incoming:";
			int z=0;
			System.out.println(ssh.getServerHostKey().getFingerprint());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void disconnect() {
		try {
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// @Override
	// public void run() {
	// int read;
	// try {
	// read = in.read(buffer);
	// // outputfinal = "";
	// // for (int i=0;(read = in.read(buffer)) > 0&&i<maxn;i++) {
	// String output = new String(buffer, 0, read);
	// // outputfinal += output;
	// System.out.println(output);
	// // }
	// } catch (IOException | NullPointerException e) {
	// System.out.println("Nothing to print!");
	// }
	// }

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