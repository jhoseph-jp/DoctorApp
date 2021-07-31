package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.doctorschedule.User.MainPageUser;

public class MyExams extends AppCompatActivity {

    ImageView backtoboardE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_exams);

        //Return board
        backtoboardE = findViewById(R.id.exams_back);

        backtoboardE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backtoboardEx = new Intent(getApplicationContext(), MainPageUser.class);
                startActivity(backtoboardEx);
            }
        });
    }
}