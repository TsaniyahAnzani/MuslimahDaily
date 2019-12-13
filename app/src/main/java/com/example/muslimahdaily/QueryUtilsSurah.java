package com.example.muslimahdaily;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import java.util.List;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */

public final class QueryUtilsSurah {
    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /** Waktu gempa */
    private int mNomor;

    /**
     * Private Constructor artinya tidak ada yang bole membuat instance dari kelas ini
     * ini adalah kelas utility  shg berisi variabel static method stactic dimana kita bisa memenggil method ini scr langsung
     * tanpa harus memiliki instance objec dari kelas
     */
    private QueryUtilsSurah() {
    }

    /**
     * Kembalikan daftar objek {@link Alquran} yang sudah terbuat dari
     * parsing respon JSON yang sudah ada.
     */
    private static List<Surah> extractFeatureFromJson(String asmaulJSON) {
        // Jika string JSON kosong atau nol, segera kembalilah.
        if (TextUtils.isEmpty(asmaulJSON)) {
            return null;
        }

        // Buat ArrayList kosong yang dapat kita masuki gempa ke
        List<Surah> surahs = new ArrayList<>();

        // Coba parse string respons JSON. Jika ada masalah dengan pemformatan JSON
        // objek exception JSONException akan dimunculkan.
        // Tangkap perkecualiannya agar aplikasi tidak mengalami crash, dan cetak pesan error-nya ke log.
        try {

            // Buat JSONObject dari string respons JSON
            JSONObject baseJsonResponse = new JSONObject(asmaulJSON);

            // Ekstrak JSONArray yang berhubungan dengan kunci bernama "features",
            // yang mewakili daftar fitur (atau gempa).
            JSONObject asmaulArray = baseJsonResponse.getJSONObject("data");

            JSONArray y = asmaulArray.getJSONArray("ayahs");
         //   JSONArray x = asmaulArray.getJSONArray("ayahs");

            // Untuk tiap gempa di earthquakeArray, buat objek {@link Earthquake} untuk
            for (int i = 0; i < y.length() ; i++) {

                    JSONObject ayahs = y.getJSONObject(i);

                    String name = ayahs.getString("text");
                    int number = ayahs.getInt("numberInSurah");
                    // JSONObject arti = x.getJSONObject("en");
                    // String englishName = arti.getString("meaning");


                    // Buat objek {@link Earthquake} baru dengan magnitudo, lokasi, waktu,
                    // dan url dari response JSON.ß
                    Surah surah = new Surah(name, number);

                    // Tambahkan {@link Earthquake} baru ke daftar gempa.
                    surahs.add(surah);



            }

        } catch (JSONException e) {
            // Jika error dimunculkan saat mengeksekusi pernyataan apapun di dalam blok "try",
            // tangkap perkecualiannya di sini, agar aplikasi tidak crash. Cetak pesan log dengan
            // pesan dari perkecualian.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // kembalikan daftar gempa
        return surahs;
    }




    /**
     * Kembalikan objek URL baru dari string URL yang ada.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Buat HTTP request ke URL yang ada dan kembalikan sebuah String sebagai respons.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Jika request berhasil (kode respons 200),
            // maka baca input stream dan lakukan parse atas respons.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Menutup input stream bisa memunculkan IOException, karena itu
                // lambang metode (method signature) makeHttpRequest(URL url) harus menyatakan IOException
                // dapat dimunculkan.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Ubah {@link InputStream} menjadi String yang mengandung seluruh
     * respon JSON dari server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    /**
     * Query dataset USGS dan kembalikan daftar objek {@link Alquran}.
     * @return
     */
    public static List<Surah> fetchEarthquakeData(String requestUrl) {
        Log.i(LOG_TAG,"TEST fetchEarthquakeData() PADA QUERYUTILS DIPANGGIL");


        //UNTUK WAKTU LOADING DATA
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // Buat objek URLß
        URL url = createUrl(requestUrl);

        // Buat HTTP request ke URL dan terima kembali respons JSON
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Ekstrak field yang relevan dari respons JSON dan buatlah daftar {@link Earthquake}
        List<Surah> surahs = extractFeatureFromJson(jsonResponse);

        // Kembalikan daftar {@link Earthquake}
        return surahs;
    }


}
