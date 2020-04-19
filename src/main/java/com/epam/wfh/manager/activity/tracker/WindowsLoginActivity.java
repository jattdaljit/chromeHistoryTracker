package com.epam.wfh.manager.activity.tracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WindowsLoginActivity {


    public static void findWindowsLoginActivity() throws InterruptedException, IOException {

        Process p;
        StringBuilder sb = new StringBuilder();

        p = Runtime.getRuntime().exec("quser");
        p.waitFor();

        BufferedReader reader
                = new BufferedReader(new InputStreamReader(p.getInputStream()));

        String line = "";
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        System.out.println(sb);

    }

}
