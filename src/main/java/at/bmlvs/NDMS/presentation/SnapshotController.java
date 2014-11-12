package at.bmlvs.NDMS.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class SnapshotController
{
	@FXML
	private TextArea descArea;
	@FXML
	private ListView<String> snapshotlist;
	
	@FXML
	public void initialize()
	{
		
		ObservableList<String> items =FXCollections.observableArrayList (
			    "Single", "Double", "Suite", "Family App");
		
		snapshotlist.setItems(items);
	    
		descArea.setText("jsdhflkasjcfnlaksjcbfhko\nksdjsdkfh");
	}
}
