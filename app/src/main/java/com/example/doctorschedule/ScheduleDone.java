package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ScheduleDone extends AppCompatActivity {

    TextView textView2;
    ArrayList<String> listitem2 = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_done);

        textView2 = findViewById(R.id.textView2);

        listitem2 = (ArrayList<String>) getIntent().getSerializableExtra("key");
        textView2.setText(String.valueOf(listitem2));textView2 = findViewById(R.id.textView2);



    }
}