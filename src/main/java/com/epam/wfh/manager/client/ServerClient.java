package com.epam.wfh.manager.client;

import org.springframework.web.client.RestTemplate;

public class ServerClient {

public static void postServerUpdate(String message){

try {
    final String uri = "http://localhost:8080/workinghour";

    RestTemplate restTemplate = new RestTemplate();

    restTemplate.postForObject(uri, message, String.class);
}catch (Exception ex){
    System.out.println("failure in sending server update"+ex);
}
}

}
