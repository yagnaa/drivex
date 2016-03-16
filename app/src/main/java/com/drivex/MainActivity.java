package com.drivex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.drivex.Utility.Utils;
import com.drivex.fragment.FilterFragment;
import com.drivex.fragment.HomeFragment;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    public static final String[] types = new String[]{"by Brand", "by Budget", "by Type"};
    TextView titleTextView;
    Toolbar toolbar;
    private Utils utils;
    private ImageView searchImage;

    public int getWhichOneSelected() {
        return whichOneSelected;
    }

    public void setWhichOneSelected(int whichOneSelected) {
        this.whichOneSelected = whichOneSelected;
    }

    int whichOneSelected;
    ImageView exploreImageView;

    boolean isHome = true;

    com.sothree.slidinguppanel.SlidingUpPanelLayout slidingUpPanelLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        loadFragment();

        toolbar.setPopupTheme(R.style.PopupMenu);
        utils = new Utils(this);
        toolbar.setNavigationIcon(R.drawable.ic_action_navigation_menu);
        titleTextView = (TextView) findViewById(R.id.text_view);

        exploreImageView = (ImageView) findViewById(R.id.explore_image);

        searchImage = (ImageView) findViewById(R.id.search_image);

        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cityIntent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(cityIntent);
                overridePendingTransition(R.anim.bottom_in, R.anim.no_anim);
            }
        });


        titleTextView.setOnClickListener(this);

        slidingUpPanelLayout = (com.sothree.slidinguppanel.SlidingUpPanelLayout) findViewById(R.id.sliding_layout);

        slidingUpPanelLayout.setPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelCollapsed(View panel) {
                exploreImageView.startAnimation(flipUp);
                exploreImageView.setImageResource(R.drawable.ic_action_navigation_arrow_drop_up);

            }

            @Override
            public void onPanelExpanded(View panel) {
                exploreImageView.startAnimation(flipDown);
                exploreImageView.setImageResource(R.drawable.ic_action_navigation_arrow_drop_down);
            }

            @Override
            public void onPanelAnchored(View panel) {

            }

            @Override
            public void onPanelHidden(View panel) {

            }
        });
    }

    private void goToCitySelection() {
        Intent cityIntent = new Intent(MainActivity.this, SelectCityActivity.class);
        startActivity(cityIntent);
        overridePendingTransition(R.anim.bottom_in, R.anim.no_anim);
    }

    @Override
    protected void onResume() {
        super.onResume();
        titleTextView.setText(utils.getSavedString("city", "Bengaluru"));
    }

    FragmentManager fragmentManager;
    FilterFragment fragment;

    public void onFliterClick(View v) {

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        int type = 0;
        switch (v.getId()) {
            case R.id.byBrand:
                type = 0;
                break;
            case R.id.byBudget:
                type = 1;
                break;
            case R.id.byType:
                type = 2;
                break;

        }

        setWhichOneSelected(type);
        fragment = new FilterFragment();

        titleTextView.setText(types[type]);


        toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow_back);

        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_left2, R.anim.slide_in_left);
        isHome = false;
        fragmentTransaction.replace(R.id.frame_layout, fragment).commit();

    }

    private void showTypePopUp(final FilterFragment fragment) {
        final PopupMenu popupMenu = new PopupMenu(MainActivity.this, titleTextView);
        popupMenu.inflate(R.menu.type_filter_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                titleTextView.setText(menuItem.getTitle().toString());
                titleTextView.setText(types[menuItem.getOrder() - 1]);
                popupMenu.dismiss();
                fragment.changeView(menuItem.getOrder() - 1);
                return true;
            }
        });
        if (popupMenu != null)
            popupMenu.show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (isHome) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                } else {

                    getSupportFragmentManager().popBackStackImmediate();
                    isHome = true;
                    titleTextView.setText("Bangalore");
                    toolbar.setNavigationIcon(R.drawable.ic_action_navigation_menu);

                }

                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void changeTitle(int position) {
        titleTextView.setText(types[position]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //  getMenuInflater().inflate(R.menu.global, menu);
        return true;
    }

    public void openMagic(View view) {
        startActivity(new Intent(MainActivity.this, VIrtualReality.class));
    }


    private void loadFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        HomeFragment fragment = new HomeFragment();
        // fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.replace(R.id.frame_layout, fragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_view:
                if (isHome) {
                    goToCitySelection();
                } else {
                    showTypePopUp(fragment);
                }
                break;
        }
    }
}
