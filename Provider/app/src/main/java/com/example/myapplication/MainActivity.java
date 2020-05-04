package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    private CheckBox checkBox;
    ListAdapter l;
    ArrayList<Student> arraylist = new ArrayList<>();
    ArrayList<Student> temp = new ArrayList<>();


    StudentDbHelper mydb;
    Student db;
    SQLiteDatabase datastore;
    Cursor cursor_datastore;

    public ArrayList<Student> getArraylist() {
        return arraylist;
    }

    public void setArraylist(ArrayList<Student> arraylist) {
        this.arraylist = arraylist;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String date = getIntent().getStringExtra("Date");
        Toast.makeText(getApplicationContext(), date, Toast.LENGTH_LONG).show();

        mydb = new StudentDbHelper(this);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        feedData(arraylist);

        final ListAdapter arrayAdapter = new ListAdapter(this, R.layout.adapter_view, arraylist);
        listView.setAdapter(arrayAdapter);

        checkBox = findViewById(R.id.checkBox2);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkBox.isChecked()) {
                    arrayAdapter._selectAll(arraylist);
                } else {
                    arrayAdapter._unselectAll(arraylist);
                }
            }
        });

        final EditText editText;
        editText = findViewById(R.id.editText2);
        editText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.s1, 0, 0, 0);

        Button button;
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayAdapter.getFilter().filter(editText.getText().toString());
            }
        });

        Button savebutton;
        savebutton = findViewById(R.id.save);
        String AUTHORITY = "com.example.myapplication";
        Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
        final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendEncodedPath("Student").build();

        Log.d("URI", CONTENT_URI.toString());

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < arraylist.size(); i++) {
                    arraylist.get(i).setDate(date);
                    ContentValues cv = new ContentValues();
                    cv.put("Id", arraylist.get(i).id.toString());
                    cv.put("Name", arraylist.get(i).name);
                    cv.put("Present", arraylist.get(i).present);
                    cv.put("Date", arraylist.get(i).date);
                    try {
                        getContentResolver().insert(CONTENT_URI, cv);
                        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();

                    } catch (SQLException e) {
                        Toast.makeText(getApplicationContext(), "Exception", Toast.LENGTH_SHORT).show();
                    }
                }
                arrayAdapter._unselectAll(arraylist);

            }
        });


        Button updatebutton;
        updatebutton = findViewById(R.id.update);
        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String where = "Date=?";
                String[] args = {date};
                int status = getContentResolver().delete(CONTENT_URI, where, args);
                if (status < 0) {
                    Toast.makeText(getApplicationContext(), "Exception", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Start Updating", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button deletebutton;
        deletebutton = findViewById(R.id.delete);
        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String where = "Date=?";
                String[] args = {date};
                int status = getContentResolver().delete(CONTENT_URI, where, args);
                if (status < 0) {
                    Toast.makeText(getApplicationContext(), "Exception", Toast.LENGTH_SHORT).show();
                } else {
                    Log.w("DELETED", "SUCCESS");
                    Toast.makeText(getApplicationContext(), "DELETED", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("listview", arraylist);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        arraylist = (ArrayList<Student>) savedInstanceState.getSerializable("listview");
    }

    public void feedData(ArrayList<Student> arraylist) {

        Boolean c = false;

        Student s1 = new Student("1", "Hamza - 17L-4001", c);
        Student s2 = new Student("2", " Hammad - 17L-4417", c);
        Student s3 = new Student("3", " Shehzar - 17L-4177", c);
        Student s4 = new Student("4", " Danish - 17L-6317", c);
        Student s5 = new Student("5", " Faheem - 17L-4037", c);

        Student s6 = new Student("6", " Ali - 17L-4011", c);
        Student s7 = new Student("7", " Ahmad 17L-4414", c);
        Student s8 = new Student("8", " Mustafa 17L-3177", c);
        Student s9 = new Student("9", " Shahid - 17L-2317", c);
        Student s10 = new Student("10", " Hamna - 17L-1037", c);

        Student s11 = new Student("11", " Hira - 17L-4101", c);
        Student s12 = new Student("12", " Aiman 17L-4497", c);
        Student s13 = new Student("13", " Haroon 17L-4677", c);
        Student s14 = new Student("14", " Maryam - 17L-7317", c);
        Student s15 = new Student("15", " Hania - 17L-4837", c);

        Student s16 = new Student("16", " Seth - 17L-4021", c);
        Student s17 = new Student("17", " Rogers 17L-4447", c);
        Student s18 = new Student("18", " Dania 17L-4117", c);
        Student s19 = new Student("19", " Bilal - 17L-6117", c);
        Student s20 = new Student("20", " Arshad - 17L-4137", c);

        arraylist.add(s1);
        arraylist.add(s2);
        arraylist.add(s3);
        arraylist.add(s4);
        arraylist.add(s5);

        arraylist.add(s6);
        arraylist.add(s7);
        arraylist.add(s8);
        arraylist.add(s9);
        arraylist.add(s10);

        arraylist.add(s11);
        arraylist.add(s12);
        arraylist.add(s13);
        arraylist.add(s14);
        arraylist.add(s15);

        arraylist.add(s16);
        arraylist.add(s17);
        arraylist.add(s18);
        arraylist.add(s19);
        arraylist.add(s20);
    }

}
