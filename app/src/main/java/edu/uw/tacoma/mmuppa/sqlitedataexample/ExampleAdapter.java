package edu.uw.tacoma.mmuppa.sqlitedataexample;

import android.content.Context;
import android.view.*;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

/**
 * Created by mmuppa on 4/13/15.
 */
public class ExampleAdapter extends ArrayAdapter<Example> {

    public ExampleAdapter(Context context, ArrayList<Example> examples) {
        super(context, 0, examples);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Example example = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_example_layout, parent, false);
        }
        // Lookup view for data population
        TextView id = (TextView) convertView.findViewById(R.id.item_id);
        TextView name = (TextView) convertView.findViewById(R.id.item_name);
        // Populate the data into the template view using the data object
        id.setText(Integer.toString(example.getId()));
        name.setText(example.getName());
        // Return the completed view to render on screen
        return convertView;
    }
}
