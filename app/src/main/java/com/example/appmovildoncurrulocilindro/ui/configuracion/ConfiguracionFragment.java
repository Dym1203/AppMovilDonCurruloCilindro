package com.example.appmovildoncurrulocilindro.ui.configuracion;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.appmovildoncurrulocilindro.R;

public class ConfiguracionFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_configuracion, container, false);

        CheckBox myCheckBox = root.findViewById(R.id.my_checkbox1);
        myCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myCheckBox.isChecked()) {
                    showSuccessDialog();
                } else {
                    showDesactivationDialog();
                }
            }
        });

        CheckBox myCheckBox2 = root.findViewById(R.id.my_checkbox2);
        myCheckBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myCheckBox2.isChecked()) {
                    showSuccessDialog();
                } else {
                    showDesactivationDialog();
                }
            }
        });

        return root;
    }
    private void showSuccessDialog() {
        View view = LayoutInflater.from(requireContext()).inflate(R.layout.success_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        Button successDone = view.findViewById(R.id.successDone);
        successDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss(); // Aquí cierra el AlertDialog
            }
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();
    }

    private void showDesactivationDialog() {
        View view = LayoutInflater.from(requireContext()).inflate(R.layout.desactivation_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        Button successDone = view.findViewById(R.id.successDone);
        successDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss(); // Aquí cierra el AlertDialog
            }
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();
    }

}