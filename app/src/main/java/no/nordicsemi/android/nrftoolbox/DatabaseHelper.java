package no.nordicsemi.android.nrftoolbox;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.BreakIterator;
import java.text.DateFormat;


public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String BRACE_TABLE = "BRACE_TABLE";
    public static final String NAME = "NAME";
    public static final String DATA = "DATA";
    public static final DateFormat DATE = DateFormat.getDateInstance();
    public static final String ACTIVITY = "ACTIVITY";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "kniebrace database", null, 1);
    }

    //db aanmaak
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = " CREATE TABLE " + BRACE_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, "  + DATA + " TEXT, " + ACTIVITY + " TEXT)";
        db.execSQL(createTableStatement);
    }

    //version updates
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(DatabaseModel databasemodel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAME, databasemodel.getName());
        cv.put(DATA, databasemodel.getData());
        //ERROR in cv.put date nog uitzoeken
        cv.put(ACTIVITY, databasemodel.getName());

        long insert = db.insert(BRACE_TABLE, null, cv);
        return insert != -1;
    }
}
