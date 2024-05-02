// Generated by view binder compiler. Do not edit!
package com.example.smartstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.smartstore.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class SearchDialogBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView Title;

  @NonNull
  public final LinearLayout content;

  @NonNull
  public final Guideline layoutLine5;

  @NonNull
  public final Guideline layoutLine6;

  @NonNull
  public final Guideline layoutLine7;

  @NonNull
  public final Guideline layoutLine8;

  @NonNull
  public final Guideline layoutLine9;

  @NonNull
  public final ConstraintLayout parentLayout;

  @NonNull
  public final TextView returnB;

  @NonNull
  public final ImageView selectCard;

  @NonNull
  public final CardView yesBtn;

  private SearchDialogBinding(@NonNull ConstraintLayout rootView, @NonNull TextView Title,
      @NonNull LinearLayout content, @NonNull Guideline layoutLine5, @NonNull Guideline layoutLine6,
      @NonNull Guideline layoutLine7, @NonNull Guideline layoutLine8,
      @NonNull Guideline layoutLine9, @NonNull ConstraintLayout parentLayout,
      @NonNull TextView returnB, @NonNull ImageView selectCard, @NonNull CardView yesBtn) {
    this.rootView = rootView;
    this.Title = Title;
    this.content = content;
    this.layoutLine5 = layoutLine5;
    this.layoutLine6 = layoutLine6;
    this.layoutLine7 = layoutLine7;
    this.layoutLine8 = layoutLine8;
    this.layoutLine9 = layoutLine9;
    this.parentLayout = parentLayout;
    this.returnB = returnB;
    this.selectCard = selectCard;
    this.yesBtn = yesBtn;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static SearchDialogBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static SearchDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.search_dialog, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static SearchDialogBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Title;
      TextView Title = ViewBindings.findChildViewById(rootView, id);
      if (Title == null) {
        break missingId;
      }

      id = R.id.content;
      LinearLayout content = ViewBindings.findChildViewById(rootView, id);
      if (content == null) {
        break missingId;
      }

      id = R.id.layout_line_5;
      Guideline layoutLine5 = ViewBindings.findChildViewById(rootView, id);
      if (layoutLine5 == null) {
        break missingId;
      }

      id = R.id.layout_line_6;
      Guideline layoutLine6 = ViewBindings.findChildViewById(rootView, id);
      if (layoutLine6 == null) {
        break missingId;
      }

      id = R.id.layout_line_7;
      Guideline layoutLine7 = ViewBindings.findChildViewById(rootView, id);
      if (layoutLine7 == null) {
        break missingId;
      }

      id = R.id.layout_line_8;
      Guideline layoutLine8 = ViewBindings.findChildViewById(rootView, id);
      if (layoutLine8 == null) {
        break missingId;
      }

      id = R.id.layout_line_9;
      Guideline layoutLine9 = ViewBindings.findChildViewById(rootView, id);
      if (layoutLine9 == null) {
        break missingId;
      }

      ConstraintLayout parentLayout = (ConstraintLayout) rootView;

      id = R.id.returnB;
      TextView returnB = ViewBindings.findChildViewById(rootView, id);
      if (returnB == null) {
        break missingId;
      }

      id = R.id.select_card;
      ImageView selectCard = ViewBindings.findChildViewById(rootView, id);
      if (selectCard == null) {
        break missingId;
      }

      id = R.id.yes_btn;
      CardView yesBtn = ViewBindings.findChildViewById(rootView, id);
      if (yesBtn == null) {
        break missingId;
      }

      return new SearchDialogBinding((ConstraintLayout) rootView, Title, content, layoutLine5,
          layoutLine6, layoutLine7, layoutLine8, layoutLine9, parentLayout, returnB, selectCard,
          yesBtn);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
