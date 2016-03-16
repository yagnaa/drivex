package com.drivex;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.drivex.adapter.FilterButtonAdapter;
import com.drivex.adapter.FilterChoiceAdapter;
import com.drivex.adapter.SearchResultAdapter;

/**
 * Created by sujeetmehta on 13/12/15.
 */
public class FilterActivity extends BaseActivity2 {
    Toolbar toolbar;

    public static final String[][] filterValues = new String[][]{
            {"1L-3L", "3L-5L", "5L-10L", "10L-20L", "20L-50L", "50L-80L", "80L-1Cr", "1Cr Above"}
            ,
            {"Maruti Suzuki", "Toyota", "Hyundai", "TATA", "Mahindra", "Honda", "Nisaan", "Renault",
                    "Volkswagon", "Ford", "Chevrolet", "Audi", "BMW", "Fiat", "Mercedes-Benz"},
            {
                    "Petrol", "Diesel", "Electric", "Hybrid"
            }, {
            "Sedan", "Hatchback", "SUV/MUV", "Luxury", "Coupe", "Convertible", "Van", "Truck"
    }
            , {"Manual", "Automatic"}
            , {
            "2", "4", "5", "7", "8"
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_navigation_close);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ListView listViewButtons = (ListView) findViewById(R.id.list_item_button);
        final FilterChoiceAdapter filterChoiceAdapter = new FilterChoiceAdapter(this);
        FilterButtonAdapter filterButtonAdapter = new FilterButtonAdapter(this, new ConnectionOfList() {
            @Override
            public void onChange() {
                filterChoiceAdapter.notifyDataSetChanged();
            }
        });
        listViewButtons.setAdapter(filterButtonAdapter);

        ListView listViewChoiceContent = (ListView) findViewById(R.id.list_item_content);


        listViewChoiceContent.setAdapter(filterChoiceAdapter);
    }

    public void onApplyClick(View view) {
        onBackPressed();

    }

    public void onCancelClick(View view) {


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.no_anim, R.anim.bottom_out);
    }

    public interface ConnectionOfList {
        public void onChange();
    }
}
