package org.demo.iot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;

/**
 * Created by silver on 2017/7/26.
 */

public class ClockView extends CircleView {

    private float mClockScalePadding;
    private float mClockScaleLength;
    private Paint mClockScalePaint;

    private float mGradientCircleWidth;
    private Paint mGradientCirclePaint;

    private CircleView mHollowCircle;
    private CircleView mSolidCircle;

    public ClockView(Context context) {
        super(context);
        init();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        initScale();
        initGradientCircle();
        initHollowCircle();
        initSolidCircle();
    }

    private void initScale() {
        mClockScalePadding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8,
            getResources().getDisplayMetrics());
        mClockScaleLength = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8,
            getResources().getDisplayMetrics());
        float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2,
            getResources().getDisplayMetrics());
        mClockScalePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mClockScalePaint.setStrokeWidth(width);
        mClockScalePaint.setColor(Color.parseColor("#343434"));
    }

    private void initGradientCircle() {
        mGradientCircleWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8,
            getResources().getDisplayMetrics());
        mGradientCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mGradientCirclePaint.setStyle(Paint.Style.STROKE);
        mGradientCirclePaint.setStrokeWidth(mGradientCircleWidth);
    }

    private void initHollowCircle() {
        float radius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8,
            getResources().getDisplayMetrics());
        float border = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2,
            getResources().getDisplayMetrics());
        float degree = 150;
        float offset =
            getBorder() + (mClockScalePadding + mClockScaleLength + mClockScalePadding) / 2;
        double radians = degree * (Math.PI / 180);
        float x = getCenter().x + (float) ((getRadius() - offset) * Math.cos(radians));
        float y = getCenter().y + (float) ((getRadius() - offset) * Math.sin(radians));
        mHollowCircle = new CircleView(getContext());
        mHollowCircle.setCenter(x, y);
        mHollowCircle.setRadius(radius);
        mHollowCircle.setBorder(border);
        mHollowCircle.setColor("#35a4c8");
    }

    private void initSolidCircle() {
        float radius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8,
            getResources().getDisplayMetrics());
        float degree = 30;
        float offset = getBorder()
            + mClockScalePadding
            + mClockScaleLength
            + mClockScalePadding
            + mGradientCircleWidth
            + radius
            + TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4,
            getResources().getDisplayMetrics());
        double radians = degree * (Math.PI / 180);
        float x = getCenter().x + (float) ((getRadius() - offset) * Math.cos(radians));
        float y = getCenter().y + (float) ((getRadius() - offset) * Math.sin(radians));
        mSolidCircle = new CircleView(getContext());
        mSolidCircle.setCenter(x, y);
        mSolidCircle.setRadius(radius);
        mSolidCircle.setColor("#35a4c8");
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawScale(canvas);
        drawGradientCircle(canvas);
        drawHollowCircle(canvas);
        drawSolidCircle(canvas);
    }

    private void drawScale(Canvas canvas) {
        int count = 12;
        float degree = 360 / count;
        float x = getWidth() / 2;
        float y = getHeight() / 2;
        float startX = getWidth() / 2;
        float startY = getBorder() + mClockScalePadding;
        float stopX = getWidth() / 2;
        float stopY = getBorder() + mClockScalePadding + mClockScaleLength;
        for (int i = 0; i < count; i++) {
            canvas.drawLine(startX, startY, stopX, stopY, mClockScalePaint);
            canvas.rotate(degree, x, y);
        }
    }

    private void drawGradientCircle(Canvas canvas) {
        float cx = getMeasuredWidth() / 2;
        float cy = getMeasuredHeight() / 2;
        int[] colors = new int[] { Color.parseColor("#1a4856"), Color.parseColor("#35a4c8") };
        float positions[] = { 0f, 1f };
        Matrix matrix = new Matrix();
        matrix.postRotate(330, cx, cy);
        Shader shader = new SweepGradient(cx, cy, colors, positions);
        shader.setLocalMatrix(matrix);
        mGradientCirclePaint.setShader(shader);
        float p = getBorder()
            + mClockScalePadding
            + mClockScaleLength
            + mClockScalePadding
            + mGradientCircleWidth / 2;
        RectF oval = new RectF(p, p, getMeasuredWidth() - p, getMeasuredHeight() - p);
        canvas.drawArc(oval, -90, 360, false, mGradientCirclePaint);
    }

    private void drawHollowCircle(Canvas canvas) {
        mHollowCircle.draw(canvas);
    }

    private void drawSolidCircle(Canvas canvas) {
        mSolidCircle.draw(canvas);
    }
}
