package com.example.ams_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button compareBtn,assignBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compareBtn = findViewById(R.id.main_compare_btn);
        assignBtn = findViewById(R.id.main_assign_btn);


        compareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent compareIntent = new Intent(MainActivity.this, CompareActivity.class);
                    startActivity(compareIntent);
            }
        });
        assignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent assignIntent = new Intent(MainActivity.this, AssignmentActivity.class);
                startActivity(assignIntent);
            }
        });




    }





}
