package com.footballscore.holders;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.footballscore.R;
import com.footballscore.models.LeagueTableModel;
import com.footballscore.svg.SvgDecoder;
import com.footballscore.svg.SvgDrawableTranscoder;
import com.footballscore.svg.SvgSoftwareLayerSetter;

import java.io.InputStream;

public class LeagueTableViewHolder extends BaseViewHolder<LeagueTableModel> {
    private static final String TAG = "LeagueTableViewHolder";
    private final GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder;

    private final ImageView logoImageView;
    private final TextView positionTextView;
    private final TextView teamNameTextView;
    private final TextView pointsTextView;
    private final TextView detailsTextView;

    public LeagueTableViewHolder(View view, Context context) {
        super(view, context);
        this.logoImageView = (ImageView) findViewById(R.id.logoImageView);
        this.positionTextView = (TextView) findViewById(R.id.positionTextView);
        this.teamNameTextView = (TextView) findViewById(R.id.teamNameTextView);
        this.pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        this.detailsTextView = (TextView) findViewById(R.id.detailsTextView);

        this.requestBuilder = Glide.with(getContext())
                .using(Glide.buildStreamModelLoader(Uri.class, getContext()), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .listener(new SvgSoftwareLayerSetter<Uri>());
    }

    @Override
    public void onBind(LeagueTableModel leagueTableModel) {
        this.positionTextView.setText(leagueTableModel.getPosition());
        this.teamNameTextView.setText(leagueTableModel.getTeamName());
        this.pointsTextView.setText(leagueTableModel.getPoints());
        this.detailsTextView.setText(String.valueOf(
                "Games: " + leagueTableModel.getPlayedGames() +
                        " Goals: " + leagueTableModel.getGoals() + "/" + leagueTableModel.getGoalsAgainst() + "\n" +
                        "Wins: " + leagueTableModel.getWins() +
                        " Draws: " + leagueTableModel.getDraws() +
                        " Losses: " + leagueTableModel.getLosses()
        ));

        String crestURI = leagueTableModel.getCrestURI();
        Log.e(TAG, "onBind: " + crestURI);

        if (crestURI != null) {
            if (crestURI.contains("svg")) {
                Uri parse = Uri.parse(crestURI);
                this.requestBuilder.diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .load(parse)
                        .into(this.logoImageView);
            } else {
                Glide.with(getContext()).load(crestURI).into(this.logoImageView);
            }
        } else {
            this.logoImageView.setImageDrawable(null);
        }
    }
}
