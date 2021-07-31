package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SignUp4th extends AppCompatActivity {

    ImageView back_SignUp4Btn;
    Button SignUp4thNextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up4th);

        back_SignUp4Btn = findViewById(R.id.back_SignUp4Btn);
        SignUp4thNextBtn = findViewById(R.id.SignUp4thNextBtn);

        back_SignUp4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToStep3 = new Intent(getApplicationContext(),SignUp3rd.class);
                startActivity(backToStep3);
            }
        });

       /* SignUp4thNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextStep3 = new Intent(getApplicationContext(), SignUp4th.class);
                startActivity(nextStep3);
            }
        });*/
    }
}