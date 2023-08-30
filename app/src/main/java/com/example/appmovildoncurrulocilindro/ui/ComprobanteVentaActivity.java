package com.example.appmovildoncurrulocilindro.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appmovildoncurrulocilindro.R;

public class ComprobanteVentaActivity extends AppCompatActivity {

    Button btnfinalizarp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprobante_venta);
        btnfinalizarp = findViewById(R.id.btnFinalizarp);
        btnfinalizarp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(ComprobanteVentaActivity.this, InicioActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });
    }

}