package com.example.appmovildoncurrulocilindro.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmovildoncurrulocilindro.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class PagoPedidoActivity extends AppCompatActivity {

    TextInputLayout tildireccion, tilcelular, tiltelefono, tilmonto, tilcomentarios;
    TextInputEditText tietdireccion, tietcelular, tiettelefono, tietmonto, tietcomentarios;
    Spinner spinnermetodo;
    CheckBox chkpromociones, chkterminos;
    Button btnboleta, btnfinalizarc;

    String[] lstmetodos = {"Efectivo", "Tarjeta de Débito", "Tarjeta de Crédito", "Yape", "Plin"};

    void EnlazarControles()
    {
        tildireccion = findViewById(R.id.tilDireccion);
        tilcelular = findViewById(R.id.tilCelular);
        tiltelefono = findViewById(R.id.tilTelefono);
        tilmonto = findViewById(R.id.tilMonto);
        tilcomentarios = findViewById(R.id.tilComentarios);
        tietdireccion = findViewById(R.id.tietDireccion);
        tietcelular = findViewById(R.id.tietCelular);
        tiettelefono = findViewById(R.id.tietTelefono);
        tietmonto = findViewById(R.id.tietMonto);
        tietcomentarios = findViewById(R.id.tietComentarios);
        spinnermetodo = findViewById(R.id.spinnerMetodo);
        chkpromociones = findViewById(R.id.chkPromociones);
        chkterminos = findViewById(R.id.chkTerminos);
        btnboleta = findViewById(R.id.btnBoleta);
        btnfinalizarc = findViewById(R.id.btnFinalizarc);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_pedido);
        //
        EnlazarControles();
        tietdireccion.setText("Av. Bolivar 451");
        tietcelular.setText("949322858");
        tietmonto.setText(String.valueOf(82.20));
        //
        btnfinalizarc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PasarMinijuego();
            }
        });
        //
        RadioButton radioFactura = findViewById(R.id.btnFactura);
        RadioButton radioBoleta = findViewById(R.id.btnBoleta);

        // Marcar la opción "Boleta" por defecto
        radioBoleta.setChecked(true);

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                lstmetodos
        );
        spinnermetodo.setAdapter(adaptador);
    }

    void PasarMinijuego()
    {
        if (tietdireccion.equals("") || tietcelular.equals("") || tietmonto.equals(""))
        {
            new SweetAlertDialog(PagoPedidoActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText("¡Todos los campos son obligatorios!")
                    .setConfirmText("Aceptar")
                    .show();
        }
        if (chkterminos.isChecked() == false)
        {
            new SweetAlertDialog(PagoPedidoActivity.this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Advertencia")
                    .setContentText("¡Debe aceptar los términos!")
                    .setConfirmText("Aceptar")
                    .show();
        }
        else
        {
            LimpiarControles();
            toastCorrecto("¡Pago exitoso!");
            finish();
            startActivity(new Intent(getApplicationContext(), MinijuegoActivity.class));
            overridePendingTransition(R.anim.left_in, R.anim.left_out);
        }
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

    void LimpiarControles()
    {
        tietdireccion.setText("");
        tietcelular.setText("");
        tiettelefono.setText("");
        tietmonto.setText("");
        tietcomentarios.setText("");
        spinnermetodo.setSelection(0);
        tietdireccion.requestFocus();
    }
}