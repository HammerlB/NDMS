package at.bmlvs.NDMS.domain.snapshots;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Snapshots
{
	private ObservableList<Snapshot> snapshots;
	
	public Snapshots()
	{
		setSnapshots(FXCollections.observableArrayList());
	}

	public ObservableList<Snapshot> getSnapshots()
	{
		return snapshots;
	}

	public void setSnapshots(ObservableList<Snapshot> snapshots)
	{
		this.snapshots = snapshots;
	}
}
