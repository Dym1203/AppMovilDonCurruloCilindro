// Generated by view binder compiler. Do not edit!
package com.example.appmovildoncurrulocilindro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appmovildoncurrulocilindro.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityPagoPedidoBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final RadioButton btnBoleta;

  @NonNull
  public final RadioButton btnFactura;

  @NonNull
  public final Button btnFinalizarc;

  @NonNull
  public final CheckBox chkPromociones;

  @NonNull
  public final CheckBox chkTerminos;

  @NonNull
  public final View divider5;

  @NonNull
  public final Spinner spinnerMetodo;

  @NonNull
  public final TextView textView14;

  @NonNull
  public final TextView textView16;

  @NonNull
  public final TextView textView17;

  @NonNull
  public final TextView textView19;

  @NonNull
  public final TextView textView21;

  @NonNull
  public final TextView textView291;

  @NonNull
  public final TextInputEditText tietCelular;

  @NonNull
  public final TextInputEditText tietComentarios;

  @NonNull
  public final TextInputEditText tietDireccion;

  @NonNull
  public final TextInputEditText tietMonto;

  @NonNull
  public final TextInputEditText tietTelefono;

  @NonNull
  public final TextInputLayout tilCelular;

  @NonNull
  public final TextInputLayout tilComentarios;

  @NonNull
  public final TextInputLayout tilDireccion;

  @NonNull
  public final TextInputLayout tilMonto;

  @NonNull
  public final TextInputLayout tilTelefono;

  private ActivityPagoPedidoBinding(@NonNull LinearLayout rootView, @NonNull RadioButton btnBoleta,
      @NonNull RadioButton btnFactura, @NonNull Button btnFinalizarc,
      @NonNull CheckBox chkPromociones, @NonNull CheckBox chkTerminos, @NonNull View divider5,
      @NonNull Spinner spinnerMetodo, @NonNull TextView textView14, @NonNull TextView textView16,
      @NonNull TextView textView17, @NonNull TextView textView19, @NonNull TextView textView21,
      @NonNull TextView textView291, @NonNull TextInputEditText tietCelular,
      @NonNull TextInputEditText tietComentarios, @NonNull TextInputEditText tietDireccion,
      @NonNull TextInputEditText tietMonto, @NonNull TextInputEditText tietTelefono,
      @NonNull TextInputLayout tilCelular, @NonNull TextInputLayout tilComentarios,
      @NonNull TextInputLayout tilDireccion, @NonNull TextInputLayout tilMonto,
      @NonNull TextInputLayout tilTelefono) {
    this.rootView = rootView;
    this.btnBoleta = btnBoleta;
    this.btnFactura = btnFactura;
    this.btnFinalizarc = btnFinalizarc;
    this.chkPromociones = chkPromociones;
    this.chkTerminos = chkTerminos;
    this.divider5 = divider5;
    this.spinnerMetodo = spinnerMetodo;
    this.textView14 = textView14;
    this.textView16 = textView16;
    this.textView17 = textView17;
    this.textView19 = textView19;
    this.textView21 = textView21;
    this.textView291 = textView291;
    this.tietCelular = tietCelular;
    this.tietComentarios = tietComentarios;
    this.tietDireccion = tietDireccion;
    this.tietMonto = tietMonto;
    this.tietTelefono = tietTelefono;
    this.tilCelular = tilCelular;
    this.tilComentarios = tilComentarios;
    this.tilDireccion = tilDireccion;
    this.tilMonto = tilMonto;
    this.tilTelefono = tilTelefono;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPagoPedidoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPagoPedidoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_pago_pedido, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPagoPedidoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnBoleta;
      RadioButton btnBoleta = ViewBindings.findChildViewById(rootView, id);
      if (btnBoleta == null) {
        break missingId;
      }

      id = R.id.btnFactura;
      RadioButton btnFactura = ViewBindings.findChildViewById(rootView, id);
      if (btnFactura == null) {
        break missingId;
      }

      id = R.id.btnFinalizarc;
      Button btnFinalizarc = ViewBindings.findChildViewById(rootView, id);
      if (btnFinalizarc == null) {
        break missingId;
      }

      id = R.id.chkPromociones;
      CheckBox chkPromociones = ViewBindings.findChildViewById(rootView, id);
      if (chkPromociones == null) {
        break missingId;
      }

      id = R.id.chkTerminos;
      CheckBox chkTerminos = ViewBindings.findChildViewById(rootView, id);
      if (chkTerminos == null) {
        break missingId;
      }

      id = R.id.divider5;
      View divider5 = ViewBindings.findChildViewById(rootView, id);
      if (divider5 == null) {
        break missingId;
      }

      id = R.id.spinnerMetodo;
      Spinner spinnerMetodo = ViewBindings.findChildViewById(rootView, id);
      if (spinnerMetodo == null) {
        break missingId;
      }

      id = R.id.textView14;
      TextView textView14 = ViewBindings.findChildViewById(rootView, id);
      if (textView14 == null) {
        break missingId;
      }

      id = R.id.textView16;
      TextView textView16 = ViewBindings.findChildViewById(rootView, id);
      if (textView16 == null) {
        break missingId;
      }

      id = R.id.textView17;
      TextView textView17 = ViewBindings.findChildViewById(rootView, id);
      if (textView17 == null) {
        break missingId;
      }

      id = R.id.textView19;
      TextView textView19 = ViewBindings.findChildViewById(rootView, id);
      if (textView19 == null) {
        break missingId;
      }

      id = R.id.textView21;
      TextView textView21 = ViewBindings.findChildViewById(rootView, id);
      if (textView21 == null) {
        break missingId;
      }

      id = R.id.textView291;
      TextView textView291 = ViewBindings.findChildViewById(rootView, id);
      if (textView291 == null) {
        break missingId;
      }

      id = R.id.tietCelular;
      TextInputEditText tietCelular = ViewBindings.findChildViewById(rootView, id);
      if (tietCelular == null) {
        break missingId;
      }

      id = R.id.tietComentarios;
      TextInputEditText tietComentarios = ViewBindings.findChildViewById(rootView, id);
      if (tietComentarios == null) {
        break missingId;
      }

      id = R.id.tietDireccion;
      TextInputEditText tietDireccion = ViewBindings.findChildViewById(rootView, id);
      if (tietDireccion == null) {
        break missingId;
      }

      id = R.id.tietMonto;
      TextInputEditText tietMonto = ViewBindings.findChildViewById(rootView, id);
      if (tietMonto == null) {
        break missingId;
      }

      id = R.id.tietTelefono;
      TextInputEditText tietTelefono = ViewBindings.findChildViewById(rootView, id);
      if (tietTelefono == null) {
        break missingId;
      }

      id = R.id.tilCelular;
      TextInputLayout tilCelular = ViewBindings.findChildViewById(rootView, id);
      if (tilCelular == null) {
        break missingId;
      }

      id = R.id.tilComentarios;
      TextInputLayout tilComentarios = ViewBindings.findChildViewById(rootView, id);
      if (tilComentarios == null) {
        break missingId;
      }

      id = R.id.tilDireccion;
      TextInputLayout tilDireccion = ViewBindings.findChildViewById(rootView, id);
      if (tilDireccion == null) {
        break missingId;
      }

      id = R.id.tilMonto;
      TextInputLayout tilMonto = ViewBindings.findChildViewById(rootView, id);
      if (tilMonto == null) {
        break missingId;
      }

      id = R.id.tilTelefono;
      TextInputLayout tilTelefono = ViewBindings.findChildViewById(rootView, id);
      if (tilTelefono == null) {
        break missingId;
      }

      return new ActivityPagoPedidoBinding((LinearLayout) rootView, btnBoleta, btnFactura,
          btnFinalizarc, chkPromociones, chkTerminos, divider5, spinnerMetodo, textView14,
          textView16, textView17, textView19, textView21, textView291, tietCelular, tietComentarios,
          tietDireccion, tietMonto, tietTelefono, tilCelular, tilComentarios, tilDireccion,
          tilMonto, tilTelefono);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}