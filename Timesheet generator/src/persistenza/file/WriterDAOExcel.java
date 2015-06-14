package persistenza.file;

import java.io.File;
import java.io.IOException;

import persistenza.dao.WriterDAO;
import dominio.BasicContainer;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class WriterDAOExcel implements WriterDAO {

	@Override
	public void write(BasicContainer container) {
		// TODO Auto-generated method stub
		try {
			Workbook workbook = Workbook.getWorkbook(new File(
					"marzo-time-ezio.xls"));
			WritableWorkbook copy = Workbook.createWorkbook(
					new File("test.xls"), workbook);
			WritableSheet sheet = copy.getSheet(0);
			for (int j = 2; j < 10; j++) {
				for (int i = 3; i < 7; i++) {
					WritableCell cell = sheet.getWritableCell(i, j);
					CellFormat feautures = cell.getCellFormat();
					Label label = new Label(i, j, "New label record");
					addCell(sheet, Border.ALL, BorderLineStyle.THIN, i, j, "All - thin");
					cell = sheet.getWritableCell(i, j);
					cell.setCellFormat(feautures);
					System.out.println(cell.getContents());
				}
			}
			copy.write();
			copy.close();
		} catch (BiffException | IOException | WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void addCell(WritableSheet sheet, Border border,
			BorderLineStyle borderLineStyle, int col, int row, String desc)
			throws WriteException {

		WritableCellFormat cellFormat = new WritableCellFormat();
		cellFormat.setBorder(border, borderLineStyle);
		Label label = new Label(col, row, desc, cellFormat);
		sheet.addCell(label);
	}

}
