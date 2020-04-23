package pnj.uts.alvintandiardi.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import pnj.uts.alvintandiardi.R;

public class DetailBeritaActivity extends AppCompatActivity {

    // Widget
    private TextView mTvJudul, mTvIsi;
    private ImageView mImgBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);

        // Widget casting
        mTvJudul = findViewById(R.id.tv_detail_judul);
        mTvIsi = findViewById(R.id.tv_detail_isi);
        mImgBerita = findViewById(R.id.img_detail_berita);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail Berita");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            Glide.with(this)
                    .load(extras.getString("image"))
                    .into(mImgBerita);

            mTvJudul.setText(extras.getString("judul"));
            mTvIsi.setText(extras.getString("desc"));

        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
