package at.bmlvs.NDMS.linker;

import java.io.Serializable;

import at.bmlvs.NDMS.domain.snapshots.Snapshot;

@SuppressWarnings("serial")
public class SnapshotToPathLinker extends Linker<Snapshot, String> implements Serializable
{
	public SnapshotToPathLinker(Snapshot element, String path)
	{
		super(element, path);
	}
}