package pnj.uts.alvintandiardi.data.db;

/*class ini untuk mendefinisikan semua nama kolom dan nama table*/

import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_NAME = "tb_alumni";

    public static final class tableColumns implements BaseColumns {

        public static String NIM = "nim";
        public static String NAMA = "nama";
        public static String TMP_LAHIR = "tempat_lahir";
        public static String TGL_LAHIR = "tanggal_lahir";
        public static String ALAMAT = "alamat";
        public static String AGAMA = "agama";
        public static String NOMOR = "nomor";
        public static String TAHUN_MASUK = "tahun_masuk";
        public static String TAHUN_LULUS = "tahun_lulus";
        public static String PEKERJAAN = "pekerjaan";
        public static String JABATAN = "jabatan";

    }

}
