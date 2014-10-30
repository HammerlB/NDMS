package at.bmlvs.NDMS.service;

import at.bmlvs.NDMS.presentation.MainWindowController;

public class PresentationService
{
	private static MainWindowController mainWindowController;

	public static MainWindowController getMainWindowController()
	{
		return mainWindowController;
	}

	public static void setMainWindowController(
			MainWindowController mainWindowController)
	{
		PresentationService.mainWindowController = mainWindowController;
	}
}