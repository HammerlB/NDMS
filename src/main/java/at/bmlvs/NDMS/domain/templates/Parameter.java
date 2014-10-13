package at.bmlvs.NDMS.domain.templates;

public class Parameter
{
	private int id;
	private String name;
	private Datatype type;
	private String value;
	
	public Parameter(int id, String name, Datatype type, String value)
	{
		setId(id);
		setName(name);
		setType(type);
		setValue(value);
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Datatype getType()
	{
		return type;
	}

	public void setType(Datatype type)
	{
		this.type = type;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
}