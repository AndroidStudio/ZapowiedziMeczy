package com.footballscore.requests;

import android.util.Log;

import com.footballscore.models.LeagueTableModel;
import com.network.library.MultipartRequestParams;
import com.network.library.RequestCreator;
import com.network.library.RequestHeaders;
import com.network.library.RequestMethod;
import com.network.library.RequestParams;

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
        return null;
    }

    @Override
    public void onResult(ArrayList<LeagueTableModel> result) throws Exception {

    }
}
