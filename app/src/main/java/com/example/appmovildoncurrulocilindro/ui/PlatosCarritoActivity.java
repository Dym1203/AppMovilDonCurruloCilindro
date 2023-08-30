package com.example.appmovildoncurrulocilindro.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmovildoncurrulocilindro.adapter.PlatoCarritoAdaptador;
import com.example.appmovildoncurrulocilindro.communication.CarritoCommunication;
import com.example.appmovildoncurrulocilindro.model.service.DetallePedido;
import com.example.appmovildoncurrulocilindro.model.service.Usuario;
import com.example.appmovildoncurrulocilindro.model.service.dto.GenerarPedidoDTO;
import com.example.appmovildoncurrulocilindro.R;
import com.example.appmovildoncurrulocilindro.utilidades.Carrito;
import com.example.appmovildoncurrulocilindro.utilidades.DateSerializer;
import com.example.appmovildoncurrulocilindro.utilidades.TimeSerializer;
import com.example.appmovildoncurrulocilindro.viewmodel.PedidoViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class PlatosCarritoActivity extends AppCompatActivity implements CarritoCommunication {

    private PedidoViewModel pedidoViewModel;
    private PlatoCarritoAdaptador adaptador;
    private RecyclerView rcvCarritoCompras;
    private Button btnSeguirComprando;
    private Button btnIrPagar;
    final Gson g = new GsonBuilder()
            .registerTypeAdapter(Date.class, new DateSerializer())
            .registerTypeAdapter(Time.class, new TimeSerializer())
            .create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platos_carrito);
        init();
        initViewModel();
        initAdapter();
    }

    private void init() {
        Toolbar toolbar = this.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_volver_atras);
        toolbar.setNavigationOnClickListener(v -> {
            this.finish();
            this.overridePendingTransition(R.anim.rigth_in, R.anim.rigth_out);
        });
        rcvCarritoCompras = findViewById(R.id.rcvCarritoCompras);
        btnSeguirComprando = findViewById(R.id.btnSeguirComprando);
        btnIrPagar = findViewById(R.id.btnIrPagar);
        btnSeguirComprando.setOnClickListener(v -> {
            this.finish();
            overridePendingTransition(R.anim.rigth_in, R.anim.rigth_out);
        });
        btnIrPagar.setOnClickListener(v -> {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String pref = preferences.getString("UsuarioJson", "");
            Usuario u = g.fromJson(pref, Usuario.class);
            int idC = u.getCliente().getId_cliente();
            if (idC != 0) {
                if (Carrito.getDetallePedidos().isEmpty()) {
                    toastIncorrecto("¡Ups!, ¡El carrito de compras está vacío!");
                }
                else {
                    toastCorrecto("Procesando pedido...");
                    registrarPedido(idC);
                }
            }
            else {
                toastIncorrecto("¡No ha iniciado sesión, se le redirigirá al Login!");
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });
    }

    private void initViewModel() {
        final ViewModelProvider vmp = new ViewModelProvider(this);
        this.pedidoViewModel = vmp.get(PedidoViewModel.class);
    }

    private void initAdapter() {
        adaptador = new PlatoCarritoAdaptador(Carrito.getDetallePedidos(), this);
        rcvCarritoCompras.setLayoutManager(new LinearLayoutManager(this));
        rcvCarritoCompras.setAdapter(adaptador);
    }

    private void registrarPedido(int idC) {
        ArrayList<DetallePedido> detallePedidos = Carrito.getDetallePedidos();
        GenerarPedidoDTO dto = new GenerarPedidoDTO();
        java.util.Date date = new java.util.Date();
        dto.getPedido().setFecha(new Date(date.getTime()));
        dto.getPedido().setAnularPedido(false);
        dto.getPedido().setTotal(getTotalV(detallePedidos));
        dto.getCliente().setId_cliente(idC);
        dto.setDetallePedido(detallePedidos);
        this.pedidoViewModel.guardarPedido(dto).observe(this, response -> {
            if (response.getRpta() == 1) {
                Carrito.limpiar();
                finish();
                startActivity(new Intent(getApplicationContext(), PagoPedidoActivity.class));
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
            else {
                toastIncorrecto("¡No se pudo registrar el pedido!");
            }
        });
    }

    private double getTotalV(List<DetallePedido> detalles) {
        double tot = 0;
        BigDecimal bd = new BigDecimal(tot).setScale(2, RoundingMode.HALF_UP);
        double total = bd.doubleValue();
        for (DetallePedido dp : detalles) {
            total += dp.getTotal();
        }
        return total;
    }

    public void toastIncorrecto(String texto) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View layoutView = layoutInflater.inflate(R.layout.custom_toast_error, (ViewGroup) findViewById(R.id.toast_error));
        TextView textView = layoutView.findViewById(R.id.txtMensajeToast2);
        textView.setText(texto);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 200);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layoutView);
        toast.show();
    }

    public void toastCorrecto(String texto) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View layoutView = layoutInflater.inflate(R.layout.custom_toast_ok, (ViewGroup) findViewById(R.id.toast_ok));
        TextView textView = layoutView.findViewById(R.id.txtMensajeToast1);
        textView.setText(texto);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 200);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layoutView);
        toast.show();
    }

    @Override
    public void eliminarDetalle(int idP) {
        Carrito.eliminar(idP);
        this.adaptador.notifyDataSetChanged();
    }

}