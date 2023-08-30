package com.example.appmovildoncurrulocilindro.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appmovildoncurrulocilindro.R;

import java.util.ArrayList;
import java.util.Collections;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MinijuegoActivity extends AppCompatActivity {

    ImageButton imb00, imb01, imb02, imb03, imb04, imb05, imb06, imb07, imb08, imb09, imb10, imb11, imb12, imb13, imb14, imb15;
    ImageButton[] tablero = new ImageButton[16];
    Button btnreiniciar;
    TextView tvpuntuacion;
    int puntuacion;
    int aciertos;

    // Imágenes
    int[] imagenes;
    int fondo;

    // Variables del juego
    ArrayList<Integer> arrayDesordenado;
    ImageButton primero;
    int numeroPrimero, numeroSegundo;
    boolean bloqueo = false;
    final Handler handler = new Handler();
    private int segundosTranscurridos = 0;
    private final Handler tiempoHandler = new Handler();
    private final Runnable tiempoRunnable = new Runnable() {
        @Override
        public void run() {
            segundosTranscurridos++;
            if (segundosTranscurridos >= 60) {
                finish();
                startActivity(new Intent(getApplicationContext(), ComprobanteVentaActivity.class));
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
            else {
                tiempoHandler.postDelayed(this, 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minijuego);
        //
        init();
        //
        tiempoHandler.post(tiempoRunnable);
    }

    private void cargarBotones()
    {
        btnreiniciar = findViewById(R.id.btnJuegoReiniciar);
        btnreiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init();
            }
        });
    }

    private void cargarTexto()
    {
        tvpuntuacion = findViewById(R.id.tvPuntuacion);
        puntuacion = 0;
        aciertos = 0;
        tvpuntuacion.setText("Puntuación: " + puntuacion);
    }

    private void cargarTablero()
    {
        imb00 = findViewById(R.id.boton00);
        imb01 = findViewById(R.id.boton01);
        imb02 = findViewById(R.id.boton02);
        imb03 = findViewById(R.id.boton03);
        imb04 = findViewById(R.id.boton04);
        imb05 = findViewById(R.id.boton05);
        imb06 = findViewById(R.id.boton06);
        imb07 = findViewById(R.id.boton07);
        imb08 = findViewById(R.id.boton08);
        imb09 = findViewById(R.id.boton09);
        imb10 = findViewById(R.id.boton10);
        imb11 = findViewById(R.id.boton11);
        imb12 = findViewById(R.id.boton12);
        imb13 = findViewById(R.id.boton13);
        imb14 = findViewById(R.id.boton14);
        imb15 = findViewById(R.id.boton15);

        tablero[0] = imb00;
        tablero[1] = imb01;
        tablero[2] = imb02;
        tablero[3] = imb03;
        tablero[4] = imb04;
        tablero[5] = imb05;
        tablero[6] = imb06;
        tablero[7] = imb07;
        tablero[8] = imb08;
        tablero[9] = imb09;
        tablero[10] = imb10;
        tablero[11] = imb11;
        tablero[12] = imb12;
        tablero[13] = imb13;
        tablero[14] = imb14;
        tablero[15] = imb15;
    }

    private void cargarImagenes()
    {
        imagenes = new int[]{
                R.drawable.la0,
                R.drawable.la1,
                R.drawable.la2,
                R.drawable.la3,
                R.drawable.la4,
                R.drawable.la5,
                R.drawable.la6,
                R.drawable.la7
        };
        fondo = R.drawable.fondo;
    }

    private ArrayList<Integer> barajar(int longitud)
    {
        ArrayList<Integer> resultado = new ArrayList<Integer>();
        for (int i = 0; i < longitud * 2; i++)
        {
            resultado.add(i % longitud);
        }
        Collections.shuffle(resultado);
        return resultado;
    }

    private void comprobar(int i, final ImageButton imgb)
    {
        if (primero == null)
        {
            primero = imgb;
            primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
            primero.setImageResource(imagenes[arrayDesordenado.get(i)]);
            primero.setEnabled(false);
            numeroPrimero = arrayDesordenado.get(i);
        }
        else
        {
            bloqueo = true;
            imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgb.setImageResource(imagenes[arrayDesordenado.get(i)]);
            imgb.setEnabled(false);
            numeroSegundo = arrayDesordenado.get(i);
            if (numeroPrimero == numeroSegundo) {
                primero = null;
                bloqueo = false;
                aciertos++;
                puntuacion++;
                tvpuntuacion.setText("Puntuación: " + puntuacion);
                if (aciertos == imagenes.length) {
                    new SweetAlertDialog(MinijuegoActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("¡Enhorabuena!")
                            .setContentText("¡Has ganado!")
                            .setConfirmText("Aceptar")
                            .show();
                }
            }
            else
            {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        primero.setImageResource(fondo);
                        primero.setEnabled(true);
                        imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imgb.setImageResource(fondo);
                        imgb.setEnabled(true);
                        bloqueo = false;
                        primero = null;
                        puntuacion--;
                        tvpuntuacion.setText("Puntuación: " + puntuacion);
                    }
                }, 1000);
            }
        }
    }

    private void init()
    {
        cargarTablero();
        cargarBotones();
        cargarTexto();
        cargarImagenes();
        arrayDesordenado = barajar(imagenes.length);
        for (int i = 0; i < tablero.length; i++)
        {
            tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            tablero[i].setImageResource(imagenes[arrayDesordenado.get(i)]);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < tablero.length; i++)
                {
                    tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
                    tablero[i].setImageResource(fondo);
                }
            }
        }, 5500);
        for (int i = 0; i < tablero.length; i++)
        {
            final int j = i;
            tablero[i].setEnabled(true);
            tablero[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!bloqueo)
                        comprobar(j, tablero[j]);
                }
            });
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        tiempoHandler.removeCallbacks(tiempoRunnable);
    }

}