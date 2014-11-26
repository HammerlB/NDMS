package at.bmlvs.NDMS.domain.templates;

import java.util.ArrayList;

@SuppressWarnings({ "serial", "hiding" })
public class ParameterList<String> extends ArrayList<String>
{
	private Parameter parent;
	
	private String selected;

	@SuppressWarnings("unchecked")
	public ParameterList(Parameter parent)
	{
		super();
		
		setParent(parent);
		
		setSelected((String) "");
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
	public String getSelected()
	{
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(String selected)
	{
		this.selected = selected;
	}

	@Override
	public boolean add(String arg0)
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