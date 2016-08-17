package com.footballscore.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.footballscore.R;
import com.purplebrain.adbuddiz.sdk.AdBuddiz;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AdBuddiz.setPublisherKey(getString(R.string.add_buddiz_api_key));
    }
}
