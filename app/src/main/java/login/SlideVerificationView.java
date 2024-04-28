package login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class SlideVerificationView extends FrameLayout {
    private ImageView Slider;
    private ImageView Track;
    public boolean isVerified = false;

    public SlideVerificationView(Context context) {
        super(context);
    }
    public SlideVerificationView(Context context, ImageView slider, ImageView track) {
        super(context);
        Slider = slider;
        Track = track;
    }

    @SuppressLint("ClickableViewAccessibility")
    public void init(SliderVerificationCallback callback) {
        Slider.setOnTouchListener(new OnTouchListener() {
            private float startX;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getRawX() - Slider.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float moveX = event.getRawX() - startX;
                        if (moveX > 0 && !isVerified && moveX <= Track.getWidth() / 1.3) {
                            Slider.setX(moveX);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        final boolean isVerifiedNow = Slider.getX() >= Track.getWidth() / 1.5;
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onSliderVerified(isVerifiedNow);
                            }
                        });
                        break;
                }
                return true;
            }
        });
    }
}