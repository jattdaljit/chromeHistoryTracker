package com.epam.wfh.manager.persistent;

import com.microsoft.graph.models.extensions.DateTimeTimeZone;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MeetingHoursCalculator {

    public static Map<String, Long > meetingsHoursMap=new HashMap<>();

    public static String stringRep="";
    public static void meetingHoursUpdater(DateTimeTimeZone start, DateTimeTimeZone end){
        String date=start.dateTime.split("T")[0];
        long duration=meetingDuration(start,end);
        System.out.println("duration>> "+duration);
        if(meetingsHoursMap.containsKey(date)){
            duration+=meetingsHoursMap.get(date);
            meetingsHoursMap.remove(date);
            meetingsHoursMap.put(date,duration);
        }else{
            meetingsHoursMap.put(date, duration);
        }
    }

    public static Long meetingDuration(DateTimeTimeZone start, DateTimeTimeZone end){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(start.dateTime);
            d2 = format.parse(end.dateTime);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;

            return  diffMinutes+(diffHours*60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (long)0;
    }

    public static void meetingHoursSerializer() throws IOException {

        StringBuffer app= new StringBuffer();
        meetingsHoursMap.keySet().forEach(
                key ->{
                    app.append(key+"="+meetingsHoursMap.get(key)+", ");
                }
        );
        if( app.length() > 0 )
            app.deleteCharAt( app.length() - 1 );
        stringRep=app.toString();
        FileWritter.writeFile("WorkingHours", app.toString(), "r");
    }

}
