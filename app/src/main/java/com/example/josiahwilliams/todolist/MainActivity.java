package com.example.josiahwilliams.todolist;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity implements ItemListFragment.OnListItemSelectedListener,
    ItemListFragment.OnNewItemRequestListener, AddNewItemFragment.OnNewItemCreatedListener {

    public static final String TODO_DETAIL = "item detail text";

    private ItemListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add new list fragment
        listFragment = new ItemListFragment();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        //add new listfragment
        ft.add(android.R.id.content, listFragment);
        ft.commit();
    }

    @Override
    public void onListItemSelected(String itemDetail) {
        //when user selects an item from the list, fragments are swapped for new view

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        //gets a new itemfragmnet
        ItemFragment itemFragment = new ItemFragment();

        //create bundle to hold item detail information
        Bundle arguments = new Bundle();
        arguments.putString(TODO_DETAIL, itemDetail);
        itemFragment.setArguments(arguments);

        ft.addToBackStack(itemFragment.getClass().getName());
        //replace the current fragment with new itemfragment
        ft.replace(android.R.id.content, itemFragment);
        ft.commit();
    }

    @Override
    public void onNewItemRequest(){
        //new fragment for adding an item to the list
        FragmentTransaction ft =  getFragmentManager().beginTransaction();
        AddNewItemFragment addFragment = new AddNewItemFragment();

        ft.addToBackStack(addFragment.getClass().getName());
        //replace old fragment with new addfragment
        ft.replace(android.R.id.content, addFragment);
        ft.commit();
    }


    @Override
    //method called whenever a new item is created to add it to the list
    public void newItemCreated(String newItem) {

        //if there is not new item or user didn't put any text
        if (newItem == null || newItem.equals("")) {
            //tell them there was nothing added
            Toast.makeText(this, "No text entered, now new item created", Toast.LENGTH_SHORT).show();
        } else {
            //if there is an item, add it to the list
            listFragment.addNewItem(newItem);

            FragmentManager fm = getFragmentManager();
            //pop the backstack so pressing back here would close the app
            fm.popBackStack();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(android.R.id.content, listFragment);
            ft.commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
