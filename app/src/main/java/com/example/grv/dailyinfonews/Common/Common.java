package com.example.grv.dailyinfonews.Common;

import com.example.grv.dailyinfonews.Interface.NewsService;
import com.example.grv.dailyinfonews.Remote.RetrofitClient;

/**
 * Created by GRV on 2/26/2018.
 */

public class Common {

    private static final String BASE_URL = "https://newsapi.org/";

    public static final String API_KEY = "2ae84de68c1b45279b47ff88afbafdfc";

    public static NewsService getNewsService(){

        return RetrofitClient.getClient(BASE_URL).create(NewsService.class);
    }
}
