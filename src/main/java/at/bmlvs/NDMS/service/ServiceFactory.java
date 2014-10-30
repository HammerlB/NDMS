package at.bmlvs.NDMS.service;

public class ServiceFactory
{
	private static PersistenceService persistenceService;
	private static PresentationService presentationService;
	private static AppConfig appConfig;

	public static PersistenceService getPersistenceService()
	{
		return persistenceService;
	}

	public static void setPersistenceService(PersistenceService persistenceService)
	{
		ServiceFactory.persistenceService = persistenceService;
	}

	public static PresentationService getPresentationService()
	{
		return presentationService;
	}

	public static void setPresentationService(PresentationService presentationService)
	{
		ServiceFactory.presentationService = presentationService;
	}

	public static AppConfig getAppConfig()
	{
		return appConfig;
	}

	public static void setAppConfig(AppConfig appConfig)
	{
		ServiceFactory.appConfig = appConfig;
	}
}