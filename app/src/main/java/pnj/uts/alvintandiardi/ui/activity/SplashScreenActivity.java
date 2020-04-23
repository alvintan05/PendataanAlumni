package pnj.uts.alvintandiardi.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import pnj.uts.alvintandiardi.R;
import pnj.uts.alvintandiardi.util.AlumniSharedPreference;

public class SplashScreenActivity extends AppCompatActivity {

    //Widget
    private ImageView mSplashLogo;
    private TextView mSplashTitle;
    private AlumniSharedPreference preference;

    //Variables
    private Animation mFadeAnim, mBottomAnim;
    private static final int SPLASH_SCREEN_DURATION = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // initialisations
        mSplashLogo = findViewById(R.id.img_splash_logo);
        mSplashTitle = findViewById(R.id.text_splash_appname);
        mFadeAnim = AnimationUtils.loadAnimation(this, R.anim.fadein_animation_splash);
        mBottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation_splash);

        preference = new AlumniSharedPreference(this);

        mSplashLogo.setAnimation(mFadeAnim);
        mSplashTitle.setAnimation(mBottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (preference.isUserLogin()) {
                    startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                    finish();
                }
            }
        }, SPLASH_SCREEN_DURATION);

    }
}
