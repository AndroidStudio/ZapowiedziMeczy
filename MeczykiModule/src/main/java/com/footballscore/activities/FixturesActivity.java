package com.footballscore.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.footballscore.R;
import com.footballscore.adapters.FixturesViewPageAdapter;
import com.footballscore.models.CompetitionsModel;
import com.footballscore.utils.Constants;
import com.footballscore.utils.MainTabListener;

public class FixturesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.fixtures_activity);
        this.onCreateToolbar();
        this.onCreateViewPager();
    }

    private void onCreateViewPager() {
        FixturesViewPageAdapter fixturesViewPageAdapter = new FixturesViewPageAdapter(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(fixturesViewPageAdapter);
        viewPager.setOffscreenPageLimit(3);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new MainTabListener(viewPager));
    }

    private void onCreateToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getCompetitionModel().getCaption().toUpperCase());
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public CompetitionsModel getCompetitionModel() {
        return (CompetitionsModel) getIntent().getSerializableExtra(Constants.COMPETITION_MODEL);
    }
}