package pnj.uts.alvintandiardi.ui.activity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import pnj.uts.alvintandiardi.R;
import pnj.uts.alvintandiardi.data.db.CrudHelper;

import static pnj.uts.alvintandiardi.data.db.DatabaseContract.tableColumns.*;

public class TambahDataActivity extends AppCompatActivity implements View.OnClickListener {

    // Widget
    private TextInputEditText mEdtNim, mEdtNama, mEdtTempat, mEdtTanggal, mEdtAlamat, mEdtAgama,
            mEdtNomor, mEdtMasuk, mEdtLulus, mEdtPekerjaan, mEdtJabatan;
    private Button mBtnTanggal, mBtnSimpan;

    // Vars

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle("Tambah Data Alumni");
        }

        mEdtNim = findViewById(R.id.edt_tambah_nim);
        mEdtNama = findViewById(R.id.edt_tambah_nama);
        mEdtTempat = findViewById(R.id.edt_tambah_tempat);
        mEdtTanggal = findViewById(R.id.edt_tambah_tanggal);
        mEdtAlamat = findViewById(R.id.edt_tambah_alamat);
        mEdtAgama = findViewById(R.id.edt_tambah_agama);
        mEdtNomor = findViewById(R.id.edt_tambah_nomor);
        mEdtMasuk = findViewById(R.id.edt_tambah_masuk);
        mEdtLulus = findViewById(R.id.edt_tambah_lulus);
        mEdtPekerjaan = findViewById(R.id.edt_tambah_pekerjaan);
        mEdtJabatan = findViewById(R.id.edt_tambah_jabatan);
        mBtnTanggal = findViewById(R.id.btn_tambah_tanggal);
        mBtnSimpan = findViewById(R.id.btn_tambah_simpan);

        mEdtTanggal.setEnabled(false);

        mBtnTanggal.setOnClickListener(this);
        mBtnSimpan.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tambah_tanggal:
                showDatePickerDialog();
                break;
            case R.id.btn_tambah_simpan:
                validation();
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

    private void validation() {

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

            long insert = helper.insertData(contentValues);

            if (insert != -1) {
                Toast.makeText(this, "Penyimpanan Data Berhasil", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Penyimpanan Data Gagal", Toast.LENGTH_SHORT).show();
            }

            helper.close();

        } else {
            Toast.makeText(this, "Mohon Lengkapi Data!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
