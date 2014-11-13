package at.bmlvs.NDMS.domain.snapshots;

import java.io.File;

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
	
	public void add(Snapshot s){
		snapshots.add(s);
	}
	
	public boolean checkSnapshot(int index){
		File f = new File(snapshots.get(index).getRelativePath());
		if(f.exists()){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean checkSnapshot(Snapshot s){
		int index = snapshots.indexOf(s);
		File f = new File(snapshots.get(index).getRelativePath());
		if(f.exists()){
			return true;
		}else{
			return false;
		}
	}
	
	public void createSnapshot(Snapshot s){
		if(!checkSnapshot(s)){
			add(s);
		}
	}
}
