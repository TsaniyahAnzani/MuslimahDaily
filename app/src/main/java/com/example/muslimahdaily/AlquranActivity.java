package com.example.muslimahdaily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class AlquranActivity extends AppCompatActivity {
    private TextView mEmptyStateTextView;
    // public static final String LOG_TAG = MainActivity.class.getName();
    private static final String LOG_TAG = AlquranActivity.class.getName();
    private static final String ALQURAN_REQUEST_URL =
            "http://api.alquran.cloud/v1/surah";

    private AlquranAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "TEST Alquran ACTIVITY DIPANGGIL");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alquran);

        ListView alquranListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        alquranListView.setEmptyView(mEmptyStateTextView);


        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            AlquranAsyncTask task = new AlquranAsyncTask();
            task.execute(ALQURAN_REQUEST_URL);

        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
        mAdapter = new AlquranAdapter(this, new ArrayList<Alquran>());

        // Atur adapter di {@link ListView}
        // agar daftar dapat diisi di antarmuka pengguna
        alquranListView.setAdapter(mAdapter);


       //  Buat adapter baru yang mengambil daftar kosong gempa sebagai inputnyaåå

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        alquranListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Temukan gempa terkini yang diklik di
                Alquran currentEarthquake = mAdapter.getItem(position);

                // Ubah String URL menjadi objek URI (untuk memasuki konstruktor Intent)
               String nomor = String.valueOf(currentEarthquake.getNomor());

                Intent surahIntent = new Intent(AlquranActivity.this, surahActivity.class);
                surahIntent.putExtra("nomor", nomor);

                // Start the new activity
                startActivity(surahIntent);
            }
        });


    }

    private class AlquranAsyncTask extends AsyncTask<String, Void, List<Alquran>> {

        @Override
        protected List<Alquran> doInBackground(String... urls) {
            // Jangan lakukan request jika tidak ada URL, atau jika URL pertama nol.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Alquran> result = QueryUtils.fetchEarthquakeData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<Alquran> data) {
            // Bersihkan adapter dari data gempa sebelumnya
            mAdapter.clear();

            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            mEmptyStateTextView.setText("Tidak ada Alquran yang ditemukan.");

            // Jika ada daftar {@link Earthquake} yang valid, tambahkan ke data set adapter.
            // Ini akan memicu pembaruan ListView..
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }
}
