package com.jscompany.allure.utils;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Silver on 04.04.2016.
 */
public class ReadData {
    public static ArrayList<String[]> get(String args) {

        String cd = new File("").getAbsolutePath();

        String csvFile = cd + File.separator + args;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        ArrayList<String[]> outList = new ArrayList<String[]>();

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use semicolon as separator
                String[] inputLine = new String[3];
                inputLine = line.trim().split(cvsSplitBy);
                outList.add(inputLine);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return outList;
    }
}
