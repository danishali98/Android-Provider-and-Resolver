package com.example.myapplication;

import android.content.SharedPreferences;
import android.database.*;
import android.database.sqlite.*;

public interface Persistable {

    void save(SQLiteDatabase dataStore);

    void load(Cursor dataStore);

    void delete(SQLiteDatabase dataStore);

    String getId();

    String getType();
}

