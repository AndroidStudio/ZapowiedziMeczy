package com.footballscore.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.footballscore.R;
import com.footballscore.holders.LeagueTableViewHolder;
import com.footballscore.models.LeagueTableModel;

import java.util.ArrayList;

/**
 * Created by Przemysław Skitał on 2016-08-11.
 */
public class LeagueTableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ArrayList<LeagueTableModel> leagueTableModelList = new ArrayList<>();
    private final LayoutInflater layoutInflater;
    private final Context context;

    public LeagueTableAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LeagueTableViewHolder(this.layoutInflater.inflate(R.layout.league_table_item, parent, false), this.context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LeagueTableModel leagueTableModel = this.leagueTableModelList.get(position);
        LeagueTableViewHolder leagueTableViewHolder = (LeagueTableViewHolder) holder;
        leagueTableViewHolder.onBind(leagueTableModel);
    }

    @Override
    public int getItemCount() {
        return this.leagueTableModelList.size();
    }

    public void setLeagueTableModelList(ArrayList<LeagueTableModel> leagueTableModelArrayList) {
        this.leagueTableModelList.addAll(leagueTableModelArrayList);
        this.notifyDataSetChanged();
    }
}
