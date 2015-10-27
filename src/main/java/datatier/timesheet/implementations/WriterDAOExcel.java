package datatier.timesheet.implementations;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Vector;

import datatier.timesheet.dao.WriterDAO;
import domain.TimesheetData;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class WriterDAOExcel implements WriterDAO {

	WritableWorkbook workBookOut;
	File currFile;

	@Override
	public void write(TimesheetData container) {
		for (int i = 0; i < container.getSize(); i++) {
			String titleTimeSheet = container.getMonthName(i) + "-time-" + container.getOwner();
			File file = new File(titleTimeSheet + ".xls");
			setWorkBook(file, container.getMonth(i));
			WritableSheet sheet = workBookOut.getSheet(0);
			writeLine(sheet, container.getLine(i));
		}
		saveAndClose();
	}

	private void writeLine(WritableSheet sheet, Vector<String> line) {
		for (int j = 5; j < line.size(); j++) {
			int day = Integer.parseInt(line.get(3));
			addCell(sheet, Border.ALL, BorderLineStyle.THIN, j - 2, day + 1, line.get(j));
		}
	}

	private void saveAndClose() {
		try {
			workBookOut.write();
			workBookOut.close();
		} catch (IOException | WriteException e) {
			e.printStackTrace();
		}
	}

	private void setWorkBook(File file, String month) {
		if (currFile == file) {
			// The current open file and the file we need to use correspond, we
			// don't need to change anything
			return;
		}
		// The current workbook has not been opened yet, create or load the
		// current one
		if (workBookOut != null) {
			// A workbook is already open, save and close the old one
			saveAndClose();
		}
		Workbook workbook;
		try {
			if (file.exists()) {
				// Still at the same month, get the current workbook
				workbook = Workbook.getWorkbook(file);
			} else {
				// A new month has begun, create a new workbook from the
				// template
				workbook = Workbook.getWorkbook(new File(getClass().getResource("/template.xls").toURI()));
			}
			workBookOut = Workbook.createWorkbook(file, workbook);
			WritableSheet sheet = workBookOut.getSheet(0);
			addCell(sheet, Border.ALL, BorderLineStyle.THIN, 3, 0, month);
		} catch (BiffException | IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	private void addCell(WritableSheet sheet, Border border, BorderLineStyle borderLineStyle, int col, int row,
			String desc) {
		WritableCellFormat cellFormat = new WritableCellFormat();
		try {
			cellFormat.setBorder(border, borderLineStyle);
			Label label = new Label(col, row, desc, cellFormat);
			sheet.addCell(label);
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

}
