package com.footballscore.requests;

import android.util.Log;

import com.footballscore.models.CompetitionsModel;
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
 * Created by Przemysław Skitał on 2016-08-10.
 */
public class CompetitionsRequest extends RequestCreator<ArrayList<CompetitionsModel>> {
    private static final String TAG = "CompetitionsRequest";
    private int season;

    public CompetitionsRequest(int season) {
        this.season = season;
    }

    @Override
    public String onCreateUrl() {
        return "http://api.football-data.org/v1/competitions/?season=" + season;
    }

    @Override
    public void onCreateRequestParams(RequestParams requestParams) {

    }

    @Override
    public void onCreateRequestHeaders(RequestHeaders requestHeaders) {
        requestHeaders.put("X-Auth-Token", "55bb23829b4e427db740f6fcc471e26b");
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
    public ArrayList<CompetitionsModel> onDownloadSuccess(InputStream inputStream) throws Exception {
        String response = getNetworkManager().convertInputStreamToString(inputStream);
        Log.e(TAG, "onDownloadSuccess: " + response);

        JSONArray responseArray = new JSONArray(response);
        int length = responseArray.length();

        ArrayList<CompetitionsModel> competitionsModelArrayList = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            JSONObject jsonObject = responseArray.getJSONObject(i);

            String id = jsonObject.getString("id");
            String caption = jsonObject.getString("caption");
            String currentMatchday = jsonObject.getString("currentMatchday");
            String numberOfMatchdays = jsonObject.getString("numberOfMatchdays");
            String numberOfTeams = jsonObject.getString("numberOfTeams");
            String numberOfGames = jsonObject.getString("numberOfGames");

            CompetitionsModel competitionsModel = new CompetitionsModel();
            competitionsModel.setId(id);
            competitionsModel.setCaption(caption);
            competitionsModel.setCurrentMatchday(currentMatchday);
            competitionsModel.setNumberOfMatchdays(numberOfMatchdays);
            competitionsModel.setNumberOfTeams(numberOfTeams);
            competitionsModel.setNumberOfGames(numberOfGames);

            competitionsModelArrayList.add(competitionsModel);
        }

        return competitionsModelArrayList;
    }

    @Override
    public void onResult(ArrayList<CompetitionsModel> competitionsModels) throws Exception {

    }
}
