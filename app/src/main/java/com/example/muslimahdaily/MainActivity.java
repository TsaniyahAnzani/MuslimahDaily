package com.example.muslimahdaily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btnMove_quran,btnMove_asmaul, btnMove_amalyaumi,btnMove_haid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMove_quran = findViewById(R.id.quran);
        btnMove_asmaul = findViewById(R.id.asmaul);
        btnMove_amalyaumi = findViewById(R.id.amalyaumi);
        btnMove_haid = findViewById(R.id.haid);
        btnMove_quran.setOnClickListener(this);
        btnMove_asmaul.setOnClickListener(this);
        btnMove_amalyaumi.setOnClickListener(this);
        btnMove_haid.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.quran:
                startActivity(new Intent(this, AlquranActivity.class));
                break;
            case R.id.asmaul:
                startActivity(new Intent(this, AsmaulActivity.class));
                break;
            case R.id.amalyaumi:
                startActivity(new Intent(this, amalyaumiActivity.class));
                break;
            case R.id.haid:
                startActivity(new Intent(this, aboutActivity.class));
                break;
        }
    }
}
