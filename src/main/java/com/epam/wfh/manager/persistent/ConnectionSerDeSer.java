package com.epam.wfh.manager.persistent;

import com.microsoft.graph.models.extensions.Event;
import com.microsoft.graph.models.extensions.Recipient;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ConnectionSerDeSer {

    public static void connectionSerializer(Map<String, List<Event>> dailyCalenderRecords) throws IOException {

        StringBuffer app= new StringBuffer("{");

        dailyCalenderRecords.keySet().forEach(

                key -> {
                    app.append(","+ key+" : [");
                    dailyCalenderRecords.get(key).forEach(

                            value ->{
                                app.append(value.organizer.emailAddress.address+", ");
                            }
                    );
                    app.append(" ]");
                }
        );


        app.append("}");

        FileWritter.writeFile("connections",app.toString(), "a");


    }
}
