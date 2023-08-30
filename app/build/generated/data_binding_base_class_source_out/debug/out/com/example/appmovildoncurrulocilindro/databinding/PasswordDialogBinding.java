// Generated by view binder compiler. Do not edit!
package com.example.appmovildoncurrulocilindro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appmovildoncurrulocilindro.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class PasswordDialogBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout layoutDialog;

  @NonNull
  public final ConstraintLayout successConstraintLayout;

  @NonNull
  public final TextView successDesc;

  @NonNull
  public final Button successDone;

  @NonNull
  public final ImageView successImage;

  @NonNull
  public final TextView successTitle;

  private PasswordDialogBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout layoutDialog, @NonNull ConstraintLayout successConstraintLayout,
      @NonNull TextView successDesc, @NonNull Button successDone, @NonNull ImageView successImage,
      @NonNull TextView successTitle) {
    this.rootView = rootView;
    this.layoutDialog = layoutDialog;
    this.successConstraintLayout = successConstraintLayout;
    this.successDesc = successDesc;
    this.successDone = successDone;
    this.successImage = successImage;
    this.successTitle = successTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static PasswordDialogBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static PasswordDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.password_dialog, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static PasswordDialogBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.layoutDialog;
      ConstraintLayout layoutDialog = ViewBindings.findChildViewById(rootView, id);
      if (layoutDialog == null) {
        break missingId;
      }

      ConstraintLayout successConstraintLayout = (ConstraintLayout) rootView;

      id = R.id.successDesc;
      TextView successDesc = ViewBindings.findChildViewById(rootView, id);
      if (successDesc == null) {
        break missingId;
      }

      id = R.id.successDone;
      Button successDone = ViewBindings.findChildViewById(rootView, id);
      if (successDone == null) {
        break missingId;
      }

      id = R.id.successImage;
      ImageView successImage = ViewBindings.findChildViewById(rootView, id);
      if (successImage == null) {
        break missingId;
      }

      id = R.id.successTitle;
      TextView successTitle = ViewBindings.findChildViewById(rootView, id);
      if (successTitle == null) {
        break missingId;
      }

      return new PasswordDialogBinding((ConstraintLayout) rootView, layoutDialog,
          successConstraintLayout, successDesc, successDone, successImage, successTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
