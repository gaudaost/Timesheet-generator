package modelli;

import main.Factory;
import datatier.state.dao.PersistenzaDAO;
import datatier.timesheet.dao.WriterDAO;
import dominio.Time;
import dominio.TimesheetData;
import dominio.TimesheetDataBuilder;

public class Model {
	private Time time;
	//initialize the start timestamp to 1st of January 1970 00:00:00
	private long timestampStart=0;
	private long currTime=0;
	private PersistenzaDAO persistenza;
	private TimesheetDataBuilder timesheetBuilder;
	private WriterDAO writer;

	public Model(PersistenzaDAO persistenza, Factory factory) {
		time =new Time();
		this.persistenza=persistenza;
		writer=factory.getWriter();
	}
	
	private void mainActivity()  {
		long[] timestamps=persistenza.read();
		if (timestampStart==0) {
			//Even if you were to travel back in time or set your computer clock back to the 1970s, this is still correct as it represents midnight: a new day begins:
			timestampStart=timestamps[0];
		}
		int dayDifference=0;
		currTime=System.currentTimeMillis();//+(long)50*24*60*60*1000;
		if((dayDifference=time.getDayDifference(timestamps[0], currTime))>0) {
			//One or more days have passed since the last time stamp of entrance was registered, add the appropriate lines to the timesheet
			TimesheetData newLines=timesheetBuilder.getData(timestamps, dayDifference);
			writer.write(newLines);
			//Start a new day
			timestampStart=currTime;
		}
		//Write the current time stamps
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
