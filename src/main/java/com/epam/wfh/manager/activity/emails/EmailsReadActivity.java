package com.epam.wfh.manager.activity.emails;

import com.epam.wfh.manager.outlook.graph.Graph;
import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.models.extensions.Message;
import com.microsoft.graph.requests.extensions.GraphServiceClient;
import com.microsoft.graph.requests.extensions.IMessageCollectionPage;

import java.util.HashMap;
import java.util.Map;

public class EmailsReadActivity {

    public static Map<String, Integer> readUnreadCount=new HashMap<>();
    public static int readCount=0;
    public static int unreadCount=0;

    public static Message getEmails(String accessToken){
try {
    IMessageCollectionPage page = Graph.getAllEmails(accessToken);
    if (page.getCurrentPage().size() != 0) {

        page.getCurrentPage().forEach(e -> {
                Message mg=Graph.getEmail(e.id);
            System.out.println(">>"+mg);
            System.out.println(mg.isRead);
            if(mg.isRead!=null) {
                if (mg.isRead.booleanValue()) {
                    readCount++;
                } else {
                    System.out.println(e.receivedDateTime.getTime());
                    unreadCount++;
                }
            }
        });
    }
}catch (Exception ex){
    System.out.println("Exception in parsing message"+ex);
    ex.printStackTrace();
}
        return null;
    }

}
