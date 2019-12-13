package com.example.muslimahdaily;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import java.util.ArrayList;


class SurahAdapter extends ArrayAdapter<Surah> {

    public SurahAdapter(surahActivity surahActivity, ArrayList<Surah> surahs) {
        super(surahActivity, 0, surahs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.surah_list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Surah currentSurah = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view.
        TextView namaTextView = (TextView) listItemView.findViewById(R.id.nama);
        // Get the Miwok translation from the currentWord object and set this text on
        // the Miwok TextView.
        namaTextView.setText(currentSurah.getText());


        TextView nomorTextView = (TextView) listItemView.findViewById(R.id.nomor);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        nomorTextView.setText(""+ currentSurah.getNomor());



        // the ListView.
        return listItemView;
    }
}

