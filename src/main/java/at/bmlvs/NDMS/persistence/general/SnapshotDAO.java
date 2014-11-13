package at.bmlvs.NDMS.persistence.general;

import at.bmlvs.NDMS.domain.snapshots.Snapshot;
import at.bmlvs.NDMS.persistence.GenericDAO;

public interface SnapshotDAO extends GenericDAO<Snapshot, String>
{
	public void write(Snapshot element, String path);

	public void delete(Snapshot element, String path);

	public Snapshot read(String path);

	public void update(Snapshot newelement, Snapshot oldelement, String path);
}
