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

public class SignUp3rd extends AppCompatActivity {

    ImageView backSignUp3Btn;
    Button SignUp3rdNextBtn;
    TextView acc3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up3rd);

        backSignUp3Btn = findViewById(R.id.backSignUp3Btn);
        SignUp3rdNextBtn = findViewById(R.id.SignUp3rdNextBtn);
        acc3 = findViewById(R.id.acc3);

        backSignUp3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToStep2 = new Intent(getApplicationContext(),SignUp2nd.class);
                startActivity(backToStep2);
            }
        });
    }

    public  void callSign3toNext (View view){
        Intent CallNextStep4 = new Intent(getApplicationContext(), SignUp4th.class);
        Pair[] pairs = new Pair[3];

        pairs[0] = new Pair<View,String>(backSignUp3Btn,"transitionBackPage");
        pairs[1] = new Pair<View,String>(acc3,"transitionTittleName");
        pairs[2] = new Pair<View,String>(SignUp3rdNextBtn,"transitionNextBtn");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp3rd.this, pairs);
        startActivity(CallNextStep4, options.toBundle());
    }
}