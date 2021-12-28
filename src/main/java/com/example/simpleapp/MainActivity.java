package com.example.simpleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText first;
    EditText description;
    EditText loc;
    EditText emails;
    Button newEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newEvent = findViewById(R.id.btn1);
        first = findViewById(R.id.item1);
        loc = findViewById(R.id.item2);
        description = findViewById(R.id.item3);
        emails = findViewById(R.id.item4);


        newEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // makes sure that at least the title and description are filled before continuing
                if(!first.getText().toString().isEmpty()&& !description.getText().toString().isEmpty()){

                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE,first.getText().toString());
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION,newEvent.getText().toString());
                    intent.putExtra(CalendarContract.Events.DESCRIPTION,description.getText().toString());
                    intent.putExtra(Intent.EXTRA_EMAIL,emails.getText().toString());


                    // all times are accessible on google calendar
                    intent.putExtra(CalendarContract.Events.ALL_DAY,"true");


                    // check to see if there is a calendar app to take care of the request
                    if(intent.resolveActivity(getPackageManager())!=null){
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Cannot find calendar",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"Complete title and description of the task", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }










}