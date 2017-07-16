package com.mylife.faiza.spara;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
/**
 * Created by faiza on 20/07/2016.
 */
public class PageAdapter extends FragmentStatePagerAdapter {
    private final int mNumOfTabs;

    // whike creating this class we have implement method getCount and create a constructer for this class
    public PageAdapter(FragmentManager fm , int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs ;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                DailyFragment dailyFragment = new DailyFragment();
                return dailyFragment ;
            case 1:
               MonthlyFragment monthlyFragment = new MonthlyFragment();
                return monthlyFragment ;
            case 2:
                YearlyFragment yearlyFragment = new YearlyFragment();
                return yearlyFragment ;
            default:
                return new Fragment();


        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
