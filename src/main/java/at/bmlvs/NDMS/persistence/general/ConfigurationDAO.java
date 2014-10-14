package at.bmlvs.NDMS.persistence.general;

import at.bmlvs.NDMS.domain.configs.Configuration;
import at.bmlvs.NDMS.persistence.GenericDAO;

public interface ConfigurationDAO extends GenericDAO<Configuration, String>
{
	public void write(Configuration element, String path);

	public void delete(Configuration element, String path);

	public Configuration read(String path);

	public void update(Configuration newelement, Configuration oldelement, String path);
}
