package at.bmlvs.NDMS.domain.templates;

import java.util.ArrayList;

public class Snippet
{
	private String name;
	private String prev;
	private String next;
	
	private ArrayList<Section> sections;
	
	public Snippet(String name, String prev, String next)
	{
		setName(name);
		setPrev(prev);
		setNext(next);
		
		setSections(new ArrayList<Section>());
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPrev()
	{
		return prev;
	}

	public void setPrev(String prev)
	{
		this.prev = prev;
	}

	public String getNext()
	{
		return next;
	}

	public void setNext(String next)
	{
		this.next = next;
	}

	public ArrayList<Section> getSections()
	{
		return sections;
	}

	public void setSections(ArrayList<Section> sections)
	{
		this.sections = sections;
	}
}