package com.epam.wfh.manager.service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class ActiveWindowDataPullService implements Callable {

    public Map<String,Integer> call() throws IOException {
        FileUtility fileUtility = new FileUtility();
        File file = new File(fileUtility.getHomePath() + fileUtility.getWfhPath() + "processStats.txt");
        String line = "";
        String cvsSplitBy = ",";
        Map<String, Integer> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                if(data[2].equals("Seconds")){
                    map.put(data[0], Integer.valueOf(data[1]));
                } else if(data[2].equals("Minutes")){
                    map.put(data[0], Integer.valueOf(data[1])*60);
                } else {
                    map.put(data[0], Integer.valueOf(data[1])*60*60);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}

