package com.drivex.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.appyvet.rangebar.RangeBar;
import com.drivex.MainActivity;
import com.drivex.R;
import com.drivex.SearchListActivity;
import com.drivex.data.Data;

/**
 * Created by sujeetkumarmehta on 11/26/15.
 */
public class FilterAdapter extends PagerAdapter {
    @Override
    public int getCount() {
        return 3;
    }


    Activity activity;

    public FilterAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = null;

        if (position == 1) {
            view = activity.getLayoutInflater().inflate(R.layout.view_budget_filter, container, false);

            setFilter(view);

        } else {

            view = activity.getLayoutInflater().inflate(R.layout.grid_view, container, false);
            GridView gridView = (GridView) view.findViewById(R.id.grid_view);
            TextView textView = (TextView) view.findViewById(R.id.text_view);
            textView.setText(position == 0 ? "SELECT BRAND" : "SELECT TYPE");
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    goSearch();
                }
            });
            if (position == 2)
                gridView.setAdapter(new BudgetFilerAdapter(activity, Data.TYPES_OF_CAR, Data.TYPES_OF_CAR_ICONS));
            else
                gridView.setAdapter(new BudgetFilerAdapter(activity, Data.TYPES_OF_BRAND, Data.TYPES_OF_BRAND_ICONS));
        }

        container.addView(view);
        return view;
    }

    private void setFilter(View view) {
        final TextView minTextView = (TextView) view.findViewById(R.id.min_text_view);
        final TextView maxTextView = (TextView) view.findViewById(R.id.max_text_view);
        final Button budgetFilter = (Button) view.findViewById(R.id.button);
        final RangeBar rangebar = (RangeBar) view.findViewById(R.id.rangebar);
        rangebar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex,
                                              int rightPinIndex,
                                              String leftPinValue, String rightPinValue) {
                minTextView.setText("MIN\n" + leftPinValue + "L");
                maxTextView.setText("MAX\n" + rightPinValue + "L");
                budgetFilter.setText(leftPinValue + "L - " + rightPinValue + "L");

            }
        });

        budgetFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSearch();
            }
        });

    }

    public void goSearch() {
        Intent intent = new Intent(activity, SearchListActivity.class);
        intent.putExtra("type", ((MainActivity) activity).getWhichOneSelected());
        activity.startActivity(intent);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
