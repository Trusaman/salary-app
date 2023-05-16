package org.example.ReadExcel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateExcelFile {
    public static void main(String[] args) {
        System.out.println("Running");

//        FileInputStream file = new FileInputStream(new File(fileLocation));
        Path path = Paths.get("C:\\Users\\KETOAN03\\IdeaProjects\\MailApp\\test.xlsx");
        try {
            Path createdPath = Files.createFile(path);
            System.out.println("Created file at: " + createdPath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
