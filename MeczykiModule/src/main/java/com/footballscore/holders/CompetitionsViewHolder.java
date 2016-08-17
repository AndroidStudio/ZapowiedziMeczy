package com.footballscore.holders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.footballscore.R;
import com.footballscore.activities.FixturesActivity;
import com.footballscore.activities.LeagueTableActivity;
import com.footballscore.models.CompetitionsModel;
import com.footballscore.utils.Constants;

/**
 * Created by Przemysław Skitał on 2016-08-10.
 */
public class CompetitionsViewHolder extends RecyclerView.ViewHolder {
    private final TextView captionTextView;
    private final TextView matchDayTextView;
    private final TextView numberOfTeamsTextView;
    private final TextView numberOfGamesTextView;
    private final Button showFixturesButton;
    private final Button showTableButton;

    public CompetitionsViewHolder(View view) {
        super(view);
        this.captionTextView = (TextView) view.findViewById(R.id.captionTextView);
        this.matchDayTextView = (TextView) view.findViewById(R.id.matchDayTextView);
        this.numberOfTeamsTextView = (TextView) view.findViewById(R.id.numberOfTeamsTextView);
        this.numberOfGamesTextView = (TextView) view.findViewById(R.id.numberOfGamesTextView);
        this.showFixturesButton = (Button) view.findViewById(R.id.showFixturesButton);
        this.showTableButton = (Button) view.findViewById(R.id.showTableButton);
    }

    public void onBind(final CompetitionsModel competitionsModel, final Context context) {
        this.captionTextView.setText(competitionsModel.getCaption());
        this.matchDayTextView.setText(String.valueOf("Match day: " + competitionsModel.getCurrentMatchday() + "/" + competitionsModel.getNumberOfMatchdays()));
        this.numberOfTeamsTextView.setText(String.valueOf("Teams: " + competitionsModel.getNumberOfTeams()));
        this.numberOfGamesTextView.setText(String.valueOf("Games: " + competitionsModel.getNumberOfGames()));

        this.showFixturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FixturesActivity.class);
                intent.putExtra(Constants.COMPETITION_MODEL, competitionsModel);
                context.startActivity(intent);
            }
        });

        this.showTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LeagueTableActivity.class);
                intent.putExtra(Constants.COMPETITION_MODEL, competitionsModel);
                context.startActivity(intent);
            }
        });

        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LeagueTableActivity.class);
                intent.putExtra(Constants.COMPETITION_MODEL, competitionsModel);
                context.startActivity(intent);
            }
        });
    }
}
