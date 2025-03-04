// Generated by view binder compiler. Do not edit!
package com.example.smartstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.smartstore.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class RoomItemBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final LinearLayout part24;

  @NonNull
  public final TextView roomName;

  @NonNull
  public final LinearLayout stgs;

  private RoomItemBinding(@NonNull ConstraintLayout rootView, @NonNull LinearLayout part24,
      @NonNull TextView roomName, @NonNull LinearLayout stgs) {
    this.rootView = rootView;
    this.part24 = part24;
    this.roomName = roomName;
    this.stgs = stgs;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static RoomItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static RoomItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.room_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static RoomItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.part_24;
      LinearLayout part24 = ViewBindings.findChildViewById(rootView, id);
      if (part24 == null) {
        break missingId;
      }

      id = R.id.room_name;
      TextView roomName = ViewBindings.findChildViewById(rootView, id);
      if (roomName == null) {
        break missingId;
      }

      id = R.id.stgs;
      LinearLayout stgs = ViewBindings.findChildViewById(rootView, id);
      if (stgs == null) {
        break missingId;
      }

      return new RoomItemBinding((ConstraintLayout) rootView, part24, roomName, stgs);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
