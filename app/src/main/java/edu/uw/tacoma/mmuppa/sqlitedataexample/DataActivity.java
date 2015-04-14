package edu.uw.tacoma.mmuppa.sqlitedataexample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;



public class DataActivity extends ActionBarActivity {

    private Example mExample;
    private EditText mIdEditText;
    private EditText mNameEditText;

    private MyData mMyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        mMyData = new MyData(this);
        mIdEditText = (EditText) findViewById(R.id.example_id);
        mNameEditText = (EditText) findViewById(R.id.example_name);
        Button submitButton = (Button) findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idStr = mIdEditText.getText().toString();

                // Check to make sure id is entered
                if (idStr.length() == 0) {
                    Toast.makeText(v.getContext(), "Enter Id!", Toast.LENGTH_SHORT).show();
                    mIdEditText.requestFocus();
                    return;
                }
                int id = Integer.parseInt(idStr);

                String name = mNameEditText.getText().toString();

                // Check to make sure name is entered
                if (name.length() == 0) {
                    Toast.makeText(v.getContext(), "Enter name!", Toast.LENGTH_SHORT).show();
                    mNameEditText.requestFocus();
                    return;
                }

                mExample = new Example(id, name);


                try {
                    mMyData.insert(id, name);
                    mMyData.close();
                }
                catch(Exception e) {
                    Toast.makeText(v.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(v.getContext(), "Successful!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_data, menu);
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
