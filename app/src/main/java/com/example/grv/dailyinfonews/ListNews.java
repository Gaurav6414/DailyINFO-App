package com.example.grv.dailyinfonews;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.grv.dailyinfonews.Adapter.ListNewsAdapter;
import com.example.grv.dailyinfonews.Common.Common;
import com.example.grv.dailyinfonews.Interface.NewsService;
import com.example.grv.dailyinfonews.Model.Article;
import com.example.grv.dailyinfonews.Model.News;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.florent37.diagonallayout.DiagonalLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListNews extends AppCompatActivity {

    NewsService mService;
    KenBurnsView kenBurnsView;
    DiagonalLayout diagonalLayout;
    SpotsDialog alertDialog;
    TextView top_author,top_title;
    SwipeRefreshLayout refreshLayout;

    String source="",sortBy="",webHostUrl;

    ListNewsAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);

        //init service

        mService = Common.getNewsService();

        alertDialog = new SpotsDialog(this);


        //init view

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh1);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNews(source,true);
            }
        });


        diagonalLayout = findViewById(R.id.diagonal_layout);
        diagonalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent detail = new Intent(getBaseContext(), DetailNews.class);
                detail.putExtra("webUrl", webHostUrl);
                startActivity(detail);

            }
        });


        kenBurnsView = findViewById(R.id.top_image);
        top_author = findViewById(R.id.top_author);
        top_title = findViewById(R.id.top_title);

        recyclerView = (RecyclerView) findViewById(R.id.first_news);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);


//Intent
        if(getIntent() != null)
        {
            source = getIntent().getStringExtra("source");
            if(!source.isEmpty())
            {
                loadNews(source,false);
            }
        }

    }

    private void loadNews(String source,boolean isRefreshed){
        if (!isRefreshed){
            alertDialog.show();

            mService.getArticles(Common.getApiUrl(source,Common.API_KEY))
                    .enqueue(new Callback<News>() {
                        @Override
                        public void onResponse(Call<News> call, Response<News> response) {
                                //get first article
                            alertDialog.dismiss();
                            Picasso.with(getBaseContext())
                                    .load(response.body().getArticles().get(0).getUrlToImage())
                                    .into(kenBurnsView);

                            top_title.setText(response.body().getArticles().get(0).getTitle());
                            top_author.setText(response.body().getArticles().get(0).getAuthor());

                            webHostUrl = response.body().getArticles().get(0).getUrl();

                            List<Article> removefirst = response.body().getArticles();

                            removefirst.remove(0);

                            adapter = new ListNewsAdapter(removefirst, getBaseContext());
                            adapter.notifyDataSetChanged();
                            recyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<News> call, Throwable t) {

                        }
                    });
        } else {
            alertDialog.show();

            mService.getArticles(Common.getApiUrl(source, Common.API_KEY))
                    .enqueue(new Callback<News>() {
                        @Override
                        public void onResponse(Call<News> call, Response<News> response) {
                            //get first article
                            alertDialog.dismiss();
                            Picasso.with(getBaseContext())
                                    .load(response.body().getArticles().get(0).getUrlToImage())
                                    .into(kenBurnsView);

                            top_title.setText(response.body().getArticles().get(0).getTitle());
                            top_author.setText(response.body().getArticles().get(0).getAuthor());

                            webHostUrl = response.body().getArticles().get(0).getUrl();

                            List<Article> removefirst = response.body().getArticles();

                            removefirst.remove(0);

                            adapter = new ListNewsAdapter(removefirst, getBaseContext());
                            adapter.notifyDataSetChanged();
                            recyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<News> call, Throwable t) {

                        }
                    });

            refreshLayout.setRefreshing(false);
        }

    }
}
