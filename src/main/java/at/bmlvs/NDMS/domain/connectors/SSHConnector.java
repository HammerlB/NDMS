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

/**
 * 
 * @author GWDH
 *
 */
public class SSHConnector extends TerminalConnector {
	private String version;
	private int port;
	private String out;

	public SSHConnector() {
		super();
		setVersion("");
		setPort(0);
	}

	public SSHConnector(String host, String username, String password,
			String version, int port) {
		super(host, username, password);
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

	public void setInput(String input) {
		
	}

	public String getOutput() {
		
	}

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
			channel.setInputStream(input);
			channel.setOutputStream(output);
			channel.connect(3 * 1000);
			// channel.disconnect();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void disconnect() {

	}
}