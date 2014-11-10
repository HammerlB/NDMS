package at.bmlvs.NDMS.domain;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import at.bmlvs.NDMS.domain.connectors.SNMPConnector;
import at.bmlvs.NDMS.domain.connectors.SSHConnector;
import at.bmlvs.NDMS.domain.connectors.ThreadSSH;
import at.bmlvs.NDMS.service.PersistenceService;
import at.bmlvs.NDMS.service.ServiceFactory;

public class InstanceTest extends Application
{
	public static void main(String[] args)
	{
		//KILL THE LOGGERS!!!
		List<Logger> loggers = Collections.<Logger>list(LogManager.getCurrentLoggers());
		loggers.add(LogManager.getRootLogger());
		for ( Logger logger : loggers ) {
		    logger.setLevel(Level.OFF);
		}
		
		String tabname = "192.168.1.12";
		
		try
		{
			ServiceFactory.setPersistenceService(new PersistenceService());
			ServiceFactory.setAppConfig(ServiceFactory.getPersistenceService()
					.getAppconfig().getElement());
			
			ThreadSSH sshc = new ThreadSSH(tabname, "Herkel", "gwdH_2014");
			sshc.start();
			
			Instance inst = new Instance(tabname, sshc.getSSHFingerprint(), tabname, sshc, new SNMPConnector("udp:" + tabname + "/161", "gwdSNMP_2014"));
			inst.populateAll();
			
			System.out.println(inst.toString());
			
			for(Interface ifer: inst.getInterfaces())
			{
				System.out.println(ifer.toString());
			}
		}
		catch (Exception e)
		{
			
		}
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
