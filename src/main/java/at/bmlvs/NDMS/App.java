package at.bmlvs.NDMS;

import javax.swing.UIManager;

import at.bmlvs.NDMS.domain.connectors.SSHConnector;
import at.bmlvs.NDMS.presentation.MainWindow;
import at.bmlvs.NDMS.presentation.TabbedWindow;
import at.bmlvs.NDMS.service.PersistenceService;
import at.bmlvs.NDMS.service.PresentationService;
import at.bmlvs.NDMS.service.ServiceFactory;

public class App
{
	@SuppressWarnings("static-access")
	public static void main(String[] args)
	{
		
		// Presentation
		// TODO Auto-generated method stub
		// Set the look and feel to users OS LaF.
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
		}

		ServiceFactory.setPersistenceService(new PersistenceService());
		ServiceFactory.setAppConfig(ServiceFactory.getPersistenceService().getAppconfig().getElement());
		ServiceFactory.setPresentationService(new PresentationService());
		ServiceFactory.getPersistenceService().saveAppConfig();
		
		//ServiceFactory.getPresentationService().setMainWindow(new MainWindow());
		//ServiceFactory.getPresentationService().setTabbedWindow(new TabbedWindow());

		ServiceFactory.getPresentationService().getMainWindow().addTopMenu();

		/*
		// SSH
		SSHConnector sshcon  = new SSHConnector("192.168.1.12","Herkel","gwdH_2014", "2", 22);
		sshcon.connect();
		sshcon.sendCmd(
				"enable\n"
				+ "gwd_2014\n"
				+ "show run | include interface\n"
				+ "         ");
		sshcon.createOutput(new byte[1024], 2000);
		System.out.println(sshcon.getOutput());
		sshcon.disconnect();

		// Test
		// ServiceFactory.getPersistenceService().setTemplates(new Tem);*/
	}
}
