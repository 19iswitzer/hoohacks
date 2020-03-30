package com.example.hoohacks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
//past expenses
public class PastExpenses extends AppCompatActivity {

    private Switch sortSwitch;
    private boolean sorted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_expenses);

        sortSwitch = findViewById(R.id.switch4);
        sortSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSorts();
            }
        });

        ArrayList<Expense> list = MainActivity.getExpenses();
        String formatted = "";
        for(Expense exp : list) {
            formatted += exp.getDate() + " - " + exp.getName() + " - $" + exp.getCost() + "\n";
        }
        TextView textView = findViewById(R.id.textView);
        textView.setText(formatted);
    }

    private void updateSorts() {
        if(sorted) {
            sorted = false;
            ArrayList<Expense> list = MainActivity.getExpenses();
            String formatted = "";
            for(Expense exp : list) {
                formatted += exp.getDate() + " - " + exp.getName() + " - $" + exp.getCost() + "\n";
            }
            TextView textView = findViewById(R.id.textView);
            textView.setText(formatted);
        } else {
            sorted = true;
            ArrayList<Expense> list;
            list = (ArrayList<Expense>) MainActivity.getExpenses().clone();
            Collections.sort(list);
            String formatted = "";
            for(Expense exp : list) {
                formatted += exp.getDate() + " - " + exp.getName() + " - $" + exp.getCost() + "\n";
            }
            TextView textView = findViewById(R.id.textView);
            textView.setText(formatted);
        }
    }
}
