package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DoctorAgenda extends AppCompatActivity {

    WebView webView;
    String url;
    private String tokenMedico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_agenda);

        tokenMedico = getIntent().getStringExtra("tok");

        webView = (WebView) findViewById(R.id.agendaMed);

       url = "https://doctor-schedule-rails.herokuapp.com/app/doctor/appointments?token=" + tokenMedico;
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        //webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(url);
    }
}