package com.mylife.faiza.spara.TabLayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.support.v4.view.ViewPager;

import com.mylife.faiza.spara.R;

import Fragments.ExpenseFragment;
import Fragments.MapsFragment;
import Fragments.WalletFragment;

/**
 * Created by faizan on 31/08/2016.
 */

//What we need  in tab  fragment View PAger and an adapter to hold this view
// number of tab to be inserted -3
// since it is fragment we have to hold this view
// fill view pager with adapter
// we need a class which extens FragmentPagerAdapter
// since its parent is fragment manager it require super function
// without run method it will  not work
    //we can provide get count by predefined number
    // getPagerTitle to set name
    // now change in main activity
public class TabFragment extends Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int tab_num = 3;
    private TabLayout.OnTabSelectedListener mOnTabSelectedListener;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle saveInstanceState) {
        View view = layoutInflater.inflate(R.layout.fagment_tab_layout, null);
        tabLayout = (TabLayout) view.findViewById(R.id.fragment_tab);
        viewPager = (ViewPager) view.findViewById(R.id.fragment_tab_view_pager);
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
       // mOnTabSelectedListener.onTabReselected(tabLayout.newTab());

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        return view;

    }

    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
           switch (position)
           {
               case 0 : return  new ExpenseFragment();
               case 1 :return new WalletFragment();
               case 2 :return new MapsFragment();

           }
            return null ;
        }

        @Override
        public int getCount() {
            return tab_num;
        }
        public CharSequence getPageTitle(int position)
        {
            switch (position)
            {
                case 0:
                    return "Expense";
                case 1:
                    return "Wallet";

                case 2:
                    return "Maps";

            }
            return null;
        }
    }
}
