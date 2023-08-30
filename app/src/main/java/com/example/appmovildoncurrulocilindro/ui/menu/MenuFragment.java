package com.example.appmovildoncurrulocilindro.ui.menu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovildoncurrulocilindro.adapter.PlatoRecomendadoAdaptador;
import com.example.appmovildoncurrulocilindro.communication.Communication;
import com.example.appmovildoncurrulocilindro.communication.MostrarBadgeCommunication;
import com.example.appmovildoncurrulocilindro.model.service.DetallePedido;
import com.example.appmovildoncurrulocilindro.model.service.Plato;
import com.example.appmovildoncurrulocilindro.R;
import com.example.appmovildoncurrulocilindro.utilidades.Carrito;
import com.example.appmovildoncurrulocilindro.viewmodel.PlatoViewModel;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MenuFragment extends Fragment implements Communication, MostrarBadgeCommunication {

    private PlatoViewModel platoViewModel;
    private RecyclerView rcvPlatosRecomendados;
    private PlatoRecomendadoAdaptador adaptador;
    private List<Plato> platos = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        initAdapter();
        loadData();
    }

    private void init(View v) {
        ViewModelProvider vmp = new ViewModelProvider(this);
        rcvPlatosRecomendados = v.findViewById(R.id.rcvPlatosRecomendados);
        rcvPlatosRecomendados.setLayoutManager(new GridLayoutManager(getContext(), 1));
        platoViewModel = vmp.get(PlatoViewModel.class);
    }

    private void initAdapter() {
        adaptador = new PlatoRecomendadoAdaptador(platos, this, this);
        rcvPlatosRecomendados.setAdapter(adaptador);
    }

    private void loadData() {
        platoViewModel.listarPlatosRecomendados().observe(getViewLifecycleOwner(), response -> {
            adaptador.updateItems(response.getBody());
        });
    }

    @Override
    public void showDetails(Intent i) {
        getActivity().startActivity(i);
        getActivity().overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    @Override
    public void exportInvoice(int idCli, int idPed, int idOrden, String fileName) {

    }

    @SuppressLint({"UnsafeExperimentalUsageError", "UnsafeOptInUsageError"})
    @Override
    public void add(DetallePedido dp) {
        successMessage(Carrito.agregarPlatos(dp));
        BadgeDrawable badgeDrawable = BadgeDrawable.create(this.getContext());
        badgeDrawable.setNumber(Carrito.getDetallePedidos().size());
        BadgeUtils.attachBadgeDrawable(badgeDrawable, getActivity().findViewById(R.id.toolbar), R.id.bolsaCompras);
    }
    public void successMessage(String message) {
        new SweetAlertDialog(this.getContext(),
                SweetAlertDialog.SUCCESS_TYPE).setTitleText("¡Buen Trabajo!")
                .setContentText(message).show();
    }

}