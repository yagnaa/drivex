package com.drivex.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.drivex.R;

/**
 * Created by sujeetkumarmehta on 11/27/15.
 */
public class ListOfImageAdapter extends BaseAdapter {

    Activity  activity;

    public ListOfImageAdapter(Activity  activity) {
        this.activity=activity;
    }

    @Override
    public int getCount() {
        return 20;
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

            convertView= LayoutInflater.from(activity).inflate(R.layout.view_image, parent, false);
            viewHolder=new ViewHolder();
        } else {
            viewHolder=(ViewHolder)convertView.getTag();


        }
        return convertView;
    }

    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
