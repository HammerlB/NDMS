package at.bmlvs.NDMS.service;

import at.bmlvs.NDMS.domain.Instances;

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
