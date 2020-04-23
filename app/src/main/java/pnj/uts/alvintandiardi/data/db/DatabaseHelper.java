package pnj.uts.alvintandiardi.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static pnj.uts.alvintandiardi.data.db.DatabaseContract.TABLE_NAME;
import static pnj.uts.alvintandiardi.data.db.DatabaseContract.tableColumns.AGAMA;
import static pnj.uts.alvintandiardi.data.db.DatabaseContract.tableColumns.ALAMAT;
import static pnj.uts.alvintandiardi.data.db.DatabaseContract.tableColumns.JABATAN;
import static pnj.uts.alvintandiardi.data.db.DatabaseContract.tableColumns.NAMA;
import static pnj.uts.alvintandiardi.data.db.DatabaseContract.tableColumns.NIM;
import static pnj.uts.alvintandiardi.data.db.DatabaseContract.tableColumns.NOMOR;
import static pnj.uts.alvintandiardi.data.db.DatabaseContract.tableColumns.PEKERJAAN;
import static pnj.uts.alvintandiardi.data.db.DatabaseContract.tableColumns.TAHUN_LULUS;
import static pnj.uts.alvintandiardi.data.db.DatabaseContract.tableColumns.TAHUN_MASUK;
import static pnj.uts.alvintandiardi.data.db.DatabaseContract.tableColumns.TGL_LAHIR;
import static pnj.uts.alvintandiardi.data.db.DatabaseContract.tableColumns.TMP_LAHIR;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "db_alumni";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE = String.format("CREATE TABLE %s"
                    + " (%s TEXT PRIMARY KEY," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT)",
            TABLE_NAME,
            NIM,
            NAMA,
            TMP_LAHIR,
            TGL_LAHIR,
            ALAMAT,
            AGAMA,
            NOMOR,
            TAHUN_MASUK,
            TAHUN_LULUS,
            PEKERJAAN,
            JABATAN
    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE " + TABLE_NAME);
            onCreate(db);
        }
    }
}
