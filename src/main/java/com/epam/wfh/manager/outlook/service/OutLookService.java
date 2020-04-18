package com.epam.wfh.manager.outlook.service;

import com.epam.wfh.manager.outlook.graph.Authentication;
import com.epam.wfh.manager.outlook.graph.Graph;
import com.epam.wfh.manager.persistent.CalenderDaily;
import com.epam.wfh.manager.persistent.ConnectionsCalculator;
import com.epam.wfh.manager.persistent.FileWritter;
import com.epam.wfh.manager.persistent.MeetingHoursCalculator;
import com.microsoft.graph.models.extensions.DateTimeTimeZone;
import com.microsoft.graph.models.extensions.Event;
import com.microsoft.graph.models.extensions.User;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

public class OutLookService {

    static final String appId = "";
    static final String[] appScopes = "User.Read,Calendars.Read".split(",");
    public static String accessToken ;
    public static User user;

    static{
        Authentication.initialize(appId);
        try {
            //accessToken = FileWritter.fileReader(new File("accesstoken"));
            accessToken = Authentication.getUserAccessToken(appScopes);
            user = Graph.getUser(accessToken);
        }catch (Exception e){
            System.out.println(e);

            accessToken = Authentication.getUserAccessToken(appScopes);
            user = Graph.getUser(accessToken);
            try{
                FileWritter.writeFile("accesstoken",accessToken,"r");
            }catch (Exception e1){
                System.out.println("Exception in writting toekn"+e1);
            }
        }

    }

    public static void listCalendarEvents() throws IOException {
        List<Event> events = Graph.getEvents(accessToken);

        System.out.println("Events:");
        for (Event event : events) {
            System.out.println("Subject: " + event.subject);
            System.out.println("  Organizer: " + event.organizer.emailAddress.name);
            System.out.println(event.start.dateTime);
            System.out.println(event.start.toString());
            System.out.println(event.start.oDataType);
            System.out.println(event.start.timeZone);
            System.out.println(">>> "+event.isCancelled);

            MeetingHoursCalculator.meetingHoursUpdater(event.start,event.end);
            ConnectionsCalculator.addOrUpdateConnection(event.organizer.emailAddress.address);

            System.out.println("  Start: " + formatDateTimeTimeZone(event.start));
            System.out.println("  End: " + formatDateTimeTimeZone(event.end));
            CalenderDaily.addEvent(event.start.dateTime.split("T")[0], event);
        }
        ConnectionsCalculator.serializeConnection();
        System.out.println();
    }
    private static String formatDateTimeTimeZone(DateTimeTimeZone date) {
        LocalDateTime dateTime = LocalDateTime.parse(date.dateTime);

        return dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)) + " (" + date.timeZone + ")";
    }






}
