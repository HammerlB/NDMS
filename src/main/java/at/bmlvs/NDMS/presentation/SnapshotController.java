package at.bmlvs.NDMS.presentation;

import java.io.IOException;

import at.bmlvs.NDMS.domain.snapshots.Snapshot;
import at.bmlvs.NDMS.linker.SnapshotToPathLinker;
import at.bmlvs.NDMS.service.PresentationService;
import at.bmlvs.NDMS.service.ServiceFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SnapshotController
{
	@FXML
	private TextArea descArea;
	@FXML
	private ListView<String> snapshotlist;
	
	private Stage stage;
	
	@FXML
	public void initialize()
	{
		ObservableList<String> items = FXCollections.observableArrayList ();
		
		try
		{
			for(SnapshotToPathLinker snapshot: ServiceFactory.getPersistenceService().getSnapshots())
			{
				items.setAll(snapshot.getElement().getName() + " " + snapshot.getElement().getDatetime());
				
			}
		}
		catch (Exception e)
		{
			
		}
		
		snapshotlist.setItems(items);

		snapshotlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        
		    	
		    	
		        System.out.println("Selected item: " + newValue);
		    }
		});
		
		descArea.setText("jsdhflkasjcfnlaksjcbfhko\nksdjsdkfh");
		
	}
	
	@FXML
	private void addsnapshot(ActionEvent event) throws IOException
	{
		
	}
	@FXML
	private void removesnapshot(ActionEvent event) throws IOException
	{
		stage = new Stage();
		
		stage.setTitle("Snapshots");
		stage.getIcons().add(new Image("file:icons/ndms.png"));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(PresentationService.getMainWindowController().getStage().getScene().getWindow());
		
		GridPane removegrid = new GridPane();
		VBox boxal = new VBox();
		
		Label tmp = new Label("  ");
		Label tmp2 = new Label("   ");
		Label tmp3 = new Label("                         ");
		Label tmp4 = new Label("  ");
		
		Label x = new Label("   Sind Sie sicher, dass Sie diesen Snapshot entfernen wollen?");
		Button yes = new Button("OK");
        yes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            	
            	stage.close();
            }
        });
		
		Button no = new Button("Abbrechen");
        no.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


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
		
		Scene scene = new Scene(boxal,330, 70);
		
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
