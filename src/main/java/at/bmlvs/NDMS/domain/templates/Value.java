package at.bmlvs.NDMS.domain.templates;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Value")
@SuppressWarnings("serial")
public class Value
{
	@XStreamAsAttribute
	private String alias;
	@XStreamAsAttribute
	private String value;

	public Value(String alias, String value)
	{
		setAlias(alias);
		setValue(value);
	}

	public String getAlias()
	{
		return alias;
	}

	public void setAlias(String alias)
	{
		this.alias = alias;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	@Override
	public String toString()
	{
		return alias;
	}
}