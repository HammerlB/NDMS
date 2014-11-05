package at.bmlvs.NDMS;

import java.io.IOException;

import javax.swing.UIManager;

import org.snmp4j.smi.OID;

import at.bmlvs.NDMS.domain.Instances;
import at.bmlvs.NDMS.domain.connectors.SNMPConnector;
import at.bmlvs.NDMS.presentation.MainWindowController;
import at.bmlvs.NDMS.service.DomainService;
import at.bmlvs.NDMS.service.PersistenceService;
import at.bmlvs.NDMS.service.PresentationService;
import at.bmlvs.NDMS.service.ServiceFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application
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
		
		try
		{
			SNMPConnector snmpc = new SNMPConnector("udp:192.168.1.12/161", "gwdSNMP_2014");
			//System.out.println(snmpc.getAsString(new OID(".1.3.6.1.2.1.1.5.0")));
			snmpc.walk(".1.3.6.1.2.1.1.5", false, true);
			snmpc.walk(".1.3.6.1.2.1.2.2.1.1", true, false);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		ServiceFactory.setPersistenceService(new PersistenceService());
		ServiceFactory.setAppConfig(ServiceFactory.getPersistenceService()
				.getAppconfig().getElement());
		ServiceFactory.setDomainService(new DomainService());
		ServiceFactory.getDomainService().setInstances(new Instances());;
		ServiceFactory.setPresentationService(new PresentationService());
		ServiceFactory.getPersistenceService().saveAppConfig();

		ServiceFactory.getPresentationService().setMainWindowController(
				new MainWindowController());

		launch(args);
	}

	@SuppressWarnings("static-access")
	@Override
	public void start(Stage stage) throws Exception
	{
		stage.setScene(new Scene(ServiceFactory.getPresentationService().getMainWindowController()));
		stage.setTitle("NDMS");
		stage.getIcons().add(new Image("file:icons/ndms.png"));
		stage.setWidth(800);
		stage.setHeight(700);
		stage.show();
	}
}