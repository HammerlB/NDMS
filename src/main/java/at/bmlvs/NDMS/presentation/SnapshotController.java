package at.bmlvs.NDMS.presentation;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import at.bmlvs.NDMS.domain.connectors.SSHConnector;
import at.bmlvs.NDMS.domain.connectors.TFTPConnector;
import at.bmlvs.NDMS.domain.instances.InstanceOnline;
import at.bmlvs.NDMS.domain.snapshots.Snapshot;
import at.bmlvs.NDMS.linker.SnapshotToPathLinker;
import at.bmlvs.NDMS.linker.TemplateToPathLinker;
import at.bmlvs.NDMS.service.PresentationService;
import at.bmlvs.NDMS.service.ServiceFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SnapshotController
{
	private MainWindowController mainWindow;

	@FXML
	private TextArea descArea;
	@FXML
	private ListView<String> snapshotlist;
	@FXML
	private Button removebtn;
	private ObservableList<String> items;
	private Stage stage;
	@FXML
	public void initialize()
	{
		removebtn.setDisable(true);
		items = FXCollections.observableArrayList();

		try
		{
			for (SnapshotToPathLinker snapshot : ServiceFactory
					.getPersistenceService().getSnapshots())
			{
				items.add(snapshot.getElement().getFullName());
			}
		}
		catch (Exception e)
		{

		}

		snapshotlist.setItems(items);

		snapshotlist.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<String>()
				{
					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue)
					{

						descArea.setText(ServiceFactory
								.getPersistenceService()
								.getSnapshots()
								.get(snapshotlist.getSelectionModel()
										.getSelectedIndex()).getElement()
								.getDescription());
						
						removebtn.setDisable(false);
						
					}
				});

	}

	@FXML
	private void addsnapshot(ActionEvent event) throws IOException
	{

		stage = new Stage();

		stage.setTitle("Neuer Snapshot");
		stage.getIcons().add(new Image("file:icons/ndms.png"));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setResizable(false);
		
		
		BorderPane bpane = new BorderPane();
		HBox hbox = new HBox();
		VBox vbox = new VBox();
		
		
		Label namelabel = new Label("Name: ");
		Label desclabel = new Label("Beschreibung: ");
		TextField name = new TextField();
		name.setFocusTraversable(false);
		TextArea desc = new TextArea();
		desc.setFocusTraversable(false);
		Button save = new Button("Speichern");
		save.setFocusTraversable(false);
		
		hbox.getChildren().add(namelabel);
		hbox.getChildren().add(name);
		hbox.setAlignment(Pos.CENTER_LEFT);
		
		vbox.getChildren().add(hbox);
		vbox.getChildren().add(new Label("    "));
		vbox.getChildren().add(desclabel);
		vbox.getChildren().add(desc);
		vbox.getChildren().add(save);
		
		
		bpane.setCenter(vbox);
		bpane.setTop(new Label("    "));
		bpane.setLeft(new Label("    "));
		bpane.setRight(new Label("    "));
		
		Scene scene = new Scene(bpane, 400, 270);

		stage.setScene(scene);
		
		save.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent e)
					{
						
						try
						{
							InstanceOnline inst = ((InstanceOnline)ServiceFactory.getDomainService().getInstances().getInstances().get(ServiceFactory.getPresentationService()
									.getMainWindowController().getTabPane()
									.getSelectionModel().getSelectedIndex()));
							
							inst.getTftpConnector().takeSnapshot(name.getText(), desc.getText(), inst.getSshConnector());
							
							items.clear();
							for (SnapshotToPathLinker snapshot : ServiceFactory
									.getPersistenceService().getSnapshots())
							{
								items.add(snapshot.getElement().getFullName());
							}
						}
						catch (Exception e1)
						{
							//e1.printStackTrace();
						}
						
						stage.close();
					}
				});
		
		stage.show();
		
	}

	@FXML
	private void removesnapshot(ActionEvent event) throws IOException
	{
		stage = new Stage();

		stage.setTitle("Snapshots");
		stage.getIcons().add(new Image("file:icons/ndms.png"));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(PresentationService.getMainWindowController()
				.getStage().getScene().getWindow());

		GridPane removegrid = new GridPane();
		VBox boxal = new VBox();

		Label tmp = new Label("  ");
		Label tmp2 = new Label("   ");
		Label tmp3 = new Label("                               ");
		Label tmp4 = new Label("  ");

		Label x = new Label(
				"   Sind Sie sicher, dass Sie diesen Snapshot entfernen wollen?");
		Button yes = new Button("OK");
		yes.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				
				stage.close();
			}
		});

		Button no = new Button("Abbrechen");
		no.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				stage.close();
			}
		});

		removegrid.add(tmp3, 1, 1);
		removegrid.add(yes, 2, 1);
		removegrid.add(tmp4, 3, 1);
		removegrid.add(no, 4, 1);

		boxal.getChildren().add(tmp);
		boxal.getChildren().add(x);
		boxal.getChildren().add(tmp2);
		boxal.getChildren().add(removegrid);

		Scene scene = new Scene(boxal, 330, 70);

		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();

	}

	@FXML
	private void einspielen(ActionEvent event) throws IOException
	{

		PresentationService.getMainWindowController().getStage().close();
	}
}
