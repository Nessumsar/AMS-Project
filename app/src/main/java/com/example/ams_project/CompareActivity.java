package com.example.ams_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CompareActivity extends AppCompatActivity{

    private Spinner filter;
    private ListView infoOfficerA;
    private ListView infoOfficerB;
    private Spinner officerA;
    private Spinner officerB;
    private DBHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        filter = findViewById(R.id.officer_type_filter);
        infoOfficerA = findViewById(R.id.officerA_info);
        infoOfficerB = findViewById(R.id.officerB_info);
        officerA = findViewById(R.id.officerA_select);
        officerB = findViewById(R.id.officerB_select);

        dbHelper = new DBHelper(CompareActivity.this);

        ArrayAdapter<CharSequence> filterAdapter = ArrayAdapter.createFromResource(this,
                R.array.officer_types_array, android.R.layout.simple_spinner_item);
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter.setAdapter(filterAdapter);

        filter.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        Object item = parent.getItemAtPosition(pos);
                        switch (item.toString()) {
                            case "Deployment":
                                //get all officers from db with filter on table type - param "deployment"
                                Toast.makeText(CompareActivity.this, dbHelper.getAllOfficers().toString(), Toast.LENGTH_SHORT).show();
                                break;
                            case "military":
                                //get all officers from db with filter on table type - param "military"
                                break;
                            case "defense":
                                //get all officers from db with filter on table type - param "defense"
                                break;
                            case "logistics":
                                //get all officers from db with filter on table type - param "logistics"
                                break;
                            case "technology":
                                //get all officers from db with filter on table type - param "technology"
                            case "all":
                                //get all officers from db
                        }
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });


    }


}
