package com.example.grv.dailyinfonews.Common;

import com.example.grv.dailyinfonews.Interface.NewsService;
import com.example.grv.dailyinfonews.Remote.RetrofitClient;

/**
 * Created by GRV on 2/26/2018.
 */

public class Common {
    private static final String BASE_URL = "https://newsapi.org/";
    public static NewsService getNewsService(){

        return RetrofitClient.getClient(BASE_URL).create(NewsService.class);
    }
}
