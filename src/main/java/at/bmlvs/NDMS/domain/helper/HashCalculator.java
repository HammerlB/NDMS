package at.bmlvs.NDMS.domain.helper;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCalculator
{
	public static String generateHashFromFile(String filepath)
	{
		MessageDigest md;

		try
		{
			md = MessageDigest.getInstance("SHA-512");

			InputStream is = Files.newInputStream(Paths.get(filepath));

			DigestInputStream dis = new DigestInputStream(is, md);

			while (dis.read() > -1)
			{
				
			}

			byte[] digest = md.digest();
			
			return convertToHex(digest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Converts the given byte[] to a hex string.
	 * 
	 * @param raw
	 *            the byte[] to convert
	 * @return the string the given byte[] represents
	 */
	private static String convertToHex(byte[] raw)
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < raw.length; i++)
		{
			sb.append(Integer.toString((raw[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		return sb.toString();
	}
}
