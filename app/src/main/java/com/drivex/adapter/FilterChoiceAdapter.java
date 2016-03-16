package com.drivex.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.drivex.FilterActivity;
import com.drivex.R;

/**
 * Created by sujeetkumarmehta on 11/27/15.
 */
public class FilterChoiceAdapter extends BaseAdapter {

    Activity activity;

    String[] values;

    public FilterChoiceAdapter(Activity activity) {
        this.activity = activity;
        values = FilterActivity.filterValues[FilterButtonAdapter.checkedPosition];
    }


    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        values = FilterActivity.filterValues[FilterButtonAdapter.checkedPosition];
    }

    @Override
    public int getCount() {
        return values.length;
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

            convertView = LayoutInflater.from(activity).inflate(R.layout.view_checkbox, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);

            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                }
            });
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //viewHolder.checkBox.setTag(position);
        viewHolder.checkBox.setText(values[position]);

        return convertView;
    }

    class ViewHolder {
        CheckBox checkBox;

    }
}
