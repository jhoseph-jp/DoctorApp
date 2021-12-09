package com.example.doctorschedule;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.doctorschedule.Common.TestSignUp;
import com.google.android.material.textfield.TextInputLayout;

public class OnBoarding extends AppCompatActivity {

    Button LoginOnBBtn, SignUpOnBBtn, signMed;
    TextInputLayout cardcrm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        LoginOnBBtn = findViewById(R.id.LoginOnBBtn);
        SignUpOnBBtn = findViewById(R.id.SignUpOnBBtn);
        signMed = findViewById(R.id.SignUpMedic);
        cardcrm = findViewById(R.id.cardCRM);

    }

    public void callLogin (View view){
        Intent callLogin = new Intent(getApplicationContext(), Login.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View,String>(LoginOnBBtn,"transition_login");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(OnBoarding.this, pairs);
        startActivity(callLogin, options.toBundle());
    }

    public void callSignup (View view){
        Intent callSignUp = new Intent(getApplicationContext(), SignUp.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View,String>(SignUpOnBBtn,"transition_signUp");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(OnBoarding.this, pairs);
        startActivity(callSignUp, options.toBundle());

    }

    public void callSignupMed(View view) {
        Intent callMed = new Intent(OnBoarding.this,SignUp.class);
       // cardcrm.setVisibility(View.VISIBLE);
        callMed.putExtra("classFrom", OnBoarding.class.toString());
        startActivity(callMed);
    }
}