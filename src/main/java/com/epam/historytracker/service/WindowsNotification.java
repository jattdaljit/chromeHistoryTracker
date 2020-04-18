package com.epam.historytracker.service;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class WindowsNotification {

    public void showMessage(String message){
        if (SystemTray.isSupported()) {
            WindowsNotification td = new WindowsNotification();
            try {
                td.displayTray(message);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("System tray not supported!");
        }
    }

    public void displayTray(String message) throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();

        //Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        Image image = Toolkit.getDefaultToolkit().createImage("src\\main\\resources\\static\\wfh.png");


        ActionListener listener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("activated click event");
                FileUtility fileUtility = new FileUtility();
                File chartImage = new File(fileUtility.getHomePath() + fileUtility.getWfhPath() + "image.png");
                Desktop dt = Desktop.getDesktop();
                try {
                    System.out.println("opening file");
                    dt.open(chartImage);
                    System.exit(0);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        };

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        trayIcon.addActionListener(listener);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("Redifining WFH");

        tray.add(trayIcon);;
        trayIcon.setImage(image);
        trayIcon.displayMessage("Redifinig WFH", message + "\n Click to see detailed chart", TrayIcon.MessageType.INFO);



    }

}
