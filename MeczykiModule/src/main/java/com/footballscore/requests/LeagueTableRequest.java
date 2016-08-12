package com.footballscore.requests;

import android.util.Log;

import com.footballscore.models.LeagueTableModel;
import com.network.library.MultipartRequestParams;
import com.network.library.RequestCreator;
import com.network.library.RequestHeaders;
import com.network.library.RequestMethod;
import com.network.library.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Przemysław Skitał on 2016-08-11.
 */
public class LeagueTableRequest extends RequestCreator<ArrayList<LeagueTableModel>> {
    private static final String TAG = "LeagueTableRequest";
    private String id;

    public LeagueTableRequest(String id) {
        this.id = id;
    }

    @Override
    public String onCreateUrl() {
        return "http://api.football-data.org/v1/competitions/" + id + "/leagueTable";
    }

    @Override
    public void onCreateRequestParams(RequestParams requestParams) {

    }

    @Override
    public void onCreateRequestHeaders(RequestHeaders requestHeaders) {

    }

    @Override
    public void onCreateMultipartRequestParams(MultipartRequestParams multipartRequestParams) {

    }

    @Override
    public int onCreateRetryCount() {
        return 3;
    }

    @Override
    public String onCreateRequestMethod() {
        return RequestMethod.GET;
    }

    @Override
    public ArrayList<LeagueTableModel> onDownloadSuccess(InputStream inputStream) throws Exception {
        String response = getNetworkManager().convertInputStreamToString(inputStream);
        Log.e(TAG, "onDownloadSuccess: " + response);

        JSONObject responseObject = new JSONObject(response);
        JSONArray jsonArray = responseObject.getJSONArray("standing");
        int length = jsonArray.length();

        ArrayList<LeagueTableModel> leagueTableModelArrayList = new ArrayList<>(length);

        for (int i = 0; i < length; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String position = jsonObject.getString("position");
            String teamName = jsonObject.getString("teamName");
            String crestURI = jsonObject.getString("crestURI");
            String playedGames = jsonObject.getString("playedGames");
            String points = jsonObject.getString("points");
            String goals = jsonObject.getString("goals");
            String goalsAgainst = jsonObject.getString("goalsAgainst");
            String wins = jsonObject.getString("wins");
            String draws = jsonObject.getString("draws");
            String losses = jsonObject.getString("losses");

            String href = jsonObject.getJSONObject("_links").getJSONObject("team").getString("href");

            LeagueTableModel leagueTableModel = new LeagueTableModel();
            leagueTableModel.setPosition(String.valueOf(i + 1));
            leagueTableModel.setTeamName(teamName);
            leagueTableModel.setCrestURI(crestURI);
            leagueTableModel.setPlayedGames(playedGames);
            leagueTableModel.setPoints(points);
            leagueTableModel.setGoals(goals);
            leagueTableModel.setGoalsAgainst(goalsAgainst);
            leagueTableModel.setWins(wins);
            leagueTableModel.setDraws(draws);
            leagueTableModel.setLosses(losses);
            leagueTableModel.setHref(href);

            leagueTableModelArrayList.add(leagueTableModel);
        }
        return leagueTableModelArrayList;
    }

    @Override
    public void onResult(ArrayList<LeagueTableModel> leagueTableModelArrayList) throws Exception {

    }
}
