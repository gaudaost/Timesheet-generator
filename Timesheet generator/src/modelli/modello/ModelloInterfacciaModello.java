package modelli.modello;

import persistenza.dao.PersistenzaDAO;
import persistenza.file.CSVWriter;
import modelli.interfaccia.ModelloInterfaccia;
import dominio.CSVContainer;
import dominio.CSVGenerator;
import dominio.Time;

public class ModelloInterfacciaModello implements ModelloInterfaccia {
	private Time time;
	//initialize the start timestamp to 1st of January 1970 00:00:00
	private long timestampStart=0;
	private long currTime=0;
	private PersistenzaDAO persistenza;
	private CSVGenerator generator;
	private CSVWriter writer;

	public ModelloInterfacciaModello(PersistenzaDAO persistenza) {
		time =new Time();
		this.persistenza=persistenza;
		generator=new CSVGenerator(time);
		writer=new CSVWriter();
	}
	
	private void mainActivity()  {
		long[] timestamps=persistenza.read();
		if (timestampStart==0) {
			//Even if you were to travel back in time or set your computer clock back to the 1970s, this is still correct as it represents midnight: a new day begins:
			timestampStart=timestamps[0];
		}
		int dayDifference=0;
		currTime=System.currentTimeMillis();
		System.out.println(dayDifference);
		if((dayDifference=time.getDayDifference(timestamps[0], currTime))>0) {
			CSVContainer newLines=generator.getCSV(timestamps, dayDifference);
			timestampStart=currTime;
			writer.write(newLines);
		}
		persistenza.write(timestampStart, currTime);
	}
	
	public void task() {
		while (true) {
			try {
				mainActivity();
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
