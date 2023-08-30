package com.example.appmovildoncurrulocilindro.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appmovildoncurrulocilindro.R;

public class ComienzoActivity extends AppCompatActivity {

    Button btnempezar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comienzo);

        btnempezar = findViewById(R.id.btnEmpezar);

        btnempezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ComienzoActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}