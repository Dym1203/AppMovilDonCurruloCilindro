package com.example.appmovildoncurrulocilindro.ui.cuentaconfg;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.appmovildoncurrulocilindro.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class CuentaConfiguracionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cuenta_configuracion, container, false);

        Button btnGuardar = rootView.findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(v -> mostrarSweetAlert());

        return rootView;
    }

    private void mostrarSweetAlert() {
        new SweetAlertDialog(requireContext(), SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("¡Éxito!")
                .setContentText("¡Cambios realizados con éxito!")
                .setConfirmText("Aceptar")
                .show();
    }

}