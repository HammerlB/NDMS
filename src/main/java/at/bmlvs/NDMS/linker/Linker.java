package at.bmlvs.NDMS.linker;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Linker<T, P> implements Serializable
{
	private T element;
	private P path;

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
}
