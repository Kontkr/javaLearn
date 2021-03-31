package mplan.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * @describe
 * @author zhujh9
 * @createdate 2020-09-18 下午8:00:03
 * 
 **/
public class PoiTest {
	public static void main(String[] args) throws Exception {
		File file = new File("F:\\20200102_APT_A_B01_TRH_202008_01(1).csv");
		InputStream in = new FileInputStream(file);
		Reader ina = new InputStreamReader(in, "GBK");
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(ina);
		for (CSVRecord record : records) {
			String lastName = null;
			for (int i = 0; i < record.size(); i++) {
				lastName = record.get(i);
				System.out.println(lastName);
			}
			System.out.println("---");
		}
	}

}
