package com.epam.historytracker;

import com.epam.historytracker.model.HistoryData;
import com.epam.historytracker.service.DataProcessor;
import com.epam.historytracker.service.PieChart;
import com.epam.historytracker.service.SqlConnection;
import com.epam.historytracker.service.WindowsNotification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryTrackerApplication {

	public static void main(String[] args) throws SQLException, ParseException {
		SqlConnection connection = new SqlConnection();
		ResultSet resultSet = connection.getData();

		DataProcessor dataProcessor = new DataProcessor();
		List<HistoryData> list = dataProcessor.processSqlData(resultSet);
		//dataProcessor.printData(list);
		Integer totalTime = dataProcessor.calculateTotalTime(list);
		Integer productiveTime = dataProcessor.getProductiveTime(list);
		System.out.println("Productive minutes: " + productiveTime/60);
		System.out.println("Non-Productive minutes: "+ (totalTime-productiveTime)/60);


		//sending notification
		WindowsNotification windowsNotification = new WindowsNotification();
		windowsNotification.showMessage("Productive minutes: " + productiveTime/60 + "\n"
		+ "Non-Productive minutes: "+ (totalTime-productiveTime)/60);

		//creating chart image out of data
		Map<String, Integer> map = new HashMap<>();
		map.put("Productive Hours", productiveTime/60);
		map.put("Non Productive Hours", (totalTime-productiveTime)/60);
		PieChart pieChart = new PieChart();
		pieChart.createChartImage(map);


	}

}
