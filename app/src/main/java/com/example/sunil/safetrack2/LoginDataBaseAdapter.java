package com.example.sunil.safetrack2;

/**
 * Created by Sunil on 09/11/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class LoginDataBaseAdapter extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "bsafe.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USERS = "users";

    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    private static final String DATABASE_CREATE = "create table USERS " +
            "( ID integer primary key autoincrement, USERNAME  text,PASSWORD text,EMAIL text, " +
            "TELEPHONE1 text, TELEPHONE2 text, TELEPHONE3 text); ";
    // Variable to hold the database instance
    // table definitions
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL= "email";
    private static final String KEY_TELEPHONE1= "telephone1";
    private static final String KEY_TELEPHONE2= "telephone2";
    private static final String KEY_TELEPHONE3= "telephone3";
    // Context of the application using the database.

    // Database open/upgrade helper
    public  LoginDataBaseAdapter(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "create table " + TABLE_USERS  +
                "( ID integer primary key autoincrement, USERNAME  text,PASSWORD text,EMAIL text, " +
                "TELEPHONE1 text, TELEPHONE2 text, TELEPHONE3 text); ";
        db.execSQL(CREATE_USERS_TABLE );
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS );

        // Create tables again
        onCreate(db);
    }

    // Adding new user
    public void addUser( Users  users) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, users.getUserName() ); // Contact Name
        values.put(KEY_PASSWORD, users.getPassWord());
        values.put(KEY_EMAIL, users.getEmail());
        values.put(KEY_TELEPHONE1 , users.getTelePhone1() );
        values.put(KEY_TELEPHONE2 , users.getTelePhone2() );
        values.put(KEY_TELEPHONE3 , users.getTelePhone3() );
        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        db.close(); // Closing database connection
    }// Getting single contact

    public Users  getUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        // get dat afrom table users where key is email
        Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID,
                        KEY_USERNAME, KEY_PASSWORD,KEY_EMAIL,KEY_TELEPHONE1 , KEY_TELEPHONE2, KEY_TELEPHONE3  }, KEY_USERNAME+ "=?",
                new String[] { String.valueOf(username) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
         Users users = new Users ( cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getString(3),
                   cursor.getString(4),cursor.getString(5), cursor.getString(6));
               // return contact
        db.close();
        return users;
    }
    public String getPassword(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_USERS, null, " USERNAME =?", new String[]{username}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        db.close(); // Closing database connection
        return password;
    }

    // Getting All Contacts
    public List<Users> getAllUsers() {
        List<Users> usersList = new ArrayList<Users>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Users users= new Users();
                users.setID(Integer.parseInt(cursor.getString(0)));
                users.setUserName(cursor.getString(1));
                users.setPassWord(cursor.getString(2));
                users.setEmail(cursor.getString(3));
                users.setTelePhone1(cursor.getString(4)) ;
                users.setTelePhone2(cursor.getString(5)) ;
                users.setTelePhone3(cursor.getString(6)) ;
                // Adding contact to list
                usersList.add(users);
            } while (cursor.moveToNext());
        }

        // return contact list
        return usersList;
    }
    // Getting total numbber of users
    public int getUserCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
    // Updating single user
    public int updateUser(Users users) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, users.getUserName());
        values.put(KEY_EMAIL, users.getEmail());
        values.put(KEY_PASSWORD, users.getPassWord());
        values.put(KEY_TELEPHONE1 , users.getTelePhone1() );
        values.put(KEY_TELEPHONE2 , users.getTelePhone2() );
        values.put(KEY_TELEPHONE3 , users.getTelePhone3() );
        // updating row
        return db.update(TABLE_USERS,  values, KEY_USERNAME + " = ?",
                new String[] { String.valueOf(users.getEmail() ) });
    }

    // Deleting single contact
    public void deleteContact(Users users) {
        SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_USERS, KEY_ID + " = ?",
                new String[] { String.valueOf(users.getEmail()) });
        db.close();
    }
}