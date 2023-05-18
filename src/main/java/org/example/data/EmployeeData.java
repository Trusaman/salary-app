package org.example.data;

import org.example.ReadExcel.ReadExcelFile;
import org.example.model.Employee;

import java.util.List;

public class EmployeeData {

    public static List<Employee> employees;

    public static void main(String[] args) throws Exception {
        employees  = new ReadExcelFile().getEmployees();

        employees.forEach(System.out::println);
    }

}
