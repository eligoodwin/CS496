package com.example.eligoodwin.sqltime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button submitButton;
    private EditText textEntry;
    private EditText intEntry;
    private Cursor sqlCursor;
    private SQLiteDatabase sqlDB;

    private SQLiteExample sqLiteExample;
    private SimpleCursorAdapter simpleCursorAdapter;
    private final String TAG = "SQLActivity";
    private SQLiteDatabase sqLiteDatabase;
    private SimpleCursorAdapter sqlCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqLiteExample = new SQLiteExample(this);
        sqlDB = sqLiteExample.getWritableDatabase();

        //init buttons
        submitButton = (Button)findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sqlDB != null){
                    ContentValues vals = new ContentValues();
                    vals.put(DBContract.DemoTable.COLUMN_NAME_DEMO_STRING, ((EditText)findViewById(R.id.stringEntry)).getText().toString());
                    vals.put(DBContract.DemoTable.COLUMN_NAME_DEMO_LAT, ((EditText)findViewById(R.id.latEntry)).getText().toString());
                    vals.put(DBContract.DemoTable.COLUMN_NAME_DEMO_LONG, ((EditText)findViewById(R.id.longEntry)).getText().toString());
                    sqlDB.insert(DBContract.DemoTable.TABLE_NAME, null, vals);
                    populateTable();
                }
                else{
                    Log.d(TAG, "Unable to find database....");
                }
            }
        });
        populateTable();
    }

    private void populateTable() {
        if(sqlDB != null){
            try {
                if(sqlCursorAdapter != null && sqlCursorAdapter.getCursor() != null){
                    if(!sqlCursorAdapter.getCursor().isClosed()){
                        sqlCursorAdapter.getCursor().close();
                    }
                }
                //make query for cursor
                sqlCursor = sqlDB.query(DBContract.DemoTable.TABLE_NAME,
                        new String[] {DBContract.DemoTable._ID,
                        DBContract.DemoTable.COLUMN_NAME_DEMO_STRING,
                        DBContract.DemoTable.COLUMN_NAME_DEMO_LONG,
                        DBContract.DemoTable.COLUMN_NAME_DEMO_LAT},
                        null,
                        null,
                        null,
                        null,
                        null);
                //init listview element
                ListView sqlListView = (ListView) findViewById(R.id.dbList);
                //init cursor adapter
                sqlCursorAdapter = new SimpleCursorAdapter(
                        this, R.layout.db_items, //the template for the items
                        sqlCursor, //the cursor
                        new String[]{DBContract.DemoTable.COLUMN_NAME_DEMO_STRING,
                                DBContract.DemoTable.COLUMN_NAME_DEMO_LONG,
                                DBContract.DemoTable.COLUMN_NAME_DEMO_LAT}, //contents
                        new int []{R.id.dbString, R.id.dbLong, R.id.dbLat}, //ids in the view
                        0);
                //set the adapter so we can start filling in the view
                sqlListView.setAdapter(sqlCursorAdapter);
            }
            catch(Exception e){
                Log.d(TAG, "Could not load database");
            }
        }
    }
}
