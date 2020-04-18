package com.epam.wfh.manager.persistent;

import com.microsoft.graph.models.extensions.DateTimeTimeZone;
import com.microsoft.graph.models.extensions.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalenderDaily {

    public static Map<String, List<Event>> dailyCalenderRecords=new HashMap<>();

    public static void addEvent(String start, Event currentEvent){
        if(dailyCalenderRecords.containsKey(start)){
            System.out.println("contains start");
          if(  dailyCalenderRecords.get(start).stream().noneMatch(
                   e ->{
                       System.out.println("checking non match");
                       System.out.println(e.start);
                       System.out.println(currentEvent.start.dateTime.split("T")[0]);
                       System.out.println(" "+e.organizer+" "+currentEvent.organizer);
                       System.out.println(e.subject+" "+currentEvent.subject);


                       return  !e.start.equals(currentEvent.start.dateTime.split("T")[0])
                    && e.organizer != currentEvent.organizer
                    && e.subject != currentEvent.subject;
                    }

            )){
              System.out.println("adding new record "+start +"current e "+currentEvent);
              dailyCalenderRecords.get(start).add(currentEvent);
              Connections.addConnection(start, currentEvent);

          }
        }else{
            List<Event> events=new ArrayList<>();
            events.add(currentEvent);
            dailyCalenderRecords.put(start, events);
            Connections.addConnection(start, currentEvent);
            System.out.println(dailyCalenderRecords.get(start).size()+"  updating  record "+start +"current e "+currentEvent);
        }
    }

public static void displayCalender(){

        dailyCalenderRecords.keySet().forEach(e -> {
            try {
                System.out.println(e+" >> "+dailyCalenderRecords.get(e));

                        //.forEach(t -> System.out.println(e + "  T " + t));
            }catch (Exception ex){
                System.out.println("Exception in getting the object");
            }
        });
}
}
