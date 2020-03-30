package com.example.hoohacks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.apache.commons.lang3.ObjectUtils;

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


        TextView amountSpentLabel = findViewById(R.id.amountSpentLabel);
        amountSpentLabel.setText(getAveragePerDay(expenses));

        TextView mostFrequentLabel = findViewById(R.id.mostFrequentLabel);
        mostFrequentLabel.setText(getMostFrequentlyVisited(expenses));

        TextView onOrOffBudgetLabel = findViewById(R.id.onOrOffBudgetLabel);
        onOrOffBudgetLabel.setText((getUnderOver()));


    }

    // todo get budget from somewhere
    private String getUnderOver() {
        int tempBudget = 200;
        int tempBalance = 100;

        if (tempBalance <= tempBalance) {
            return "Stayed under budget by $" + (tempBudget - tempBalance) + ". Good Job!";
        } else {
            return "went over budget by $" + (tempBudget - tempBalance) + ". Was there anything you could have cut out to help you meet your goal?";
        }
    }

    private String getMostFrequentlyVisited(ArrayList<Expense> expenses) {
        Map<String, Integer> timesVisited = new HashMap<>();
        int maxVis = -1;
        String maxKey = null;

        // put everything in the map with key being name and value being times visited
        for (Expense expense : expenses) {
            // Increment each locations number of times visited
            if (!timesVisited.containsKey(expense.getName())) {
                timesVisited.put(expense.getName(), 1);
            } else {
                timesVisited.replace(expense.getName(), timesVisited.get(expense.getName()) + 1);
            }
        }

        // find the place visited the most
        for (String key : timesVisited.keySet()) {
            if (timesVisited.get(key) > maxVis) {
                maxKey = key;
                maxVis = timesVisited.get(key);
            }
        }

        if (maxKey == null) {
            return "Your most frequently visited place this week: N/A";

        } else
            return "Your most frequently visited place this week: " + maxKey;

    }

    private void filterExpenses(ArrayList<Expense> expenses) {
        Format f = new SimpleDateFormat("MM/dd/yy");
        String endDate = f.format(new Date());

        int indexOfLastSlash = endDate.indexOf("/");
        int month = Integer.parseInt(endDate.substring(0, indexOfLastSlash));

        int day = Integer.parseInt(endDate.substring(indexOfLastSlash + 1, endDate.indexOf("/", indexOfLastSlash + 1)));
        indexOfLastSlash = endDate.indexOf("/", indexOfLastSlash + 1);
        int year = Integer.parseInt(endDate.substring(indexOfLastSlash + 1));

        ArrayList<Expense> copyArr = (ArrayList<Expense>) expenses.clone();

        // Account for day over flow
        if (day - 7 < 0) {
            if (month - 1 < 0) {
                month = 12;
                year -= 1;

            } else month -= 1;
        }

        String startDate = month + "/" + day + "/" + year;

        for (Expense expense : copyArr) {

            // if it is out of the date range don't include it
            if (expense.getDate().compareTo(startDate) < 0 || expense.getDate().compareTo(endDate) > 0) {
                expenses.remove(expense);
            }

        }
    }

    private String getAveragePerDay(ArrayList<Expense> expenses) {
        int sum = 0;
        for (Expense expense : expenses) {
            sum += expense.getCost();
        }

        return "Your average amount spent daily this past week was: " + (sum / 7);
    }
}
