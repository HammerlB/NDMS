package at.bmlvs.NDMS.service;

import at.bmlvs.NDMS.domain.Instances;
import at.bmlvs.NDMS.domain.templates.Templates;

public class DomainService
{
	private static Instances instances;
	private static Templates templates;

	public static Instances getInstances()
	{
		return instances;
	}

	public static void setInstances(Instances instances)
	{
		DomainService.instances = instances;
	}

	public static Templates getTemplates()
	{
		return templates;
	}

	public static void setTemplates(Templates templates)
	{
		DomainService.templates = templates;
	}
}
