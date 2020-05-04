package com.example.resolver;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        getDataFromProvider();
        super.onResume();
    }

    void getDataFromProvider()
    {
        TableLayout table = findViewById(R.id.att_table);
        table.removeAllViews();

        TableRow.LayoutParams textViewParam = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT,1.0f);

        TableRow header=new TableRow(this);

        TextView temp1=new TextView(this);
        temp1.setText("Id");
        temp1.setTypeface(null, Typeface.BOLD);
        temp1.setGravity(Gravity.CENTER);
        temp1.setLayoutParams(textViewParam);

        TextView temp2=new TextView(this);
        temp2.setText("Name");
        temp2.setTypeface(null, Typeface.BOLD);
        temp2.setGravity(Gravity.CENTER);
        temp2.setLayoutParams(textViewParam);

        TextView temp3=new TextView(this);
        temp3.setText("Status");
        temp3.setTypeface(null, Typeface.BOLD);
        temp3.setGravity(Gravity.CENTER);
        temp3.setLayoutParams(textViewParam);

        TextView temp4=new TextView(this);
        temp4.setText("Date");
        temp4.setTypeface(null, Typeface.BOLD);
        temp4.setGravity(Gravity.CENTER);
        temp4.setLayoutParams(textViewParam);

        header.addView(temp1);
        header.addView(temp2);
        header.addView(temp3);
        header.addView(temp4);

        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        header.setLayoutParams(tableRowParams);

        table.addView(header);

        Uri contentUri = Uri.parse("content://com.example.myapplication/Student");
        Cursor cursor = getContentResolver().query(contentUri, null, null, null,
                null);

        if (cursor != null) {
            cursor.moveToFirst();
            int i = 0;
            // Loop in the cursor to get each row.
            do {

                TableRow row = new TableRow(this);
                // Get column 1 value.
                int column1Index = cursor.getColumnIndex("Id");
                String column1Value = cursor.getString(column1Index);
                TextView id = new TextView(this);
                id.setText(column1Value);
                id.setGravity(Gravity.CENTER);
                id.setLayoutParams(textViewParam);

                int column2Index = cursor.getColumnIndex("Name");
                String column2Value = cursor.getString(column2Index);
                TextView name = new TextView(this);
                name.setText(column2Value);
                name.setGravity(Gravity.CENTER);
                name.setLayoutParams(textViewParam);

                int column3Index = cursor.getColumnIndex("Present");
                String column3Value = cursor.getString(column3Index);
                TextView status = new TextView(this);
                status.setText(column3Value);
                status.setGravity(Gravity.CENTER);
                status.setLayoutParams(textViewParam);

                int column4Index = cursor.getColumnIndex("Date");
                String column4Value = cursor.getString(column4Index);
                TextView date = new TextView(this);
                date.setText(column4Value);
                date.setGravity(Gravity.CENTER);
                date.setLayoutParams(textViewParam);

                row.addView(id);
                row.addView(name);
                row.addView(status);
                row.addView(date);

                row.setLayoutParams(tableRowParams);

                table.addView(row);
                i++;
            } while (cursor.moveToNext());
        }
        else
            Toast.makeText(getApplicationContext(),"Cursor returned null!",Toast.LENGTH_SHORT).show();
    }
}
