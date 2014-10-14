package at.bmlvs.NDMS.presentation;

import javax.swing.JPanel;
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
		PresentationFactory.setTabbedWindow(new TabbedWindow());
		
		PresentationFactory.getMainWindow().addTopMenu();
		//PresentationFactory.getTabbedWindow().addTabbedMenu("10.0.0.1");
		
	}

}
