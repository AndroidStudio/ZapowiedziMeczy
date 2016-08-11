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
import com.footballscore.requests.LeagueTableRequest;
import com.footballscore.utils.Constants;
import com.footballscore.utils.DividerItemDecoration;
import com.network.library.NetworkManager;
import com.network.library.NetworkManagerCallbacks;

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
    }

    private void onCreateLeagueTable() {
        String id = ((CompetitionsModel) getIntent().getSerializableExtra(Constants.COMPETITION_MODEL)).getId();
        LeagueTableRequest leagueTableRequest = new LeagueTableRequest(id) {

        };

        NetworkManager networkManager = new NetworkManager(this, true);
        networkManager.setDelay(500);
        networkManager.addRequest(leagueTableRequest);
        networkManager.setNetworkManagerCallbacks(networkManagerCallbacks);
        networkManager.execute();
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
        recyclerView.addItemDecoration(new DividerItemDecoration(getDrawable(R.drawable.separator)));
        recyclerView.setAdapter(leagueTableAdapter);
    }

    private void onCreateToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Table");

        setSupportActionBar(toolbar);
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
}
