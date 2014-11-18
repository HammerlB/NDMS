package at.bmlvs.NDMS;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.swing.UIManager;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.snmp4j.smi.OID;

import at.bmlvs.NDMS.domain.connectors.SNMPConnector;
import at.bmlvs.NDMS.domain.instances.Instances;
import at.bmlvs.NDMS.presentation.MainWindowController;
import at.bmlvs.NDMS.service.DomainService;
import at.bmlvs.NDMS.service.PersistenceService;
import at.bmlvs.NDMS.service.PresentationService;
import at.bmlvs.NDMS.service.ServiceFactory;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application
{
	@SuppressWarnings("static-access")
	public static void main(String[] args)
	{
		//KILL THE LOGGERS!!!
		List<Logger> loggers = Collections.<Logger>list(LogManager.getCurrentLoggers());
		loggers.add(LogManager.getRootLogger());
		for ( Logger logger : loggers ) {
		    logger.setLevel(Level.OFF);
		}
		
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
		
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
            	System.out.println("Stage is closing");
            	System.exit(0);
                
            }
        });        
        //stage.close();
          
	}
}