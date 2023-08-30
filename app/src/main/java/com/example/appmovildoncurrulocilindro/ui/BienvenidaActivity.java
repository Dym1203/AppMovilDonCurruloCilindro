package com.example.appmovildoncurrulocilindro.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appmovildoncurrulocilindro.adapter.ViewPagerAdaptador;
import com.example.appmovildoncurrulocilindro.R;

public class BienvenidaActivity extends AppCompatActivity {

    ViewPager sliderviewpager;
    LinearLayout dotindicator;
    ViewPagerAdaptador adaptador;
    Button btnsiguiente, btnsaltar, btnretroceder;
    TextView[] dots;

    ViewPager.OnPageChangeListener viewpagerlistener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onPageSelected(int position) {
            setDotIndicator(position);
            if (position > 0) {
                btnretroceder.setVisibility(View.VISIBLE);
            }
            else {
                btnretroceder.setVisibility(View.INVISIBLE);
            }
            if (position == 2) {
                btnsiguiente.setText("Terminar");
            }
            else {
                btnsiguiente.setText("Siguiente");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        btnsiguiente = findViewById(R.id.btnSiguiente);
        btnsaltar = findViewById(R.id.btnSaltar);
        btnretroceder = findViewById(R.id.btnRegresar);

        btnretroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getItem(0) > 0) {
                    sliderviewpager.setCurrentItem(getItem(-1), true);
                }
            }
        });

        btnsiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getItem(0) < 2) {
                    sliderviewpager.setCurrentItem(getItem(1), true);
                }
                else {
                    Intent i = new Intent(BienvenidaActivity.this, ComienzoActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        btnsaltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BienvenidaActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        sliderviewpager = (ViewPager) findViewById(R.id.slideViewPager);
        dotindicator = (LinearLayout) findViewById(R.id.dotIndicator);

        adaptador = new ViewPagerAdaptador(this);
        sliderviewpager.setAdapter(adaptador);

        setDotIndicator(0);
        sliderviewpager.addOnPageChangeListener(viewpagerlistener);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setDotIndicator(int position) {
        dots = new TextView[3];
        dotindicator.removeAllViews();

        for (int i = 0; i < dots.length; i++)
        {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226", Html.FROM_HTML_MODE_LEGACY));
            dots[i].setTextSize(30);
            dots[i].setTextColor(getResources().getColor(R.color.grey, getApplicationContext().getTheme()));
            dotindicator.addView(dots[i]);
        }
        dots[position].setTextColor(getResources().getColor(R.color.lavender, getApplicationContext().getTheme()));
    }

    private int getItem(int i) {
        return sliderviewpager.getCurrentItem() + i;
    }
}