package at.bmlvs.NDMS.linker;

import java.io.Serializable;

import at.bmlvs.NDMS.domain.templates.Templates;

@SuppressWarnings("serial")
public class TemplatesToPathLinker extends Linker<Templates, String> implements Serializable
{
	public TemplatesToPathLinker(Templates element, String path)
	{
		super(element, path);
	}
}
