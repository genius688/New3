package search;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

public class InfiniteScrollLayoutManager extends LinearLayoutManager {

    private int extraItemCount;
    private boolean isAttachedToWindow;

    public InfiniteScrollLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

}