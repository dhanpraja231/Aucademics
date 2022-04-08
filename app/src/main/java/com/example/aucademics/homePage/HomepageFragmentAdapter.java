package com.example.aucademics.homePage;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.aucademics.bunkFragment.BunkFragment;
import com.example.aucademics.cgpaFragment.CgpaFragment;

public class HomepageFragmentAdapter extends FragmentStateAdapter {

    private String[] titles = new String[]{"Bunk manager","CGPA"};

    public HomepageFragmentAdapter(@NonNull FragmentActivity fm) {
        super(fm);

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch ((position)){
            case 0:
                BunkFragment bunkFragment = new BunkFragment();
                return bunkFragment;
            case 1:
                CgpaFragment cgpaFragment = new CgpaFragment();
                return cgpaFragment;
            default:
                return new BunkFragment();
        }
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
