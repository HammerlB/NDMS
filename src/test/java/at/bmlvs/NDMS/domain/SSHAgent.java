package at.bmlvs.NDMS.domain;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

//	import com.javasrc.jolt.component.linux.model.FileSystem;

/**
 * The SSHAgent allows a Java application to execute commands on a remote server
 * via SSH
 * 
 * @author shaines
 *
 */
public class SSHAgent {
	/**
	 * The hostname (or IP address) of the server to connect to
	 */
	private String hostname;

	/**
	 * The username of the user on that server
	 */
	private String username;

	/**
	 * The password of the user on that server
	 */
	private String password;

	/**
	 * A connection to the server
	 */
	private Connection connection;

	/**
	 * Creates a new SSHAgent
	 * 
	 * @param hostname
	 * @param username
	 * @param password
	 */
	public SSHAgent(String hostname, String username, String password) {
		this.hostname = hostname;
		this.username = username;
		this.password = password;
	}

	/**
	 * Connects to the server
	 * 
	 * @return True if the connection succeeded, false otherwise
	 */
	public boolean connect() throws SSHException {
		try {
			// Connect to the server
			connection = new Connection(hostname);
			connection.connect();

			// Authenticate
			boolean result = connection.authenticateWithPassword(username,
					password);
			System.out.println("Connection result: " + result);
			return result;
		} catch (Exception e) {
			throw new SSHException(
					"An exception occurred while trying to connect to the host: "
							+ hostname + ", Exception=" + e.getMessage());
		}
	}

	/**
	 * Executes the specified command and returns the response from the server
	 * 
	 * @param command
	 *            The command to execute
	 * @return The response that is returned from the server (or null)
	 */
	public String executeCommand(String command) throws SSHException {
		try {
			// Open a session
			Session session = connection.openSession();

			// Execute the command
			session.execCommand(command);

			// Read the results
			StringBuilder sb = new StringBuilder();
			InputStream stdout = new StreamGobbler(session.getStdout());
			BufferedReader br = new BufferedReader(
					new InputStreamReader(stdout));
			String line = br.readLine();
			while (line != null) {
				sb.append(line + "\n");
				line = br.readLine();
			}

			// DEBUG: dump the exit code
			System.out.println("ExitCode: " + session.getExitStatus());

			// Close the session
			session.close();

			// Return the results to the caller
			return sb.toString();
		} catch (Exception e) {
			throw new SSHException(
					"An exception occurred while executing the following command: "
							+ command + ". Exception = " + e.getMessage());
		}
	}

	/**
	 * Logs out from the server
	 * 
	 * @throws SSHException
	 */
	public void logout() throws SSHException {
		try {
			connection.close();
		} catch (Exception e) {
			throw new SSHException(
					"An exception occurred while closing the SSH connection: "
							+ e.getMessage());
		}
	}

	/**
	 * Returns true if the underlying authentication is complete, otherwise
	 * returns false
	 * 
	 * @return
	 */
	public boolean isAuthenticationComplete() {
		return connection.isAuthenticationComplete();
	}

	public static void main(String[] args) {
		
		
		
//		try {
//			SSHAgent sshAgent = new SSHAgent("192.168.1.12", "Herkel",
//					"gwdH_2014");
//			if (sshAgent.connect()) {
//				String diskInfo = sshAgent.executeCommand("show flash");
//				System.out.println("Disk info: " + diskInfo);
//
////				String processInfo = sshAgent.executeCommand("top -b -n 1");
////				System.out.println("Process Info: " + processInfo);
//
//				// Logout
//				sshAgent.logout();
//			}
//		} catch (SSHException e) {
//			e.printStackTrace();
//		}
	}
}
