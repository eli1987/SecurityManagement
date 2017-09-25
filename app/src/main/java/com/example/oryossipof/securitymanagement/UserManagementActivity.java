package com.example.oryossipof.securitymanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class UserManagementActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private EditText userid,IDText,nameText;
    private Firebase myRef;
    private Button registerBT;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management_activity);
        Firebase.setAndroidContext(this);
        firebaseAuth= FirebaseAuth.getInstance();
        userid = (EditText)findViewById(R.id.userID) ;


        IDText = (EditText) findViewById(R.id.userID);
        nameText =  (EditText) findViewById(R.id.userName);



       // myRef = new Firebase("https://securitymanagment-2427a.firebaseio.com/");
        myRef = new Firebase("https://securitymanagement-8dd8d.firebaseio.com/");


         registerBT = (Button)findViewById(R.id.signupbt) ;
        registerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();


            }
        });


    }
    public void signup() {



        if (!validate()) {
            onSignupFailed();
            return;
        }

        registerBT.setEnabled(false);


        String name = IDText.getText().toString();




        // TODO: Implement your own signup logic here.



        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();

                    }
                }, 2000);
    }


    public void onSignupSuccess() {
        registerBT.setEnabled(true);
        DataBase.signupUser(IDText.getText().toString(),nameText.getText().toString());

        setResult(RESULT_OK, null);
        Toast.makeText(getBaseContext(), "Signup Succeeded!", Toast.LENGTH_LONG).show();

          finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Signup failed!", Toast.LENGTH_LONG).show();

        registerBT.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String id = IDText.getText().toString();
        String name = nameText.getText().toString();

        if (id.isEmpty() || id.length() != 9 ) {
            IDText.setError("ID need to be  9 characters!");
            valid = false;

        }

        else if(name.isEmpty()) {
            nameText.setError("Name cant be empty!");
            valid = false;
        }
        else {
            IDText.setError(null);
        }

        return valid;
    }




}












