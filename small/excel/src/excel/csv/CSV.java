package excel.csv;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;

import easyexcel.Student;

/**
 * @describe
 * @author zhujh9
 * @createdate 2020-08-27 下午9:23:51
 * 
 **/
public class CSV {

	public void readExcel(String fileName) {
		try {
			// 创建输入流
			InputStream inputStream = new FileInputStream(fileName);
			// 构建要添加Student类
			Sheet sheet = new Sheet(1, 1, Student.class);
			// 读sheet表中的内容
			List<Object> studentList = EasyExcelFactory.read(inputStream, sheet);
			List<Student> students = new LinkedList<Student>();
			for (Object student : studentList) {
				students.add((Student) student);
			}
			for (Student student : students) {
				System.out.println(student);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println();

		// File file = new File("F:\\abc.csv");
		File file = null;
		new CSV().readExcel("F:\\abc.csv");
		FileOutputStream fos = new FileOutputStream(file);
		// OutputStreamWriter osw = new OutputStreamWriter(fos, "GBK");
		//
		// CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader("姓名", "年龄", "家乡");
		// CSVPrinter csvPrinter = new CSVPrinter(osw, csvFormat);
		//
		// // csvPrinter = CSVFormat.DEFAULT.withHeader("姓名", "年龄", "家乡").print(osw);
		//
		// for (int i = 0; i < 10; i++) {
		// csvPrinter.printRecord("张三", 20, "湖北");
		// }
		//
		// csvPrinter.flush();
		// csvPrinter.close();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		OutputStreamWriter as = new OutputStreamWriter(bos);
		CSVPrinter print = CSVFormat.DEFAULT.withHeader("姓名", "年龄", "家乡").print(as);

		for (int i = 0; i < 10; i++) {
			print.printRecord("张三", 20, "湖北");
		}
		char[] bf = new char[1024];
		as.write(bf);
		// System.out.println();

		StringBuffer csvBuffer = new StringBuffer();
		for (int i = 0; i < 5; i++) {
			csvBuffer.append("值" + i);

		}

	}

}
