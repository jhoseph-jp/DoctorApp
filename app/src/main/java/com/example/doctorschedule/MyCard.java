package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.doctorschedule.User.MainPageUser;

public class MyCard extends AppCompatActivity {

    //Variables
    ImageView backBoardmyc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_card);


            //return board
        backBoardmyc =findViewById(R.id.card_back);

        backBoardmyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backtoboardmyc = new Intent(getApplicationContext(), MainPageUser.class);
                startActivity(backtoboardmyc);
            }
        });
    }
}