package com.epam.wfh.manager.persistent;

import com.microsoft.graph.models.extensions.DateTimeTimeZone;
import com.microsoft.graph.models.extensions.Event;
import com.microsoft.graph.models.extensions.Recipient;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Connections {


    public static Map<String, List<Event>> dailyConnectionRecords=new HashMap<>();


    public static void addConnection(String time , Event with){

        if(dailyConnectionRecords.containsKey(time)){
            System.out.println("connection add new"+time);
            if(dailyConnectionRecords.get(time).stream().noneMatch(
                    e ->!e.organizer.equals(with.organizer)
            )){
                System.out.println("append");
                dailyConnectionRecords.get(time).add(with);

            }
        }else{
            System.out.println("adding new connection"+with);

            List<Event> names=new ArrayList<>();
            names.add(with);
            dailyConnectionRecords.put(time,names);

        }

    }

    public static void dispalyConnections() {

        dailyConnectionRecords.keySet().forEach(

                e -> {try {
                    dailyConnectionRecords.get(e).forEach(t -> System.out.println(e + "  with >> " + t.organizer));
                }catch (Exception em){
                    System.out.println("Eception e"+em);
                }
                }
        );


    }


}
