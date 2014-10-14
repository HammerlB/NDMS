package at.bmlvs.NDMS.service;

public class ServiceFactory
{
	private static PersistenceService persistenceService;

	public static PersistenceService getPersistenceService()
	{
		return persistenceService;
	}

	public static void setPersistenceService(PersistenceService persistenceService)
	{
		ServiceFactory.persistenceService = persistenceService;
	}
}