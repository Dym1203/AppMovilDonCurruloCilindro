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

public final class ItemPlatosPorCategoriaBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnOrdenarPC;

  @NonNull
  public final CircleImageView imgPlatoC;

  @NonNull
  public final ImageView iv1;

  @NonNull
  public final TextView namePlatoC;

  @NonNull
  public final TextView tvPrecioPlatoC;

  private ItemPlatosPorCategoriaBinding(@NonNull LinearLayout rootView,
      @NonNull Button btnOrdenarPC, @NonNull CircleImageView imgPlatoC, @NonNull ImageView iv1,
      @NonNull TextView namePlatoC, @NonNull TextView tvPrecioPlatoC) {
    this.rootView = rootView;
    this.btnOrdenarPC = btnOrdenarPC;
    this.imgPlatoC = imgPlatoC;
    this.iv1 = iv1;
    this.namePlatoC = namePlatoC;
    this.tvPrecioPlatoC = tvPrecioPlatoC;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemPlatosPorCategoriaBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemPlatosPorCategoriaBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_platos_por_categoria, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemPlatosPorCategoriaBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnOrdenarPC;
      Button btnOrdenarPC = ViewBindings.findChildViewById(rootView, id);
      if (btnOrdenarPC == null) {
        break missingId;
      }

      id = R.id.imgPlatoC;
      CircleImageView imgPlatoC = ViewBindings.findChildViewById(rootView, id);
      if (imgPlatoC == null) {
        break missingId;
      }

      id = R.id.iv1;
      ImageView iv1 = ViewBindings.findChildViewById(rootView, id);
      if (iv1 == null) {
        break missingId;
      }

      id = R.id.namePlatoC;
      TextView namePlatoC = ViewBindings.findChildViewById(rootView, id);
      if (namePlatoC == null) {
        break missingId;
      }

      id = R.id.tvPrecioPlatoC;
      TextView tvPrecioPlatoC = ViewBindings.findChildViewById(rootView, id);
      if (tvPrecioPlatoC == null) {
        break missingId;
      }

      return new ItemPlatosPorCategoriaBinding((LinearLayout) rootView, btnOrdenarPC, imgPlatoC,
          iv1, namePlatoC, tvPrecioPlatoC);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}