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
public class BudgetFilerAdapter extends BaseAdapter {

    Activity activity;

    String[] names;
    int[] icons;

    public BudgetFilerAdapter(Activity activity, String[] names, int[] icons) {
        this.activity = activity;
        this.names = names;
        this.icons = icons;
    }

    @Override
    public int getCount() {
        return names.length;
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

            convertView = LayoutInflater.from(activity).inflate(R.layout.view_image_title, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.text_view);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_view);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setImageResource(icons[position]);
        viewHolder.textView.setText(names[position]);
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
