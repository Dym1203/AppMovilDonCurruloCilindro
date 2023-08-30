package com.example.appmovildoncurrulocilindro.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmovildoncurrulocilindro.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RecoverPasswordActivity extends AppCompatActivity {

    TextInputLayout tilnuevaclave, tilconfirmarclave, tilcorreocliente;
    TextInputEditText tietnuevaclave, tietconfirmarclave, tietcorreocliente;
    Button btnaceptar, btncancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);
        init();
        btnaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validar()) {
                    if (!tietnuevaclave.getText().toString().equals(tietconfirmarclave.getText().toString())) {
                        toastError("¡Las contraseñas no coinciden!");
                    }
                    else {
                        showSuccessDialog();
                        LimpiarControles();
                    }
                }
            }
        });

        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.rigth_in, R.anim.rigth_out);
            }
        });
    }

    private void init() {
        tilnuevaclave = findViewById(R.id.tilNuevaClave);
        tilconfirmarclave = findViewById(R.id.tilConfirmarClave);
        tilcorreocliente = findViewById(R.id.tilCorreoCliente);
        tietnuevaclave = findViewById(R.id.tietNuevaClave);
        tietconfirmarclave = findViewById(R.id.tietConfirmarClave);
        tietcorreocliente = findViewById(R.id.tietCorreoCliente);
        btnaceptar = findViewById(R.id.btnAceptar);
        btncancelar = findViewById(R.id.btnCancelar);
        /**/
        tietcorreocliente.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tilcorreocliente.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tietnuevaclave.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tilnuevaclave.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tietconfirmarclave.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tilconfirmarclave.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void toastError(String msg){
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_toast_error,(ViewGroup) findViewById(R.id.toast_error));
        TextView txtMensaje = view.findViewById(R.id.txtMensajeToast2);
        txtMensaje.setText(msg);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM,0,200);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }

    private void showSuccessDialog() {
        ConstraintLayout successConstraintLayout = findViewById(R.id.successConstraintLayout);
        View view = LayoutInflater.from(RecoverPasswordActivity.this).inflate(R.layout.password_dialog, successConstraintLayout);
        Button successDone = view.findViewById(R.id.successDone);

        AlertDialog.Builder builder = new AlertDialog.Builder(RecoverPasswordActivity.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        successDone.findViewById(R.id.successDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }

    public boolean validar() {
        boolean retorno = true;
        String correo, nuevaContrasenia, confirmarContrasenia;
        correo = tietcorreocliente.getText().toString();
        nuevaContrasenia = tietnuevaclave.getText().toString();
        confirmarContrasenia = tietconfirmarclave.getText().toString();

        if (correo.isEmpty() || nuevaContrasenia.isEmpty() || confirmarContrasenia.isEmpty()) {
            toastError("¡Por favor, complete todos los campos!");
            retorno = false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            toastError("¡Ingrese un correo electrónico válido!");
            retorno = false;
        }
        else if (nuevaContrasenia.length() < 8) {
            toastError("¡La nueva contraseña como mínimo debe tener 8 caracteres!");
            retorno = false;
        }
        else if (!nuevaContrasenia.matches(".*\\d.*") || !nuevaContrasenia.matches(".*[a-zA-Z].*") || !nuevaContrasenia.matches(".*[!@#$%^&*()-+].*")) {
            toastError("¡La nueva contraseña debe contener al menos un número, una letra y un caracter especial!");
            retorno = false;
        }
        return retorno;
    }

    public void LimpiarControles() {
        tietcorreocliente.setText("");
        tietnuevaclave.setText("");
        tietconfirmarclave.setText("");
        tietcorreocliente.requestFocus();
    }
}