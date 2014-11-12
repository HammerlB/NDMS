package at.bmlvs.NDMS.domain.snapshots;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Snapshot
{
	private StringProperty fingerprint;
	private StringProperty name;
	private StringProperty datetime;
	
	public Snapshot(String name, String datetime)
	{
		setName(name);
		setDatetime(datetime);
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
}
