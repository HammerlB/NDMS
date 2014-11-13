package at.bmlvs.NDMS.domain.snapshots;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Snapshot
{
	private StringProperty fingerprint;
	private StringProperty name;
	private StringProperty datetime;
	private StringProperty description;
	private StringProperty fullName;
	
	public Snapshot(String name, String desc)
	{
		setName(name);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		setDatetime(dateFormat.format(date));
		
		setFullName(getName()+"("+getDatetime()+")");
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
	
	public final String getDescription()
	{
		if (description != null)
		{
			return description.get();
		}

		return null;
	}

	public final void setDescription(String description)
	{
		this.descriptionProperty().set(description);
	}

	public final StringProperty descriptionProperty()
	{
		if (description == null)
		{
			description = new SimpleStringProperty(null);
		}

		return description;
	}
	
	public final String getFullName()
	{
		if (fullName != null)
		{
			return fullName.get();
		}

		return null;
	}

	public final void setFullName(String fullName)
	{
		this.nameProperty().set(fullName);
	}

	public final StringProperty fullNameProperty()
	{
		if (fullName == null)
		{
			fullName = new SimpleStringProperty(null);
		}

		return fullName;
	}
}
