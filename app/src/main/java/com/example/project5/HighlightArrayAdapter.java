package com.example.project5;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * An ArrayAdapter extension that allows for
 * highlighting the selected item in a list.
 * This class is used to visually distinguish the
 * currently selected item from other items in a list view.
 *
 * @author Zain Zulfiqar, Nicholas Yim
 */
public class HighlightArrayAdapter extends ArrayAdapter<String> {
    private int selectedPosition = -1;

    /**
     * Constructor for HighlightArrayAdapter.
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing
     *                 a layout to use when instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public HighlightArrayAdapter(Context context,
                                 int resource, List<String> objects) {
        super(context, resource, objects);
    }

    /**
     * Sets the selected position in the list
     * and notifies the adapter that the data set has changed.
     *
     * @param position The position of the item within the adapter's
     *                 data set to be highlighted.
     */
    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }


    /**
     * Gets the currently selected position in the list.
     *
     * @return The position of the currently selected item.
     */
    public int getSelectedPosition(){return selectedPosition;}

    /**
     * Gets a View that displays the data at the specified position in the data set.
     * Highlights the view if it is the selected position.
     *
     * @param position    The position of the item within the adapter's data set
     *                    of the item whose view we want.
     * @param convertView The old view to reuse, if possible.
     * @param parent      The parent that this view will eventually be attached to.
     * @return A View corresponding to the data at the specified position.
     */
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