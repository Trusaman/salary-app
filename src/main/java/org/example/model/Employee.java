package org.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {
    private String name;
    private Float age;
    private String gender;
    private String email;
    private String pdfFilePath;

}
