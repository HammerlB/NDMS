package at.bmlvs.NDMS.service;

import at.bmlvs.NDMS.presentation.MainWindow;
import at.bmlvs.NDMS.presentation.TabbedWindow;

public class PresentationService
{
	private static MainWindow mainWindow;
	private static TabbedWindow tabbedWindow;

	public static MainWindow getMainWindow()
	{
		return mainWindow;
	}

	public static void setMainWindow(MainWindow mainWindow)
	{
		PresentationService.mainWindow = mainWindow;
	}

	public static TabbedWindow getTabbedWindow()
	{
		return tabbedWindow;
	}

	public static void setTabbedWindow(TabbedWindow tabbedWindow)
	{
		PresentationService.tabbedWindow = tabbedWindow;
	}
}
