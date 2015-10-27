package datatier.state.dao;

public interface PersistenceDAOState {
	//Writes the first and last timestamp registered 
	public void write(long timeStampFirst, long timestampLast);
	//returns the first and the last timestamp registered
	public long[] read();
}
