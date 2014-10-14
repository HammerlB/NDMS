package at.bmlvs.NDMS.domain.connectors;

//import java.io.InputStream;
//import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

//			System.out.print("Username: ");
//			String username = reader.readLine();
			String username = "Herkel";
			pwd.setUsername(username);

//			System.out.print("Password: ");
//			String password = reader.readLine();
			String password = "gwdH_2014";
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
			//COMMANDS
			OutputStream out = session.getOutputStream();
			InputStream in = session.getInputStream();
			String cmd = "enable\n"
					+ "gwd_2014\n"
					+ "show run | include interface\n"
					+ "      ";
			out.write(cmd.getBytes());
			/**
			* Reading from the session InputStream
			*/
			//OUTPUT
			byte buffer[] = new byte[1024];
			int read = in.read(buffer);
			String outputfinal=null;
			while((read = in.read(buffer)) > 0) {
			   String output = new String(buffer, 0, read);
			   outputfinal+=output;
			   System.out.println(output);
			   if(output==" ")
				   break;
			}
			
			System.out.println("Input String matches regex - "+
			Pattern.compile(".xx.").matcher("MxxY").matches());
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
