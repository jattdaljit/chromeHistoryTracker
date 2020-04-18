package com.epam.wfh.manager.persistent;

import java.io.*;

public class FileWritter {


    public static void writeFile(File file, String content, String mode) throws IOException {
        //mode a append, r rewrite
        System.out.println("content "+content);


        if(!file.exists() || mode.equals("r")) {
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file);
        writer.write(content);
        System.out.println("file path "+file.getAbsolutePath());
        writer.flush();
        writer.close();
    }

    public static String fileReader(File file) throws IOException {
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
