package com.example.ams_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ApiActivity extends AppCompatActivity implements View.OnClickListener {

    Button createBtn, readBtn, updateBtn, deleteBtn;
    ListView listView;
    ImageView imageView;
    TextView textId, textName, textEmail;
    ArrayAdapter adp;
    ArrayList<UserModal> users_data;

    private final static String SERVER_URL = "https://reqres.in/api/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_api);
        createBtn = findViewById(R.id.createbtn);
        readBtn = findViewById(R.id.readBtn);
        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        imageView = findViewById(R.id.profilePicture);
        textId = findViewById(R.id.profileId);
        textName = findViewById(R.id.profileName);
        textEmail = findViewById(R.id.profileEmail);
        listView = findViewById(R.id.resultlist);

        registerForContextMenu(listView);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

    private void updateViews() {
        adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,users_data);
        adp.setDropDownViewResource(android.R.layout.simple_list_item_1);
        listView.setAdapter(adp);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.createbtn:

            case R.id.readBtn:

            case R.id.updateBtn:

            case R.id.deleteBtn:


        }
    }
}
