package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Doctor extends AppCompatActivity {

    ImageView doctor_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        doctor_back = findViewById(R.id.doctor_back);

        doctor_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MedicBack = new Intent(getApplicationContext(), Scheduling.class);
                startActivity(MedicBack);
            }
        });


    }
}