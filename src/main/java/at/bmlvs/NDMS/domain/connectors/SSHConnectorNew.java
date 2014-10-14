package at.bmlvs.NDMS.domain.connectors;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.sshtools.j2ssh.io.IOStreamConnector;
import com.sshtools.j2ssh.io.IOStreamConnectorState;
import com.sshtools.j2ssh.connection.*;

import com.sshtools.j2ssh.SshClient;

import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.session.SessionChannelClient;

import com.sshtools.j2ssh.configuration.SshConnectionProperties;

import com.sshtools.j2ssh.transport.ConsoleHostKeyVerification;

public class SSHConnectorNew {
	// import org.apache.log4j.BasicConfigurator;
	// import org.apache.log4j.PatternLayout;
	// import org.apache.log4j.RollingFileAppender;

	SshClient ssh = null;
	SshConnectionProperties properties = null;
	SessionChannelClient session = null;

	public SSHConnectorNew(String hostName, String userName, String passwd) {

		try {
			// Make a client connection
			ssh = new SshClient();
			properties = new SshConnectionProperties();
			properties.setHost(hostName);

			// Connect to the host
			ssh.connect(properties, new ConsoleHostKeyVerification());

			// Create a password authentication instance
			PasswordAuthenticationClient pwd = new PasswordAuthenticationClient();

			pwd.setUsername(userName);
			pwd.setPassword(passwd);

			// Try the authentication
			int result = ssh.authenticate(pwd);

			// Evaluate the result
			if (result == AuthenticationProtocolState.COMPLETE) {

				System.out.println("Connection Authenticated");
			}
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}

	}// end of method.

	public String execCmd(String cmd) {
		String theOutput = "";
		try {
			// The connection is authenticated we can now do some real work!
			session = ssh.openSessionChannel();

			if (session.executeCommand(cmd)) {
				IOStreamConnector output = new IOStreamConnector();
				java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
				output.connect(session.getInputStream(), bos);
				session.getState().waitForState(ChannelState.CHANNEL_CLOSED);
				theOutput = bos.toString();
			}
			// else
			// throw Exception("Failed to execute command : " + cmd);
			// System.out.println("Failed to execute command : " + cmd);
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}

		return theOutput;
	}

}
