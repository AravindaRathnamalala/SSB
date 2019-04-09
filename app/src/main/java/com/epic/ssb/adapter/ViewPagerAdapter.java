package com.epic.ssb.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epic.ssb.ui.mainView.ArakshawaFragment;
import com.epic.ssb.ui.mainView.SurekumaFragment;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager manager){
      super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ArakshawaFragment();
            case 1:
                return new SurekumaFragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

}
