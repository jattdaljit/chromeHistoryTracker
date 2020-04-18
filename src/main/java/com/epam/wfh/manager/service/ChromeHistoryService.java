package com.epam.wfh.manager.service;

import com.epam.wfh.manager.model.ChromeHistoryTimes;
import com.epam.wfh.manager.model.HistoryData;

import java.sql.ResultSet;
import java.util.List;
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
        return chromeHistoryTimes;
    }
}
