package at.bmlvs.NDMS.domain;

import java.util.Collections;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.bmlvs.NDMS.domain.connectors.SNMPConnector;
import at.bmlvs.NDMS.domain.connectors.SSHConnector;
import at.bmlvs.NDMS.domain.templates.Command;
import at.bmlvs.NDMS.domain.templates.Datatype;
import at.bmlvs.NDMS.domain.templates.Parameter;
import at.bmlvs.NDMS.domain.templates.Section;
import at.bmlvs.NDMS.domain.templates.Snippet;
import at.bmlvs.NDMS.domain.templates.Template;
import at.bmlvs.NDMS.domain.templates.Templates;
import at.bmlvs.NDMS.service.PersistenceService;
import at.bmlvs.NDMS.service.ServiceFactory;

public class TemplateTest
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
			
			ServiceFactory.getDomainService().setTemplates(new Templates());
			
			Template templateBasic = new Template("1.0", "15.0(2)SE6", "C2960-LANBASEK9-M");

			Snippet snippetBasic = new Snippet("Basic-Snippet", "None", "Interface-Snippet");
			Snippet snippetInterfaces = new Snippet("Interface-Snippet", "Basic-Snippet", "None");
			
			Section sectionBasicDeviceConfigurations = new Section("Basic Device Configurations");
			
			Command commandSetHostname = new Command("hostname");
			
			Datatype textDatatype = new Datatype();
			
			Parameter parameterSetHostname = new Parameter(0, "Hostname", textDatatype, "GWDSWITCH");
			
			Section sectionSNMPConfiguration = new Section("SNMP Configuration");
			
			Section sectionAllInterfaces = new Section("Interface Configuration");
		}
		catch (Exception e)
		{
			
		}
	}
	
	@SuppressWarnings("static-access")
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
