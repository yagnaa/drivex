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
public class FilterButtonAdapter extends BaseAdapter {

    Activity activity;

    String[] filterButtons = new String[]{"Price", "Brand", "Fuel Type", "Body Type", "Transmission", "Seating"};
    int[] images = new int[]{R.drawable.ic_action_price, R.drawable.ic_action_brand, R.drawable.ic_action_fuel, R.drawable.ic_action_body
            , R.drawable.ic_action_transmission, R.drawable.ic_action_seat};

    FilterActivity.ConnectionOfList connectionOfList;
    public FilterButtonAdapter(Activity activity, FilterActivity.ConnectionOfList connectionOfList) {
        this.activity = activity;
        this.connectionOfList=connectionOfList;
    }

    @Override
    public int getCount() {
        return filterButtons.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

   public static int checkedPosition = 0;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder;
        if (convertView == null) {

            convertView = LayoutInflater.from(activity).inflate(R.layout.view_filter_button, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.text_view);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_view);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);

            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int position = (int) buttonView.getTag();
                    if (isChecked) {
                        checkedPosition = position;
                        notifyDataSetChanged();
                        connectionOfList.onChange();
                    }
                }
            });
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        viewHolder.checkBox.setTag(position);
        viewHolder.textView.setText(filterButtons[position]);
        viewHolder.imageView.setImageResource(images[position]);

        if (checkedPosition == position)
            viewHolder.checkBox.setChecked(true);
        else viewHolder.checkBox.setChecked(false);
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textView;
        CheckBox checkBox;
    }

//    public void uncheckOthers(int pos) {
//        for (int i = 0; i < itemList.size(); i++) {
//            if (i == pos) {
//                itemList.get(i).setIsChecked(true);
//
//            } else {
//                itemList.get(i).setIsChecked(false);
//            }
//        }
//        notifyDataSetChanged();
//    }
}
