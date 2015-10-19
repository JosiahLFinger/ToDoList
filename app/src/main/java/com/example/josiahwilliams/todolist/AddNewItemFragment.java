package com.example.josiahwilliams.todolist;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Josiah Williams on 10/19/2015.
 */
public class AddNewItemFragment extends Fragment {

    Button saveButton;
    EditText itemDetails;

    OnNewItemCreatedListener newItemListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            newItemListener = (OnNewItemCreatedListener) activity;
        } catch (ClassCastException cce) {
            throw new ClassCastException(activity.toString() + " must implement" +
                    "onNewItemCreatedListener") ;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_add_new_item, group, false);

        //create button with listener to save user input
        saveButton = (Button) v.findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save text and send back to activity
                String info = itemDetails.getText().toString();
                newItemListener.newItemCreated(info);
            }
        });

        itemDetails = (EditText) v.findViewById(R.id.new_item_edit_text);

        return v;
    }

    interface OnNewItemCreatedListener {
        void newItemCreated(String newItem);
    }
}
