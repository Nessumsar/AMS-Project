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
    private static final String OFFICER_COL_ID = "OFFICER_COL_ID";
    private static final String OFFICER_COL_NAME = "OFFICER_COL_NAME";
    private static final String OFFICER_COL_TYPE = "OFFICER_COL_TYPE";
    private static final String SKILL_TABLE = "SKILL_TABLE";
    private static final String SKILL_COL_ID = "SKILL_COL_ID";
    private static final String SKILL_COL_NAME = "SKILL_COL_NAME";
    private static final String SKILL_COL_TOOLTIP = "SKILL_COL_TOOLTIP";
    private static final String SKILL_COL_EFFECT = "SKILL_COL_EFFECT";

    DBHelper(@Nullable Context context) {
        super(context, "officers.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_officer_table = "CREATE TABLE " + OFFICER_TABLE + " ("
                + OFFICER_COL_ID + " INTEGER UNIQUE PRIMARY KEY, "
                + OFFICER_COL_NAME + " TEXT UNIQUE NOT NULL, "
                + OFFICER_COL_TYPE + " TEXT NOT NULL)";
        String query_skill_table = "CREATE TABLE " + SKILL_TABLE + " ("
                + SKILL_COL_ID + " INTEGER, "
                + SKILL_COL_NAME + " TEXT NOT NULL, "
                + SKILL_COL_TOOLTIP + " TEXT NOT NULL, "
                + SKILL_COL_EFFECT + " TEXT NOT NULL, " +
                "FOREIGN KEY (" + SKILL_COL_ID + ") REFERENCES "+OFFICER_TABLE+"("+OFFICER_COL_ID+"))";
        db.execSQL(query_officer_table);
        db.execSQL(query_skill_table);
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

        ContentValues cv = new ContentValues();
        cv.put(OFFICER_COL_ID, officerModal.getId());
        cv.put(OFFICER_COL_NAME,officerModal.getName());
        cv.put(OFFICER_COL_TYPE, officerModal.getType());
        long insertStatus = db.insert(OFFICER_TABLE, null,cv);

        if(insertStatus == -1) {
            db.close();
            return false;
        }else{
            db.close();
            return true;
        }
    }

    public boolean addSkillToDB(SkillModal skillModal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(SKILL_COL_ID, skillModal.getId());
        cv.put(SKILL_COL_NAME, skillModal.getName());
        cv.put(SKILL_COL_TOOLTIP, skillModal.getTooltip());
        cv.put(SKILL_COL_EFFECT, skillModal.getEffect());

        long insertStatus = db.insert(SKILL_TABLE, null, cv);

        if (insertStatus == -1){
            db.close();
            return false;
        }else{
            db.close();
            return true;
        }
    }

    public List<String> getOfficerNameListByType(String selectedType){
        List<String> result = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String get_all_officer_query = "SELECT "+OFFICER_COL_NAME+
                " FROM "+OFFICER_TABLE+
                " WHERE "+OFFICER_COL_TYPE+
                " LIKE '" +selectedType+ "'";

        Cursor cursor = db.rawQuery(get_all_officer_query,null);
        if(cursor.moveToFirst()){
            do {
                String name = cursor.getString(0);
                result.add(name);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public long getOfficerIdByOfficerName(String officerName){
        long result = -1;
        SQLiteDatabase db = this.getReadableDatabase();
        String get_id_by_name = "SELECT "+OFFICER_COL_ID+" FROM "+OFFICER_TABLE+" WHERE "+OFFICER_COL_NAME+ " LIKE '" + officerName + "' ";
        Cursor cursor = db.rawQuery(get_id_by_name, null);
        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                result = id;
            } while(cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public List<SkillModal> getSkillSet(String officerName){
        List<SkillModal> result = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        long officerId = getOfficerIdByOfficerName(officerName);

        String get_skill_set_query = "SELECT * FROM "+SKILL_TABLE+" WHERE "+SKILL_COL_ID+" LIKE "+officerId+" ";
        Cursor cursor = db.rawQuery(get_skill_set_query,null);
        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String tooltip = cursor.getString(2);
                String effect = cursor.getString(3);
                SkillModal skillModal = new SkillModal(id, name, tooltip, effect);
                result.add(skillModal);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public List<String> getSkillEffects(String officerName){
        List<String> result = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        long officerId = getOfficerIdByOfficerName(officerName);

        String get_all_skills_query = "SELECT "+SKILL_COL_EFFECT+" FROM "+SKILL_TABLE+" WHERE "+SKILL_COL_ID+" LIKE "+officerId+" ";
        Cursor cursor = db.rawQuery(get_all_skills_query,null);
        if(cursor.moveToFirst()){
            do {
                String effect = cursor.getString(0);
                result.add(effect);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return result;
    }
}
