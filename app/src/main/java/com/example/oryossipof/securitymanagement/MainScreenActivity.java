package com.example.oryossipof.securitymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainScreenActivity extends AppCompatActivity {
    private DataBase db;
private Button userManagementBT,exitBT, porterageBT,eventActBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_screen);
        db = new DataBase(MainScreenActivity.this);


        userManagementBT = (Button) findViewById(R.id.userManagementBT);
        userManagementBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreenActivity.this, UserManagementActivity.class);
                startActivity(intent);
            }
        });

        eventActBt = (Button) findViewById(R.id.logBookBT);
      eventActBt.setOnClickListener(new View.OnClickListener() {
           public void onClick(View view) {
                Intent intent = new Intent(MainScreenActivity.this, AddEventAct.class);
               startActivity(intent);
        }
       });

        exitBT = (Button) findViewById(R.id.exitBT);
        exitBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });


        porterageBT = (Button) findViewById(R.id.porterageBT);

        porterageBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MainScreenActivity.this, LoginActivity.class);
                //startActivity(intent);
            }
        });
    }

}
