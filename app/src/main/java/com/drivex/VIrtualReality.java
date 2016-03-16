package com.drivex;

import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.drivex.R;


/**
 * Created by sujeetkumarmehta on 8/11/15.
 */
public class VIrtualReality extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webpage);



        final WebView webView = (WebView) findViewById(R.id.webpage);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.eyerevolution.co.uk/wp-content/virtual-tours/ftype-2014/");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
                webView.setVisibility(View.GONE);
            }
        });
//        if (Utility.isNetworkAvailable(getApplicationContext())) {
//            if (getIntent().getStringExtra("url") != null) {
//                webView.loadUrl(getIntent().getStringExtra("url"));
//
//            } else {
//
//            }
//
//        } else {
//            progressBar.setVisibility(View.GONE);
//            Utility.showToast("No Network Available!");
//        }


    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
