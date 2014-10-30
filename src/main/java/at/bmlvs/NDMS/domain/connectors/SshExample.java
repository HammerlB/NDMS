package at.bmlvs.NDMS.domain.connectors;

//import java.io.InputStream;
//import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Pattern;

import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.transport.ConsoleKnownHostsKeyVerification;

public class SshExample {
	// private static BufferedReader reader = new BufferedReader(
	// new InputStreamReader(System.in));
	private static SessionChannelClient session;

	public static void connect() {
		try {
			SshClient ssh = new SshClient();
			ssh.connect("192.168.1.12", new ConsoleKnownHostsKeyVerification());
			PasswordAuthenticationClient pwd = new PasswordAuthenticationClient();

			// System.out.print("Username: ");
			// String username = reader.readLine();
			String username = "Herkel";
			pwd.setUsername(username);

			// System.out.print("Password: ");
			// String password = reader.readLine();
			String password = "gwdH_2014";
			pwd.setPassword(password);

			int result = ssh.authenticate(pwd);
			ssh.getServerHostKey();

			if (result == AuthenticationProtocolState.FAILED)
				System.out.println("The authentication failed");

			if (result == AuthenticationProtocolState.PARTIAL)
				System.out.println("The authentication succeeded but another"
						+ "authentication is required");

			if (result == AuthenticationProtocolState.COMPLETE)
				System.out.println("The authentication is complete");
			session = ssh.openSessionChannel();
			session.startShell();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void send(String cmd) {
		OutputStream out = session.getOutputStream();
		InputStream in = session.getInputStream();
		try {
			out.write(cmd.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void disconnect(){
		try {
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws IOException {
		connect();
		send("enable\n"
			+ "gwd_2014\n"
			+ "conf t\n"
			+ "hostname test\n");
		

		// // OUTPUT
		// byte buffer[] = new byte[1024];
		// int read = 0;
		// String outputfinal = "Incoming:";
		// int z = 0;
		// System.out.println(ssh.getServerHostKey().getFingerprint());
		// while ((read = in.read(buffer)) >= 0) {
		// String output = new String(buffer, 0, read);
		// outputfinal += output;
		// // System.out.println(output);
		// // if(output==" ")
		// // break;
		// }
		// System.out.println(outputfinal);
		// // System.out.println("Input String matches regex - "+
		// // Pattern.compile(".xx.").matcher("MxxY").matches());

		

	}
}
