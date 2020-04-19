package com.epam.wfh.manager.service;

import com.epam.wfh.manager.model.ChromeHistoryTimes;
import com.epam.wfh.manager.model.HistoryData;
import org.springframework.web.client.RestTemplate;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class ChromeHistoryService implements Callable {

    private Object ChromeTimes;

    @Override
    public Object call() throws Exception {
        SqlConnection connection = new SqlConnection();
        ResultSet resultSet = connection.getData();

        DataProcessor dataProcessor = new DataProcessor();
        List<HistoryData> list = dataProcessor.processSqlData(resultSet);

        ChromeHistoryTimes chromeHistoryTimes = new ChromeHistoryTimes();
        Integer totalTime = dataProcessor.calculateTotalTime(list);
        Integer productive = dataProcessor.getProductiveTime(list);
        chromeHistoryTimes.setProductiveTime(productive);
        chromeHistoryTimes.setNonProductiveTime(totalTime - productive);
        Map<String,Integer> map = dataProcessor.getWebsiteVisitFrequency(list);
        postUpdateToServer(map);
        return chromeHistoryTimes;
    }


    public void postUpdateToServer(Map<String,Integer> map){
        try {
            final String uri = "http://localhost:8080/chrome-history";

            RestTemplate restTemplate = new RestTemplate();

            restTemplate.postForObject(uri, map, Map.class);
        }catch (Exception ex){
            System.out.println("failure in sending server update from history chrome"+ex);
        }

    }
}
