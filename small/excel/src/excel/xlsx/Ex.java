package excel.xlsx;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @describe
 * @author zhujh9
 * @createdate 2020-08-19 下午7:32:20
 * 
 **/
public class Ex {

	public static void main(String[] args) throws Exception {

		String[] str = { "1", "2", "3", "4", "5" };

		// List<String>

		Date date = new Date(43801);
		System.out.println(date);
		System.out.println(new Date().getTime());

		File file = new File("F:\\a.csv");
		OutputStream out = new FileOutputStream(file);
		System.out.println("---");

		HSSFWorkbook workbook = new HSSFWorkbook();// 创建一个Excel
		HSSFSheet sheet = workbook.createSheet("Sheet1as");// 创建一个工作表

		for (int i = 1; i <= 5; i++) {
			HSSFRow row = sheet.createRow(i);// 创建第一行
			for (int j = 1; j <= 5; j++) {
				HSSFCell createCell = row.createCell(j);
				createCell.setCellValue("值" + i + j);
			}
		}

		workbook.write(out);
	}

}
