package at.bmlvs.NDMS.linker;

import java.io.Serializable;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

@SuppressWarnings("serial")
public abstract class Linker<T, P> implements Serializable
{
	private T element;
	private P path;
	private BooleanProperty changed;

	public Linker(T element, P path)
	{
		setElement(element);
		setPath(path);
	}

	public T getElement()
	{
		return element;
	}

	public void setElement(T element)
	{
		this.element = element;
	}

	public P getPath()
	{
		return path;
	}

	public void setPath(P path)
	{
		this.path = path;
	}
	
	public final boolean wasChanged()
	{
		if (changed != null)
		{
			return changed.get();
		}

		return false;
	}

	public final void setChanged(boolean changed)
	{
		this.changedProperty().set(changed);
	}

	public final BooleanProperty changedProperty()
	{
		if (changed == null)
		{
			changed = new SimpleBooleanProperty();
		}

		return changed;
	}
}