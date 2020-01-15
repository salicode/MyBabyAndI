package com.bawp.babyneeds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> BreastFeeding = new ArrayList<String>();
        BreastFeeding.add("LEFT");
        BreastFeeding.add("RIGHT");


        List<String> Growth = new ArrayList<String>();
        Growth.add("Growth");


        List<String> Diet = new ArrayList<String>();
        Diet.add("Diet");

        List<String> Benefits = new ArrayList<String>();
        Benefits.add(" Benefits");

        List<String> Pumping = new ArrayList<String>();
        Pumping.add(" Pumping");

        List<String> Reminder = new ArrayList<String>();
        Reminder.add("Reminder");


        List<String> Logs = new ArrayList<String>();
        Logs.add("Logs");


        expandableListDetail.put("BREAST FEEDING",BreastFeeding);
        expandableListDetail.put("GROWTH", Growth);
        expandableListDetail.put("DIET", Diet);
        expandableListDetail.put("BENEFITS", Benefits);
        expandableListDetail.put("PUMPING", Pumping);
        expandableListDetail.put("REMINDER", Reminder);
        expandableListDetail.put("LOGS", Logs);
        return expandableListDetail;
    }
}


