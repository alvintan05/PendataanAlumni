package pnj.uts.alvintandiardi.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static pnj.uts.alvintandiardi.data.db.DatabaseContract.TABLE_NAME;
import static pnj.uts.alvintandiardi.data.db.DatabaseContract.tableColumns.NAMA;
import static pnj.uts.alvintandiardi.data.db.DatabaseContract.tableColumns.NIM;

public class CrudHelper {

    private static final String TABLE = TABLE_NAME;
    private static DatabaseHelper databaseHelper;
    private static CrudHelper INSTANCE;

    private static SQLiteDatabase database;

    private CrudHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static CrudHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CrudHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();

        if (database.isOpen()) database.close();
    }

    public Cursor getAllData() {
        return database.rawQuery("SELECT * FROM " + TABLE + " ORDER BY " + NAMA, null);
    }

    public Cursor getDetailData(String nim) {
        return database.rawQuery("SELECT * FROM " + TABLE + " WHERE nim=" + nim, null);
    }

    public long insertData(ContentValues values) {
        return database.insert(TABLE, null, values);
    }

    public long updateData(String nim, ContentValues values) {
        return database.update(TABLE, values, NIM + "=?", new String[]{nim});
    }

    public long deleteData(String nim) {
        return database.delete(TABLE, NIM + "=?", new String[]{nim});
    }

}