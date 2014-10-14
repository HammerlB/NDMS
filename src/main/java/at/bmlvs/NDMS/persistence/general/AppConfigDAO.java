package at.bmlvs.NDMS.persistence.general;

import at.bmlvs.NDMS.persistence.GenericDAO;
import at.bmlvs.NDMS.service.AppConfig;

public interface AppConfigDAO extends GenericDAO<AppConfig, String>
{
	public void write(AppConfig element, String path);

	public void delete(AppConfig element, String path);

	public AppConfig read(String path);

	public void update(AppConfig newelement, AppConfig oldelement, String path);
}