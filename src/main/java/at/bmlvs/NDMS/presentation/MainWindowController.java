package at.bmlvs.NDMS.presentation;

import java.io.IOException;

import javax.swing.event.ChangeListener;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Sample custom control hosting a text field and a button.
 */
@SuppressWarnings("unused")
public class MainWindowController extends VBox
{
	@FXML
	private TabPane tabPane;

	private Stage stage;

	public MainWindowController()
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"xml/MainWindow.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try
		{
			fxmlLoader.load();
		}
		catch (IOException exception)
		{
			throw new RuntimeException(exception);
		}

	}
	
	public TabPane getTabPane()
	{
		return tabPane;
	}

	public void setTabPane(TabPane tabPane)
	{
		this.tabPane = tabPane;
	}

	public Stage getStage()
	{
		return stage;
	}

	public void setStage(Stage stage)
	{
		this.stage = stage;
	}

	@FXML
	private void addnew(ActionEvent event) throws IOException
	{

		stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("xml/AddTab.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Verbinden zu...");
		stage.getIcons().add(new Image("file:icons/ndms.png"));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(this.getScene().getWindow());
		stage.setScene(scene);
		stage.setResizable(false);

		stage.show();
	}
}