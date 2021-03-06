package sg.edu.np.week_6_whackamole_3_0;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {
    /*
        The Database has the following properties:
        1. Database name is WhackAMole.db
        2. The Columns consist of
            a. Username
            b. Password
            c. Level
            d. Score
        3. Add user method for adding user into the Database.
        4. Find user method that finds the current position of the user and his corresponding
           data information - username, password, level highest score for each level
        5. Delete user method that deletes based on the username
        6. To replace the data in the database, we would make use of find user, delete user and add user

        The database shall look like the following:

        Username | Password | Level | Score
        --------------------------------------
        User A   | XXX      | 1     |    0
        User A   | XXX      | 2     |    0
        User A   | XXX      | 3     |    0
        User A   | XXX      | 4     |    0
        User A   | XXX      | 5     |    0
        User A   | XXX      | 6     |    0
        User A   | XXX      | 7     |    0
        User A   | XXX      | 8     |    0
        User A   | XXX      | 9     |    0
        User A   | XXX      | 10    |    0
        User B   | YYY      | 1     |    0
        User B   | YYY      | 2     |    0

     */
    private static final String DATABASE_NAME = "WhackAMole.db";
    public static final String TABLE_USERS = "Users";
    public static final String COLUMN_USERNAME = "Username";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_LEVEL = "Level";
    public static final String COLUMN_SCORE = "Score";


    private static final String FILENAME = "MyDBHandler.java";
    private static final String TAG = "Whack-A-Mole3.0!";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        /* HINT:
            This is used to init the database.
         */
        super(context, DATABASE_NAME, factory, version);

    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        /* HINT:
            This is triggered on DB creation.
            Log.v(TAG, "DB Created: " + CREATE_ACCOUNTS_TABLE);
         */
        Log.v(TAG, "DB Created: " + DATABASE_NAME);
        String CREATE_USERS_TABLE = "CREATE TABLE " + "'"+TABLE_USERS+"'" +
                "("+
                COLUMN_USERNAME + " TEXT, "+
                COLUMN_PASSWORD + " TEXT," +
                COLUMN_LEVEL + " INTEGER," +
                COLUMN_SCORE + " INTEGER" +
                ")";
        db.execSQL(CREATE_USERS_TABLE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        /* HINT:
            This is triggered if there is a new version found. ALL DATA are replaced and irreversible.
         */
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);

    }

    public void addUser(UserData userData)
    {
            /* HINT:
                This adds the user to the database based on the information given.
                Log.v(TAG, FILENAME + ": Adding data for Database: " + values.toString());
             */
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();

        for (int level : userData.getLevels()) {
            values.put(COLUMN_USERNAME, userData.getMyUserName());
            values.put(COLUMN_PASSWORD, userData.getMyPassword());
            values.put(COLUMN_LEVEL, level);
            values.put(COLUMN_SCORE, 0);
            db.insert(TABLE_USERS, null, values);
        }

        db.close();
    }

    public UserData findUser(String username)
    {
        /* HINT:
            This finds the user that is specified and returns the data information if it is found.
            If not found, it will return a null.
            Log.v(TAG, FILENAME +": Find user form database: " + query);

            The following should be used in getting the query data.
            you may modify the code to suit your design.

            if(cursor.moveToFirst()){
                do{
                    ...
                    .....
                    ...
                }while(cursor.moveToNext());
                Log.v(TAG, FILENAME + ": QueryData: " + queryData.getLevels().toString() + queryData.getScores().toString());
            }
            else{
                Log.v(TAG, FILENAME+ ": No data found!");
            }
         */
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE "
                + COLUMN_USERNAME
                + " = \"" + username + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        UserData user = new UserData();
        ArrayList<Integer> levellist = new ArrayList<Integer>();
        ArrayList<Integer> scorelist = new ArrayList<Integer>();

        if (cursor.moveToFirst() && cursor.getCount() >= 1) {
            do{
                user.setMyUserName(cursor.getString(0));
                user.setMyPassword(cursor.getString(1));
                levellist.add(Integer.parseInt(cursor.getString(2)));
                scorelist.add(Integer.parseInt(cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        else {
            return null;
        }

        user.setLevels(levellist);
        user.setScores(scorelist);
        cursor.close();
        return user;

    }

    public boolean deleteAccount(String username) {
        /* HINT:
            This finds and delete the user data in the database.
            This is not reversible.
            Log.v(TAG, FILENAME + ": Database delete user: " + query);
         */
        boolean result = false;
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE "
                + COLUMN_USERNAME
                + " = \"" + username + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst() && cursor.getCount() >= 1) {
            do{
                db.delete(TABLE_USERS, COLUMN_USERNAME + " = ?",
                        new String[] { String.valueOf(username) });
            } while (cursor.moveToNext());
            result = true;
        }
        return result;
    }

    public void updateScore(String username, int level, int score){
        UserData user = findUser(username);
        if (user.getScores().get(level-1) < score) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("UPDATE "+TABLE_USERS+" SET score = "+Integer.toString(score) + " WHERE Username = "+"'"+username+"'"+" AND Level = "+Integer.toString(level-1));
        }
    }

}
