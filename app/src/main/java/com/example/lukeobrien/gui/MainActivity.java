package com.example.lukeobrien.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonaddbook = (Button) findViewById(R.id.button);
        buttonaddbook.setOnClickListener(new OnClickListeneraddbook());

        //Toast.makeText(getApplicationContext(), "about to call read records", Toast.LENGTH_LONG).show();

        readRecords();
    }


    public void readRecords() {

        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();

        List<Bookobject> books = new BookController(this).read();

        if (books.size() > 0) {

            for (Bookobject obj : books) {

                int id = obj.id;
                String booktitle = obj.title;
                String bookauthor = obj.author;

                String textViewContents = booktitle + " - " + bookauthor;

                TextView textViewStudentItem= new TextView(this);
                textViewStudentItem.setPadding(0, 10, 0, 10);
                textViewStudentItem.setText(textViewContents);
                textViewStudentItem.setTag(Integer.toString(id));


                textViewStudentItem.setOnLongClickListener(new updatedelete());

                linearLayoutRecords.addView(textViewStudentItem);
            }

        }

        else {

            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }

    }
}
