package com.simi.codestrokealert.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.simi.codestrokealert.R;


public class CustomAdapter extends BaseAdapter {

    private String[] items;
    private Context context;
    private LayoutInflater inflter;
    int selectedIndex = -1;  //to store only selected item position

    public CustomAdapter(Context context, String[] items) {
        this.context = context;
        this.items = items;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.list_items, null);
        RadioButton rbSelect = (RadioButton) view.findViewById(R.id.rb_checked);
        TextView text = (TextView) view.findViewById(R.id.text_view_checked);
        text.setText(items[position]);

        if(selectedIndex == position){
            rbSelect.setChecked(true);
            text.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        }
        else{
            rbSelect.setChecked(false);
            text.setTextColor(ContextCompat.getColor(context, R.color.light_gray));
        }


        return view;
    }

    public void setSelectedIndex(int index){
        selectedIndex = index;
    }


}
