package org.xe.conversion.uat.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import org.xe.conversion.uat.base.TestBase;

public class ReadXlData extends TestBase {

	public static Object[][] getXESiteData(String workbookname, String sheetName) throws IOException {
		FileInputStream file = null;
		List<Row> rows = null;
		ReadableWorkbook workBook = null;
		// Object[][] data = null;
		file = new FileInputStream("../CurrencyConversion/src/main/resources/InputDataSets/" + workbookname);
		// System.out.println(fileName + "-" + ShtName +" 57");
		workBook = new ReadableWorkbook(file);
		Optional<Sheet> workSheets = workBook.findSheet(sheetName);
		Sheet workSheet = workSheets.get();
		rows = workSheet.read();
		workBook.close();
		Object[][] data = new Object[rows.size()][rows.get(0).getCellCount()];
		for (int i = 0; i < rows.size(); i++) {
			for (int j = 0; j < rows.get(0).getCellCount(); j++)
				data[i][j] = rows.get(i).getCell(j).getText() + "";
		}
		return data;
	}

	public static HashMap<String, String> getXEDescriptionDetails(Object[][] dataObject, String queryValue) {
		String[][] data = null;
		data = new String[1][dataObject[0].length];
		String colval = "";
		new LinkedHashMap<>();
		int dataFoundRow=-1;
		HashMap<String,String> map=new HashMap<String,String>();
		for (int i = 0; i < dataObject.length; i++) {
			colval = "";
			data = new String[1][dataObject[0].length];
			for (int j = 0; j < dataObject[0].length; j++) {
				data[0][j] = dataObject[i][j] + "";
				if (dataObject[i][j].toString().toLowerCase().trim().equals(queryValue.trim().toLowerCase())) {
					colval = colval + "#" + dataObject[i][j] + "#[" + i + "]";	
					dataFoundRow=i;
					break;
				}
			}
		}
		data = new String[2][dataObject[0].length];
		if(dataFoundRow>-1)
			for (int i = 0; i < dataObject[0].length; i++)
			{	
				map.put(dataObject[0][i].toString(),  dataObject[dataFoundRow][i].toString());
			}
		else
		{
			data = new String[1][dataObject[0].length];
			for (int i = 0; i < dataObject[0].length; i++)
			{	
				map.put(dataObject[0][i].toString(),  "error not found");
			}
		}
		return map;
	}

	public static Object[][] getTestData(String workBookName, String SheetName) throws IOException {
		FileInputStream file = null;
		List<Row> rows = null;
		ReadableWorkbook workBook = null;
		// Object[][] data = null;
		file = new FileInputStream("../CurrencyConversion/src/main/resources/InputDataSets/" + workBookName);
		// System.out.println(fileName + "-" + ShtName +" 57");
		workBook = new ReadableWorkbook(file);
		Optional<Sheet> workSheets = workBook.findSheet(SheetName);
		Sheet workSheet = workSheets.get();
		rows = workSheet.read();
		workBook.close();
		Object[][] data = new Object[rows.size() - 1][rows.get(0).getCellCount()];
		for (int i = 1; i < rows.size(); i++) {
			for (int j = 0; j < rows.get(0).getCellCount(); j++)
				data[i - 1][j] = rows.get(i).getCell(j).getText() + "";
		}
		return data;
	}
}
