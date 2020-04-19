package com.epam.wfh.manager.service;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Map;

public class PieChart {

    private PieDataset createData(Map<String, Integer> map, String timeUnit) {
        DefaultPieDataset data = new DefaultPieDataset();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            data.setValue(entry.getKey().toUpperCase() + ":\n " + Double.valueOf(map.get(entry.getKey()))
                    + " " + timeUnit.toLowerCase(), Double.valueOf(map.get(entry.getKey())));
        }
        return data;
    }

    private JFreeChart createChart(PieDataset data) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Redifining WFH: Analytics", data, true, true, true);
        return chart;
    }

    public void createDemoPanel(Map<String, Integer> map, String timeUnit){
        JFreeChart chart = createChart(createData(map, timeUnit));
        createImageOutOfChart(chart);
    }

    public void createChartImage(Map<String, Integer> map, String timeUnit){
        createDemoPanel(map, timeUnit);
    }

    public void createImageOutOfChart(JFreeChart chart) {
        BufferedImage objBufferedImage = chart.createBufferedImage(600, 800);
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        try {
            ImageIO.write(objBufferedImage, "png", bas);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] byteArray = bas.toByteArray();

        InputStream in = new ByteArrayInputStream(byteArray);
        BufferedImage image = null;
        try {
            image = ImageIO.read(in);
            FileUtility fileUtility = new FileUtility();
            File outputfile = new File(fileUtility.getHomePath() + fileUtility.getWfhPath() + "image.png");
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}