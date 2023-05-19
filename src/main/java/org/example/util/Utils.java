package org.example.util;

public class Utils {
    public static String getFilePath() {
        // get current folder path of the project
        String fileLocation = System.getProperty("user.dir");
        System.out.println(fileLocation);
        return fileLocation;
    }
}
