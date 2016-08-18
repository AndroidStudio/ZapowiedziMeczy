package com.footballscore.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.footballscore.R;
import com.purplebrain.adbuddiz.sdk.AdBuddiz;

public class BaseActivity extends AppCompatActivity {

    private static int advertiseCount = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AdBuddiz.setPublisherKey(getString(R.string.add_buddiz_api_key));
    }

    public void updateAdvertiseCounter() {
        BaseActivity.advertiseCount++;
    }

    private int getAdvertiseCounter() {
        return BaseActivity.advertiseCount;
    }

    public boolean showAdvertise() {
        return getAdvertiseCounter() % 3 == 0;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (this instanceof CompetitionsActivity) {
            BaseActivity.advertiseCount = 0;
        }
    }
}
