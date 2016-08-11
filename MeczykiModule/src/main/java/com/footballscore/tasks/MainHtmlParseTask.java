package com.footballscore.tasks;

import android.os.AsyncTask;

import com.footballscore.models.FixtureModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by Przemysław Skitał on 2016-07-17.
 */
public class MainHtmlParseTask extends AsyncTask<String, String, ArrayList<FixtureModel>> {

    @Override
    protected ArrayList<FixtureModel> doInBackground(String... strings) {
        ArrayList<FixtureModel> fixtureModelArrayList = new ArrayList<>();
        try {
            Thread.sleep(500);
            Document document = Jsoup.connect("http://www.meczyki.pl").get();
            Elements contents = document.getElementsByClass("box-list-items");
            Element element = contents.get(0);

            Elements links = element.getElementsByTag("a");
            int size = links.size();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fixtureModelArrayList;
    }
}
