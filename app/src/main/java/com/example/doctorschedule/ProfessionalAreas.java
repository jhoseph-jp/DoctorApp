package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.doctorschedule.User.MainPageUser;

public class ProfessionalAreas extends AppCompatActivity {

    ImageView backboardProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional_areas);

        backboardProf = findViewById(R.id.prof_area_back);

        backboardProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backtoboardProf = new Intent(getApplicationContext(), MainPageUser.class);
                startActivity(backtoboardProf);
            }
        });
    }
}