package com.example.grv.dailyinfonews.Interface;

import com.example.grv.dailyinfonews.Model.WebSite;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by GRV on 2/26/2018.
 */

public interface NewsService {

    @GET ("v2/sources?language=en&apiKey=2ae84de68c1b45279b47ff88afbafdfc")

    Call<WebSite> getSources();

}