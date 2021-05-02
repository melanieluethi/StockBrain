package com.example.stockbrain.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StockBrainDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "StockBrainDB"; // the name of our database
    private static final int DB_VERSION = 1; // the version of the database


    StockBrainDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }
    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE SECURITY_TABLE ("
                + "'security_id' INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "'ticker' VARCHAR(10) NOT NULL, "
                + "'name' VARCHAR DEFAULT NULL, "
                + "PRIMARY KEY ('security_id'));");

        db.execSQL("CREATE TABLE DailyPrice ( " +
                "  'dailyPrice_id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "  'ticker_id' INTEGER NOT NULL, " +
                "  'price_date' DATE NOT NULL, " +
                "  'close_price' DECIMAL(11,6) DEFAULT NULL, " +
                "  'volume' BIGINT(20) DEFAULT NULL, " +
                "  CONSTRAINT 'fk_ticker_id' " +
                "    FOREIGN KEY ('ticker_id') " +
                "    REFERENCES 'security' ('security_id'));");


        db.execSQL("Create Table FundamentalData (" +
                "'fundamentalData_id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "'ticker_id' INTEGER NOT NULL, " +
                "'Revenue' DECIMAL(40,4) DEFAULT NULL, " +
                "'Profit' DECIMAL(40,4) DEFAULT NULL, " +
                "'Market_capitalization' DECIMAL(40,4) DEFAULT NULL, " +

                "PRIMARY KEY ('fundamentalData_id'), " +
                "CONSTRAINT 'fk_ticker_id' " +
                "    FOREIGN KEY ('ticker_id') " +
                "    REFERENCES 'security' ('security_id') " +
                "    ON DELETE NO ACTION " +
                "    ON UPDATE NO ACTION);");




    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS SECURITY");
        db.execSQL("DROP TABLE IF EXISTS DailyPrice");
        db.execSQL("DROP TABLE IF EXISTS FundamentalData");
        onCreate(db);
    }

 //   public void showData(){//     Cursor result = db.rawQuery("SELECT Revenue FROM FundamentalData WHERE FK_Ticker_Symbol = Ticker_Symbol;");

}
