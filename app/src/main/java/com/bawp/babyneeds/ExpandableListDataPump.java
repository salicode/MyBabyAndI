package com.bawp.babyneeds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class ExpandableListDataPump {
    static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<>();

        List<String> BreastFeeding = new ArrayList<>();
        BreastFeeding.add("Breast Feeding");
        //BreastFeeding.add("RIGHT");
        BreastFeeding.add("Stop");
        BreastFeeding.add("Reset");


        List<String> Growth = new ArrayList<>();
        Growth.add("Growth");


        List<String> Diet = new ArrayList<>();
        Diet.add("Diet");
        Diet.add("Increase Breast Milk production");
        Diet.add("Best foods to increase breast milk");

        List<String> Benefits = new ArrayList<>();
        Benefits.add(" Benefits");

        List<String> Pumping = new ArrayList<>();
        Pumping.add(" Pumping");
        Pumping.add("Breast Feeding");
       // Pumping.add("Right");
        Pumping.add("Stop");
        Pumping.add("Reset");

        List<String> Reminder = new ArrayList<>();
        Reminder.add("Reminder");


        List<String> Logs = new ArrayList<>();
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


