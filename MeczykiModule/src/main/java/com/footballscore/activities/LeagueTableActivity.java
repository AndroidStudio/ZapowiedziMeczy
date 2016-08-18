package com.footballscore.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.footballscore.R;
import com.footballscore.adapters.LeagueTableAdapter;
import com.footballscore.models.CompetitionsModel;
import com.footballscore.models.LeagueTableModel;
import com.footballscore.requests.LeagueTableRequest;
import com.footballscore.utils.Constants;
import com.footballscore.utils.DividerItemDecoration;
import com.network.library.NetworkManager;
import com.network.library.NetworkManagerCallbacks;
import com.purplebrain.adbuddiz.sdk.AdBuddiz;

import java.util.ArrayList;

/**
 * Created by Przemysław Skitał on 2016-07-17.
 */
public class LeagueTableActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.league_table_activity);
        this.onCreateToolbar();
        this.onCreateRecyclerView();
        this.onCreateLeagueTable();
        this.onCreateAdd();
    }

    private void onCreateAdd() {
        if (showAdvertise())
            AdBuddiz.showAd(this);
        updateAdvertiseCounter();
    }

    private void onCreateLeagueTable() {
        NetworkManager networkManager = new NetworkManager(this, true);
        networkManager.setDelay(500);
        networkManager.addRequest(onCreateLeagueTableRequest());
        networkManager.setNetworkManagerCallbacks(this.networkManagerCallbacks);
        networkManager.execute();
    }

    private LeagueTableRequest onCreateLeagueTableRequest() {
        return new LeagueTableRequest(getCompetitionsId()) {
            @Override
            public void onResult(ArrayList<LeagueTableModel> leagueTableModelArrayList) throws Exception {
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                if (recyclerView != null) {
                    LeagueTableAdapter leagueTableAdapter = (LeagueTableAdapter) recyclerView.getAdapter();
                    leagueTableAdapter.setLeagueTableModelList(leagueTableModelArrayList);

                    if (leagueTableModelArrayList.size() < 1) {
                        findViewById(R.id.noResultsLayout).setVisibility(View.VISIBLE);
                    }
                }
            }
        };
    }

    private final NetworkManagerCallbacks networkManagerCallbacks = new NetworkManagerCallbacks() {

        @Override
        public void onStart() throws Exception {
            findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
        }

        @Override
        public void onError(String error) throws Exception {
            findViewById(R.id.progressBar).setVisibility(View.GONE);
        }

        @Override
        public void onSuccess() throws Exception {
            findViewById(R.id.progressBar).setVisibility(View.GONE);
        }

        @Override
        public void onCancelled() throws Exception {
            findViewById(R.id.progressBar).setVisibility(View.GONE);
        }
    };

    private void onCreateRecyclerView() {
        LeagueTableAdapter leagueTableAdapter = new LeagueTableAdapter(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.separator)));
        recyclerView.setAdapter(leagueTableAdapter);
    }

    private void onCreateToolbar() {
        CompetitionsModel competitionsModel = (CompetitionsModel) getIntent().getSerializableExtra(Constants.COMPETITION_MODEL);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(competitionsModel.getCaption());

        this.setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowTitleEnabled(true);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
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

    public String getCompetitionsId() {
        return ((CompetitionsModel) getIntent().getSerializableExtra(Constants.COMPETITION_MODEL)).getId();
    }
}
