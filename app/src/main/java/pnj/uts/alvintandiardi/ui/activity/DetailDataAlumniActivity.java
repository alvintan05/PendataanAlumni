package pnj.uts.alvintandiardi.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import pnj.uts.alvintandiardi.R;
import pnj.uts.alvintandiardi.data.db.CrudHelper;

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

public class DetailDataAlumniActivity extends AppCompatActivity implements View.OnClickListener {

    // Widget
    private TextInputEditText mEdtNim, mEdtNama, mEdtTempat, mEdtTanggal, mEdtAlamat, mEdtAgama,
            mEdtNomor, mEdtMasuk, mEdtLulus, mEdtPekerjaan, mEdtJabatan;
    private Button mBtnTanggal, mBtnUbah, mBtnHapus;

    // Vars
    CrudHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data_alumni);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle("Detail Alumni");
        }

        mEdtNim = findViewById(R.id.edt_detail_nim);
        mEdtNama = findViewById(R.id.edt_detail_nama);
        mEdtTempat = findViewById(R.id.edt_detail_tempat);
        mEdtTanggal = findViewById(R.id.edt_detail_tanggal);
        mEdtAlamat = findViewById(R.id.edt_detail_alamat);
        mEdtAgama = findViewById(R.id.edt_detail_agama);
        mEdtNomor = findViewById(R.id.edt_detail_nomor);
        mEdtMasuk = findViewById(R.id.edt_detail_masuk);
        mEdtLulus = findViewById(R.id.edt_detail_lulus);
        mEdtPekerjaan = findViewById(R.id.edt_detail_pekerjaan);
        mEdtJabatan = findViewById(R.id.edt_detail_jabatan);
        mBtnTanggal = findViewById(R.id.btn_detail_tanggal);
        mBtnUbah = findViewById(R.id.btn_detail_ubah);
        mBtnHapus = findViewById(R.id.btn_detail_hapus);

        mEdtNim.setEnabled(false);
        mEdtTanggal.setEnabled(false);

        String nim = getIntent().getStringExtra(NIM);
        helper = CrudHelper.getInstance(getApplicationContext());

        getDetailData(nim);

        mBtnTanggal.setOnClickListener(this);
        mBtnUbah.setOnClickListener(this);
        mBtnHapus.setOnClickListener(this);

    }

    private void getDetailData(String nim) {
        helper.open();
        Cursor cursor = helper.getDetailData(nim);

        if (cursor.moveToFirst()) {
            do {
                mEdtNim.setText(cursor.getString(0));
                mEdtNama.setText(cursor.getString(1));
                mEdtTempat.setText(cursor.getString(2));
                mEdtTanggal.setText(cursor.getString(3));
                mEdtAlamat.setText(cursor.getString(4));
                mEdtAgama.setText(cursor.getString(5));
                mEdtNomor.setText(cursor.getString(6));
                mEdtMasuk.setText(cursor.getString(7));
                mEdtLulus.setText(cursor.getString(8));
                mEdtPekerjaan.setText(cursor.getString(9));
                mEdtJabatan.setText(cursor.getString(10));
            } while (cursor.moveToNext());
        }

        cursor.close();
        helper.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_detail_tanggal:
                showDatePickerDialog();
                break;
            case R.id.btn_detail_ubah:
                updateData();
                break;
            case R.id.btn_detail_hapus:
                deleteData();
                break;
        }
    }

    private void showDatePickerDialog() {
        Calendar todayDate = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar dateSelected = Calendar.getInstance();
                dateSelected.set(Calendar.YEAR, year);
                dateSelected.set(Calendar.MONTH, month);
                dateSelected.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy");
                mEdtTanggal.setText(simpleDateFormat.format(dateSelected.getTime()));

            }
        };

        new DatePickerDialog(this, dateSetListener,
                todayDate.get(Calendar.YEAR),
                todayDate.get(Calendar.MONTH),
                todayDate.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    private void updateData() {
        String nim = mEdtNim.getText().toString().trim();
        String nama = mEdtNama.getText().toString().trim();
        String tempat = mEdtTempat.getText().toString().trim();
        String tanggal = mEdtTanggal.getText().toString().trim();
        String alamat = mEdtAlamat.getText().toString().trim();
        String agama = mEdtAgama.getText().toString().trim();
        String nomor = mEdtNomor.getText().toString().trim();
        String masuk = mEdtMasuk.getText().toString().trim();
        String lulus = mEdtLulus.getText().toString().trim();
        String pekerjaan = mEdtPekerjaan.getText().toString().trim();
        String jabatan = mEdtJabatan.getText().toString().trim();

        if (!nim.isEmpty() && !nama.isEmpty() && !tempat.isEmpty() && !tanggal.isEmpty() &&
                !alamat.isEmpty() && !agama.isEmpty() && !nomor.isEmpty() && !masuk.isEmpty() &&
                !lulus.isEmpty() && !pekerjaan.isEmpty() && !jabatan.isEmpty()) {

            CrudHelper helper = CrudHelper.getInstance(getApplicationContext());
            helper.open();

            ContentValues contentValues = new ContentValues();
            contentValues.put(NIM, nim);
            contentValues.put(NAMA, nama);
            contentValues.put(TMP_LAHIR, tempat);
            contentValues.put(TGL_LAHIR, tanggal);
            contentValues.put(ALAMAT, alamat);
            contentValues.put(AGAMA, agama);
            contentValues.put(NOMOR, nomor);
            contentValues.put(TAHUN_MASUK, masuk);
            contentValues.put(TAHUN_LULUS, lulus);
            contentValues.put(PEKERJAAN, pekerjaan);
            contentValues.put(JABATAN, jabatan);

            long update = helper.updateData(nim, contentValues);

            if (update != -1) {
                Toast.makeText(this, "Update Data Berhasil", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Update Data Gagal", Toast.LENGTH_SHORT).show();
            }

            helper.close();

        } else {
            Toast.makeText(this, "Mohon Lengkapi Data!", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Are you sure want to delete this?");

        builder
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        helper.open();
                        long hapus = helper.deleteData(mEdtNim.getText().toString().trim());
                        if (hapus != -1) {
                            Toast.makeText(DetailDataAlumniActivity.this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(DetailDataAlumniActivity.this, "Data Gagal Dihapus!", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
