/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.drivex;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.drivex.Utility.Utils;
import com.drivex.adapter.ListOfImageAdapter;
import com.drivex.adapter.SearchResultAdapter;

import java.util.ArrayList;


public class DetailActivity extends AppCompatActivity {

    ScrollView scrollView;
    private Utils utils;
    private TextView titleTextView;
    private ImageView shareImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final WebView webView = (WebView) findViewById(R.id.car360webpage);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        titleTextView = (TextView) findViewById(R.id.text_view);
        titleTextView.setText("Toyota Corolla Altis");
        titleTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
        shareImageView = (ImageView) findViewById(R.id.search_image);
        titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, GalleryActivity.class));

            }
        });
        shareImageView.setImageResource(R.drawable.ic_action_social_share_white);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.thedrivex.com/360/car-360.html");
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

        utils = new Utils(this);

        utils.setStatusBarColor(this, android.R.color.black);

        ViewPager viewPager = (ViewPager) findViewById(R.id.review_viewpager);
        ReviewPagerAdapter reviewPagerAdapter = new ReviewPagerAdapter();
        viewPager.setAdapter(reviewPagerAdapter);

        addRadioButtons(5);
        radioButtonArrayList.get(0).setChecked(true);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                unchrckAll();
                radioButtonArrayList.get(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        toolbar.setBackgroundColor(getResources().getColor(R.color.transparent50));
        scrollView = (ScrollView) findViewById(R.id.scroll_view);

        final int headerHeight = (int) Utils.convertDpToPixel(220);

        if (scrollView.getViewTreeObserver().isAlive()) {
            scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {

                @Override
                public void onScrollChanged() {
                    final int toolBarHeight = toolbar.getHeight();
                    int scrollY = scrollView.getScrollY(); //for verticalScrollView

                    final float ratio = (float) scrollY / headerHeight;

                    if (ratio > 0 && scrollY > (headerHeight - toolBarHeight)) {
                        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        utils.setStatusBarColor(DetailActivity.this, R.color.colorPrimaryDark);
                        toolbar.setAlpha(ratio + 0.2f);
                    } else {
                        toolbar.setBackgroundColor(getResources().getColor(R.color.transparent50));
                        toolbar.setAlpha(1);
                        utils.setStatusBarColor(DetailActivity.this, android.R.color.black);
                    }

                }
            });
        }


    }

    public void onGalleryClicked(View view) {
        startActivity(new Intent(DetailActivity.this, GalleryActivity.class));
    }

    private void unchrckAll() {
        for (int i = 0; i < radioButtonArrayList.size(); i++) {
            radioButtonArrayList.get(i).setChecked(false);
        }
    }

    ArrayList<RadioButton> radioButtonArrayList = new ArrayList<>();

    public void addRadioButtons(int number) {

        int size = (int) Utils.convertDpToPixel(8.0f);
        for (int row = 0; row < 1; row++) {
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);

            for (int i = 1; i <= number; i++) {
                RadioButton rdbtn = new RadioButton(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);
                layoutParams.setMargins(size / 2, size, 0, size);
                rdbtn.setLayoutParams(layoutParams);
                rdbtn.setBackgroundResource(R.drawable.radio_view_pager_indicator);
                rdbtn.setButtonDrawable(null);
                rdbtn.setId((row * 2) + i);
                rdbtn.setText("");
                ll.addView(rdbtn);
                radioButtonArrayList.add(rdbtn);
            }
            ((ViewGroup) findViewById(R.id.radiogroup)).addView(ll);
        }

    }

    public void onSpecClick(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View viewSpec = getLayoutInflater().inflate(R.layout.view_spec_of_car, null);
        Button buttonClose = (Button) viewSpec.findViewById(R.id.close_button);

        builder.setView(viewSpec);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }


    class ReviewPagerAdapter extends PagerAdapter {


        public ReviewPagerAdapter() {
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View view = getLayoutInflater().inflate(R.layout.view_reviews, container, false);

            container.addView(view);
            return view;
        }
    }
}
