package com.example.muslimahdaily;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

public class amalyaumiActivity extends AppCompatActivity {
    int quantity=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amalyaumi);
    }

    public void onRadioButtonClicked(View view) {
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void increment(View view) {
        if(quantity==1000){
            Toast.makeText(this, "You Cannot have more than 1000 pages", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity+1;
        display(quantity);
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_tilawah);
        quantityTextView.setText(" " + number);
    }

    public void decrement(View view) {
        if(quantity==0){
            Toast.makeText(this, "You Cannot have less than 0 page", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity-1;
        display(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitMurobbi(View view) {
        EditText tanggalField= (EditText) findViewById(R.id.tanggal_field);
        String tanggal= tanggalField.getText().toString();

        CheckBox magribCheckBox= (CheckBox) findViewById(R.id.magrib);
        boolean hasMagrib= magribCheckBox.isChecked();

        CheckBox isyaCheckBox= (CheckBox) findViewById(R.id.isya);
        boolean hasIsya= isyaCheckBox.isChecked();

        CheckBox subuhCheckBox= (CheckBox) findViewById(R.id.subuh);
        boolean hasSubuh= subuhCheckBox.isChecked();

        CheckBox dzuhurCheckBox= (CheckBox) findViewById(R.id.dzuhur);
        boolean hasDzuhur= dzuhurCheckBox.isChecked();

        CheckBox asharCheckBox= (CheckBox) findViewById(R.id.ashar);
        boolean hasAshar= asharCheckBox.isChecked();

        EditText dhuhaField= (EditText) findViewById(R.id.dhuha);
        String dhuha= dhuhaField.getText().toString();

        EditText rawatibField= (EditText) findViewById(R.id.rawatib);
        String rawatib= rawatibField.getText().toString();


        String PriceMessage= createAmalYaumiSumary(tanggal,hasMagrib,hasIsya,hasSubuh,hasDzuhur,hasAshar,dhuha,rawatib);
        //displayMessage(PriceMessage);

        Intent intent= new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.Amal_yaumi_for)+"  "+ tanggal);
        intent.putExtra(Intent.EXTRA_TEXT,PriceMessage);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }


    private String createAmalYaumiSumary(String tanggal, Boolean Magrib, Boolean Isya, Boolean Subuh, Boolean dzuhur, Boolean Ashar, String Dhuha, String Rawatib){
        String AmalMessage= getString(R.string.tanggal)+":  " + tanggal ;
        AmalMessage +="\n"+getString(R.string.mengerjakan_sholat_magrib)+ " : "+Magrib;
        AmalMessage +="\n"+ getString(R.string.mengerjakan_sholat_isya) +": "+ Isya;
        AmalMessage +="\n"+ getString(R.string.mengerjakan_sholat_subuh)+": "+ Subuh;
        AmalMessage +="\n"+getString(R.string.mengerjakan_sholat_dzuhur)+": "+ dzuhur;
        AmalMessage +="\n"+ getString(R.string.mengerjakan_sholat_ashar)+": " + Ashar;
        AmalMessage +="\n"+ getString(R.string.membaca_alquran) +": "+ quantity +" Halaman";
        AmalMessage +="\n"+ getString(R.string.mengerjakan_sholat_dhuha)  +": "+Dhuha + " Rakaat";
        AmalMessage +="\n"+getString(R.string.mengerjakan_sholat_rawatib)+": "+ Rawatib +" Kali";
        AmalMessage +="\n"+getString(R.string.terimah_kasih);
        return AmalMessage;
    }


}


