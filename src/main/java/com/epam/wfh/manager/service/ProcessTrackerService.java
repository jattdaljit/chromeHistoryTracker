package com.epam.wfh.manager.service;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Java ProcessBuilder Demo
 */
public class ProcessTrackerService {

    public void trackProcesses(){
        ProcessTrackerService pt= new ProcessTrackerService();
        ProcessBuilder processBuilder = new ProcessBuilder(pt.getRelPath(),"args","60","SECONDS");
        Process process= null;
        try {
            process = processBuilder.inheritIO().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ProcessHandle processHandle = process.toHandle();
        CompletableFuture<ProcessHandle> onProcessExit = processHandle.onExit();
        try {
            onProcessExit.get();
            System.out.println(processHandle.isAlive());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private String getRelPath(){
        File file = new File("src/main/resources/windows-exp.exe");
        String absolutePath = file.getAbsolutePath();
        return absolutePath;
    }
}
