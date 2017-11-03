package com.example.eligoodwin.sqltime;

import android.provider.BaseColumns;

/**
 * Created by eligoodwin on 11/2/17.
 */
final class DBContract {
    private DBContract(){};

    public final class DemoTable implements BaseColumns {
        public static final String DB_NAME = "demo_db";
        public static final String TABLE_NAME = "demo";
        public static final String COLUMN_NAME_DEMO_STRING = "demo_string";
        public static final String COLUMN_NAME_DEMO_INT = "demo_int";
        public static final String COLUMN_NAME_DEMO_LAT = "latitude";
        public static final String COLUMN_NAME_DEMO_LONG = "longitude";

        public static final int DB_VERSION = 7;


        public static final String SQL_CREATE_DEMO_TABLE = "CREATE TABLE " +
                DemoTable.TABLE_NAME + "(" + DemoTable._ID + " INTEGER PRIMARY KEY NOT NULL," +
                DemoTable.COLUMN_NAME_DEMO_STRING + " VARCHAR(255)," +
                DemoTable.COLUMN_NAME_DEMO_LAT + " DOUBLE," +
                DemoTable.COLUMN_NAME_DEMO_LONG + " DOUBLE);";

        public  static final String SQL_DROP_DEMO_TABLE = "DROP TABLE IF EXISTS " + DemoTable.TABLE_NAME;
    }
}