package com.epam.wfh.manager.outlook.service;


import com.epam.wfh.manager.persistent.*;
import com.epam.wfh.manager.service.WindowsNotification;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App

{
    public static boolean isExecuted=false;
    public static void serviceExecutor(){

        SimpleDateFormat format=new SimpleDateFormat("YYYY-MM-dd");
        try{
          String username=  OutLookService.user.displayName;
          System.out.println("Logged In as "+username);
            OutLookService.listCalendarEvents();
            ConnectionSerDeSer.connectionSerializer(Connections.dailyConnectionRecords);
            CalenderSerDeSer.serialize(CalenderDaily.dailyCalenderRecords);
            System.out.println(MeetingHoursCalculator.meetingsHoursMap);
            MeetingHoursCalculator.meetingHoursSerializer();
            ConnectionsCalculator.deserializeConnection();
            System.out.println("updated connections");
            System.out.println(ConnectionsCalculator.newConnections);
            CalenderDaily.displayCalender();
            Connections.dispalyConnections();
            isExecuted=true;
            float meetingTime=0;
            float productiveTime=8;

            String currentDate=format.format(new Date());
            if(MeetingHoursCalculator.meetingsHoursMap.containsKey(currentDate)) {
                meetingTime = MeetingHoursCalculator.meetingsHoursMap.get(currentDate);
                productiveTime= productiveTime-(meetingTime/60);
            }
            String message="Meeting Hours: "+meetingTime+"hr\n"+"Productive Time: "+productiveTime+"hrs";
            if(ConnectionsCalculator.newConnections.size()>0){
                message+="\nNew Connection: "+ConnectionsCalculator.newConnections.size();
            }
            sendNotificationForMeetings(message);

        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Exception in starting outlook sync "+ex);
        }

    }

    public static void sendNotificationForMeetings(String message){
        WindowsNotification windowsNotification = new WindowsNotification();
        windowsNotification.showMessage(message);
    }

    public static void main( String[] args ) throws IOException {
        Scanner input = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            System.out.println("0. Exit");
            System.out.println("1. Display access token");
            System.out.println("2. List calendar events");

            try {
                choice = input.nextInt();
            } catch (InputMismatchException ex) {
                // Skip over non-integer input
                input.nextLine();
            }
            // Process user choice
            switch(choice) {
                case 2:
                    serviceExecutor();
                    break;
                case 3:
                    CalenderDaily.displayCalender();
                    Connections.dispalyConnections();
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }

        input.close();
    }



}
