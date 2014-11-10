/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package at.bmlvs.NDMS.domain.connectors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.commons.net.tftp.TFTP;
import org.apache.commons.net.tftp.TFTPClient;

/***
 * This is an example of a simple Java tftp client. Notice how all of the code
 * is really just argument processing and error handling.
 * <p>
 * Usage: tftp [options] hostname localfile remotefile hostname - The name of
 * the remote host localfile - The name of the local file to send or the name to
 * use for the received file remotefile - The name of the remote file to receive
 * or the name for the remote server to use to name the local file being sent.
 * options: (The default is to assume -r -b) -s Send a local file -r Receive a
 * remote file -a Use ASCII transfer mode -b Use binary transfer mode
 * <p>
 ***/
public class ConnectionTFTP extends FileTransferConnector
{
	private TFTPClient tftpc;

	private int transfermode;
	private int timeout;

	private String localfile;
	private String remotefile;

	private boolean ascii_transfermode;
	private boolean binary_transfermode;
	
	/*
	 * "Usage: tftp [options] hostname localfile remotefile\n\n" +
	 * "hostname   - The name of the remote host\n" +
	 * "localfile  - The name of the local file to send or the name to use for\n"
	 * + "\tthe received file\n" +
	 * "remotefile - The name of the remote file to receive or the name for\n" +
	 * "\tthe remote server to use to name the local file being sent.\n\n" +
	 * "options: (The default is to assume -r -b)\n" +
	 * "\t-s Send a local file\n" + "\t-r Receive a remote file\n" +
	 * "\t-a Use ASCII transfer mode\n" + "\t-b Use binary transfer mode\n";
	 */
	public ConnectionTFTP(String host, String localfile, String remotefile)
	{
		super(host);
		setLocalfile(localfile);
		setRemotefile(remotefile);
		setTftpc(new TFTPClient());
		setTransfermode(TFTP.ASCII_MODE);
		setTimeout(60000);
	}

	@Override
	public void connect()
	{
		tftpc.setDefaultTimeout(getTimeout());

		// Open local socket
		try
		{
			tftpc.open();
		}
		catch (SocketException e)
		{
			System.err.println("Error: could not open local UDP socket.");
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

	@Override
	public void disconnect()
	{
		tftpc.close();
	}

	@Override
	public void send()
	{
		// We haven't closed the local file yet.
		boolean closed = false;
				
		// We're sending a file
		FileInputStream input = null;

		// Try to open local file for reading
		try
		{
			input = new FileInputStream(getLocalfile());
		}
		catch (IOException e)
		{
			disconnect();
			System.err.println("Error: could not open local file for reading.");
			System.err.println(e.getMessage());
			System.exit(1);
		}

		// Try to send local file via TFTP
		try
		{
			tftpc.sendFile(getRemotefile(), getTransfermode(), input, getHost());
		}
		catch (UnknownHostException e)
		{
			System.err.println("Error: could not resolve hostname.");
			System.err.println(e.getMessage());
			System.exit(1);
		}
		catch (IOException e)
		{
			System.err
					.println("Error: I/O exception occurred while sending file.");
			System.err.println(e.getMessage());
			System.exit(1);
		}
		finally
		{
			// Close local socket and input file
			disconnect();
			
			try
			{
				if (input != null)
				{
					input.close();
				}
				closed = true;
			}
			catch (IOException e)
			{
				closed = false;
				System.err.println("Error: error closing file.");
				System.err.println(e.getMessage());
			}
		}

		if (!closed)
		{
			System.exit(1);
		}
	}

	@Override
	public void receive()
	{
		// We haven't closed the local file yet.
		boolean closed = false;

		FileOutputStream output = null;
		File file;
		file = new File(getLocalfile());

		// If file exists, don't overwrite it.
		if (file.exists())
		{
			System.err.println("Error: " + getLocalfile() + " already exists.");
			System.exit(1);
		}

		// Try to open local file for writing
		try
		{
			output = new FileOutputStream(file);
		}
		catch (IOException e)
		{
			disconnect();
			System.err.println("Error: could not open local file for writing.");
			System.err.println(e.getMessage());
			System.exit(1);
		}

		// Try to receive remote file via TFTP
		try
		{
			tftpc.receiveFile(getRemotefile(), getTransfermode(), output,
					getHost());
		}
		catch (UnknownHostException e)
		{
			System.err.println("Error: could not resolve hostname.");
			System.err.println(e.getMessage());
			System.exit(1);
		}
		catch (IOException e)
		{
			System.err
					.println("Error: I/O exception occurred while receiving file.");
			System.err.println(e.getMessage());
			System.exit(1);
		}
		finally
		{
			// Close local socket and output file
			disconnect();
			
			try
			{
				if (output != null)
				{
					output.close();
				}
				closed = true;
			}
			catch (IOException e)
			{
				closed = false;
				System.err.println("Error: error closing file.");
				System.err.println(e.getMessage());
			}
		}

		if (!closed)
		{
			System.exit(1);
		}
	}

	public boolean isAscii_transfermode()
	{
		return ascii_transfermode;
	}

	public void setAscii_transfermode(boolean ascii_transfermode)
	{
		this.ascii_transfermode = ascii_transfermode;
	}

	public boolean isBinary_transfermode()
	{
		return binary_transfermode;
	}

	public void setBinary_transfermode(boolean binary_transfermode)
	{
		this.binary_transfermode = binary_transfermode;
	}

	public TFTPClient getTftpc()
	{
		return tftpc;
	}

	public void setTftpc(TFTPClient tftpc)
	{
		this.tftpc = tftpc;
	}

	public int getTransfermode()
	{
		return transfermode;
	}

	public void setTransfermode(int transfermode)
	{
		this.transfermode = transfermode;
	}

	public int getTimeout()
	{
		return timeout;
	}

	public void setTimeout(int timeout)
	{
		this.timeout = timeout;
	}

	public String getLocalfile()
	{
		return localfile;
	}

	public void setLocalfile(String localfile)
	{
		this.localfile = localfile;
	}

	public String getRemotefile()
	{
		return remotefile;
	}

	public void setRemotefile(String remotefile)
	{
		this.remotefile = remotefile;
	}
}