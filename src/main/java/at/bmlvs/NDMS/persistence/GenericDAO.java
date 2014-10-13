package at.bmlvs.NDMS.persistence;

public interface GenericDAO<T, P>
{
	public void write(T element, P path);

	public void delete(T element, P path);

	public T read(P path);

	public void update(T newelement, T oldelement, P path);
}