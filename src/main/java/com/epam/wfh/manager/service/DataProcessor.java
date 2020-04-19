package com.epam.wfh.manager.service;

import com.epam.wfh.manager.model.HistoryData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataProcessor {

    private static final Pattern p = Pattern.compile("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}");
    private static final Pattern http = Pattern.compile("https?:\\/\\/(www\\.)?");

    public List<String> getProductiveTermList() {
        List<String> list = new ArrayList<>();
        list.add("code");
        list.add("stackoverflow");
        list.add("bealdung");
        list.add("java");
        list.add("javascript");
        list.add("angular");
        list.add("chrome");
        list.add("go");

        return list;
    }

    public List<HistoryData> processSqlData(ResultSet resultSet) throws SQLException, ParseException {
        SimpleDateFormat formatter5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        List<HistoryData> list = new ArrayList<>();

        while (resultSet.next()) {
            HistoryData data = new HistoryData();
            data.setUrl(resultSet.getString("URL"));
            data.setLastVisitTime(formatter5.parse(resultSet.getString("TIME")));
            data.setDuration(resultSet.getString("DURATION"));
            data.setVisitCount(Integer.valueOf(resultSet.getString("COUNT")));
            list.add(data);
        }
        Collections.sort(list);
        return getHistoryForLast24Hours(list);
    }

    public void printData(List<HistoryData> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("URL: " + list.get(i).getUrl() + " LAST_VISIT_TIME: " + list.get(i).getLastVisitTime()
                + " DURATION: " + list.get(i).getDuration() + " COUNT: " + list.get(i).getVisitCount());
        }
    }

    public List<HistoryData> getHistoryForLast24Hours(List<HistoryData> list) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -24);
        Date date = calendar.getTime();
        List<HistoryData> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLastVisitTime().after(date)) {
                result.add(list.get(i));
            }
        }
        return result;
    }

    public Integer getProductiveTime(List<HistoryData> list) {
        List<String> productiveTermList = getProductiveTermList();
        Integer seconds = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < productiveTermList.size(); j++) {
                if (list.get(i).getUrl().contains(productiveTermList.get(j))) {
                    seconds += Integer.valueOf(list.get(i).getDuration());
                    break;
                }
            }
        }
        return seconds;
    }

    public Integer calculateTotalTime(List<HistoryData> list) {
        Integer seconds = 0;
        for (int i = 0; i < list.size(); i++) {
            seconds += Integer.valueOf(list.get(i).getDuration());
        }
        return seconds;
    }

    public Map<String, Integer> getWebsiteVisitFrequency(List<HistoryData> list) {
        Map<String, Integer> map = new TreeMap<>();
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Matcher matcher = p.matcher(list.get(i).getUrl());
            if (matcher.find()) {
                temp.add(removeExtraFromString(matcher.group(0)));
            }
        }
        for (int i = 0; i < temp.size(); i++) {
            if(map.get(temp.get(i)) != null) continue;
            int count = search(temp, temp.get(i));
            map.put(temp.get(i), count);

        }
        return map;
    }

    public int search(List<String> list, String searchTerm)
    {
        int counter = 0;
        for (int j = 0; j < list.size(); j++)
            if (searchTerm.equals(list.get(j)))
                counter++;
        return counter;
    }

    public String removeExtraFromString(String s){
        s = s.split("https?:\\/\\/(www\\.)?")[1];
        return s;
    }
}
