package com.drivex;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.drivex.adapter.SearchResultAdapter;

/**
 * Created by sujeetmehta on 13/12/15.
 */
public class SearchListActivity extends BaseActivity2 {
    Toolbar toolbar;

    String[] sortStrings = new String[]{
            "Price Low to High",
            "Price High to Low",
            "Mileage Low to High",
            "Mileage High to Low"
    };
    private TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        titleTextView = (TextView) findViewById(R.id.text_view);

        titleTextView.setText(MainActivity.types[getIntent().getIntExtra("type", 0)]);
        titleTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        ListView listView = (ListView) findViewById(R.id.listview);
        SearchResultAdapter searchResultAdapter = new SearchResultAdapter(this);
        listView.setAdapter(searchResultAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(SearchListActivity.this,DetailActivity.class));
            }
        });

    }

    public void onSortClick(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(SearchListActivity.this);
        builder.setSingleChoiceItems(sortStrings, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setTitle("Sort");

        builder.setPositiveButton("APPLY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void onFilterClick(View view) {
        Intent intentFilter = new Intent(SearchListActivity.this, FilterActivity.class);
        startActivity(intentFilter);
        overridePendingTransition(R.anim.bottom_in, R.anim.no_anim);
        //startActivity(new Intent(SearchListActivity.this,DetailActivity.class));
    }
}
