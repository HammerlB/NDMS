package at.bmlvs.NDMS.persistence.xml;

import at.bmlvs.NDMS.domain.templates.Templates;
import at.bmlvs.NDMS.persistence.GenericDAO;

public interface TemplatesDAO extends GenericDAO<Templates, String>
{
	public void write(Templates element, String path);

	public void delete(Templates element, String path);

	public Templates read(String path);

	public void update(Templates newelement, Templates oldelement, String path);
}