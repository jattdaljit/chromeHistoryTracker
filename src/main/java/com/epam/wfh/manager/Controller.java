package com.epam.wfh.manager;

import com.epam.wfh.manager.model.ChromeHistoryTimes;
import com.epam.wfh.manager.outlook.service.App;
import com.epam.wfh.manager.service.ChromeHistoryService;

import java.util.concurrent.*;

public class Controller {
    private static ExecutorService executor = null;
    private static ScheduledExecutorService executorOutlook = null;
    private static volatile Future taskOneResults = null;
    private static volatile Future taskTwoResults = null;

    public static void main(String args[]) {
        executor = Executors.newFixedThreadPool(1);
        executorOutlook=Executors.newScheduledThreadPool(1);
        outlookExecutor();
        try {
            checkTasks();
            checkTaskDone();
        } catch (Exception e) {
            System.err.println("Caught exception: " + e.getMessage());
        }

    }
    private static void outlookExecutor(){
         executorOutlook.scheduleAtFixedRate(() -> {
             App.serviceExecutor();
        }, 0, 8, TimeUnit.HOURS);
    }

    private static void checkTasks() throws Exception {
        taskOneResults = executor.submit(new ChromeHistoryService());
        //taskTwoResults = executor.submit(new TestTwo());
    }

    private static void checkTaskDone() throws ExecutionException, InterruptedException {
        while (!taskOneResults.isDone()) {
            //System.out.println("Task is not completed yet....");
            Thread.sleep(100); //sleep for 1 millisecond before checking again
        }
        ChromeHistoryTimes chromeHistoryTimes = (ChromeHistoryTimes) taskOneResults.get();
        System.out.println(chromeHistoryTimes);
        executor.shutdown();
    }
}
