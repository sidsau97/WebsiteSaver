package testapp.example.com.databaseonlist;

import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by roeland on 15-6-2016.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Database";

    private static final String WEBSITE_TABLE = "Websites";

    private static final String KEY_ID = "id";
    private static final String KEY_WEBSITE = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_COMMENT = "comment";
    private static final String KEY_URL = "url";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WEBSITES_TABLE = "CREATE TABLE " + WEBSITE_TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + KEY_WEBSITE + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_USERNAME + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_COMMENT + " TEXT,"
                + KEY_URL + " TEXT)";
        db.execSQL(CREATE_WEBSITES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WEBSITE_TABLE);
        onCreate(db);
    }

    void addWebsite(String name, String email, String username, String password, String comment, String url) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WEBSITE, name);
        values.put(KEY_EMAIL,email);
        values.put(KEY_USERNAME, username);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_COMMENT, comment);
        values.put(KEY_URL, url);

        db.insert(WEBSITE_TABLE, null, values);
        db.close();
    }

    public List<Website> getAllWebsites() {
        List<Website>websites = new ArrayList<Website>();
        String getWebsitesQuery = "SELECT  * FROM " + WEBSITE_TABLE + " ORDER BY " + KEY_WEBSITE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(getWebsitesQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Website website = new Website();
                website.setId(cursor.getInt(0));
                website.setName(cursor.getString(1));
                website.setEmail(cursor.getString(2));
                website.setUsername(cursor.getString(3));
                website.setPassword(cursor.getString(4));
                website.setComment(cursor.getString(5));
                website.setUrl(cursor.getString(6));

                websites.add(website);
            } while (cursor.moveToNext());
        }

        return websites;
    }
    public void deleteWebsite(Website website){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(WEBSITE_TABLE, KEY_ID + " = ? ", new String[]{String.valueOf(website.getId())});
        db.close();
    }
   public String findWebsite(String website){
       String findQuery = "SELECT " + KEY_ID + ", "+ KEY_WEBSITE + ", " + KEY_EMAIL + ", " + KEY_USERNAME + ", " + KEY_PASSWORD + ", " + KEY_COMMENT + " FROM " + WEBSITE_TABLE + " WHERE " + KEY_WEBSITE + "='" + website + "'";
       SQLiteDatabase db = this.getWritableDatabase();
       Cursor cursor = db.rawQuery(findQuery, null);
       String websiteString;
       if (cursor.moveToFirst()) {
           cursor.moveToFirst();
           websiteString = cursor.getString(0)+"\n"+cursor.getString(1)+"\n"+cursor.getString(2)+"\n"+cursor.getString(3)+"\n"+cursor.getString(4)+"\n"+cursor.getString(5);
           cursor.close();
       } else {
           websiteString = null;
       }
       db.close();
       return websiteString;
    }
    public void updateWebsite(String id, String name, String email, String username, String password, String comment, String url){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WEBSITE, name);
        values.put(KEY_EMAIL, email);
        values.put(KEY_USERNAME, username);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_COMMENT, comment);
        values.put(KEY_URL, url);

        String where = "id=?";

        db.update(WEBSITE_TABLE, values , where, new String[]{id});
        db.close();

    }
}
