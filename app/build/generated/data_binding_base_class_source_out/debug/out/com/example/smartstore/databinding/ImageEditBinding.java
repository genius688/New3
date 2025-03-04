// Generated by view binder compiler. Do not edit!
package com.example.smartstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.smartstore.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ImageEditBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final RecyclerView horizontalRecyclerView;

  @NonNull
  public final Button imageEditReturnBtn;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final LinearLayout linearLayout3;

  @NonNull
  public final TextView pages;

  @NonNull
  public final Guideline part29;

  @NonNull
  public final ImageView sumbitBtn;

  private ImageEditBinding(@NonNull ScrollView rootView,
      @NonNull RecyclerView horizontalRecyclerView, @NonNull Button imageEditReturnBtn,
      @NonNull ImageView imageView2, @NonNull LinearLayout linearLayout3, @NonNull TextView pages,
      @NonNull Guideline part29, @NonNull ImageView sumbitBtn) {
    this.rootView = rootView;
    this.horizontalRecyclerView = horizontalRecyclerView;
    this.imageEditReturnBtn = imageEditReturnBtn;
    this.imageView2 = imageView2;
    this.linearLayout3 = linearLayout3;
    this.pages = pages;
    this.part29 = part29;
    this.sumbitBtn = sumbitBtn;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ImageEditBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ImageEditBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.image_edit, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ImageEditBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.horizontal_recycler_view;
      RecyclerView horizontalRecyclerView = ViewBindings.findChildViewById(rootView, id);
      if (horizontalRecyclerView == null) {
        break missingId;
      }

      id = R.id.image_edit_return_btn;
      Button imageEditReturnBtn = ViewBindings.findChildViewById(rootView, id);
      if (imageEditReturnBtn == null) {
        break missingId;
      }

      id = R.id.imageView2;
      ImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      id = R.id.linearLayout3;
      LinearLayout linearLayout3 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout3 == null) {
        break missingId;
      }

      id = R.id.pages;
      TextView pages = ViewBindings.findChildViewById(rootView, id);
      if (pages == null) {
        break missingId;
      }

      id = R.id.part29;
      Guideline part29 = ViewBindings.findChildViewById(rootView, id);
      if (part29 == null) {
        break missingId;
      }

      id = R.id.sumbit_btn;
      ImageView sumbitBtn = ViewBindings.findChildViewById(rootView, id);
      if (sumbitBtn == null) {
        break missingId;
      }

      return new ImageEditBinding((ScrollView) rootView, horizontalRecyclerView, imageEditReturnBtn,
          imageView2, linearLayout3, pages, part29, sumbitBtn);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
