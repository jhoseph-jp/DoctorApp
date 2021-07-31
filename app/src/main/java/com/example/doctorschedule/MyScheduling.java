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
import android.widget.ImageView;
import android.widget.TableLayout;

import com.example.doctorschedule.User.MainPageUser;
import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MyScheduling extends AppCompatActivity {

    //variables
    ImageView backBoardmys;
    TabItem callNextTab, callRealize, cancelledtab;


    private TabLayout tabLayout;
    private ViewPager viewPager;
    public VPAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_scheduling);
        backBoardmys = findViewById(R.id.my_scheduling_back);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        callNextTab = findViewById(R.id.tabLayoutnext);
        callRealize = findViewById(R.id.realize_consult);

        pagerAdapter = new VPAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(pagerAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    pagerAdapter.notifyDataSetChanged();
                } else if (tab.getPosition() == 1) {
                    pagerAdapter.notifyDataSetChanged();
                } else if (tab.getPosition() == 2) {
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        backBoardmys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backtoboardmys = new Intent(getApplicationContext(), MainPageUser.class);
                startActivity(backtoboardmys);
            }
        });

       /* callNextTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Testfrag testfrag = new Testfrag();
                loadfragments(testfrag);
            }
        });


        callRealize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DoneConsult doneConsult = new DoneConsult();
                loadfragments(doneConsult);
            }
        });

        cancelledtab = findViewById(R.id.cancelled_consult);

        cancelledtab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CancelledConsult cancelledConsult = new CancelledConsult();
                loadfragments(cancelledConsult);
            }
        });

    }

    public void loadfragments(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmenttv, fragment);
        transaction.commit();
    }*/
    }
}