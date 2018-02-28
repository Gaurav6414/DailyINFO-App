package com.example.grv.dailyinfonews;

import android.app.AlertDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.grv.dailyinfonews.Adapter.ListSourceAdapter;
import com.example.grv.dailyinfonews.Common.Common;
import com.example.grv.dailyinfonews.Interface.NewsService;
import com.example.grv.dailyinfonews.Model.WebSite;
import com.google.gson.Gson;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView listWebSite;
    RecyclerView.LayoutManager layoutManager;
    NewsService mService;
    ListSourceAdapter adapter;
    AlertDialog dialog;
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialization cache

        Paper.init(this);

        // Init news service

        mService = Common.getNewsService();

        //init layout view

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadWebSiteSOurce(true);
            }
        });

        listWebSite = (RecyclerView) findViewById(R.id.list_item_recycle);

        listWebSite.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        listWebSite.setLayoutManager(layoutManager);

        dialog = new SpotsDialog(this);

        loadWebSiteSOurce(false);
    }

    private void loadWebSiteSOurce(boolean isRefreshed) {

        if (! isRefreshed){

            String cache = Paper.book().read("cache");

            if(cache != null && !cache.isEmpty()){

                WebSite webSite = new Gson().fromJson(cache,WebSite.class);
                adapter = new ListSourceAdapter(getBaseContext(),webSite);
                adapter.notifyDataSetChanged();
                listWebSite.setAdapter(adapter);
            }
            else
            {
                dialog.show();  //fetch latest data

                mService.getSources().enqueue(new Callback<WebSite>() {
                    @Override
                    public void onResponse(Call<WebSite> call, Response<WebSite> response) {
                        adapter = new ListSourceAdapter(getBaseContext(),response.body());
                        adapter.notifyDataSetChanged();
                        listWebSite.setAdapter(adapter);


                        Paper.book().write("cache",new Gson().toJson(response.body())); //saving cache
                    }

                    @Override
                    public void onFailure(Call<WebSite> call, Throwable t)
                    {

                    }
                });
            }
        }

        else
        {
            dialog.show();  //fetch latest data

            mService.getSources().enqueue(new Callback<WebSite>() {
                @Override
                public void onResponse(Call<WebSite> call, Response<WebSite> response) {
                    adapter = new ListSourceAdapter(getBaseContext(),response.body());
                    adapter.notifyDataSetChanged();
                    listWebSite.setAdapter(adapter);


                    Paper.book().write("cache",new Gson().toJson(response.body())); //saving cache

                    //dismiss refresh

                    refreshLayout.setRefreshing(false);
                }



                @Override
                public void onFailure(Call<WebSite> call, Throwable t)
                {

                }
            });
        }
    }
}
