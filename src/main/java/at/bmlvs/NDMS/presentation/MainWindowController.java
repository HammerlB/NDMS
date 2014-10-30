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
	@FXML
	private ToggleGroup radiotoggle;
	@FXML
	private RadioButton rbaddress;
	@FXML
	private RadioButton rbrange;
	@FXML
	private RestrictiveTextField ipaddress1;
	@FXML
	private RestrictiveTextField ipaddress2;
	@FXML
	private RestrictiveTextField ipaddress3;
	@FXML
	private RestrictiveTextField ipaddress4;
	@FXML
	private RestrictiveTextField iprange1;
	@FXML
	private RestrictiveTextField iprange2;
	@FXML
	private RestrictiveTextField iprange3;
	@FXML
	private RestrictiveTextField iprange4;
	@FXML
	private RestrictiveTextField iprange5;
	@FXML
	private RestrictiveTextField iprange6;
	@FXML
	private RestrictiveTextField iprange7;
	@FXML
	private RestrictiveTextField iprange8;

	private Stage stage;

	public MainWindowController()
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"MainWindow.fxml"));
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

	@FXML
	private void addnew(ActionEvent event) throws IOException
	{

		stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("AddTab.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Verbinden zu...");
		stage.getIcons().add(new Image("file:icons/ndms.png"));
		stage.setScene(scene);
		stage.setResizable(false);

        //stage.initModality(Modality.WINDOW_MODAL);
        //stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
        
        //tabPane.getTabs().add(new Tab("asdfadf"));

		stage.show();
	}

	@FXML
	private void startconnection(ActionEvent event) throws IOException
	{

    	tabPane.getTabs().add(new Tab("asdfadf"));
    	tabPane.getTabs().add(sd);
    	
    	//tabPane.getTabs().add(new Tab("asdfadf"));

	}

	@FXML
	private void startoffline(ActionEvent event) throws IOException
	{

		tabPane.getTabs().add(new Tab("asdfadf"));
    	
	}

	@FXML
	private void iptoggle(ActionEvent event) throws IOException
	{
		iprange1.setDisable(true);
		iprange2.setDisable(true);
		iprange3.setDisable(true);
		iprange4.setDisable(true);
		iprange5.setDisable(true);
		iprange6.setDisable(true);
		iprange7.setDisable(true);
		iprange8.setDisable(true);
		
		iprange1.setText("");
		iprange2.setText("");
		iprange3.setText("");
		iprange4.setText("");
		iprange5.setText(""); 
		iprange6.setText("");
		iprange7.setText("");
		iprange8.setText("");

		ipaddress1.setDisable(false);
		ipaddress2.setDisable(false);
		ipaddress3.setDisable(false);
		ipaddress4.setDisable(false);
	}

	@FXML
	private void rangetoggle(ActionEvent event) throws IOException
	{
		iprange1.setDisable(false);
		iprange2.setDisable(false);
		iprange3.setDisable(false);
		iprange4.setDisable(false);
		iprange5.setDisable(false);
		iprange6.setDisable(false);
		iprange7.setDisable(false);
		iprange8.setDisable(false);

		ipaddress1.setDisable(true);
		ipaddress2.setDisable(true);
		ipaddress3.setDisable(true);
		ipaddress4.setDisable(true);
		
		ipaddress1.setText("");
		ipaddress2.setText("");
		ipaddress3.setText("");
		ipaddress4.setText("");
	}
}