package pnj.uts.alvintandiardi.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;

import pnj.uts.alvintandiardi.R;
import pnj.uts.alvintandiardi.util.AlumniSharedPreference;

public class HomeFragment extends Fragment {

    // Widget
    private TextView mTvNama;
    private SliderLayout sliderLayout;

    // Vars
    private AlumniSharedPreference preference;

    public HomeFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Widget casting

        mTvNama = view.findViewById(R.id.tv_user_name);
        sliderLayout = view.findViewById(R.id.slider);

        preference = new AlumniSharedPreference(getActivity());
        mTvNama.setText(preference.getNama());

        initSlider();
    }

    private void initSlider() {
        HashMap<String, String> file_maps = new HashMap<String, String>();
        file_maps.put("satu", "https://media-exp1.licdn.com/dms/image/C511BAQF-TqGzJJeXQQ/company-background_10000/0?e=2159024400&v=beta&t=idR2HQe4Vq2ujebSzFnc8KT5aaHFUimwEnziute-MLQ");
        file_maps.put("dua", "https://img.okezone.com/content/2015/09/27/65/1221621/lulusan-pnj-dibekali-surat-pendamping-ijazah-mbkpWSkg3W.jpg");
        file_maps.put("tiga", "https://www.patroon.co.id/wp-content/uploads/2017/09/Perpustakaan-Pusat-Politeknik-Negeri-Jakarta-1.jpg");
        file_maps.put("empat", "https://kampusaja.com/wp-content/uploads/2019/03/Akreditasi-Program-Studi-PNJ-Jurusan-Kampus-Politeknik-Negeri-Jakarta.jpg");

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            sliderLayout.addSlider(textSliderView);
        }

    }


}
