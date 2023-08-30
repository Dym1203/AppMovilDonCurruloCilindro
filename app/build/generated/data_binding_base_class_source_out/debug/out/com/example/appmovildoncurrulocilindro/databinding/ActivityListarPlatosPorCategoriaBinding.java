// Generated by view binder compiler. Do not edit!
package com.example.appmovildoncurrulocilindro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appmovildoncurrulocilindro.R;
import com.google.android.material.appbar.AppBarLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityListarPlatosPorCategoriaBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final AppBarLayout bar;

  @NonNull
  public final RecyclerView rcvPlatosPorCategoria;

  @NonNull
  public final Toolbar toolbar;

  private ActivityListarPlatosPorCategoriaBinding(@NonNull LinearLayout rootView,
      @NonNull AppBarLayout bar, @NonNull RecyclerView rcvPlatosPorCategoria,
      @NonNull Toolbar toolbar) {
    this.rootView = rootView;
    this.bar = bar;
    this.rcvPlatosPorCategoria = rcvPlatosPorCategoria;
    this.toolbar = toolbar;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityListarPlatosPorCategoriaBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityListarPlatosPorCategoriaBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_listar_platos_por_categoria, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityListarPlatosPorCategoriaBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bar;
      AppBarLayout bar = ViewBindings.findChildViewById(rootView, id);
      if (bar == null) {
        break missingId;
      }

      id = R.id.rcvPlatosPorCategoria;
      RecyclerView rcvPlatosPorCategoria = ViewBindings.findChildViewById(rootView, id);
      if (rcvPlatosPorCategoria == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      return new ActivityListarPlatosPorCategoriaBinding((LinearLayout) rootView, bar,
          rcvPlatosPorCategoria, toolbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
