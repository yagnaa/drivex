package com.drivex;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.drivex.adapter.FilterAdapter;

/**
 * Created by sujeetkumarmehta on 11/26/15.
 */
public class SearchFilterActivity extends BaseActivity2 {

    String[] types = new String[]{"by Brand", "by Budget", "by Type"};
    TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("");
        int TYPE = getIntent().getIntExtra("type", 0);
        titleTextView = (TextView) findViewById(R.id.title);

        titleTextView.setText(types[TYPE]);

        final Context context = getBaseContext();

        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);


        viewPager.setAdapter(new FilterAdapter(SearchFilterActivity.this));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                titleTextView.setText(types[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(context, titleTextView);
                popupMenu.inflate(R.menu.type_filter_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        titleTextView.setText(menuItem.getTitle().toString());
                        titleTextView.setText(types[menuItem.getOrder() - 1]);
                        popupMenu.dismiss();
                        return true;
                    }
                });
                if (popupMenu != null)
                    popupMenu.show();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.global, menu);
        return true;
    }
}
