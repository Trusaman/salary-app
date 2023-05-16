package org.example.ReadExcel;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadExcelFile {
    public static void main(String[] args) throws Exception {
        InputStream file = new FileInputStream(new File("C:\\Users\\KETOAN03\\IdeaProjects\\MailApp\\test.xlsx"));

        System.out.println("Running");
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        XSSFSheet sheet = workbook.getSheetAt(0);

        Map<Integer, List<String>> data = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            data.put(i, new ArrayList<String>());
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        System.out.println("A String");
                        break;
                    case NUMERIC:
                        System.out.println("A Number");
                        break;
                    case BOOLEAN:
                        System.out.println("A boolean");
                        break;
                    case FORMULA:
                        break;
                    default: data.get(String.valueOf(i)).add(" ");
                }
            }
            i++;
            System.out.println("data = " + data);
        }


    }


}
