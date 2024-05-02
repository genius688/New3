// Generated by view binder compiler. Do not edit!
package com.example.smartstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.smartstore.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class WaterfallListItemBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView commentBackground;

  @NonNull
  public final ImageView customImage;

  @NonNull
  public final ImageView imageView16;

  @NonNull
  public final Guideline l1;

  @NonNull
  public final Guideline l2;

  @NonNull
  public final Guideline l3;

  @NonNull
  public final Guideline l4;

  @NonNull
  public final Guideline l5;

  @NonNull
  public final Guideline l6;

  @NonNull
  public final Guideline l7;

  @NonNull
  public final Guideline l8;

  @NonNull
  public final TextView postLike;

  @NonNull
  public final ImageView postLikeIcon;

  @NonNull
  public final TextView tx;

  @NonNull
  public final TextView userID;

  private WaterfallListItemBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageView commentBackground, @NonNull ImageView customImage,
      @NonNull ImageView imageView16, @NonNull Guideline l1, @NonNull Guideline l2,
      @NonNull Guideline l3, @NonNull Guideline l4, @NonNull Guideline l5, @NonNull Guideline l6,
      @NonNull Guideline l7, @NonNull Guideline l8, @NonNull TextView postLike,
      @NonNull ImageView postLikeIcon, @NonNull TextView tx, @NonNull TextView userID) {
    this.rootView = rootView;
    this.commentBackground = commentBackground;
    this.customImage = customImage;
    this.imageView16 = imageView16;
    this.l1 = l1;
    this.l2 = l2;
    this.l3 = l3;
    this.l4 = l4;
    this.l5 = l5;
    this.l6 = l6;
    this.l7 = l7;
    this.l8 = l8;
    this.postLike = postLike;
    this.postLikeIcon = postLikeIcon;
    this.tx = tx;
    this.userID = userID;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static WaterfallListItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static WaterfallListItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.waterfall_list_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static WaterfallListItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.comment_background;
      ImageView commentBackground = ViewBindings.findChildViewById(rootView, id);
      if (commentBackground == null) {
        break missingId;
      }

      id = R.id.custom_image;
      ImageView customImage = ViewBindings.findChildViewById(rootView, id);
      if (customImage == null) {
        break missingId;
      }

      id = R.id.imageView16;
      ImageView imageView16 = ViewBindings.findChildViewById(rootView, id);
      if (imageView16 == null) {
        break missingId;
      }

      id = R.id.l1;
      Guideline l1 = ViewBindings.findChildViewById(rootView, id);
      if (l1 == null) {
        break missingId;
      }

      id = R.id.l2;
      Guideline l2 = ViewBindings.findChildViewById(rootView, id);
      if (l2 == null) {
        break missingId;
      }

      id = R.id.l3;
      Guideline l3 = ViewBindings.findChildViewById(rootView, id);
      if (l3 == null) {
        break missingId;
      }

      id = R.id.l4;
      Guideline l4 = ViewBindings.findChildViewById(rootView, id);
      if (l4 == null) {
        break missingId;
      }

      id = R.id.l5;
      Guideline l5 = ViewBindings.findChildViewById(rootView, id);
      if (l5 == null) {
        break missingId;
      }

      id = R.id.l6;
      Guideline l6 = ViewBindings.findChildViewById(rootView, id);
      if (l6 == null) {
        break missingId;
      }

      id = R.id.l7;
      Guideline l7 = ViewBindings.findChildViewById(rootView, id);
      if (l7 == null) {
        break missingId;
      }

      id = R.id.l8;
      Guideline l8 = ViewBindings.findChildViewById(rootView, id);
      if (l8 == null) {
        break missingId;
      }

      id = R.id.postLike;
      TextView postLike = ViewBindings.findChildViewById(rootView, id);
      if (postLike == null) {
        break missingId;
      }

      id = R.id.postLikeIcon;
      ImageView postLikeIcon = ViewBindings.findChildViewById(rootView, id);
      if (postLikeIcon == null) {
        break missingId;
      }

      id = R.id.tx;
      TextView tx = ViewBindings.findChildViewById(rootView, id);
      if (tx == null) {
        break missingId;
      }

      id = R.id.userID;
      TextView userID = ViewBindings.findChildViewById(rootView, id);
      if (userID == null) {
        break missingId;
      }

      return new WaterfallListItemBinding((ConstraintLayout) rootView, commentBackground,
          customImage, imageView16, l1, l2, l3, l4, l5, l6, l7, l8, postLike, postLikeIcon, tx,
          userID);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
