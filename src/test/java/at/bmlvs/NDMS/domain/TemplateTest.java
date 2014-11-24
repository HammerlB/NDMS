package at.bmlvs.NDMS.domain;

import java.util.Collections;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.bmlvs.NDMS.domain.templates.Command;
import at.bmlvs.NDMS.domain.templates.Parameter;
import at.bmlvs.NDMS.domain.templates.Section;
import at.bmlvs.NDMS.domain.templates.Snippet;
import at.bmlvs.NDMS.domain.templates.Template;
import at.bmlvs.NDMS.linker.TemplateToPathLinker;
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
			
			Template templateBasic = new Template("Basiskonfiguration", "1.0", "15.0(2)SE6", "C2960-LANBASEK9-M");

			Snippet snippetBasic = new Snippet("Basic-Snippet", "None", "Interface-Snippet");
			Snippet snippetInterfaces = new Snippet("Interface-Snippet", "Basic-Snippet", "None");
			
			Section sectionBasicDeviceConfigurations = new Section("Basic Device Configurations");
			
			//String name, String alias, boolean appendParameters
			Command commandSetHostname = new Command("hostname", "", true);
			
			//int id, String name, String alias, String type, String defaultValue, String value, boolean used, boolean useName
			Parameter parameterSetHostname = new Parameter(0, "Hostname", "Hostname", "DatatypeString", "GWDSWITCH", "", true, false);
			
			commandSetHostname.getParameters().add(parameterSetHostname);
			
			sectionBasicDeviceConfigurations.getCommands().add(commandSetHostname);
			
			snippetBasic.getSections().add(sectionBasicDeviceConfigurations);
			
			Section sectionSNMPConfiguration = new Section("SNMP Configuration");
			
			snippetBasic.getSections().add(sectionSNMPConfiguration);
			
			Section sectionAllInterfaces = new Section("Interface Configuration");
			
			snippetInterfaces.getSections().add(sectionAllInterfaces);
			
			templateBasic.getSnippets().add(snippetBasic);
			templateBasic.getSnippets().add(snippetInterfaces);
			
			TemplateToPathLinker templateToPathLinker = new TemplateToPathLinker(templateBasic, ServiceFactory.getAppConfig().getNDMS_DEFAULT_PATH_APP() + "\\" + ServiceFactory.getAppConfig().getNDMS_DEFAULT_PATH_TEMPLATE_SOURCE_DIRECTORY() + "\\" + templateBasic.getFullName());
			
			ServiceFactory.getPersistenceService().getTemplates().add(templateToPathLinker);
			
			ServiceFactory.getPersistenceService().saveAllTemplates();
		}
		catch (Exception e)
		{
			e.printStackTrace();
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
