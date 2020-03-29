package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button weeklyReportButton;
    private Button manageCostButton;
    private Button pastExpensesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weeklyReportButton = findViewById(R.id.weeklyReportButton);
        weeklyReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeeklyReport();
            }
        });

        manageCostButton = findViewById(R.id.manageCostButton);
        manageCostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCostManager();
            }
        });

        pastExpensesButton = findViewById(R.id.pastExpensesButton);
        pastExpensesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPastExpenses();
            }
        });

    }

    private void openPastExpenses() {
        Intent intent = new Intent(this, PastExpenses.class);
        startActivity(intent);
    }

    private void openCostManager() {
        Intent intent = new Intent(this, ManageCosts.class);
        startActivity(intent);
    }


    public void openWeeklyReport() {
        Intent intent = new Intent(this, WeeklyReportActivity.class);
        startActivity(intent);

    }

}
