package com.epam.wfh.manager.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileUtility {

    final static String CHROME_PATH = "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\History";
    final static String WFH_PATH = "\\AppData\\Local\\WFH\\";
    final static String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public final static String USER_HOME_DIRECTORY = "user.home";

    String copyFile() throws IOException {
        String filename = getFileNameBasedOnCurrentTime();
        copyFileUsingJava7Files(filename);
        return filename;
    }

    private String getFileNameBasedOnCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDateTime now = LocalDateTime.now();
        String filename = dtf.format(now);
        filename = filename.replaceAll("[ :/]", "");
        return filename;
    }

    private void copyFileUsingJava7Files(String destinationFile) throws IOException {
        File source = new File( getHomePath() + CHROME_PATH);
        new File(getHomePath() + WFH_PATH).mkdir();
        File dest = new File(getHomePath() + WFH_PATH + destinationFile);
        Files.copy(source.toPath(), dest.toPath());
    }

    public String getHomePath(){
        return System.getProperty(USER_HOME_DIRECTORY);
    }

    public static String getChromePath() {
        return CHROME_PATH;
    }

    public static String getWfhPath() {
        return WFH_PATH;
    }
}
