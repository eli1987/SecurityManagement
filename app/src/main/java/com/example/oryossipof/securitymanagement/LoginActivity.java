package com.example.oryossipof.securitymanagement;

import android.app.ProgressDialog;
import android.icu.util.Freezable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    private EditText _nameText ;
    private Button _signupButton ;
    private Firebase myRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_login);
         _nameText = (EditText) findViewById(R.id.input_name);
        _signupButton = (Button) findViewById(R.id.btn_signup);

        myRef = new Firebase("https://securitymanagement-8dd8d.firebaseio.com/");
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });


    }

    public void signup() {
        Log.d(TAG, "Signup");


        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);


        String name = _nameText.getText().toString();




        // TODO: Implement your own signup logic here.



        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();

                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        Firebase mRefChild = myRef.child("Name");
        mRefChild.setValue(_nameText.getText()+"");
        setResult(RESULT_OK, null);
        Toast.makeText(getBaseContext(), "Sign in Succeed", Toast.LENGTH_LONG).show();

        //  finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();

        if (name.isEmpty() || name.length() < 6) {
            _nameText.setError("at least 6 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        return valid;
    }
}

