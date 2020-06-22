package com.example.ams_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Xml;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterViewAnimator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ApiActivity extends AppCompatActivity implements View.OnClickListener {

    Button createBtn, readBtn, updateBtn, deleteBtn;
    TextView resultText;
    ListView listView;
    ImageView imageView;
    TextView textId, textName, textEmail;
    ArrayAdapter adp;
    ArrayList<UserModal> users_data = new ArrayList<>();

    private final static String SERVER_URL = "https://reqres.in/api/";
    private final static String DELETE_URL = "https://jsonplaceholder.typicode.com/posts/1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_api);
        createBtn = findViewById(R.id.createbtn);
        readBtn = findViewById(R.id.readBtn);
        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        imageView = findViewById(R.id.imageView);
        textId = findViewById(R.id.profileId);
        textName = findViewById(R.id.profileName);
        textEmail = findViewById(R.id.profileEmail);
        listView = findViewById(R.id.resultList);
        resultText = findViewById(R.id.resultText);

        registerForContextMenu(listView);

        createBtn.setOnClickListener(this);
        readBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ApiActivity.this, "Email: " + users_data.get(position).getProfilePicture(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, v.getId(), 0, "View Details"); //.setOnMenuItemClickListener()
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            TextView id,name,email;
            ImageView pic;

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inf= getLayoutInflater();

            View dialogView = inf.inflate(R.layout.user_dialog,null);

            id = dialogView.findViewById(R.id.profileId);
            name = dialogView.findViewById(R.id.profileName);
            email = dialogView.findViewById(R.id.profileEmail);
            pic = dialogView.findViewById(R.id.profilePicture);

            alertDialogBuilder.setView(dialogView);

            String fullname = users_data.get(info.position).getFirstName()+" "+users_data.get(info.position).getLastName();

            id.setText(String.valueOf(users_data.get(info.position).getId()));
            name.setText(fullname);
            email.setText(users_data.get(info.position).getEmail());

            Picasso.get().load(users_data.get(info.position).getProfilePicture()).into(pic);

            final AlertDialog userInfoDialog = alertDialogBuilder.create();

            userInfoDialog.show();
            return true;
    }


    private void updateViews() {
        adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,users_data);
        listView.setAdapter(adp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.createbtn:
                JSONObject postData = new JSONObject();
                try {
                    postData.put("name", "Lukas");
                    postData.put("job", "Student");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest myCreateReq = new JsonObjectRequest(Request.Method.POST, SERVER_URL+"users", postData, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        resultText.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                VolleyNetwork.getInstance(this.getApplicationContext()).addToRequestQueue(myCreateReq);
                break;
            case R.id.readBtn:
                JsonObjectRequest myGetReq = new JsonObjectRequest(Request.Method.GET, SERVER_URL + "users?page=2", null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray dataObject = response.getJSONArray("data");
                            for(int i=0; i < dataObject.length();i++){
                                JSONObject userObject = dataObject.getJSONObject(i);

                                UserModal tempUser = new UserModal(userObject.getInt("id"),
                                        userObject.getString("email"),
                                        userObject.getString("first_name"),
                                        userObject.getString("last_name"),
                                        userObject.getString("avatar"));
                                users_data.add(tempUser);

                                Picasso.get().load(userObject.getString("avatar")).into(imageView);
                                updateViews();

                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                VolleyNetwork.getInstance(this.getApplicationContext()).addToRequestQueue(myGetReq);
                break;
            case R.id.updateBtn:
                JSONObject putData = new JSONObject();
                try {
                    putData.put("name", "Lukas");
                    putData.put("job", "Developer");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest myPutReq = new JsonObjectRequest(Request.Method.PUT, SERVER_URL+"users/2", putData, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        resultText.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                VolleyNetwork.getInstance(this.getApplicationContext()).addToRequestQueue(myPutReq);
                break;
            case R.id.deleteBtn:
                JSONObject deleteData = new JSONObject();
                try {
                    deleteData.put("name", "Lukas");
                    deleteData.put("job", "Student");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest myDeleteReq = new JsonObjectRequest(Request.Method.DELETE, DELETE_URL, deleteData, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        resultText.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                VolleyNetwork.getInstance(this.getApplicationContext()).addToRequestQueue(myDeleteReq);
                break;
        }
    }


}
