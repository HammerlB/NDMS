package at.bmlvs.NDMS.persistence.general;

import at.bmlvs.NDMS.domain.templates.Template;
import at.bmlvs.NDMS.persistence.GenericDAO;

public interface TemplateDAO extends GenericDAO<Template, String>
{
	public void write(Template element, String path);

	public void delete(Template element, String path);

	public Template read(String path);

	public void update(Template newelement, Template oldelement, String path);
}