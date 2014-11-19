package at.bmlvs.NDMS.domain.connectors.CustomExceptions;

public class SnapshotUndefinedException extends Exception {
	public SnapshotUndefinedException() {
		super("The snapshot is undefined");
	}
}
