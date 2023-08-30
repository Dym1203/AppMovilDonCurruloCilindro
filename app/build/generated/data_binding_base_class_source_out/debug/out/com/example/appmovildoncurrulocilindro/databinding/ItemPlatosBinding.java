// Generated by view binder compiler. Do not edit!
package com.example.appmovildoncurrulocilindro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appmovildoncurrulocilindro.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemPlatosBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnOrdenar;

  @NonNull
  public final CircleImageView imgPlato;

  @NonNull
  public final ImageView iv1;

  @NonNull
  public final TextView namePlato;

  @NonNull
  public final TextView precioPlato;

  private ItemPlatosBinding(@NonNull LinearLayout rootView, @NonNull Button btnOrdenar,
      @NonNull CircleImageView imgPlato, @NonNull ImageView iv1, @NonNull TextView namePlato,
      @NonNull TextView precioPlato) {
    this.rootView = rootView;
    this.btnOrdenar = btnOrdenar;
    this.imgPlato = imgPlato;
    this.iv1 = iv1;
    this.namePlato = namePlato;
    this.precioPlato = precioPlato;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemPlatosBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemPlatosBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_platos, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemPlatosBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnOrdenar;
      Button btnOrdenar = ViewBindings.findChildViewById(rootView, id);
      if (btnOrdenar == null) {
        break missingId;
      }

      id = R.id.imgPlato;
      CircleImageView imgPlato = ViewBindings.findChildViewById(rootView, id);
      if (imgPlato == null) {
        break missingId;
      }

      id = R.id.iv1;
      ImageView iv1 = ViewBindings.findChildViewById(rootView, id);
      if (iv1 == null) {
        break missingId;
      }

      id = R.id.namePlato;
      TextView namePlato = ViewBindings.findChildViewById(rootView, id);
      if (namePlato == null) {
        break missingId;
      }

      id = R.id.precioPlato;
      TextView precioPlato = ViewBindings.findChildViewById(rootView, id);
      if (precioPlato == null) {
        break missingId;
      }

      return new ItemPlatosBinding((LinearLayout) rootView, btnOrdenar, imgPlato, iv1, namePlato,
          precioPlato);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}