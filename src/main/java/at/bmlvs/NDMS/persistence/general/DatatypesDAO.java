package at.bmlvs.NDMS.persistence.general;

import at.bmlvs.NDMS.domain.templates.Datatypes;
import at.bmlvs.NDMS.persistence.GenericDAO;

public interface DatatypesDAO extends GenericDAO<Datatypes, String>
{
	public void write(Datatypes element, String path);

	public void delete(Datatypes element, String path);

	public Datatypes read(String path);

	public void update(Datatypes newelement, Datatypes oldelement, String path);
}
