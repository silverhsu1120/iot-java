package org.demo.iot.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import org.demo.iot.R;

/**
 * Created by silver on 2017/7/26.
 */

public class CircleView extends View {

    private float mPointX;
    private float mPointY;
    private float mRadius;
    private float mBorder;
    private String mColorS;

    private RectF mRectF;
    private Paint mPaint;

    public CircleView(Context context) {
        super(context);
        init(null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(mRectF, -90, 360, false, mPaint);
    }

    public PointF getCenter() {
        return new PointF(mPointX, mPointY);
    }

    public void setCenter(float x, float y) {
        mPointX = x;
        mPointY = y;
        mRectF.set(x - mRadius, y - mRadius, x + mRadius, y + mRadius);
    }

    public float getRadius() {
        return mRadius;
    }

    public void setRadius(float radius) {
        mRadius = radius;
        mRectF.set(mPointX - radius, mPointY - radius, mPointX + radius, mPointY + radius);
    }

    public float getBorder() {
        return mBorder;
    }

    public void setBorder(float width) {
        mBorder = width;
        mPaint.setStyle(width > 0 ? Paint.Style.STROKE : Paint.Style.FILL);
        mPaint.setStrokeWidth(width);
    }

    public void setColor(String colorString) {
        mColorS = colorString;
        mPaint.setColor(Color.parseColor(colorString));
    }

    private void init(@Nullable AttributeSet attrs) {

        float defRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10,
            getResources().getDisplayMetrics());
        float defBorder = 0;
        String defColor = "#35a4c8";

        if (attrs == null) {
            mPointX = defRadius;
            mPointY = defRadius;
            mRadius = defRadius;
            mBorder = defBorder;
            mColorS = defColor;
        } else {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CircleView);
            mPointX = a.getDimension(R.styleable.CircleView_pointX, defRadius);
            mPointY = a.getDimension(R.styleable.CircleView_pointY, defRadius);
            mRadius = a.getDimension(R.styleable.CircleView_radius, defRadius);
            mBorder = a.getDimension(R.styleable.CircleView_border, defBorder);
            mColorS = a.getString(R.styleable.CircleView_colorS);
            mColorS = (mColorS == null) ? defColor : mColorS;
            a.recycle();
        }

        mRectF = new RectF(mPointX - mRadius + mBorder / 2, mPointY - mRadius + mBorder / 2,
            mPointX + mRadius - mBorder / 2, mPointY + mRadius - mBorder / 2);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor(mColorS));
        mPaint.setStyle(mBorder > 0 ? Paint.Style.STROKE : Paint.Style.FILL);
        mPaint.setStrokeWidth(mBorder);
    }
}
