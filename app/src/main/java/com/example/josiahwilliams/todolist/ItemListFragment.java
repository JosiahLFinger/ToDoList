package com.example.josiahwilliams.todolist;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by Josiah Williams on 10/18/2015.
 */
public class ItemListFragment extends Fragment {

    private ListView toDoListView;
    private ArrayAdapter<String> toDoArray;

    private Button addNewItem;

    private OnListItemSelectedListener selectedListener;
    private OnNewItemRequestListener newItemListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group,
                             Bundle savedInstanceState) {
        //connects view to xml
        View v = inflater.inflate(R.layout.fragment_item_list, group, false);

        //create and connect listview
        toDoListView = (ListView) v.findViewById(R.id.to_do_list_view);
        toDoListView.setAdapter(toDoArray);
        //set to only allow 1 selection
        toDoListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        //give the selection a listener for when an item in the list is selected
        toDoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedListener.onListItemSelected(toDoArray.getItem(position));
            }
        });

        //create button and listener for adding new items to the list
        addNewItem = (Button) v.findViewById(R.id.add_new_item);
        addNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v){
                //
             newItemListener.onNewItemRequest();
            }
        });

        return v;
    }

    void addNewItem(String newItem) {
        //add new item to array adapter and notify change
        toDoArray.add(newItem);
        toDoArray.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        //array adapter for the listview
        toDoArray = new ArrayAdapter<>(this.getActivity(), R.layout.list_view_item, R.id.list_item);

        try {
            selectedListener = (OnListItemSelectedListener) activity;
            newItemListener = (OnNewItemRequestListener) activity;
        } catch (ClassCastException cce) {
            throw new ClassCastException(activity.toString() + "must implement all required listeners");
        }
    }

    public interface OnListItemSelectedListener {
        void onListItemSelected(String todoItem);
    }

    public interface OnNewItemRequestListener {
        void onNewItemRequest();
    }
}
