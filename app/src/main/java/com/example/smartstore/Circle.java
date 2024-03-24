package com.example.smartstore;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

public class Circle extends androidx.appcompat.widget.AppCompatImageView {

    //画笔
    private Paint mPaint;
    //圆形图片的半径
    private int mRadius;
    //图片的宿放比例
    private float mScale;

    public Circle(Context context) {
        super(context);
    }

    public Circle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Circle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //由于是圆形，宽高应保持一致
        int size = Math.min(getMeasuredWidth(), getMeasuredHeight());
        mRadius = size / 2;
        setMeasuredDimension(size, size);
    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        // 初始化画笔用于绘制边框
        Paint borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setColor(0xff65C9B9); // 设置边框颜色
        borderPaint.setStrokeWidth(15); // 设置边框宽度
        borderPaint.setStyle(Paint.Style.STROKE); // 设置为描边模式

        // 初始化画笔用于绘制圆形图片
        Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setStyle(Paint.Style.FILL); // 设置为填充模式

        Drawable drawable = getDrawable();
        if (null != drawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

            // 计算缩放比例，确保图片完全填充圆形
            float scale = Math.min(1.0f * mRadius * 2 / bitmap.getWidth(), 1.0f * mRadius * 2 / bitmap.getHeight());

            // 创建BitmapShader并设置其本地矩阵
            BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            Matrix matrix = new Matrix();
            matrix.setScale(scale, scale);
            matrix.postTranslate(mRadius - (bitmap.getWidth() * scale) / 2, mRadius - (bitmap.getHeight() * scale) / 2);
            bitmapShader.setLocalMatrix(matrix);
            circlePaint.setShader(bitmapShader);

            // 先绘制带有边框的圆形背景（如果需要的话）
            canvas.drawCircle(mRadius, mRadius, mRadius - borderPaint.getStrokeWidth() / 2, borderPaint);

            // 再绘制圆形图片
            canvas.drawCircle(mRadius, mRadius, mRadius - borderPaint.getStrokeWidth() / 2, circlePaint);


        } else {
            super.onDraw(canvas);
        }
    }
}