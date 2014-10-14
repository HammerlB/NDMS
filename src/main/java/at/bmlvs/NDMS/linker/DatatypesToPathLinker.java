package at.bmlvs.NDMS.linker;

import java.io.Serializable;

import at.bmlvs.NDMS.domain.templates.Datatypes;

@SuppressWarnings("serial")
public class DatatypesToPathLinker extends Linker<Datatypes, String> implements Serializable
{
	public DatatypesToPathLinker(Datatypes element, String path)
	{
		super(element, path);
	}
}