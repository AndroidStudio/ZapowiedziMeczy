package com.footballscore.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.footballscore.R;
import com.footballscore.holders.CompetitionsViewHolder;
import com.footballscore.models.CompetitionsModel;

import java.util.ArrayList;

/**
 * Created by Przemysław Skitał on 2016-08-10.
 */
public class CompetitionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ArrayList<CompetitionsModel> competitionsModelArrayList = new ArrayList<>();
    private final LayoutInflater layoutInflater;
    private final Context context;

    public CompetitionsAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CompetitionsViewHolder(layoutInflater.inflate(R.layout.competitions_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final CompetitionsModel competitionsModel = competitionsModelArrayList.get(position);
        CompetitionsViewHolder competitionsViewHolder = (CompetitionsViewHolder) holder;
        competitionsViewHolder.onBind(competitionsModel, this.context);
    }

    @Override
    public int getItemCount() {
        return this.competitionsModelArrayList.size();
    }

    public void setCompetitionsModelArrayList(ArrayList<CompetitionsModel> competitionsModelArrayList) {
        this.competitionsModelArrayList.addAll(competitionsModelArrayList);
        this.notifyDataSetChanged();
    }

    public void clearAdapter() {
        this.competitionsModelArrayList.clear();
        this.notifyDataSetChanged();
    }
}
