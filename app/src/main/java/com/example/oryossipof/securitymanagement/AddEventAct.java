package com.example.oryossipof.securitymanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEventAct extends AppCompatActivity {
    private TextView dateTxt ,eventText;
    private Firebase myRef;
    private Button showEventsBtt ,addEventBtClass,addPhoto;
    private String currentDateandTime;
    private String currentDateandTime2;
    private StorageReference storageRef;
    private ProgressDialog progressbar;
    private final int GALLERY = 2;
    private  Uri donwloadImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);


        progressbar = new ProgressDialog(this);
        myRef = new Firebase("https://securitymanagement-8dd8d.firebaseio.com/");


        // Take this

        storageRef = FirebaseStorage.getInstance().getReference();

        progressbar = new ProgressDialog(this);

        //



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
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy "/* hh:mm*/);
                currentDateandTime = sdf.format(new Date());
                SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yy-hh:mm "/* hh:mm*/);
                currentDateandTime2 = sdf2.format(new Date());

                SimpleDateFormat sdf3 = new SimpleDateFormat("hh:mm:ss "/* hh:mm*/);
                String currentDateandTime3 = sdf3.format(new Date());


                String str = eventText.getText().toString();
                if(!str.equals("")) {

                    Log.e("Add event:", "addEventBtClass onClick");

                    if(donwloadImage != null) {
                        String stringUri;
                        stringUri = donwloadImage.toString();
                        DataBase.addEventToDataBase(currentDateandTime, str, currentDateandTime2, currentDateandTime3, stringUri);
                        setResult(RESULT_OK, null);
                        Toast.makeText(getBaseContext(), "Event successfully registered to the logbook!", Toast.LENGTH_LONG).show();

                    }
                    else{
                        DataBase.addEventToDataBase(currentDateandTime, str, currentDateandTime2, currentDateandTime3, "");
                        setResult(RESULT_OK, null);
                        Toast.makeText(getBaseContext(), "Event successfully registered to the logbook!", Toast.LENGTH_LONG).show();
                    }
                    }
                else
                {
                    Toast.makeText(getBaseContext(), "Please enter a description", Toast.LENGTH_LONG).show();
                }
            }
        });






        // take this 2

        addPhoto = (Button)findViewById(R.id.attachPhotobt);
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");

                startActivityForResult(intent ,GALLERY);

            }
        });


        dateTxt = (TextView)findViewById(R.id.datetxt);
        dateTxt.setText(currentDateandTime);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2 && resultCode == RESULT_OK)
        {
            progressbar.setMessage("uploading........");
            progressbar.show();
            Uri uri = data.getData();
            StorageReference filePath = storageRef.child("Eventsimages/"+uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                     donwloadImage = taskSnapshot.getDownloadUrl();

                    progressbar.setMessage("Uploading file finish...");
                    progressbar.dismiss();
                    Toast.makeText(getBaseContext(), "Upload done", Toast.LENGTH_LONG).show();


                }
            });


        }
    }

    ///
}
