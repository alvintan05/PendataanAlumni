package pnj.uts.alvintandiardi.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import pnj.uts.alvintandiardi.R;
import pnj.uts.alvintandiardi.ui.activity.LoginActivity;
import pnj.uts.alvintandiardi.util.AlumniSharedPreference;

public class ProfileFragment extends Fragment {

    // Widget
    private TextView mTvEmail, mTvNim, mTvName, mTvClass;
    private Button mBtnLogout;

    // Variables
    private AlumniSharedPreference preference;

    public ProfileFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Widget casting
        mTvEmail = view.findViewById(R.id.tv_profile_email);
        mTvNim = view.findViewById(R.id.tv_profile_nim);
        mTvName = view.findViewById(R.id.tv_profile_name);
        mTvClass = view.findViewById(R.id.tv_profile_class);
        mBtnLogout = view.findViewById(R.id.btn_profile_logout);

        preference = new AlumniSharedPreference(getActivity());

        setTextProfile();

        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Are you sure want logout?");

        builder
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logOut();
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

    private void logOut() {
        preference.deleteUserLoginSession();
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }

    private void setTextProfile() {

        // Set text from shared preferences
        mTvEmail.setText(preference.getEmail());
        mTvNim.setText(preference.getNim());
        mTvName.setText(preference.getNama());
        mTvClass.setText(preference.getKelas());

    }


}
