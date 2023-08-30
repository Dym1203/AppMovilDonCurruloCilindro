package com.example.appmovildoncurrulocilindro.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appmovildoncurrulocilindro.api.ConfigApi;
import com.example.appmovildoncurrulocilindro.model.service.DetallePedido;
import com.example.appmovildoncurrulocilindro.model.service.Plato;
import com.example.appmovildoncurrulocilindro.R;
import com.example.appmovildoncurrulocilindro.utilidades.Carrito;
import com.example.appmovildoncurrulocilindro.utilidades.DateSerializer;
import com.example.appmovildoncurrulocilindro.utilidades.TimeSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.sql.Date;
import java.sql.Time;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DetallePlatoActivity extends AppCompatActivity {

    private ImageView imgPlatoDetalle;
    private Button btnAgregarCarrito;
    private TextView tvNamePlatoDetalle, tvPrecioPlatoDetalle, tvDescripcionPlatoDetalle;
    private EditText tietCantidadP;
    private ImageButton btnIncrementar, btnDecrementar;
    private int cantidad = 1;
    final Gson g = new GsonBuilder()
            .registerTypeAdapter(Date.class, new DateSerializer())
            .registerTypeAdapter(Time.class, new TimeSerializer())
            .create();
    Plato plato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_plato);
        init();
        loadData();
        btnIncrementar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cantidad < 100) {
                    cantidad++;
                    actualizarCantidad();
                }
            }
        });
        btnDecrementar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cantidad > 1) {
                    cantidad--;
                    actualizarCantidad();
                }
            }
        });
    }

    private void actualizarCantidad() {
        tietCantidadP.setText(String.valueOf(cantidad));
    }

    private void init() {
        Toolbar toolbar = this.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_volver_atras);
        toolbar.setNavigationOnClickListener(v -> {
            this.finish();
            this.overridePendingTransition(R.anim.rigth_in, R.anim.rigth_out);
        });
        this.imgPlatoDetalle = findViewById(R.id.imgPlatoDetalle);
        this.btnAgregarCarrito = findViewById(R.id.btnAgregarCarrito);
        this.tietCantidadP = findViewById(R.id.edtCantidadP);
        this.btnIncrementar = findViewById(R.id.btnIncrementar);
        this.btnDecrementar = findViewById(R.id.btnDecrementar);
        this.tvNamePlatoDetalle = findViewById(R.id.tvNamePlatoDetalle);
        this.tvPrecioPlatoDetalle = findViewById(R.id.tvPrecioPlatoDetalle);
        this.tvDescripcionPlatoDetalle = findViewById(R.id.tvDescripcionPlatoDetalle);
    }

    private void loadData() {
        final String detalleString = this.getIntent().getStringExtra("detallePlato");
        if (detalleString != null) {
            plato = g.fromJson(detalleString, Plato.class);
            this.tvNamePlatoDetalle.setText(plato.getNombre());
            this.tvPrecioPlatoDetalle.setText(String.format(Locale.ENGLISH, "S/%.2f", plato.getPrecio()));
            this.tvDescripcionPlatoDetalle.setText(plato.getDescripcion());
            this.tietCantidadP.setText(Integer.toString(1));
            String url = ConfigApi.ipDylan + "/api/documento-almacenado/download/" + plato.getFoto().getFileName(); //baseUrlE

            Picasso picasso = new Picasso.Builder(this)
                    .downloader(new OkHttp3Downloader(ConfigApi.getClient()))
                    .build();
            picasso.load(url)
                    .error(R.drawable.image_not_found)
                    .into(this.imgPlatoDetalle);
        }
        else {
            System.out.println("Error al obtener los detalles del plato");
        }
        //Agregar platos al carrito
        this.btnAgregarCarrito.setOnClickListener(v -> {
            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setPlato(plato);
            detallePedido.setCantidad(Integer.parseInt(tietCantidadP.getText().toString()));
            detallePedido.setPrecio(plato.getPrecio());
            successMessage(Carrito.agregarPlatos(detallePedido));
            this.tietCantidadP.setText(Integer.toString(1));
        });
    }

    public void successMessage(String message) {
        new SweetAlertDialog(this,
                SweetAlertDialog.SUCCESS_TYPE).setTitleText("¡Buen Trabajo!")
                .setContentText(message).show();
    }

}