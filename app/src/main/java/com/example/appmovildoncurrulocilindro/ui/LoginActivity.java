package com.example.appmovildoncurrulocilindro.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmovildoncurrulocilindro.model.service.Usuario;
import com.example.appmovildoncurrulocilindro.R;
import com.example.appmovildoncurrulocilindro.utilidades.DateSerializer;
import com.example.appmovildoncurrulocilindro.utilidades.TimeSerializer;
import com.example.appmovildoncurrulocilindro.viewmodel.UsuarioViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.sql.Date;
import java.sql.Time;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout tilcorreo, tilclave;
    TextInputEditText tietcorreo, tietclave;
    Button btnlogin, btnregistrarse, btnrecuperarpass;
    CheckBox chkrecuerdame;
    UsuarioViewModel viewModel;
    private int intentosFallidos = 0;
    private boolean loginButtonEnabled = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.initViewModel();
        this.init();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(UsuarioViewModel.class);
    }

    private void init() {
        tietcorreo = findViewById(R.id.tietCorreo);
        tietclave = findViewById(R.id.tietClave);
        tilcorreo = findViewById(R.id.tilCorreo);
        tilclave = findViewById(R.id.tilClave);
        chkrecuerdame = findViewById(R.id.chkPromociones);
        btnlogin = findViewById(R.id.btnLogin);
        btnlogin.setOnClickListener(v -> {
            try {
                if (validar()) {
                    if (loginButtonEnabled) {
                        viewModel.login(tietcorreo.getText().toString(), tietclave.getText().toString()).observe(this, response -> {
                            if (response.getRpta() == 1) {
                                toastCorrecto(response.getMessage());
                                Usuario u = response.getBody();
                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                                SharedPreferences.Editor editor = preferences.edit();
                                final Gson g = new GsonBuilder()
                                        .registerTypeAdapter(Date.class, new DateSerializer())
                                        .registerTypeAdapter(Time.class, new TimeSerializer())
                                        .create();
                                editor.putString("UsuarioJson", g.toJson(u, new TypeToken<Usuario>() {
                                }.getType()));
                                editor.apply();
                                tietcorreo.setText("");
                                tietclave.setText("");
                                chkrecuerdame.setChecked(false);
                                startActivity(new Intent(this, InicioActivity.class));
                            } else {
                                toastError(response.getMessage());
                                intentosFallidos++;
                                if (intentosFallidos >= 3) {
                                    loginButtonEnabled = false;
                                    btnlogin.setEnabled(false);
                                    new Handler().postDelayed(() -> {
                                        loginButtonEnabled = true;
                                        btnlogin.setEnabled(true);
                                        intentosFallidos = 0;
                                    }, 30000);
                                }
                            }
                        });
                    }
                } else {
                    toastError("¡Por favor, complete todos los campos!");
                }

            } catch (Exception e) {
                toastError("Ocurrió un error " + e.getMessage());
            }
        });
        tietcorreo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tilcorreo.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        tietclave.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tilclave.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ////////////////////////
        btnregistrarse = findViewById(R.id.btnRegistrarse);
        btnregistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
        btnrecuperarpass = findViewById(R.id.btnRecuperarPass);
        btnrecuperarpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RecoverPasswordActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).setTitleText("¡Has oprimido el botón atrás!")
                .setContentText("¿Quieres cerrar la aplicación?")
                .setCancelText("Cancelar").setConfirmText("Cerrar")
                .showCancelButton(true).setCancelClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("¡Operación Cancelada!")
                            .setContentText("¡No saliste de la aplicación!")
                            .show();
                }).setConfirmClickListener(sweetAlertDialog -> {
                    sweetAlertDialog.dismissWithAnimation();
                    System.exit(0);
                }).show();
    }

    public void toastCorrecto(String msg) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_toast_ok,(ViewGroup) findViewById(R.id.toast_ok));
        TextView txtMensaje = view.findViewById(R.id.txtMensajeToast1);
        txtMensaje.setText(msg);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM,0,200);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }

    public void toastError(String msg) {
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

    private boolean validar() {
        boolean retorno = true;
        String usuario, password;
        usuario = tietcorreo.getText().toString();
        password = tietclave.getText().toString();
        if (usuario.isEmpty()){
            tilcorreo.setError("¡Ingrese su correo electrónico!");
            retorno = false;
        } else {
            tilcorreo.setErrorEnabled(false);
        }
        if (password.isEmpty()){
            tilclave.setError("¡Ingrese su contraseña!");
            retorno = false;
        } else {
            tilclave.setErrorEnabled(false);
        }
        return retorno;
    }

    /*Omitir la sesión con Shared Preferences*/
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String pref = preferences.getString("UsuarioJson", "");
        if (!pref.equals("")) {
            toastCorrecto("¡Se detecto una sesión activa!");
            this.startActivity(new Intent(this, InicioActivity.class));
            this.overridePendingTransition(R.anim.left_in, R.anim.left_out);
        }
    }

}