package com.footballscore.holders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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
    private final ImageView selectImageButton;

    public CompetitionsViewHolder(View view) {
        super(view);
        this.captionTextView = (TextView) view.findViewById(R.id.captionTextView);
        this.matchDayTextView = (TextView) view.findViewById(R.id.matchDayTextView);
        this.numberOfTeamsTextView = (TextView) view.findViewById(R.id.numberOfTeamsTextView);
        this.numberOfGamesTextView = (TextView) view.findViewById(R.id.numberOfGamesTextView);
        this.selectImageButton = (ImageView) view.findViewById(R.id.selectImageButton);
    }

    public void onBind(final CompetitionsModel competitionsModel, final Context context) {
        this.captionTextView.setText(competitionsModel.getCaption());
        this.matchDayTextView.setText(String.valueOf("Match day: " + competitionsModel.getCurrentMatchday() + "/" + competitionsModel.getNumberOfMatchdays()));
        this.numberOfTeamsTextView.setText(String.valueOf("Number of teams: " + competitionsModel.getNumberOfTeams()));
        this.numberOfGamesTextView.setText(String.valueOf("Number of games: " + competitionsModel.getNumberOfGames()));

        this.selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreateMenuPopup(view, context, competitionsModel);
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

    private void onCreateMenuPopup(View anchor, final Context context, final CompetitionsModel competitionsModel) {
        PopupMenu popupMenu = new PopupMenu(context, anchor, Gravity.END, 0, R.style.PopupMenuStyle);
        popupMenu.getMenuInflater().inflate(R.menu.competitions_item_menu, popupMenu.getMenu());
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.actionFixtures:
                        Intent intent = new Intent(context, FixturesActivity.class);
                        intent.putExtra(Constants.COMPETITION_MODEL, competitionsModel);
                        context.startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }
}
