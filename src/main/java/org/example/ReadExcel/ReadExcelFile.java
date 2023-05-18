package org.example.ReadExcel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.constant.Constant;
import org.example.model.Employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadExcelFile {
    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() throws Exception {
        setEmployees();
        return employees;
    }

    public void setEmployees() throws Exception {
        InputStream file = new FileInputStream(new File(Constant.EXCEL_FILE_PATH + "test.xlsx"));

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
                        data.get(i).add(cell.getRichStringCellValue().getString());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            data.get(i).add(cell.getDateCellValue() + "");
                        } else {
                            data.get(i).add(cell.getNumericCellValue() + "");
                        }
                        break;
                    case BOOLEAN:
                        data.get(i).add(cell.getBooleanCellValue() + "");
                        break;
                    case FORMULA:
                        data.get(i).add(cell.getCellFormula() + "");
                        break;
                    default:
                        data.get(String.valueOf(i)).add(" ");
                }
            }
            i++;
        }


        // set data without header
        int count = 0;

        for (Map.Entry<Integer, List<String>> entry : data.entrySet()) {
            if (count == 0) {
                count++;
                continue;
            }
            System.out.println(entry.getKey() + " : " + entry.getValue());
            Employee employee = new Employee();
            employee.setName(entry.getValue().get(0));

            // convert entry.getValue().get(1) to String


            employee.setAge(Float.parseFloat(entry.getValue().get(1).trim()));
            employee.setGender(entry.getValue().get(2));

            employees.add(employee);
        }


    }
}