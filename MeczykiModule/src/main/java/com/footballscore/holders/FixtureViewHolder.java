package com.footballscore.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.footballscore.R;
import com.footballscore.models.FixtureModel;

/**
 * Created by Przemysław Skitał on 2016-07-17.
 */
public class FixtureViewHolder extends RecyclerView.ViewHolder {
    private final TextView homeTeamTextView;
    private final TextView dateTextView;
    private final TextView awayTeamTextView;
    private final TextView scoreTextView;

    public FixtureViewHolder(View view) {
        super(view);
        this.homeTeamTextView = (TextView) view.findViewById(R.id.homeTeamTextView);
        this.awayTeamTextView = (TextView) view.findViewById(R.id.awayTeamTextView);
        this.scoreTextView = (TextView) view.findViewById(R.id.scoreTextView);

        this.dateTextView = (TextView) view.findViewById(R.id.dateTextView);

    }

    public void onBind(FixtureModel fixtureModel) {
        this.homeTeamTextView.setText(fixtureModel.getHomeTeamName());
        this.awayTeamTextView.setText(fixtureModel.getAwayTeamName());
        this.scoreTextView.setText(fixtureModel.getGoalsHomeTeam() + " : " + fixtureModel.getGoalsAwayTeam());

        this.dateTextView.setText(fixtureModel.getDate());
    }
}
