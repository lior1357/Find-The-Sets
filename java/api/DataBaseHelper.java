package afinal.game.lior.findthesets;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 18/11/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "scoreBoardDb.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "leadBoarderTbl" +
            "";
    public static final String UID = "_id";                 // primary Key, automatic ID
    public static final String KEY_NAME = "name";           // name of the player
    public static final String SCORE = "score";               // number of wins


    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NAME + " TEXT, " + SCORE + " INTEGER );";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "+ TABLE_NAME;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    /**
     * @param name
     */
    public void addData(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(SCORE, 0);
        boolean inserted =  db.insert(TABLE_NAME, null, cv)>0;
    }

    public ArrayList<String> showScores(String orderBy)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> rows= new ArrayList<>();
        Cursor cursor;
        if(orderBy.equals("name"))
            cursor= db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY name ASC", null);
        else
            cursor= db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY cast(score as REAL) DESC", null);

        int nameColumn = cursor.getColumnIndex("name");
        int scoreColumn= cursor.getColumnIndex("score");

        cursor.moveToFirst();
        if(cursor != null && cursor.getColumnCount() > 0) {
            do {
                String name = cursor.getString(nameColumn);
                String score = cursor.getString(scoreColumn);

                rows.add("\t\tname: " + name + " score: " + score);
            }while (cursor.moveToNext());
        }

        return rows;
    }

    public void addData(String name,int score)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(SCORE, score);
        boolean inserted =  db.insert(TABLE_NAME, null, cv)>0;
    }

    public void remove( String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_NAME + "= ? " , new String[]{name});
    }
}

