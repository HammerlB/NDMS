package at.bmlvs.NDMS.service;

import at.bmlvs.NDMS.domain.Instances;
import at.bmlvs.NDMS.domain.templates.Templates;
import at.bmlvs.NDMS.domain.snapshots.Snapshots;

public class DomainService
{
	private static Instances instances;
	
	public static Instances getInstances()
	{
		return instances;
	}

	public static void setInstances(Instances instances)
	{
		DomainService.instances = instances;
	}
}