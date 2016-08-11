package com.footballscore.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.footballscore.fragments.FixturesFragment;
import com.footballscore.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Przemysław Skitał on 2016-07-17.
 */
public class FixturesViewPageAdapter extends FragmentPagerAdapter {
    private final List<String> fragmentTitleList = new ArrayList<>();

    public FixturesViewPageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragmentTitleList.add("MATCH PREVIEWS");
        this.fragmentTitleList.add("LAST MATCHES");
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                FixturesFragment fixturesFragment = new FixturesFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.FIXTURE_TYPE, Constants.MATCH_PREVIEWS);
                fixturesFragment.setArguments(bundle);
                return fixturesFragment;
            }
            case 1: {
                FixturesFragment fixturesFragment = new FixturesFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.FIXTURE_TYPE, Constants.LAST_MATCHES);
                fixturesFragment.setArguments(bundle);
                return fixturesFragment;
            }
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }
}
