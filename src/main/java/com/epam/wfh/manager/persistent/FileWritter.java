package com.epam.wfh.manager.persistent;

import com.epam.wfh.manager.service.FileUtility;

import java.io.*;

public class FileWritter {


    public static String getHomePath(){
        return System.getProperty(FileUtility.USER_HOME_DIRECTORY);
    }

    public static void writeFile(String fileName, String content, String mode) throws IOException {
        //mode a append, r rewrite
        System.out.println("content "+content);
        new File(getHomePath() + FileUtility.getWfhPath()).mkdir();
        File file =new File(getHomePath() +FileUtility.getWfhPath()+fileName);

        if(!file.exists() || mode.equals("r")) {
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file);
        writer.write(content);
        System.out.println("file path "+file.getAbsolutePath());
        writer.flush();
        writer.close();
    }

    public static String fileReader(String fileName) throws IOException {
        new File(getHomePath() + FileUtility.getWfhPath()).mkdir();
        File file =new File(getHomePath() +FileUtility.getWfhPath()+fileName);
        String content="";
        FileReader fr = new FileReader(file);
        char [] a = new char[50];
        fr.read(a);   // reads the content to the array
        for(char c : a)
            content+=c;   // prints the characters one by one
        fr.close();
        System.out.println("content read "+content);
        return content;
    }
}
