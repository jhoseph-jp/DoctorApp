package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.doctorschedule.User.MainPageUser;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    ImageView backLoginOnB1Btn;
    Button SignInBtn;
    Button LoginBtn;
    ProgressBar loginProgress;
    TextInputLayout emailLogin, passLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //vincular variael
        backLoginOnB1Btn = findViewById(R.id.backLoginOnB1Btn);
        LoginBtn = findViewById(R.id.LoginBtn);
        SignInBtn = findViewById(R.id.SignInBtn);
        loginProgress = findViewById(R.id.login_progressBar);
        emailLogin = findViewById(R.id.txtEmailLogin);
        passLogin = findViewById(R.id.txtSenhaLogin);

        //chamar botao

        backLoginOnB1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backOnBoarding = new Intent(getApplicationContext(),OnBoarding.class);
                startActivity(backOnBoarding);
            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent enterdashboard = new Intent(getApplicationContext(), MainPageUser.class);
                startActivity(enterdashboard);
            }
        });

       SignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callSignupPage = new Intent(getApplicationContext(),SignUp.class);
                startActivity(callSignupPage);
            }
        });
    }
}