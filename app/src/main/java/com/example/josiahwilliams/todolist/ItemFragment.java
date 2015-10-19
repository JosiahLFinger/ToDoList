package com.example.josiahwilliams.todolist;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Josiah Williams on 10/19/2015.
 */
public class ItemFragment extends Fragment {

    //TODO delete button?

    TextView toDoInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle savedInstanceState) {
        //inflate view from xml
        View v = inflater.inflate(R.layout.fragment_item_details, group, false);

        //builds textview and links to xml
        toDoInfo = (TextView) v.findViewById(R.id.item_detail_text);
        String itemText = getArguments().getString(MainActivity.TODO_DETAIL);
        toDoInfo.setText(itemText);

        return v;
    }
}
