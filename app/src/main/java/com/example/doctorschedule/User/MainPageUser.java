package com.example.doctorschedule.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.doctorschedule.Clinics;
import com.example.doctorschedule.Http;
import com.example.doctorschedule.Login;
import com.example.doctorschedule.MyCard;
import com.example.doctorschedule.MyExams;
import com.example.doctorschedule.MyScheduling;
import com.example.doctorschedule.OnBoarding;
import com.example.doctorschedule.ProfessionalAreas;
import com.example.doctorschedule.R;
import com.example.doctorschedule.Scheduling;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainPageUser extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //variables

    CardView myCards, schedule, myConsults, clinics, profArea;
   private String passinfoUser, passtoken;

    static final float END_SCALE = 0.7f;
    static final int ACTIVITY_1_REQUEST = 1;

    //menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;
    LinearLayout content;
    private MenuItem item;
    String passUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_user);

        passinfoUser = getIntent().getStringExtra("iduser");
        passtoken = getIntent().getStringExtra("tokenUser");



        //menu
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navegation_view);
        menuIcon = findViewById(R.id.ic_Main_menu);
        content = findViewById(R.id.content);
        //declare
        myCards = findViewById(R.id.my_cards);
        schedule = findViewById(R.id.scheduling);
        myConsults = findViewById(R.id.my_consults);
        profArea = findViewById(R.id.Prof_areas);
        clinics = findViewById(R.id.clinics);

        navegationMenu();
        animateMenu();

        //Call card views

        myCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mycard = new Intent(getApplicationContext(), MyCard.class);
                mycard.putExtra("idu",  passinfoUser);
                startActivity(mycard);
            }
        });

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent schedule = new Intent(getApplicationContext(), Scheduling.class);
                schedule.putExtra("tok", passtoken);
                startActivity(schedule);
            }
        });

        myConsults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myconsult = new Intent(getApplicationContext(), MyScheduling.class);
                myconsult.putExtra("tokUser", passtoken);
                startActivity(myconsult);
            }
        });

        clinics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent clinic = new Intent(getApplicationContext(), Clinics.class);
                startActivity(clinic);
            }
        });

        profArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profarea = new Intent(getApplicationContext(), ProfessionalAreas.class);
                startActivity(profarea);
            }
        });

    }


    //Navegation menu functions
    private void animateMenu() {
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                content.setScaleX(offsetScale);
                content.setScaleY(offsetScale);

                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = content.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                content.setTranslationX(xTranslation);
            }
        });
    }


    private void navegationMenu() {

        //Navegation menu
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_logout:
                Intent i = new Intent(getApplicationContext(), OnBoarding.class);
                Toast.makeText(getApplicationContext(), "Saindo", Toast.LENGTH_SHORT).show();
                startActivity(i);
                return true;
            default:
                return false;
        }
    }

    private void Logout() {
        String url = "";
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http = new Http(MainPageUser.this, url);
                http.setMethod("post");
                http.setToken(true);
                http.send();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code = http.getStatusCode();
                        if (code == 200) {
                            Intent logout = new Intent(getApplicationContext(), Login.class);
                            startActivity(logout);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro no logout", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();
    }
}