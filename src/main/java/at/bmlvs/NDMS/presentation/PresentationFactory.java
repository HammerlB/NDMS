package at.bmlvs.NDMS.presentation;

public class PresentationFactory
{
	private static MainWindow mainWindow;
	private static TabbedWindow tabbedWindow;

	public static MainWindow getMainWindow()
	{
		return mainWindow;
	}

	public static void setMainWindow(MainWindow mainWindow)
	{
		PresentationFactory.mainWindow = mainWindow;
	}

	public static TabbedWindow getTabbedWindow()
	{
		return tabbedWindow;
	}

	public static void setTabbedWindow(TabbedWindow tabbedWindow)
	{
		PresentationFactory.tabbedWindow = tabbedWindow;
	}
}
