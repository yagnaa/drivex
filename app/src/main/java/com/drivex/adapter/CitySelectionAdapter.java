package com.drivex.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.drivex.R;
import com.drivex.Utility.Utils;
import com.drivex.data.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by sujeetkumarmehta on 11/27/15.
 */
public class CitySelectionAdapter extends BaseAdapter {

    Activity activity;


    List<String> cityArrayList, tempCityArrayList;

    public CitySelectionAdapter(Activity activity) {
        this.activity = activity;
        city = new Utils(activity).getSavedString("city", "Bengaluru");
        cityArrayList = new ArrayList<>(Arrays.asList(Data.CITY_LIST));
        tempCityArrayList=new ArrayList<>();
        tempCityArrayList.addAll(cityArrayList);
    }

    String matching = "";

    public void filter(String s) {
        s = s.toLowerCase(Locale.getDefault());

        matching = s;
        cityArrayList.clear();
        if (s.length() == 0) {
            cityArrayList.addAll(tempCityArrayList);
        } else {
            for (String cDM : tempCityArrayList) {
                if (cDM.toLowerCase().contains(s.toLowerCase())) {
                    cityArrayList.add(cDM);
                }
            }
        }


        notifyDataSetChanged();
    }

    String city;

    @Override
    public int getCount() {
        return cityArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder;
        if (convertView == null) {

            convertView = LayoutInflater.from(activity).inflate(R.layout.view_checked_text, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.checked_text_view);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String cityNow=cityArrayList.get(position);

        if (city.equalsIgnoreCase(cityArrayList.get(position))){
            viewHolder.textView.setTextAppearance(activity,R.style.boldText);}
        else{
            viewHolder.textView.setTextAppearance(activity,R.style.normalText);}

        viewHolder.textView.setText(cityNow.toLowerCase().contains(matching)?
                Utils.colorfullText(cityNow, cityNow.toLowerCase().indexOf(matching), matching.length()):cityNow);



        return convertView;
    }

    class ViewHolder {
        TextView textView;
    }
}
