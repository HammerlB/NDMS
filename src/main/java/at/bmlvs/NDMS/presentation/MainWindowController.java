package at.bmlvs.NDMS.presentation;

import java.io.IOException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import at.bmlvs.NDMS.service.PresentationService;
import at.bmlvs.NDMS.service.ServiceFactory;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Sample custom control hosting a text field and a button.
 */
@SuppressWarnings("unused")

public class MainWindowController extends VBox
{
	@FXML
	private TabPane tabPane;
	private AddTabController tabcontrol = new AddTabController();
	private SnapshotController snapcontrol = new SnapshotController();
	private Stage stage;
	@FXML
	private ComboBox<String> templateBox;
	
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
		templatusBox();
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
	@FXML
	private void showsnapshotview(ActionEvent event) throws IOException
	{

		stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("xml/SnapshotWindow.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Snapshots");
		stage.getIcons().add(new Image("file:icons/ndms.png"));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(this.getScene().getWindow());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
		
	}
	@FXML
	private void templatusBox()
	{
		ObservableList<String> items =FXCollections.observableArrayList (
			    "Single", "Double", "Suite", "Family App", "fredlkind","herkelkind");
		templateBox.setItems(items);
		
		templateBox.setOnAction((event) -> {
		    System.out.println(templateBox.getSelectionModel().getSelectedItem());
		    
		    templateview(tabPane.getSelectionModel().getSelectedIndex());
		    
		});	
	}
	
	private void templateview(int id)
	{
		try
		{
			StackPane viewstack = new StackPane();

			GridPane left = new GridPane();
			GridPane right = new GridPane();

			SplitPane splitter = new SplitPane();
			splitter.setOrientation(Orientation.HORIZONTAL);
			
			TextArea show = new TextArea();
			show.disableProperty();

			Button btn1 = new Button("pos11");
			
			left.add(btn1, 1, 1);

			splitter.getItems().addAll(left, show);
			splitter.setDividerPositions(0.6f, 0.4f);

			viewstack.getChildren().add(splitter);

			PresentationService.getMainWindowController().getTabPane()
					.getTabs().get(id).setContent(viewstack);
		}
		catch (Exception e)
		{
		}
	}
	
	@FXML
	private void showportview()
	{
		try
		{
			tabcontrol.portview(tabPane.getSelectionModel().getSelectedIndex());
			
		} catch(Exception e){}
		
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
}