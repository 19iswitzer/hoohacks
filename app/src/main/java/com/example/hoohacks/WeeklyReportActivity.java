package com.example.hoohacks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WeeklyReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_report);
        ArrayList<Expense> expenses = MainActivity.getExpenses();
        filterExpenses(expenses);

        getAveragePerDay();
        getMostFrequentlyVisited();
        getUnderOver();
    }

    private void filterExpenses(ArrayList<Expense> expenses) {
        Map<String, Integer> timesVisited = new HashMap<>();
        Format f = new SimpleDateFormat("MM/dd/yy");
        String endDate = f.format(new Date());
        int month = Integer.parseInt(endDate.substring(0, 2));
        int day = Integer.parseInt(endDate.substring(3, 5));
        int year = Integer.parseInt(endDate.substring(6));

        // Account for day over flow
        if (day - 7 < 0) {
            if (month - 1 < 0) {
                month = 12;
                year -= 1;

            } else month -= 1;
        }

        String startDate = month + "/" + day + "/" + year;

        for (Expense expense : expenses) {

            if (expense.getDate().compareTo(startDate)<0){

            }
            // Increment each locations number of times visited
            if (!timesVisited.containsKey(expense.getName())) {
                timesVisited.put(expense.getName(), 1);
            } else {
                timesVisited.replace(expense.getName(), timesVisited.get(expense.getName()) + 1);
            }


        }

    }

    private void getAveragePerDay() {

    }
}
