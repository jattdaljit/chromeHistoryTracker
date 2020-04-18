package com.epam.wfh.manager.outlook.service;


import com.epam.wfh.manager.persistent.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App


{

    public static void main( String[] args ) throws IOException {

       System.out.println("Welcome " + OutLookService.user.displayName);
        System.out.println();


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
                    OutLookService.listCalendarEvents();
                    ConnectionSerDeSer.connectionSerializer(Connections.dailyConnectionRecords);
                    CalenderSerDeSer.serialize(CalenderDaily.dailyCalenderRecords);
                    System.out.println(MeetingHoursCalculator.meetingsHoursMap);
                    MeetingHoursCalculator.meetingHoursSerializer();
                    ConnectionsCalculator.deserializeConnection();
                    System.out.println("updated connections");
                    System.out.println(ConnectionsCalculator.newConnections);
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
