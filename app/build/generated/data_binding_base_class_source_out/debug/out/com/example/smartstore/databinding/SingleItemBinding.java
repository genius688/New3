// Generated by view binder compiler. Do not edit!
package com.example.smartstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.smartstore.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class SingleItemBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ImageView itFav;

  @NonNull
  public final TextView itemDeadline;

  @NonNull
  public final TextView itemDisciption;

  @NonNull
  public final TextView itemtitle;

  @NonNull
  public final ImageView starObject1;

  @NonNull
  public final CardView starObjectCard1;

  private SingleItemBinding(@NonNull RelativeLayout rootView, @NonNull ImageView itFav,
      @NonNull TextView itemDeadline, @NonNull TextView itemDisciption, @NonNull TextView itemtitle,
      @NonNull ImageView starObject1, @NonNull CardView starObjectCard1) {
    this.rootView = rootView;
    this.itFav = itFav;
    this.itemDeadline = itemDeadline;
    this.itemDisciption = itemDisciption;
    this.itemtitle = itemtitle;
    this.starObject1 = starObject1;
    this.starObjectCard1 = starObjectCard1;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static SingleItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static SingleItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.single_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static SingleItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.it_fav;
      ImageView itFav = ViewBindings.findChildViewById(rootView, id);
      if (itFav == null) {
        break missingId;
      }

      id = R.id.item_deadline;
      TextView itemDeadline = ViewBindings.findChildViewById(rootView, id);
      if (itemDeadline == null) {
        break missingId;
      }

      id = R.id.item_disciption;
      TextView itemDisciption = ViewBindings.findChildViewById(rootView, id);
      if (itemDisciption == null) {
        break missingId;
      }

      id = R.id.itemtitle;
      TextView itemtitle = ViewBindings.findChildViewById(rootView, id);
      if (itemtitle == null) {
        break missingId;
      }

      id = R.id.star_object_1;
      ImageView starObject1 = ViewBindings.findChildViewById(rootView, id);
      if (starObject1 == null) {
        break missingId;
      }

      id = R.id.star_object_card_1;
      CardView starObjectCard1 = ViewBindings.findChildViewById(rootView, id);
      if (starObjectCard1 == null) {
        break missingId;
      }

      return new SingleItemBinding((RelativeLayout) rootView, itFav, itemDeadline, itemDisciption,
          itemtitle, starObject1, starObjectCard1);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
