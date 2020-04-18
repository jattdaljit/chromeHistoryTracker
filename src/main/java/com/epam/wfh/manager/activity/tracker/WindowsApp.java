package com.epam.wfh.manager.activity.tracker;

import java.io.IOException;

public class WindowsApp {

    public static void main(String [] args){

        try {
            WindowsLoginActivity.findWindowsLoginActivity();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
