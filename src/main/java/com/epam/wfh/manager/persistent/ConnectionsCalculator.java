package com.epam.wfh.manager.persistent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConnectionsCalculator {
    public static Map<String, Integer> connectionStrength=new HashMap<>();
    public static Set<String> newConnections=new HashSet<>();

    public static void addOrUpdateConnection(String with){

        if(connectionStrength.containsKey(with)){

            int val=connectionStrength.get(with)+1;
            connectionStrength.remove(with);
            connectionStrength.put(with, val);
            if(newConnections.contains(with)){
                newConnections.remove(with);
            }
        }else{
            connectionStrength.put(with, 1);
            newConnections.add(with);
        }

    }

    public static void serializeConnection() throws IOException {
        StringBuffer app= new StringBuffer();
        connectionStrength.keySet().forEach(
                key ->{
                    app.append(key+"="+connectionStrength.get(key)+", ");
                }
        );
        if( app.length() > 0 )
            app.deleteCharAt( app.length() - 1 );

        FileWritter.writeFile("connections-list", app.toString(), "r");
    }


    public static void deserializeConnection() throws IOException {
        try {
            connectionStrength.keySet().forEach(c -> connectionStrength.remove(c));
            String value = FileWritter.fileReader("connections-list");
            String[] all = value.split(",");
            for (String s : all) {
                s = s.trim();
                String[] v = s.split("=");
                if (v[1].contains("=")) {
                    v[1] = v[1].substring(0, v[1].indexOf("="));
                }
                connectionStrength.put(v[0], Integer.parseInt(v[1]));
            }
        }catch (Exception e){
            System.out.println("unable to de");
        }
    }


}
