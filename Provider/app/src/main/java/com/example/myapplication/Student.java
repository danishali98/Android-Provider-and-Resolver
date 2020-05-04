package com.example.myapplication;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.CheckBox;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student implements Serializable, Persistable {

    String id;
    String name;
    CheckBox status;
    boolean present;

    String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Student(String id, String name, boolean present) {
        this.id = id;
        this.name = name;
        //this.status = status;
        this.present = present;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CheckBox getStatus() {
        return status;
    }

    public void setStatus(CheckBox status) {
        this.status = status;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return getClass().getName();
    }

    public void delete(SQLiteDatabase dataStore) {
        String[] args = new String[1];
        args[0] = id;
        dataStore.delete("Student", "Id = ? ", args);
    }

    public void save(SQLiteDatabase dataStore) {

        ContentValues values = new ContentValues();
        values.put("Id", id);
        values.put("Name", name);
        values.put("Present", present);
        values.put("Date", date);

        dataStore.insert("Student", null, values);

    }

    public void load(Cursor dataStore) {


        id = dataStore.getString(dataStore.getColumnIndex("Id"));
        name = dataStore.getString(dataStore.getColumnIndex("Name"));
        present = dataStore.getInt(dataStore.getColumnIndex("Present")) == 1;
        date = dataStore.getString(dataStore.getColumnIndex("Date"));

    }


}

