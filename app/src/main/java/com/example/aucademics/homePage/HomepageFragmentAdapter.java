package com.example.aucademics.homePage;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.aucademics.bunkFragment.BunkFragment;
import com.example.aucademics.cgpaFragment.CgpaFragment;

public class HomepageFragmentAdapter extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;
    public HomepageFragmentAdapter(@NonNull FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch ((position)){
            case 0:
                BunkFragment bunkFragment = new BunkFragment();
                return bunkFragment;
            case 1:
                CgpaFragment cgpaFragment = new CgpaFragment();
                return cgpaFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
