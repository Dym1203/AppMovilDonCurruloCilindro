// Generated by view binder compiler. Do not edit!
package com.example.appmovildoncurrulocilindro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appmovildoncurrulocilindro.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentCuentaConfiguracionBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnGuardar;

  @NonNull
  public final CircleImageView imageUser;

  @NonNull
  public final LinearLayout linearParte3;

  @NonNull
  public final LinearLayout myChe;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextInputEditText tietApellidos;

  @NonNull
  public final TextInputEditText tietCorreo;

  @NonNull
  public final TextInputEditText tietDireccion;

  @NonNull
  public final TextInputEditText tietNombre;

  @NonNull
  public final TextInputEditText tietTelefono;

  @NonNull
  public final TextInputLayout tilApellidos;

  @NonNull
  public final TextInputLayout tilCorreo;

  @NonNull
  public final TextInputLayout tilDireccion;

  @NonNull
  public final TextInputLayout tilNombre;

  @NonNull
  public final TextInputLayout tilTelefono;

  private FragmentCuentaConfiguracionBinding(@NonNull LinearLayout rootView,
      @NonNull Button btnGuardar, @NonNull CircleImageView imageUser,
      @NonNull LinearLayout linearParte3, @NonNull LinearLayout myChe, @NonNull TextView textView2,
      @NonNull TextInputEditText tietApellidos, @NonNull TextInputEditText tietCorreo,
      @NonNull TextInputEditText tietDireccion, @NonNull TextInputEditText tietNombre,
      @NonNull TextInputEditText tietTelefono, @NonNull TextInputLayout tilApellidos,
      @NonNull TextInputLayout tilCorreo, @NonNull TextInputLayout tilDireccion,
      @NonNull TextInputLayout tilNombre, @NonNull TextInputLayout tilTelefono) {
    this.rootView = rootView;
    this.btnGuardar = btnGuardar;
    this.imageUser = imageUser;
    this.linearParte3 = linearParte3;
    this.myChe = myChe;
    this.textView2 = textView2;
    this.tietApellidos = tietApellidos;
    this.tietCorreo = tietCorreo;
    this.tietDireccion = tietDireccion;
    this.tietNombre = tietNombre;
    this.tietTelefono = tietTelefono;
    this.tilApellidos = tilApellidos;
    this.tilCorreo = tilCorreo;
    this.tilDireccion = tilDireccion;
    this.tilNombre = tilNombre;
    this.tilTelefono = tilTelefono;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentCuentaConfiguracionBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentCuentaConfiguracionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_cuenta_configuracion, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentCuentaConfiguracionBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnGuardar;
      Button btnGuardar = ViewBindings.findChildViewById(rootView, id);
      if (btnGuardar == null) {
        break missingId;
      }

      id = R.id.imageUser;
      CircleImageView imageUser = ViewBindings.findChildViewById(rootView, id);
      if (imageUser == null) {
        break missingId;
      }

      id = R.id.linearParte3;
      LinearLayout linearParte3 = ViewBindings.findChildViewById(rootView, id);
      if (linearParte3 == null) {
        break missingId;
      }

      LinearLayout myChe = (LinearLayout) rootView;

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.tietApellidos;
      TextInputEditText tietApellidos = ViewBindings.findChildViewById(rootView, id);
      if (tietApellidos == null) {
        break missingId;
      }

      id = R.id.tietCorreo;
      TextInputEditText tietCorreo = ViewBindings.findChildViewById(rootView, id);
      if (tietCorreo == null) {
        break missingId;
      }

      id = R.id.tietDireccion;
      TextInputEditText tietDireccion = ViewBindings.findChildViewById(rootView, id);
      if (tietDireccion == null) {
        break missingId;
      }

      id = R.id.tietNombre;
      TextInputEditText tietNombre = ViewBindings.findChildViewById(rootView, id);
      if (tietNombre == null) {
        break missingId;
      }

      id = R.id.tietTelefono;
      TextInputEditText tietTelefono = ViewBindings.findChildViewById(rootView, id);
      if (tietTelefono == null) {
        break missingId;
      }

      id = R.id.tilApellidos;
      TextInputLayout tilApellidos = ViewBindings.findChildViewById(rootView, id);
      if (tilApellidos == null) {
        break missingId;
      }

      id = R.id.tilCorreo;
      TextInputLayout tilCorreo = ViewBindings.findChildViewById(rootView, id);
      if (tilCorreo == null) {
        break missingId;
      }

      id = R.id.tilDireccion;
      TextInputLayout tilDireccion = ViewBindings.findChildViewById(rootView, id);
      if (tilDireccion == null) {
        break missingId;
      }

      id = R.id.tilNombre;
      TextInputLayout tilNombre = ViewBindings.findChildViewById(rootView, id);
      if (tilNombre == null) {
        break missingId;
      }

      id = R.id.tilTelefono;
      TextInputLayout tilTelefono = ViewBindings.findChildViewById(rootView, id);
      if (tilTelefono == null) {
        break missingId;
      }

      return new FragmentCuentaConfiguracionBinding((LinearLayout) rootView, btnGuardar, imageUser,
          linearParte3, myChe, textView2, tietApellidos, tietCorreo, tietDireccion, tietNombre,
          tietTelefono, tilApellidos, tilCorreo, tilDireccion, tilNombre, tilTelefono);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
