package search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import java.util.HashMap;

public class CardLinearLayout extends LinearLayout {

    private int expandIndex = -1;
    private HashMap<Integer, Bounds> boundsHashMap;
    private float carEvPercnet = 0.7f;//每个卡片的错开距离是一张卡片高度的百分比
    private float animationPercent = 0;
    private int animationDuration = 300;
    private OnAnimationListen onAnimationListen;
    private Animation animationShadowOut, animationShadowIn;

    //构造，创建卡片边缘哈希表，创建上下动画
    public CardLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        boundsHashMap = new HashMap<>();
        //淡出动画
        animationShadowOut = AnimationUtils.loadAnimation(getContext(), com.google.android.material.R.anim.abc_fade_out);
        //设置动画时长
        animationShadowOut.setDuration(animationDuration);

        animationShadowIn = AnimationUtils.loadAnimation(getContext(), com.google.android.material.R.anim.abc_fade_in);
        animationShadowIn.setDuration(animationDuration);
    }

    public Bounds getBunds(int index) {
        if (boundsHashMap.containsKey(index)) {
            return boundsHashMap.get(index);
        } else {
            Bounds bounds = new Bounds(index);
            boundsHashMap.put(index, bounds);
            return bounds;
        }
    }


    public static boolean mesured = false;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mesured) {//只需要测量一次
            return;
        }
        mesured = true;

        //获取子View的数量和根View的尺寸
        int childCount = getChildCount();

        if (childCount > 0) {
            //获取第一个子view
            View child0 = getChildAt(0);

            int sizeHeight = MeasureSpec.getSize(child0.getMeasuredHeight());

            for (int i = 0; i < childCount; i++) {
                int top = (int) (i * (sizeHeight * 0.3));
                getBunds(i).setTop(top);
                System.out.println("--------" + top);
                System.out.println("---------------");
                getBunds(i).setCurrentTop(top);
                getBunds(i).setLastCurrentTop(top);
                getBunds(i).setHeight(sizeHeight);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int sl, int st, int sr, int sb) {
        int childCount = getChildCount();
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                View view = getChildAt(i);
                int mWidth = view.getMeasuredWidth();
                int mw = MeasureSpec.getSize(mWidth);
                int l = 0, r = l + mw;//左右边界
                view.layout(l, getBunds(i).getCurrentTop(), r, getBunds(i).getCurrentTop() + getBunds(i).getHeight());
            }
        }
    }


    boolean isFirst = true;

    //计算点击卡片时所有卡片的目标位置
    public void expandItem(int willExpandIndex) {

        for (int i = 0; i < getChildCount(); i++) {
            Bounds bounds = getBunds(i);
//            上面的卡片
            if (i <= willExpandIndex) {
                //这样会造成前面的视图突然变化
                int targetTop = (int) (bounds.getTop());
                bounds.setTargetTop(targetTop);
            } else {
//             展开的下方的卡片
                if (i == (willExpandIndex + 1)) {
                    //待点击卡片的下一张
                    //将其目标高度设置为当前位置以下 半张卡片的距离
                    int targetTop = (int) (bounds.getTop() + bounds.getHeight() * carEvPercnet);
                    bounds.setTargetTop(targetTop);
                } else {
                    //上一张已经设置位置完毕，当前只需紧跟上一张即可
                    bounds.setTargetTop((int) (getBunds(i - 1).getTargetTop() + bounds.getHeight() * 0.3));
                }
            }
        }

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1.f);
        valueAnimator.setDuration(animationDuration);
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            //动画结束时调用
            public void onAnimationEnd(Animator animation) {
                for (int i = 0; i < getChildCount(); i++) {
                    Bounds bns = getBunds(i);
                    bns.setLastCurrentTop(bns.getCurrentTop());
                    //应该新增一个记住最后的位置
                    isFirst = false;
                }
            }
        });

        valueAnimator.addUpdateListener(animation -> {
            animationPercent = (float) animation.getAnimatedValue();
            //实时更新动画位置
            for (int i = 0; i < getChildCount(); i++) {
                Bounds bns = getBunds(i);
                if (isFirst) {
                    int dis = bns.getTargetTop() - bns.getTop();
                    int delta = (int) (dis * animationPercent);
                    int currentTop = bns.getTop() + delta;
                    bns.setCurrentTop(currentTop);
                } else {
                    int dis = bns.getTargetTop() - bns.getLastCurrentTop();
                    int delta = (int) (dis * animationPercent);
                    int currentTop = bns.getLastCurrentTop() + delta;
                    bns.setCurrentTop(currentTop);
                }
            }
            requestLayout();//重新测量布局
        });

        valueAnimator.start();
        this.expandIndex = willExpandIndex;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public static class OnAnimationListen {
        public void onChange(float percent, int top) {
        }
    }

}