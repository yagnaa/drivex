package com.drivex;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.drivex.Utility.Utils;
import com.drivex.adapter.CitySelectionAdapter;
import com.drivex.adapter.FilterButtonAdapter;
import com.drivex.adapter.FilterChoiceAdapter;
import com.drivex.data.Data;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sujeetmehta on 13/12/15.
 */
public class SelectCityActivity extends BaseActivity2 {
    Toolbar toolbar;

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_selection);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_navigation_close);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ListView citySelectionList = (ListView) findViewById(R.id.listview);
        final CitySelectionAdapter citySelectionAdapter = new CitySelectionAdapter(this);
        citySelectionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new Utils(SelectCityActivity.this).saveStringData("city", Data.CITY_LIST[position]);
                onBackPressed();
            }
        });
        citySelectionList.setAdapter(citySelectionAdapter);

        editText = (EditText) findViewById(R.id.edit_text);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                citySelectionAdapter.filter(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    @Override
    public void onBackPressed() {
        new Utils(this).hideKeyBoard(editText);

        super.onBackPressed();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                overridePendingTransition(R.anim.no_anim, R.anim.bottom_out);
            }
        }, 300);


    }


}
