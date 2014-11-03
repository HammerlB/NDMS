package at.bmlvs.NDMS;

import javax.swing.UIManager;

import at.bmlvs.NDMS.domain.helper.UUIDGenerator;
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

		ServiceFactory.setPersistenceService(new PersistenceService());
		ServiceFactory.setAppConfig(ServiceFactory.getPersistenceService()
				.getAppconfig().getElement());
		ServiceFactory.setDomainService(new DomainService());
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
		stage.setTitle("NDSM");
		stage.getIcons().add(new Image("file:icons/ndms.png"));
		stage.setWidth(800);
		stage.setHeight(700);
		stage.show();
	}
}