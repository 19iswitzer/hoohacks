package com.example.hoohacks;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private Button weeklyReportButton;
    private Button manageCostButton;
    private Button pastExpensesButton;
    private static double budget = 1000;

    private static GoogleCredentials credentials = null;
    private static ArrayList<Expense> expenses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.curBalNum);
        textView.setText("" + budget);

        InputStream test = null;
        try {
            test = getAssets().open("credentialskey.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            credentials = GoogleCredentials.fromStream(test);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .setProjectId("advance-elixir-272521")
                .build();
        FirebaseApp.initializeApp(options);

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

        updateExpenses();
    }


    public static ArrayList<Expense> updateExpenses() {
        final ArrayList<String>[] data = new ArrayList[]{new ArrayList<>()};

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Firestore db = FirestoreClient.getFirestore();
                    ApiFuture<QuerySnapshot> query = db.collection("users").get();
                    String t = db.collection("users").document("user1").getId();
                    QuerySnapshot querySnapshot = null;
                    try {
                        querySnapshot = query.get();
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
                    data[0] = (ArrayList<String>) documents.get(0).getData().get("expenses");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<String> temp;
        temp = data[0];
        expenses.clear();

        // make global list of expenses to use
        for (int i = 0; i < temp.size(); i++) {
            String[] splitLine = temp.get(i).split(" ");
            expenses.add(new Expense(splitLine[0], splitLine[1], splitLine[2]));
        }
        return expenses;
        // sort based on date expense was done
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

    public static ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public static void subtractFromBudget(double c) {
        budget -= c;
    }
}
