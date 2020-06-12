package com.example.ams_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper{

    private static final String OFFICER_TABLE = "OFFICER_TABLE";
    private static final String OFFICER_COL_NAME = "officer_name";
    private static final String OFFICER_COL_TYPE = "officer_type";

    private static final String SKILL_TABLE = "SKILL_TABLE";
    private static final String SKILL_COL_NAME = "skill_name";
    private static final String SKILL_COL_TOOLTIP = "skill_tooltip";
    private static final String SKILL_COL_EFFECT = "skill_effect";


    DBHelper(@Nullable Context context) {
        super(context, "officers.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_officer_table_query = "CREATE TABLE " + OFFICER_TABLE + " ("
                        + OFFICER_COL_NAME + " TEXT NOT NULL UNIQUE PRIMARY KEY, " + OFFICER_COL_TYPE + " TEXT NOT NULL)";
        db.execSQL(create_officer_table_query);

        String create_skill_table_query = "CREATE TABLE " + SKILL_TABLE + " ("
                +SKILL_COL_NAME + " TEXT PRIMARY KEY, " + SKILL_COL_TOOLTIP + " TEXT NOT NULL, " + SKILL_COL_EFFECT + " TEXT NOT NULL)";
        db.execSQL(create_skill_table_query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void resetDB(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + OFFICER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SKILL_TABLE);
        onCreate(db);
    }


    public boolean addOfficerToDB(OfficerModal officerModal){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv1 = new ContentValues();
        cv1.put(OFFICER_COL_NAME,officerModal.getName());
        cv1.put(OFFICER_COL_TYPE, officerModal.getType());

        long insertStatus1 = db.insert(OFFICER_TABLE, null,cv1);

        if(insertStatus1 == -1){
            db.close();
            return false;
        }else{
            db.close();
            return true;
        }
    }


    public List<OfficerModal> getAllOfficers(){

        List<OfficerModal> result = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String get_all_officer_query = "SELECT * FROM " + OFFICER_TABLE;

        Cursor cursor = db.rawQuery(get_all_officer_query,null);

        if(cursor.moveToFirst()){
            do {
                String officer_name = cursor.getString(0);
                String officer_type = cursor.getString(1);

                OfficerModal officerModal = new OfficerModal(officer_name, officer_type);

                result.add(officerModal);

            } while(cursor.moveToNext());
        }else {
            //manage failure
        }
        cursor.close();
        return result;
    }



}
