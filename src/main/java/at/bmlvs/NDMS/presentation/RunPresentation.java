package at.bmlvs.NDMS.presentation;

import javax.swing.UIManager;

public class RunPresentation
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		// Set the look and feel to users OS LaF.
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
		}

		PresentationFactory.setMainWindow(new MainWindow());
		PresentationFactory.getMainWindow().addTopMenu();
		PresentationFactory.getMainWindow().addTabbedMenu();
	}

}
