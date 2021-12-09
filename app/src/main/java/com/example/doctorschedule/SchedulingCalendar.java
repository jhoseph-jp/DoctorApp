package com.example.doctorschedule;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


public class SchedulingCalendar extends AppCompatActivity {

    WebView editweb;
    String url;
    private String tokenMedico2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduling_calendar);

        tokenMedico2 = getIntent().getStringExtra("tokenedit");

       editweb = findViewById(R.id.editAgenda);

        url = "https://doctor-schedule-rails.herokuapp.com/app/doctor/edit?token=" + tokenMedico2;

        editweb.getSettings().setJavaScriptEnabled(true);
        editweb.getSettings().setDomStorageEnabled(true);
        editweb.setWebViewClient(new WebViewClient());
        //webView.setWebChromeClient(new WebChromeClient());
        editweb.loadUrl(url);


    }


    public void BackSchedule(View view){
        this.finish();
    }



}