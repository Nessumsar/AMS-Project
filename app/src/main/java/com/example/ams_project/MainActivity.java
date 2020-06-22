package com.example.ams_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button compareBtn, apiBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compareBtn = findViewById(R.id.main_compare_btn);
        apiBtn = findViewById(R.id.main_api_btn);


        compareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent compareIntent = new Intent(MainActivity.this, CompareActivity.class);
                    startActivity(compareIntent);
            }
        });
        apiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent assignIntent = new Intent(MainActivity.this, ApiActivity.class);
                startActivity(assignIntent);
            }
        });




    }





}
