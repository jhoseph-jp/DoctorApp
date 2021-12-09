package com.example.doctorschedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.example.doctorschedule.User.MainPageUser;
import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MyScheduling extends AppCompatActivity {

    //variables
    ImageView backBoardmys;
    String url;
    WebView webViewMyS;
    private String tokenPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_scheduling);


        webViewMyS = (WebView) findViewById(R.id.webviewMyscheduling);

        tokenPatient = getIntent().getStringExtra("tokUser");

        url = "https://doctor-schedule-rails.herokuapp.com/app/patient/appointments?token=" + tokenPatient;
        webViewMyS.getSettings().setJavaScriptEnabled(true);
        webViewMyS.getSettings().setDomStorageEnabled(true);
        webViewMyS.setWebViewClient(new WebViewClient());
        //webView.setWebChromeClient(new WebChromeClient());
        webViewMyS.loadUrl(url);

    }
}