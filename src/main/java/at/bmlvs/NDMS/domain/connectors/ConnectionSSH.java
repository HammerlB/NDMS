package at.bmlvs.NDMS.domain.connectors;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.transport.ConsoleKnownHostsKeyVerification;
import com.sshtools.j2ssh.transport.IgnoreHostKeyVerification;

/**
 * 
 * @author GWDH
 *
 */
public class ConnectionSSH extends TerminalConnector implements Runnable {
	private String version;
	private int port;
	private SshClient ssh;
	private SessionChannelClient session;
	private String host;
	private String user;
	private String pass;
	private String fingerprint;
	private InputStream in;
	private OutputStream out;
	private boolean connected;
	private byte buffer[] = new byte[1024];
	private int read = 0;

	// public SSHConnector() {
	// super();
	// this.ssh = new SshClient();
	// // this.pwd = new PasswordAuthenticationClient();
	// this.version = "";
	// this.port = 0;
	// this.host = "192.168.1.12";
	// this.user = "Herkel";
	// this.pass = "gwdH_2014";
	// }

	public ConnectionSSH(String host, String user, String pass
	// ,String version,int port
	) {
		super(host, user, pass);
		this.ssh = new SshClient();
		this.version = version;
		this.port = port;
		this.host = host;
		this.user = user;
		this.pass = pass;
		connected = false;
	}

	public String getSSHFingerprint() {
		return fingerprint;
	}

	public void setSSHFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}

	public void sendCmd(String cmd) {
		try {
			out.write(cmd.getBytes());
		} catch (IOException e) {
			System.out.println("SendCMD: " + e.getMessage());
		}

		// byte buffer[] = new byte[cmd.getBytes().length];
		// int read = 0;
		// for (int i = 0; i < 100; i++) {
		// try {
		// read = in.read(buffer);
		// } catch (IOException e) {
		// System.out.println("ReadCMD: " + e.getMessage());
		// }
		// }
	}

	@Override
	public void connect() throws Exception {
		SshClient ssh = new SshClient();

		ssh.connect(host, new IgnoreHostKeyVerification());
		PasswordAuthenticationClient pwd = new PasswordAuthenticationClient();

		pwd.setUsername(user);
		pwd.setPassword(pass);
		setSSHFingerprint(ssh.getServerHostKey().getFingerprint());
		int result = ssh.authenticate(pwd);

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
		out = session.getOutputStream();
		in = session.getInputStream();
	}

	@Override
	public void disconnect() throws Exception {
		// session.close();
		ssh.disconnect();
	}

	public InputStream getIn() {
		return in;
	}

	public void setIn(InputStream in) {
		this.in = in;
	}

	public OutputStream getOut() {
		return out;
	}

	public void setOut(OutputStream out) {
		this.out = out;
	}

	public void run() {
		try {
			while (true) {
				read = in.read(buffer);
			}
		} catch (IOException e) {
			System.out.println("Reader has stopped!");
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
	// System.out.println(output); // // }
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