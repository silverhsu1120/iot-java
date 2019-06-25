package org.demo.iot.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import org.demo.iot.R;

/**
 * Created by silver on 2017/8/2.
 */

public class StatisticsView extends CircleView {

    private float mBtnSize;
    private float mTextGap;

    private RectF mRectF;
    private Paint mPaint;

    private Events mEvents;

    public interface Events {
        void onAirQuality();
        void onPowerConsumption();
        void onFilterRemains();
    }

    public StatisticsView(Context context) {
        super(context);
        init();
    }

    public StatisticsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mBtnSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32,
            getResources().getDisplayMetrics());
        mTextGap = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32,
            getResources().getDisplayMetrics());
        mRectF = new RectF();
        mPaint = new Paint();
    }

    public void setEvents(Events events) {
        mEvents = events;
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTitle(canvas);
        drawButtonLeft(canvas);
        drawButtonCenter(canvas);
        drawButtonRight(canvas);
    }

    private void drawTitle(Canvas canvas) {
        float x = getWidth() / 2;
        float y = getHeight() / 16 * 3;
        drawText(canvas, x, y, 16, "Statistics");
        // draw underline
        float pen = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2,
            getResources().getDisplayMetrics());
        float len = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36,
            getResources().getDisplayMetrics());
        float gap = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16,
            getResources().getDisplayMetrics());
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#ffffff"));
        mPaint.setStrokeWidth(pen);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(x - len / 2, y + gap, x + len / 2, y + gap, mPaint);
    }

    private void drawButtonLeft(Canvas canvas) {
        float cx = getWidth() / 4;
        float cy = getHeight() / 2;
        drawBitmap(canvas, cx, cy, R.drawable.ic_statistics_quality);
        drawText(canvas, cx, cy + mBtnSize + mTextGap, 10, "Air Quality");
    }

    private void drawButtonCenter(Canvas canvas) {
        float cx = getWidth() / 2;
        float cy = getHeight() / 2;
        drawBitmap(canvas, cx, cy, R.drawable.ic_statistics_energy);
        float offset = mTextGap / 5;
        drawText(canvas, cx, cy + mBtnSize + mTextGap - offset, 10, "Power");
        drawText(canvas, cx, cy + mBtnSize + mTextGap + offset, 10, "Consumption");
    }

    private void drawButtonRight(Canvas canvas) {
        float cx = getWidth() / 4 * 3;
        float cy = getHeight() / 2;
        drawBitmap(canvas, cx, cy, R.drawable.ic_statistics_filter);
        drawText(canvas, cx, cy + mBtnSize + mTextGap, 10, "Filter Remains");
    }

    private void drawText(Canvas canvas, float x, float y, float sp, String text) {
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
            getResources().getDisplayMetrics()));
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setColor(Color.parseColor("#ffffff"));
        canvas.drawText(text, x, y, mPaint);
    }

    private void drawBitmap(Canvas canvas, float x, float y, int resId) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resId);
        float w = mBtnSize;
        float h = mBtnSize / bmp.getWidth() * bmp.getHeight();
        Rect src = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
        RectF dst = new RectF(x - w, y - h, x + w, y + h);
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        canvas.drawBitmap(bmp, src, dst, mPaint);
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isTouchButtonLeft(event.getX(),  event.getY())) {
                    if (mEvents != null) {
                        mEvents.onAirQuality();
                    }
                } else if (isTouchButtonCenter(event.getX(),  event.getY())) {
                    if (mEvents != null) {
                        mEvents.onPowerConsumption();
                    }
                } else if (isTouchButtonRight(event.getX(),  event.getY())) {
                    if (mEvents != null) {
                        mEvents.onFilterRemains();
                    }
                }
                break;
        }
        return true;
    }

    private boolean isTouchButtonLeft(float x, float y) {
        float cx = getWidth() / 4;
        float cy = getHeight() / 2;
        double dis = Math.sqrt((x - cx) * (x - cx) + (y - cy) * (y - cy));
        return dis < mBtnSize;
    }

    private boolean isTouchButtonCenter(float x, float y) {
        float cx = getWidth() / 2;
        float cy = getHeight() / 2;
        double dis = Math.sqrt((x - cx) * (x - cx) + (y - cy) * (y - cy));
        return dis < mBtnSize;
    }

    private boolean isTouchButtonRight(float x, float y) {
        float cx = getWidth() / 4 * 3;
        float cy = getHeight() / 2;
        double dis = Math.sqrt((x - cx) * (x - cx) + (y - cy) * (y - cy));
        return dis < mBtnSize;
    }
}
