package datatier.state.implementations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import datatier.state.dao.PersistenceDAOState;

public class PersistenceDAOStateFile implements PersistenceDAOState {
	private static PersistenceDAOStateFile persistenza;
	private final String nomeFile = "ultimascrittura.txt";

	private PersistenceDAOStateFile() {
	}

	public static PersistenceDAOStateFile getPersistenza() {
		// ensures that the persistence is never instantiated more than once
		if (persistenza == null) {
			synchronized (PersistenceDAOStateFile.class) {
				if (persistenza == null) {
					return new PersistenceDAOStateFile();
				}
			}
		}
		return persistenza;
	}

	private long[] getTimestamps(File file) {
		Scanner sc;
		long[] timestamps = new long[2];
		try {
			sc = new Scanner(file);
			timestamps[0] = sc.nextLong();
			timestamps[1] = sc.nextLong();
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return timestamps;
	}

	@Override
	public long[] read() {
		File file = new File(nomeFile);
		if (file.exists()) {
			// read the timestamps from the file and return them
			return getTimestamps(file);
		} else {
			// The program is being opened for the first time, or the save-file
			// has been removed/moved
			return new long[] { System.currentTimeMillis(), System.currentTimeMillis() };
		}
	}

	@Override
	public void write(long timeStampFirst, long timestampLast) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(nomeFile, "UTF-8");
			writer.println(timeStampFirst);
			writer.println(timestampLast);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
