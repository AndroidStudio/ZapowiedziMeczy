package com.footballscore.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.footballscore.R;
import com.footballscore.adapters.CompetitionsAdapter;
import com.footballscore.models.CompetitionsModel;
import com.footballscore.requests.CompetitionsRequest;
import com.footballscore.utils.DividerItemDecoration;
import com.network.library.NetworkManager;
import com.network.library.NetworkManagerCallbacks;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Przemysław Skitał on 2016-08-10.
 */
public class CompetitionsActivity extends BaseActivity {

    private int competitionSeason = Calendar.getInstance().get(Calendar.YEAR);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.competitions_activity);
        this.onCreateToolbar();
        this.onCreateRecyclerView();
        this.onCreateCompetitionList();
    }

    private void onCreateCompetitionList() {
        this.findViewById(R.id.noResultsLayout).setVisibility(View.GONE);

        NetworkManager networkManager = new NetworkManager(this, true);
        networkManager.setDelay(500);
        networkManager.addRequest(onCreateCompetitionsRequest());
        networkManager.setNetworkManagerCallbacks(this.networkManagerCallbacks);
        networkManager.execute();
    }

    private CompetitionsRequest onCreateCompetitionsRequest() {
        return new CompetitionsRequest(CompetitionsActivity.this.competitionSeason) {
            @Override
            public void onResult(ArrayList<CompetitionsModel> result) throws Exception {
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                if (recyclerView != null) {
                    CompetitionsAdapter competitionsAdapter = (CompetitionsAdapter) recyclerView.getAdapter();
                    competitionsAdapter.setCompetitionsModelArrayList(result);

                    if (result.size() < 1) {
                        findViewById(R.id.noResultsLayout).setVisibility(View.VISIBLE);
                    }
                }
            }
        };
    }

    private void onCreateRecyclerView() {
        CompetitionsAdapter competitionsAdapter = new CompetitionsAdapter(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(getDrawable(R.drawable.separator)));
        recyclerView.setAdapter(competitionsAdapter);
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

    private void onCreateToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.activity_title);
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(true);
            supportActionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.competitions_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.moveTaskToBack(true);
                return true;
            case R.id.season2015:
                onCreateSeason(2015);
                return true;
            case R.id.season2016:
                onCreateSeason(2016);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onCreateSeason(int season) {
        this.competitionSeason = season;

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        CompetitionsAdapter competitionsAdapter = (CompetitionsAdapter) recyclerView.getAdapter();
        competitionsAdapter.clearAdapter();

        this.onCreateCompetitionList();
    }

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }
}
