package com.footballscore.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.footballscore.models.LeagueTableModel;

import java.util.ArrayList;

/**
 * Created by Przemysław Skitał on 2016-08-11.
 */
public class LeagueTableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ArrayList<LeagueTableModel> leagueTableModelList = new ArrayList<>();
    private final LayoutInflater layoutInflater;

    public LeagueTableAdapter(Context context) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return this.leagueTableModelList.size();
    }
}
