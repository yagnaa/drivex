package com.drivex.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drivex.MainActivity;
import com.drivex.R;
import com.drivex.adapter.FilterAdapter;

/**
 * Created by sujeetkumarmehta on 8/30/15.
 */
public class FilterFragment extends Fragment {
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_search_filter, container, false);

        final Context context = getContext();

        viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);


        viewPager.setAdapter(new FilterAdapter(getActivity()));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((MainActivity) getActivity()).changeTitle(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setCurrentItem(((MainActivity) getActivity()).getWhichOneSelected());

        return rootView;
    }

    public void changeView(int position) {

        if (viewPager != null) {
            viewPager.setCurrentItem(position);
        }
    }
}
