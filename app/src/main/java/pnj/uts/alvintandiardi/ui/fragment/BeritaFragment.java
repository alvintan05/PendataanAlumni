package pnj.uts.alvintandiardi.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pnj.uts.alvintandiardi.R;
import pnj.uts.alvintandiardi.adapter.RecyclerBeritaAdapter;
import pnj.uts.alvintandiardi.data.entity.Berita;
import pnj.uts.alvintandiardi.ui.activity.DetailBeritaActivity;


public class BeritaFragment extends Fragment {

    // Widget
    private RecyclerView mRvBerita;

    // Vars
    private RecyclerBeritaAdapter adapter;
    private List<Berita> listBerita = new ArrayList<>();

    public BeritaFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_berita, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Widget casting
        mRvBerita = view.findViewById(R.id.rv_berita);

        mRvBerita.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRvBerita.setHasFixedSize(true);
        adapter = new RecyclerBeritaAdapter(listBerita, getActivity());

        getList();
        mRvBerita.setAdapter(adapter);

        adapter.setOnItemClickCallback(new RecyclerBeritaAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Berita data) {
                Intent intent = new Intent(getActivity(), DetailBeritaActivity.class);
                intent.putExtra("berita", data);
                startActivity(intent);
            }
        });

    }

    private void getList() {
        String[] title = getResources().getStringArray(R.array.list_judul);
        String[] desc = getResources().getStringArray(R.array.list_deskripsi);
        String[] image = getResources().getStringArray(R.array.list_image);

        for (int i = 0; i < title.length; i++) {

            Berita berita = new Berita();
            berita.setTitle(title[i]);
            berita.setDesc(desc[i]);
            berita.setImage(image[i]);

            listBerita.add(berita);

        }

        adapter.notifyDataSetChanged();

    }

}
