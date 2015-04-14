package edu.uw.tacoma.mmuppa.sqlitedataexample;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class ExampleListActivity extends ActionBarActivity {


    private MyData mMyData;
    private ListView mListView;
    protected final static String EXAMPLE_ID = "EXAMPLE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_list);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Retrieve data from the database
        mMyData = new MyData(this);
        final ArrayList<Example> allData = mMyData.selectAll();
        mMyData.close();

        // Instantiate the adapter and bind to the listview
        ExampleAdapter exampleAdapter = new ExampleAdapter(this, allData);

        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(exampleAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_example_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Added a + to add a new item
        if (id == R.id.action_new) {
            Intent intent = new Intent(this, DataActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
