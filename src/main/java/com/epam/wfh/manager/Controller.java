package com.epam.wfh.manager;

import com.epam.wfh.manager.model.ChromeHistoryTimes;
import com.epam.wfh.manager.outlook.service.App;
import com.epam.wfh.manager.service.ChromeHistoryService;
import com.epam.wfh.manager.service.ProcessTrackerService;
import com.epam.wfh.manager.service.WindowsNotification;

import java.util.concurrent.*;

public class Controller {
    private static ExecutorService executor = null;
    private static ScheduledExecutorService executorOutlook = null;
    private static volatile Future taskOneResults = null;
    private static volatile Future taskTwoResults = null;

    public static void main(String args[]) {
        executor = Executors.newFixedThreadPool(1);
        executorOutlook=Executors.newScheduledThreadPool(2);
        outlookExecutor();
        startProcessJob();
        showNotifications();
        try {
            checkTasks();
            checkTaskDone();
        } catch (Exception e) {
            System.err.println("Caught exception: " + e.getMessage());
        }

    }

    private static void showNotifications(){
        executorOutlook.scheduleAtFixedRate(() -> {
            try {
                WindowsNotification.showMessage();
            }catch (Exception ex){

            }
        }, 2, 8, TimeUnit.MINUTES);

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
    public static void startProcessJob(){
        executor.execute(()->
            startProcessTracker());
    }

    public static void startProcessTracker(){
try {
    //need to make it argument driven
    ProcessTrackerService service = new ProcessTrackerService();
    service.trackProcesses();
}catch (Exception ex){
    System.out.println("Exception in starting the process tracker");
    ex.printStackTrace();
}
    }
}
