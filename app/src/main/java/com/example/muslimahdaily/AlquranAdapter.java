package com.example.muslimahdaily;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;



class AlquranAdapter extends ArrayAdapter<Alquran> {

    public AlquranAdapter(AlquranActivity mainActivity, ArrayList<Alquran> alqurans) {
        super(mainActivity, 0, alqurans);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.alquran_list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Alquran currentAlquran = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view.
        TextView namaTextView = (TextView) listItemView.findViewById(R.id.nama);
        // Get the Miwok translation from the currentWord object and set this text on
        // the Miwok TextView.
        namaTextView.setText(currentAlquran.getNama());

        TextView artiTextView = (TextView) listItemView.findViewById(R.id.arti);
        // Get the Miwok translation from the currentWord object and set this text on
        // the Miwok TextView.
        artiTextView.setText(currentAlquran.getArti());

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView asmaTextView = (TextView) listItemView.findViewById(R.id.asma);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        asmaTextView.setText(currentAlquran.getAsma());

        TextView ayatTextView = (TextView) listItemView.findViewById(R.id.ayat);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        ayatTextView.setText(""+ currentAlquran.getAyat());

        TextView typeTextView = (TextView) listItemView.findViewById(R.id.type);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        typeTextView.setText(currentAlquran.getType());

        TextView nomorTextView = (TextView) listItemView.findViewById(R.id.nomor);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        nomorTextView.setText(""+ currentAlquran.getNomor());



        // the ListView.
        return listItemView;
    }
}
