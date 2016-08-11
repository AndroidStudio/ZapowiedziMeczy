package com.footballscore.requests;

import android.util.Log;

import com.footballscore.models.FixtureModel;
import com.footballscore.utils.Constants;
import com.network.library.MultipartRequestParams;
import com.network.library.RequestCreator;
import com.network.library.RequestHeaders;
import com.network.library.RequestMethod;
import com.network.library.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Przemysław Skitał on 2016-08-10.
 */
public class FixtureRequest extends RequestCreator<ArrayList<FixtureModel>> {
    private static final String TAG = "FixtureRequest";
    private final String id;
    private final int fixtureType;

    public FixtureRequest(String id, int fixtureType) {

        this.id = id;
        this.fixtureType = fixtureType;
    }

    @Override
    public String onCreateUrl() {
        return "http://api.football-data.org//v1/competitions/" + id + "/fixtures" +
                (fixtureType == Constants.LAST_MATCHES ? "/?timeFrame=p360" : "/?timeFrame=n360");
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
    public ArrayList<FixtureModel> onDownloadSuccess(InputStream inputStream) throws Exception {
        String response = getNetworkManager().convertInputStreamToString(inputStream);
        Log.e(TAG, "onDownloadSuccess: " + response);

        JSONObject responseObject = new JSONObject(response);
        JSONArray fixtures = responseObject.getJSONArray("fixtures");
        int length = fixtures.length();

        ArrayList<FixtureModel> fixtureModelArrayList = new ArrayList<>(length);

        SimpleDateFormat toDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        SimpleDateFormat fromDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());

        if (fixtureType == Constants.LAST_MATCHES) {
            for (int i = length - 1; i >= 0; i--) {
                JSONObject jsonObject = fixtures.getJSONObject(i);

                String date = jsonObject.getString("date");
                String homeTeamName = jsonObject.getString("homeTeamName");
                String awayTeamName = jsonObject.getString("awayTeamName");

                JSONObject resultObject = jsonObject.getJSONObject("result");
                String goalsHomeTeam = resultObject.isNull("goalsHomeTeam") ? "?" : resultObject.getString("goalsHomeTeam");
                String goalsAwayTeam = resultObject.isNull("goalsAwayTeam") ? "?" : resultObject.getString("goalsAwayTeam");

                FixtureModel fixtureModel = new FixtureModel();
                fixtureModel.setDate(toDateFormat.format(fromDateFormat.parse(date)));
                fixtureModel.setHomeTeamName(homeTeamName);
                fixtureModel.setAwayTeamName(awayTeamName);
                fixtureModel.setGoalsHomeTeam(goalsHomeTeam);
                fixtureModel.setGoalsAwayTeam(goalsAwayTeam);
                fixtureModelArrayList.add(fixtureModel);
            }
        } else {
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = fixtures.getJSONObject(i);

                String date = jsonObject.getString("date");
                String homeTeamName = jsonObject.getString("homeTeamName");
                String awayTeamName = jsonObject.getString("awayTeamName");

                JSONObject resultObject = jsonObject.getJSONObject("result");
                String goalsHomeTeam = resultObject.isNull("goalsHomeTeam") ? "?" : resultObject.getString("goalsHomeTeam");
                String goalsAwayTeam = resultObject.isNull("goalsAwayTeam") ? "?" : resultObject.getString("goalsAwayTeam");

                FixtureModel fixtureModel = new FixtureModel();
                fixtureModel.setDate(toDateFormat.format(fromDateFormat.parse(date)));
                fixtureModel.setHomeTeamName(homeTeamName);
                fixtureModel.setAwayTeamName(awayTeamName);
                fixtureModel.setGoalsHomeTeam(goalsHomeTeam);
                fixtureModel.setGoalsAwayTeam(goalsAwayTeam);
                fixtureModelArrayList.add(fixtureModel);
            }
        }

        return fixtureModelArrayList;
    }

    @Override
    public void onResult(ArrayList<FixtureModel> result) throws Exception {

    }
}
