package com.epam.wfh.manager.persistent;

import com.microsoft.graph.models.extensions.Event;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CalenderSerDeSer {

    public static void serialize(Map<String, List<Event>> dailyCalenderRecords) throws IOException {

        StringBuffer rep= new StringBuffer("{");

        dailyCalenderRecords.keySet().forEach(e -> {
            try {
                rep.append(e);
                rep.append(": [");
                dailyCalenderRecords.get(e).stream().forEach(t ->{
                    rep.append(t.getRawObject()+", ");
                });
                rep.append("]");
                System.out.println(e+" >> "+dailyCalenderRecords.get(e));

                //.forEach(t -> System.out.println(e + "  T " + t));
            }catch (Exception ex){
                System.out.println("Exception in getting the object");
            }
        });
        rep.append("}");
        FileWritter.writeFile("calender", rep.toString(), "r");
    }

}
