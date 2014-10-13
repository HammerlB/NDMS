package at.bmlvs.NDMS.presentation;

public class PresentationFactory
{
	private static MainWindow mainWindow;

	public static MainWindow getMainWindow()
	{
		return mainWindow;
	}

	public static void setMainWindow(MainWindow mainWindow)
	{
		PresentationFactory.mainWindow = mainWindow;
	}
}
