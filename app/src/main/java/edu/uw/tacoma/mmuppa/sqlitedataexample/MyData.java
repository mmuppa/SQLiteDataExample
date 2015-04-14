package edu.uw.tacoma.mmuppa.sqlitedataexample;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;


public class MyData
{

	private static final String DATABASE_NAME = "my.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME_EXAMPLE = "example";

	private Context context;
	private SQLiteDatabase db;

	private SQLiteStatement insertStmt;
	private static final String INSERT = "insert into " + TABLE_NAME_EXAMPLE
			+ "(id, name) values (?, ?)";

	public MyData(Context context)
	{
		this.context = context;
		OpenHelper openHelper = new OpenHelper(this.context);
		this.db = openHelper.getWritableDatabase();
		this.insertStmt = this.db.compileStatement(INSERT);
	}

    /** Inserts the id and name into example
    If successful, returns the rowid otherwise -1.
     */
	public long insert(int id, String name) throws Exception
	{
            this.insertStmt.bindLong(1, id);
            this.insertStmt.bindString(2, name);

            long rowID = this.insertStmt.executeInsert();
            if (rowID == -1) {
                throw new Exception("Unable to insert");
            }
            return rowID;
	}

    /**
     * Delete everything from example
     */
	public void deleteAll()
	{
		this.db.delete(TABLE_NAME_EXAMPLE, null, null);
	}


    /**
     * Return an array list of Example objects from the
     * data returned from select query on example table.
     * @return
     */
	public ArrayList<Example> selectAll()
	{
        ArrayList<Example> list = new ArrayList<Example>();
		Cursor cursor = this.db.query(TABLE_NAME_EXAMPLE, new String[]
		{ "id", "name" }, null, null, null, null, null);
		if (cursor.moveToFirst())
		{
			do
			{

				Example e = new Example(cursor.getInt(0), cursor.getString(1));
                list.add(e);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed())
		{
			cursor.close();
		}
		return list;
	}

    /**
     * Return the name when id is passed.
     * null if no record found.
     * @param id
     * @return
     */
	public String selectByID(int id)
	{
		Cursor cursor = this.db.query(TABLE_NAME_EXAMPLE, new String[]
		{ "name" }, "id=?",
				new String[]
				{ Long.toString(id) }, null, null, null);
		if (cursor.moveToFirst())
		{
			do
			{
				return cursor.getString(0);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed())
		{
			cursor.close();
		}
		return null;
	}

    /**
     * Close the connection
     */
	public void close()
	{
		db.close();
	}

	private static class OpenHelper extends SQLiteOpenHelper
	{

		OpenHelper(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db)
		{
			db.execSQL("CREATE TABLE " + TABLE_NAME_EXAMPLE
					+ " (id INTEGER PRIMARY KEY, name TEXT)");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			Log.w("Example",
					"Upgrading database, this will drop tables and recreate.");
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_EXAMPLE);
			onCreate(db);
		}
	}
}
