package at.bmlvs.NDMS.domain.snapshots;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Snapshot
{
	private StringProperty fingerprint;
	private StringProperty name;
	private StringProperty datetime;
	private StringProperty content;
	
	public Snapshot(String fingerprint, String name, String datetime, String content)
	{
		setFingerprint(fingerprint);
		setName(name);
		setDatetime(datetime);
		setContent(content);
	}
	
	public final String getName()
	{
		if (name != null)
		{
			return name.get();
		}

		return null;
	}

	public final void setName(String name)
	{
		this.nameProperty().set(name);
	}

	public final StringProperty nameProperty()
	{
		if (name == null)
		{
			name = new SimpleStringProperty(null);
		}

		return name;
	}
	
	public final String getDatetime()
	{
		if (datetime != null)
		{
			return datetime.get();
		}

		return null;
	}

	public final void setDatetime(String datetime)
	{
		this.datetimeProperty().set(datetime);
	}

	public final StringProperty datetimeProperty()
	{
		if (datetime == null)
		{
			datetime = new SimpleStringProperty(null);
		}

		return datetime;
	}

	public final String getFingerprint()
	{
		if (fingerprint != null)
		{
			return fingerprint.get();
		}

		return null;
	}

	public final void setFingerprint(String fingerprint)
	{
		this.fingerprintProperty().set(fingerprint);
	}

	public final StringProperty fingerprintProperty()
	{
		if (fingerprint == null)
		{
			fingerprint = new SimpleStringProperty(null);
		}

		return fingerprint;
	}
	
	public final String getContent()
	{
		if (content != null)
		{
			return content.get();
		}

		return null;
	}

	public final void setContent(String content)
	{
		this.contentProperty().set(content);
	}

	public final StringProperty contentProperty()
	{
		if (content == null)
		{
			content = new SimpleStringProperty(null);
		}

		return content;
	}
}
