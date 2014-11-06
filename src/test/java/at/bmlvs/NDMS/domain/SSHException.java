package at.bmlvs.NDMS.domain;

public class SSHException extends Exception {
	public SSHException(){
        super();
    }

    public SSHException(String message){
        super(message);
    }
}
