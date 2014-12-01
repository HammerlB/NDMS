package at.bmlvs.NDMS.domain.templates;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("ParameterList")
@SuppressWarnings({ "serial" })
public class ParameterList extends ArrayList<Value>
{
	@XStreamAsAttribute
	private Parameter parent;
	
	@XStreamAsAttribute
	private Value selected;

	public ParameterList(Parameter parent)
	{
		super();
		
		setParent(parent);
		
		setSelected(new Value("", ""));
	}

	/**
	 * @return the parent
	 */
	public Parameter getParent()
	{
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Parameter parent)
	{
		this.parent = parent;
	}

	/**
	 * @return the selected
	 */
	public Value getSelected()
	{
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(Value selected)
	{
		this.selected = selected;
	}

	@Override
	public boolean add(Value arg0)
	{
		setSelected(arg0);
		
		return super.add(arg0);
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public String get(int index)
//	{
//		try
//		{
//			if(super.get(index) != null)
//			{
//				return super.get(index);
//			}
//			else
//			{
//				return (String) "";
//			}
//		}
//		catch (IndexOutOfBoundsException e)
//		{
//			return (String) "";
//		}
//	}
}