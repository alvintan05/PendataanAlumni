package pnj.uts.alvintandiardi.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import pnj.uts.alvintandiardi.R;
import pnj.uts.alvintandiardi.util.AlumniSharedPreference;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // Widget
    private TextInputEditText mEdtEmail, mEdtPassword;
    private Button mBtnLogin;

    // Variables
    private AlumniSharedPreference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Widget casting
        mEdtEmail = findViewById(R.id.edt_login_email);
        mEdtPassword = findViewById(R.id.edt_login_password);
        mBtnLogin = findViewById(R.id.btn_login_login);

        preference = new AlumniSharedPreference(this);

        mBtnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_login:
                validation();
                break;
        }
    }

    private void validation() {

        String email = mEdtEmail.getText().toString().trim();
        String password = mEdtPassword.getText().toString().trim();

        if (!email.isEmpty() && !password.isEmpty()) {

            if (email.equals("alvintandiardi@gmail.com") && password.equals("alvin")) {
                preference.createUserLoginSession(email, "4617010024", "Alvin Tandiardi", "TI 6A");
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Email / Password Salah!", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Mohon Lengkapi Data!", Toast.LENGTH_SHORT).show();
        }
    }
}
