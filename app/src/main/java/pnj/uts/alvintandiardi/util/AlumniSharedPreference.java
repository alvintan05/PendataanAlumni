package pnj.uts.alvintandiardi.util;

import android.content.Context;
import android.content.SharedPreferences;

public class AlumniSharedPreference {

    private final static String PREFERENCES_NAME = "UtsPref";
    private final static String EMAIL_KEY = "EmailKey";
    private final static String NIM_KEY = "NimKey";
    private final static String NAMA_KEY = "NamaKey";
    private final static String KELAS_KEY = "KelasKey";
    private final static String IS_LOGIN_KEY = "IsLoginKey";

    private final SharedPreferences sharedPreferences;
    private Context c;

    public AlumniSharedPreference(Context c) {
        this.c = c;
        sharedPreferences = c.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void createUserLoginSession(String email, String nim, String nama, String kelas){
        SharedPreferences.Editor editor = sharedPreferences.edit();;
        editor.putString(EMAIL_KEY, email);
        editor.putString(NIM_KEY, nim);
        editor.putString(NAMA_KEY, nama);
        editor.putString(KELAS_KEY, kelas);
        editor.putBoolean(IS_LOGIN_KEY, true);
        editor.apply();
    }

    public boolean isUserLogin() {
        return sharedPreferences.getBoolean(IS_LOGIN_KEY, false);
    }

    public String getEmail() {
        return sharedPreferences.getString(EMAIL_KEY, "");
    }

    public String getNim() {
        return sharedPreferences.getString(NIM_KEY, "");
    }

    public String getNama() {
        return sharedPreferences.getString(NAMA_KEY, "");
    }

    public String getKelas() {
        return sharedPreferences.getString(KELAS_KEY, "");
    }

    public void deleteUserLoginSession() {
        SharedPreferences.Editor editor = sharedPreferences.edit();;
        editor.remove(NIM_KEY);
        editor.remove(NAMA_KEY);
        editor.remove(EMAIL_KEY);
        editor.remove(KELAS_KEY);
        editor.remove(IS_LOGIN_KEY);
        editor.apply();
    }

}
