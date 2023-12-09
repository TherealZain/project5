package com.example.project5;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class HighlightArrayAdapter extends ArrayAdapter<String> {
    private int selectedPosition = -1;

    public HighlightArrayAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }

    public int getSelectedPosition(){return selectedPosition;}


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        if (position == selectedPosition) {
            view.setBackgroundColor(Color.LTGRAY); // Highlight color
        } else {
            view.setBackgroundColor(Color.TRANSPARENT); // Default color
        }
        return view;
    }
}