package pnj.uts.alvintandiardi.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import pnj.uts.alvintandiardi.R;
import pnj.uts.alvintandiardi.adapter.RecyclerAlumniAdapter;
import pnj.uts.alvintandiardi.data.db.CrudHelper;
import pnj.uts.alvintandiardi.data.entity.Alumni;

import static pnj.uts.alvintandiardi.data.db.DatabaseContract.tableColumns.NIM;

public class DataAlumniActivity extends AppCompatActivity {

    // Widget
    private RecyclerView mRvAlumni;

    // Vars
    private RecyclerAlumniAdapter adapter;
    private List<Alumni> listAlumni = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_alumni);

        if (getSupportActionBar() != null) {
            setTitle("List Data Alumni");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mRvAlumni = findViewById(R.id.rv_alumni);

        mRvAlumni.setLayoutManager(new LinearLayoutManager(this));
        mRvAlumni.setHasFixedSize(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataFromDb();
    }

    private void getDataFromDb() {
        listAlumni.clear();
        CrudHelper helper = CrudHelper.getInstance(getApplicationContext());
        helper.open();
        Cursor cursor = helper.getAllData();

        if (cursor.moveToFirst()) {
            do {
                Alumni data = new Alumni();
                data.setNim(cursor.getString(0));
                data.setNama(cursor.getString(1));
                listAlumni.add(data);
            } while (cursor.moveToNext());
        }

        cursor.close();
        helper.close();

        adapter = new RecyclerAlumniAdapter(listAlumni, DataAlumniActivity.this, new RecyclerAlumniAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(DataAlumniActivity.this, DetailDataAlumniActivity.class);
                intent.putExtra(NIM, listAlumni.get(position).getNim());
                startActivity(intent);
            }
        });

        mRvAlumni.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
