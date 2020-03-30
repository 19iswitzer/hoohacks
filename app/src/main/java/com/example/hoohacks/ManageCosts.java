package com.example.hoohacks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class ManageCosts extends AppCompatActivity {
    private Button expenseButton;
    private Button depositButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_costs);

        ArrayList<Expense> expenses = MainActivity.getExpenses();

        expenseButton = findViewById(R.id.expenseButton);
        expenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processExpense();
            }
        });

        depositButton = findViewById(R.id.depositButton);
        depositButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processDeposit();
            }
        });


    }

    private void processDeposit() {
    }

    private void processExpense() {
        EditText editText = findViewById(R.id.editText);
        String text = editText.getText().toString();
        //System.out.println("money: " + text);
        MainActivity.subtractFromBudget(Double.parseDouble(text));
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
    }
}
