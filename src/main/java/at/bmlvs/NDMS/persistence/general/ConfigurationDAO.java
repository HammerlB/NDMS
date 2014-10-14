package at.bmlvs.NDMS.persistence.general;

import at.bmlvs.NDMS.persistence.GenericDAO;

public interface ConfigurationDAO extends GenericDAO<String, String>
{
	public void write(String element, String path);

	public void delete(String element, String path);

	public String read(String path);

	public void update(String newelement, String oldelement, String path);
}
