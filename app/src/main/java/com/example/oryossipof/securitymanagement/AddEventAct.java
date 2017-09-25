package com.example.oryossipof.securitymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEventAct extends AppCompatActivity {
    private TextView dateTxt ,eventText;
    private Firebase myRef;
    private Button showEventsBtt ,addEventBtClass;
    private String currentDateandTime;
    private String currentDateandTime2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);


        myRef = new Firebase("https://securitymanagement-8dd8d.firebaseio.com/");

        eventText = (EditText) findViewById(R.id.EventText);

        showEventsBtt = (Button) findViewById(R.id.showEventsBt);
        showEventsBtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddEventAct.this, LogbookEvents.class);
                startActivity(intent);
            }
        });

        addEventBtClass =  (Button) findViewById(R.id.addEventBT);
        addEventBtClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy-hh:mm:ss "/* hh:mm*/);
                currentDateandTime = sdf.format(new Date());
                SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yy-hh:mm "/* hh:mm*/);
                currentDateandTime2 = sdf2.format(new Date());

                String str = eventText.getText().toString();
                if(!str.equals("")) {
                    Log.e("Add event:", "addEventBtClass onClick");
                    DataBase.addEventToDataBase(currentDateandTime, str, currentDateandTime2);
                    setResult(RESULT_OK, null);
                    Toast.makeText(getBaseContext(), "Event successfully registered to the logbook!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getBaseContext(), "Please enter a description", Toast.LENGTH_LONG).show();
                }
            }
        });

        dateTxt = (TextView)findViewById(R.id.datetxt);
        dateTxt.setText(currentDateandTime);
    }
}
