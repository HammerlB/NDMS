package at.bmlvs.NDMS.presentation;

import at.bmlvs.NDMS.domain.snapshots.Snapshot;
import at.bmlvs.NDMS.service.ServiceFactory;
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
		
		ObservableList<String> items = FXCollections.observableArrayList ();
		
		try
		{
			for(Snapshot snapshot: ServiceFactory.getDomainService().getSnapshots().getSnapshots())
			{
				items.setAll(snapshot.getName() + " " + snapshot.getDatetime());
				
			}
		}
		catch (Exception e)
		{
			
		}

		
		snapshotlist.setItems(items);
		
		descArea.setText("jsdhflkasjcfnlaksjcbfhko\nksdjsdkfh");
		
	}
}
