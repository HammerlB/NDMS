package at.bmlvs.NDMS.domain.connectors;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.sshtools.j2ssh.*;
import com.sshtools.j2ssh.authentication.*;
import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.transport.*;

public class SshExample {
	private static BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in));

	public static void main(String args[]) {
		try {
			SshClient ssh = new SshClient();
			System.out.print("Host to connect: ");
			ssh.connect("192.168.1.12", new ConsoleKnownHostsKeyVerification());
			PasswordAuthenticationClient pwd = new PasswordAuthenticationClient();

			System.out.print("Username: ");
			String username = reader.readLine();
			pwd.setUsername(username);

			System.out.print("Password: ");
			String password = reader.readLine();
			pwd.setPassword(password);

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
