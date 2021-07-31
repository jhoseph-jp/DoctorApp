package com.example.doctorschedule;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class VPAdapter extends FragmentStatePagerAdapter {

    private  int numoftabs;

    public VPAdapter(@NonNull @NotNull FragmentManager fm, int numoftabs) {
        super(fm);
    this.numoftabs = numoftabs;


    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Nextconsult();
            case 1:
                return new DoneConsult();
            case 2:
                return new CancelledConsult();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numoftabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
