package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doctorschedule.Common.TestSignUp;

public class SignUp2nd extends AppCompatActivity {

    ImageView back_SignUp2Btn;
    TextView account2;
    Button SignUp2ndNextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2nd);

        back_SignUp2Btn = findViewById(R.id.back_SignUp2Btn);
        SignUp2ndNextBtn = findViewById(R.id.SignUp2ndNextBtn);
        account2 = findViewById(R.id.acc2);

        back_SignUp2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToStep1 = new Intent(getApplicationContext(), SignUp.class);
                startActivity(backToStep1);
            }
        });
    }

    public void callSign2toNext (View view){
        Intent callNextStep3 = new Intent(getApplicationContext(), SignUp3rd.class);
        Pair[] pairs = new Pair[3];

        pairs[0] = new Pair<View,String>(back_SignUp2Btn,"transitionBackPage");
        pairs[1] = new Pair<View,String>(account2,"transitionTittleName");
        pairs[2] = new Pair<View,String>(SignUp2ndNextBtn,"transitionNextBtn");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp2nd.this, pairs);
        startActivity(callNextStep3, options.toBundle());
    }
}