// Generated by view binder compiler. Do not edit!
package com.example.smartstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public final class ChattingMyContentBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final CardView cardView;

  @NonNull
  public final TextView chattingMyContent;

  @NonNull
  public final ImageView chattingMySingleCard;

  @NonNull
  public final Guideline layoutLine5;

  @NonNull
  public final Guideline layoutLine6;

  @NonNull
  public final TextView postlikes;

  @NonNull
  public final ImageView postmedia;

  @NonNull
  public final TextView postname;

  @NonNull
  public final TextView postrlstime;

  private ChattingMyContentBinding(@NonNull ConstraintLayout rootView, @NonNull CardView cardView,
      @NonNull TextView chattingMyContent, @NonNull ImageView chattingMySingleCard,
      @NonNull Guideline layoutLine5, @NonNull Guideline layoutLine6, @NonNull TextView postlikes,
      @NonNull ImageView postmedia, @NonNull TextView postname, @NonNull TextView postrlstime) {
    this.rootView = rootView;
    this.cardView = cardView;
    this.chattingMyContent = chattingMyContent;
    this.chattingMySingleCard = chattingMySingleCard;
    this.layoutLine5 = layoutLine5;
    this.layoutLine6 = layoutLine6;
    this.postlikes = postlikes;
    this.postmedia = postmedia;
    this.postname = postname;
    this.postrlstime = postrlstime;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ChattingMyContentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ChattingMyContentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.chatting_my_content, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ChattingMyContentBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cardView;
      CardView cardView = ViewBindings.findChildViewById(rootView, id);
      if (cardView == null) {
        break missingId;
      }

      id = R.id.chatting_my_content;
      TextView chattingMyContent = ViewBindings.findChildViewById(rootView, id);
      if (chattingMyContent == null) {
        break missingId;
      }

      id = R.id.chatting_my_single_card;
      ImageView chattingMySingleCard = ViewBindings.findChildViewById(rootView, id);
      if (chattingMySingleCard == null) {
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

      id = R.id.postlikes;
      TextView postlikes = ViewBindings.findChildViewById(rootView, id);
      if (postlikes == null) {
        break missingId;
      }

      id = R.id.postmedia;
      ImageView postmedia = ViewBindings.findChildViewById(rootView, id);
      if (postmedia == null) {
        break missingId;
      }

      id = R.id.postname;
      TextView postname = ViewBindings.findChildViewById(rootView, id);
      if (postname == null) {
        break missingId;
      }

      id = R.id.postrlstime;
      TextView postrlstime = ViewBindings.findChildViewById(rootView, id);
      if (postrlstime == null) {
        break missingId;
      }

      return new ChattingMyContentBinding((ConstraintLayout) rootView, cardView, chattingMyContent,
          chattingMySingleCard, layoutLine5, layoutLine6, postlikes, postmedia, postname,
          postrlstime);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
