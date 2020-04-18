package com.epam.wfh.manager;

import com.epam.wfh.manager.model.ChromeHistoryTimes;
import com.epam.wfh.manager.service.ChromeHistoryService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Controller {
    private static ExecutorService executor = null;
    private static volatile Future taskOneResults = null;
    private static volatile Future taskTwoResults = null;

    public static void main(String args[]) {
        executor = Executors.newFixedThreadPool(3);
        try {
            checkTasks();
            checkTaskDone();
        } catch (Exception e) {
            System.err.println("Caught exception: " + e.getMessage());
        }

    }

    private static void checkTasks() throws Exception {
        taskOneResults = executor.submit(new ChromeHistoryService());
        //taskTwoResults = executor.submit(new TestTwo());
    }

    private static void checkTaskDone() throws ExecutionException, InterruptedException {
        while (!taskOneResults.isDone()) {
            System.out.println("Task is not completed yet....");
            Thread.sleep(100); //sleep for 1 millisecond before checking again
        }
        ChromeHistoryTimes chromeHistoryTimes = (ChromeHistoryTimes) taskOneResults.get();
        System.out.println(chromeHistoryTimes);
        executor.shutdown();
    }
}
