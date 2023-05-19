package org.example.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.ReadExcel.ReadExcelFile;
import org.example.constant.Constant;
import org.example.model.Employee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class ProcessPdfFile {
    public static List<Employee> getEmp() throws Exception {

        List<Employee> employees = new ReadExcelFile().getEmployees();

        for (int i = 0; i < employees.size(); i++) {
            extracted(employees.get(i));
        }

        return employees;
    }

    private static void extracted(Employee employee) throws FileNotFoundException, DocumentException {

        Document document = new Document();

        String filePath = Constant.PDF_FILE_PATH + employee.getName() + ".pdf";
        OutputStream outputStream = new FileOutputStream(new File(filePath));

        PdfWriter.getInstance(document, outputStream);

        document.open();

        PdfPTable table = new PdfPTable(2);
//        addTableHeader(table);
        addRows(table, employee);
//        addCustomRows(table);

        document.add(table);

        document.close();

        employee.setPdfFilePath(filePath);
        System.out.println("Write PDF file successfully");
    }

//    private static void addTableHeader(PdfPTable table) {
//        Stream.of("Name", "Age", "Gender").forEach(columnTitle -> {
//            PdfPCell header = new PdfPCell();
//            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            header.setBorderWidth(2);
//            header.setPhrase(new Phrase(columnTitle));
//            table.addCell(header);
//        });
//    }

    private static void addRows(PdfPTable table, Employee employee) {

        table.addCell("Name");
        PdfPCell nameCell = new PdfPCell(new Phrase(employee.getName()));
        // format nameCell to align right
        nameCell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        table.addCell(nameCell);

        table.addCell("Age");
        PdfPCell ageCell = new PdfPCell(new Phrase(String.valueOf(employee.getAge().intValue())));
        ageCell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        table.addCell(ageCell);

        table.addCell("Gender");
        PdfPCell genderCell = new PdfPCell(new Phrase(employee.getGender()));
        genderCell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        table.addCell(genderCell);
    }
}
