package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DoctorDashboard extends AppCompatActivity {

    CardView myAgenda, edtagenda;
    private String recToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);

        myAgenda = findViewById(R.id.myAgenda);
        edtagenda = findViewById(R.id.editagenda);

        recToken = getIntent().getStringExtra("tokenmed");


        myAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent agendaMed = new Intent(getApplicationContext(), DoctorAgenda.class);
                agendaMed.putExtra("tok", recToken);
                startActivity(agendaMed);
            }
        });

        edtagenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edtagenda = new Intent(getApplicationContext(), SchedulingCalendar.class);
                edtagenda.putExtra("tokenedit", recToken);
                startActivity(edtagenda);

            }
        });


    }
}