package com.example.grv.dailyinfonews;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import dmax.dialog.SpotsDialog;

public class DetailNews extends AppCompatActivity {

    WebView webView;
    SpotsDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        alertDialog = new SpotsDialog(this);
        alertDialog.show();

        //webview

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                alertDialog.dismiss();
            }
        });

        if (getIntent() != null) {

            if (!getIntent().getStringExtra("webUrl").isEmpty())
                webView.loadUrl(getIntent().getStringExtra("webUrl"));
        }

    }
}
