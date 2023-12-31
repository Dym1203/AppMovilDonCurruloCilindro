// Generated by view binder compiler. Do not edit!
package com.example.appmovildoncurrulocilindro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appmovildoncurrulocilindro.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemPlatosCarritoBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView btnAdd;

  @NonNull
  public final ImageView btnDecrease;

  @NonNull
  public final ImageView btnEliminarPlCarrito;

  @NonNull
  public final MaterialCardView cvMisCompras;

  @NonNull
  public final EditText edtCantidad;

  @NonNull
  public final ShapeableImageView imgPlatoDC;

  @NonNull
  public final LinearLayout ly1;

  @NonNull
  public final TextView tvNombrePlatoDC;

  @NonNull
  public final TextView tvPrecioPDC;

  private ItemPlatosCarritoBinding(@NonNull LinearLayout rootView, @NonNull ImageView btnAdd,
      @NonNull ImageView btnDecrease, @NonNull ImageView btnEliminarPlCarrito,
      @NonNull MaterialCardView cvMisCompras, @NonNull EditText edtCantidad,
      @NonNull ShapeableImageView imgPlatoDC, @NonNull LinearLayout ly1,
      @NonNull TextView tvNombrePlatoDC, @NonNull TextView tvPrecioPDC) {
    this.rootView = rootView;
    this.btnAdd = btnAdd;
    this.btnDecrease = btnDecrease;
    this.btnEliminarPlCarrito = btnEliminarPlCarrito;
    this.cvMisCompras = cvMisCompras;
    this.edtCantidad = edtCantidad;
    this.imgPlatoDC = imgPlatoDC;
    this.ly1 = ly1;
    this.tvNombrePlatoDC = tvNombrePlatoDC;
    this.tvPrecioPDC = tvPrecioPDC;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemPlatosCarritoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemPlatosCarritoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_platos_carrito, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemPlatosCarritoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnAdd;
      ImageView btnAdd = ViewBindings.findChildViewById(rootView, id);
      if (btnAdd == null) {
        break missingId;
      }

      id = R.id.btnDecrease;
      ImageView btnDecrease = ViewBindings.findChildViewById(rootView, id);
      if (btnDecrease == null) {
        break missingId;
      }

      id = R.id.btnEliminarPlCarrito;
      ImageView btnEliminarPlCarrito = ViewBindings.findChildViewById(rootView, id);
      if (btnEliminarPlCarrito == null) {
        break missingId;
      }

      id = R.id.cvMisCompras;
      MaterialCardView cvMisCompras = ViewBindings.findChildViewById(rootView, id);
      if (cvMisCompras == null) {
        break missingId;
      }

      id = R.id.edtCantidad;
      EditText edtCantidad = ViewBindings.findChildViewById(rootView, id);
      if (edtCantidad == null) {
        break missingId;
      }

      id = R.id.imgPlatoDC;
      ShapeableImageView imgPlatoDC = ViewBindings.findChildViewById(rootView, id);
      if (imgPlatoDC == null) {
        break missingId;
      }

      id = R.id.ly1;
      LinearLayout ly1 = ViewBindings.findChildViewById(rootView, id);
      if (ly1 == null) {
        break missingId;
      }

      id = R.id.tvNombrePlatoDC;
      TextView tvNombrePlatoDC = ViewBindings.findChildViewById(rootView, id);
      if (tvNombrePlatoDC == null) {
        break missingId;
      }

      id = R.id.tvPrecioPDC;
      TextView tvPrecioPDC = ViewBindings.findChildViewById(rootView, id);
      if (tvPrecioPDC == null) {
        break missingId;
      }

      return new ItemPlatosCarritoBinding((LinearLayout) rootView, btnAdd, btnDecrease,
          btnEliminarPlCarrito, cvMisCompras, edtCantidad, imgPlatoDC, ly1, tvNombrePlatoDC,
          tvPrecioPDC);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
