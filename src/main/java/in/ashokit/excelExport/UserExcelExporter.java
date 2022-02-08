package in.ashokit.excelExport;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import in.ashokit.bindings.InsurancePlanResponseBinding;
import in.ashokit.entity.InsurancePlanEntity;

public class UserExcelExporter {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<InsurancePlanResponseBinding> listUsers;

	public UserExcelExporter(List<InsurancePlanResponseBinding> listUsers) {
		this.listUsers = listUsers;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("InsurancePlan");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "Insurance ID", style);
		createCell(row, 1, "User Name", style);
		createCell(row, 2, "Plan Name", style);
		createCell(row, 3, "Holder SSN", style);
		createCell(row, 4, "Plan Status", style);

	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void writeDataLines() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (InsurancePlanResponseBinding user : listUsers) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			createCell(row, columnCount++, user.getPlanId(), style);
			createCell(row, columnCount++, user.getPlanHolder(), style);
			createCell(row, columnCount++, user.getPlanName(), style);
			createCell(row, columnCount++, user.getHolderSsn().toString(), style);
			createCell(row, columnCount++, user.getPlanStatus(), style);

		}
	}

	public void export(HttpServletResponse response) throws IOException {
		writeHeaderLine();
		writeDataLines();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}
}
