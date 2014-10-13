package at.bmlvs.NDMS.persistence;

public class GenericDAOException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public GenericDAOException(String reason)
	{
		super(reason);
	}
}
