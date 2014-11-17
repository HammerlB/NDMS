package at.bmlvs.NDMS.domain.instances;

import at.bmlvs.NDMS.domain.helper.UUIDGenerator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;

public abstract class Instance extends Tab
{
	private StringProperty uuid;
	private StringProperty name;
	
	private ObservableList<Interface> interfaces;
	
	public Instance(String name)
	{
		setUUID(UUIDGenerator.generateUUID());
		setName(name);
		
		setInterfaces(FXCollections.observableArrayList());
	}
	
	public final String getUUID()
	{
		if (uuid != null)
		{
			return uuid.get();
		}

		return null;
	}

	public final void setUUID(String uuid)
	{
		this.uuidProperty().set(uuid);
	}

	public final StringProperty uuidProperty()
	{
		if (uuid == null)
		{
			uuid = new SimpleStringProperty(null);
		}

		return uuid;
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
	
	public ObservableList<Interface> getInterfaces()
	{
		return interfaces;
	}

	public void setInterfaces(ObservableList<Interface> interfaces)
	{
		this.interfaces = interfaces;
	}
	
	public abstract void populateInterfaces();
	
	public abstract void populateInstance();
	
	public void populateAll()
	{
		populateInstance();
		populateInterfaces();
	}
}
