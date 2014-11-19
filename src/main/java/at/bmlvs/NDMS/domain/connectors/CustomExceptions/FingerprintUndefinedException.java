package at.bmlvs.NDMS.domain.connectors.CustomExceptions;

public class FingerprintUndefinedException extends Exception{
	public FingerprintUndefinedException(){
		super("The fingerprint is undefined");
	}
}
