package com.footballscore.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.footballscore.R;
import com.footballscore.holders.FixtureViewHolder;
import com.footballscore.models.FixtureModel;

import java.util.ArrayList;

/**
 * Created by Przemysław Skitał on 2016-07-17.
 */
public class FixturesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ArrayList<FixtureModel> fixtureModelList = new ArrayList<>();
    private final LayoutInflater layoutInflater;

    public FixturesAdapter(Context context) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FixtureViewHolder(this.layoutInflater.inflate(R.layout.fixture_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FixtureModel fixtureModel = this.fixtureModelList.get(position);
        FixtureViewHolder headerViewHolder = (FixtureViewHolder) holder;
        headerViewHolder.onBind(fixtureModel);
    }

    public void setFixtureModelList(ArrayList<FixtureModel> fixtureModelList) {
        this.fixtureModelList.addAll(fixtureModelList);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.fixtureModelList.size();
    }
}
