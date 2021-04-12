package com.example.stockbrain.model.database;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
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

        db.execSQL("CREATE TABLE Exchange ("
                + "'exchange_id' INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "'name' VARCHAR(10) NOT NULL, "
                + "'currency' CHAR(3) DEFAULT NULL, "
                + "'created_date' DATETIME DEFAULT CURRENT_TIMESTAMP(), "
                + "'last_updated' DATETIME DEFAULT CURRENT_TIMESTAMP(), "
                + "DEFAULT CHARACTER SET = utf8;");


        db.execSQL("CREATE TABLE SECURITY ("
                + "'security_id' INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "'exchange_id' INTEGER NOT NULL, "
                + "'ticker' VARCHAR(10) NOT NULL, "
                + "'name' VARCHAR DEFAULT NULL, "
                + "'sector' VARCHAR DEFAULT NULL, "
                + "'industry' VARCHAR, "
                + "'created_date' DATETIME DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP(), "
                + "'last_updated' DATETIME DEFAULT CURRENT_TIMESTAMP() " +
                "ON UPDATE CURRENT_TIMESTAMP(), "
                + "PRIMARY KEY ('security_id'), "
                + "INDEX 'exchange_id' ('exchange_id' ASC),"
                + "INDEX 'ticker' ('ticker' ASC), "
                + "CONSTRAINT 'fk_exchange_id' FOREIGN KEY ('exchange_id') " +
                "REFERENCES 'exchange' ('id')" +
                "ON DELETE NO ACTION" +
                "ON UPDATE NO ACTION)," +
                "DEFAULT CHARACTER SET = utf8;");

        db.execSQL("CREATE TABLE DailyPrice ( " +
                "  'dailyPrice_id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "  'data_vendor_id' INT(11) NOT NULL, " +
                "  'ticker_id' INT(11) NOT NULL, " +
                "  'price_date' DATE NOT NULL, " +
                "  'created_date' DATETIME DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP(), " +
                "  'last_updated' DATETIME DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP(), " +
                "  'open_price'' DECIMAL(11,6) NULL DEFAULT NULL, " +
                "  'high_price'' DECIMAL(11,6) NULL DEFAULT NULL, " +
                "  'low_price' DECIMAL(11,6) NULL DEFAULT NULL, " +
                "  'close_price' DECIMAL(11,6) NULL DEFAULT NULL, " +
                "  'adj_close_price' DECIMAL(11,6) NULL DEFAULT NULL, " +
                "  'volume' BIGINT(20) NULL DEFAULT NULL, " +
                "  PRIMARY KEY (‘dailyPrice_id’), " +
                "  INDEX ‘price_date’ (‘price_date’ ASC), " +
                "  INDEX ‘ticker_id’ (‘ticker_id’ ASC), " +
                "  CONSTRAINT ‘fk_ticker_id’ " +
                "    FOREIGN KEY (‘ticker_id’) " +
                "    REFERENCES ‘security’ (‘dailyPrice_id’) " +
                "    ON DELETE NO ACTION " +
                "    ON UPDATE NO ACTION, " +
                "  CONSTRAINT ‘fk_data_vendor_id’ " +
                "    FOREIGN KEY (‘data_vendor_id’) " +
                "    REFERENCES ‘data_vendor’ (‘dailyPrice_id’) " +
                "    ON DELETE NO ACTION " +
                "    ON UPDATE NO ACTION) " +
                "DEFAULT CHARACTER SET = utf8; ");

        db.execSQL("Create Table FundamentalData (" +
                "'fundamentalData_id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "'ticker_id' INTEGER NOT NULL, " +
                "'turnover' DECIMAL(40,4) DEFAULT NULL, " +
                "'profit' DECIMAL(40,4) DEFAULT NULL, " +
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

    }
}
