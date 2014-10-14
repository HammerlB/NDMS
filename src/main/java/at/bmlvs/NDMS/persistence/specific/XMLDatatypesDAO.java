package at.bmlvs.NDMS.persistence.specific;

import java.io.Serializable;

import at.bmlvs.NDMS.domain.templates.Datatypes;
import at.bmlvs.NDMS.persistence.general.DatatypesDAO;

@SuppressWarnings("serial")
public class XMLDatatypesDAO implements DatatypesDAO, Serializable
{
	public void write(Datatypes element, String path)
	{
		// TODO Auto-generated method stub
	}
	public void delete(Datatypes element, String path)
	{
		// TODO Auto-generated method stub
	}

	public Datatypes read(String path)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Datatypes newelement, Datatypes oldelement, String path)
	{
		// TODO Auto-generated method stub
	}
}