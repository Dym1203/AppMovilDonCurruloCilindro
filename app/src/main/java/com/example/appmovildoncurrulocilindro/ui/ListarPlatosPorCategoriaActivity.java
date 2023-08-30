package com.example.appmovildoncurrulocilindro.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.appmovildoncurrulocilindro.adapter.PlatoPorCategoriaAdaptador;
import com.example.appmovildoncurrulocilindro.communication.Communication;
import com.example.appmovildoncurrulocilindro.communication.MostrarBadgeCommunication;
import com.example.appmovildoncurrulocilindro.model.service.DetallePedido;
import com.example.appmovildoncurrulocilindro.model.service.Plato;
import com.example.appmovildoncurrulocilindro.R;
import com.example.appmovildoncurrulocilindro.utilidades.Carrito;
import com.example.appmovildoncurrulocilindro.viewmodel.PlatoViewModel;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ListarPlatosPorCategoriaActivity extends AppCompatActivity implements Communication, MostrarBadgeCommunication {

    private PlatoViewModel platoViewModel;
    private PlatoPorCategoriaAdaptador adapter;
    private List<Plato> platos = new ArrayList<>();
    private RecyclerView rcvPlatoPorCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_platos_por_categoria);
        init();
        initViewModel();
        initAdapter();
        loadData();
    }

    private void init() {
        String nomC = getIntent().getStringExtra("nomC");
        Toolbar toolbar = this.findViewById(R.id.toolbar);
        toolbar.setTitle(nomC);
        toolbar.setNavigationIcon(R.drawable.ic_volver_atras);
        toolbar.setNavigationOnClickListener(v -> {
            this.finish();
            this.overridePendingTransition(R.anim.rigth_in, R.anim.rigth_out);
        });
    }

    private void initViewModel() {
        final ViewModelProvider vmp = new ViewModelProvider(this);
        this.platoViewModel = vmp.get(PlatoViewModel.class);
    }

    private void initAdapter() {
        adapter = new PlatoPorCategoriaAdaptador(platos, this, this);
        rcvPlatoPorCategoria = findViewById(R.id.rcvPlatosPorCategoria);
        rcvPlatoPorCategoria.setAdapter(adapter);
        rcvPlatoPorCategoria.setLayoutManager(new LinearLayoutManager(this));
        //rcvPlatilloPorCategoria.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void loadData() {
        int idC = getIntent().getIntExtra("idC", 0); //Recibimos el idCategoria
        platoViewModel.listarPlatosPorCategoria(idC).observe(this, response -> {
            adapter.updateItems(response.getBody());
        });
    }

    @SuppressLint({"UnsafeExperimentalUsageError", "UnsafeOptInUsageError"})
    @Override
    public void add(DetallePedido dp) {
        successMessage(Carrito.agregarPlatos(dp));
        BadgeDrawable badgeDrawable = BadgeDrawable.create(this);
        badgeDrawable.setNumber(Carrito.getDetallePedidos().size());
        BadgeUtils.attachBadgeDrawable(badgeDrawable, findViewById(R.id.toolbar), R.id.bolsaCompras);
    }

    public void successMessage(String message) {
        new SweetAlertDialog(this,
                SweetAlertDialog.SUCCESS_TYPE).setTitleText("¡Buen Trabajo!")
                .setContentText(message).show();
    }

    @Override
    public void showDetails(Intent i) {
        startActivity(i);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    @Override
    public void exportInvoice(int idCli, int idPed, int idOrden, String fileName) {

    }

}