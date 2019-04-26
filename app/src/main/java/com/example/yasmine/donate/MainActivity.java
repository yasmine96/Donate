package com.example.yasmine.donate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button health;
    Button loan;
    Button education;
    Button projects;
    Button brides;
    Button appliances;
    Button village;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        health = findViewById(R.id.health);
        loan= findViewById(R.id.loan);
        education = findViewById(R.id.education);
        projects = findViewById(R.id.projects);
        brides = findViewById(R.id.brides);
        appliances = findViewById(R.id.appliances);
        village = findViewById(R.id.village);

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , HealthActivity.class);
                startActivity(intent);
            }
        });

        loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , LoanActivity.class);
                startActivity(intent);
            }
        });

        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , EducationActivity.class);
                startActivity(intent);
            }
        });

        projects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , ProjectsActivity.class);
                startActivity(intent);
            }
        });

        /*brides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , bridesActivity.class);
                startActivity(intent);
            }
        });

        appliances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , appliancesActivity.class);
                startActivity(intent);
            }
        });

        village.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , villageActivity.class);
                startActivity(intent);
            }
        });*/

    }
}
