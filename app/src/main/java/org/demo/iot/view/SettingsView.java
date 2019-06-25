package org.demo.iot.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import org.demo.iot.R;

/**
 * Created by silver on 2017/7/31.
 */

public class SettingsView extends CircleView {

    private static final int STATE_NONE = 0;
    private static final int STATE_TOP = 1;
    private static final int STATE_RIGHT = 2;
    private static final int STATE_BOTTOM = 3;
    private static final int STATE_LEFT = 4;

    private int mState;
    private float mSpace;
    private RectF mRectF;
    private Paint mPaint;

    private float mTouchDegree = 270;

    public SettingsView(Context context) {
        super(context);
        init();
    }

    public SettingsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mSpace = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32,
            getResources().getDisplayMetrics());
        mRectF = new RectF();
        mPaint = new Paint();
    }

    @Override protected void onDraw(Canvas canvas) {
        //clearCanvas(canvas);
        super.onDraw(canvas);
        drawCtrlArc(canvas);
        switch (mState) {
            case STATE_TOP:
                drawCtrlArcTop(canvas);
                break;
            case STATE_RIGHT:
                drawCtrlArcRight(canvas);
                break;
            case STATE_BOTTOM:
                drawCtrlArcBottom(canvas);
                break;
            case STATE_LEFT:
                drawCtrlArcLeft(canvas);
                break;
        }
    }

    private void clearCanvas(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }

    private void drawCtrlArc(Canvas canvas) {
        // title
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16,
            getResources().getDisplayMetrics()));
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setColor(Color.parseColor("#ffffff"));
        canvas.drawText("Settings", getWidth() / 2, getHeight() / 8, mPaint);
        // ctrl arc
        float x = getWidth() / 2;
        float y = getHeight() / 2;
        float r = Math.min(getWidth(), getHeight()) / 4;
        mRectF.set(x - r, y - r, x + r, y + r);
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#303030"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mSpace);
        canvas.drawArc(mRectF, -90, 360, false, mPaint);
        // light icon
        drawBitmap(canvas, getWidth() / 2, getHeight() / 4, R.drawable.ic_backlight, "#35a4c8");
        // wi-fi icon
        drawBitmap(canvas, getWidth() / 4 * 3, getHeight() / 2, R.drawable.ic_menu_wifi, "#35a4c8");
        // standby icon
        drawBitmap(canvas, getWidth() / 2, getHeight() / 4 * 3, R.drawable.ic_standby, "#35a4c8");
        // speaker icon
        drawBitmap(canvas, getWidth() / 4, getHeight() / 2, R.drawable.ic_speaker, "#35a4c8");
        // settings icon
        drawBitmap(canvas, getWidth() / 2, getHeight() / 2, R.drawable.ic_settings, "#ffffff");
    }

    private void drawCtrlArcTop(Canvas canvas) {
        // ctrl arc top
        float x = getWidth() / 2;
        float y = getHeight() / 2;
        float r = Math.min(getWidth(), getHeight()) / 4;
        mRectF.set(x - r, y - r, x + r, y + r);
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#35a4c8"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mSpace);
        canvas.drawArc(mRectF, -135, 90, false, mPaint);
        // light icon
        drawBitmap(canvas, getWidth() / 2, getHeight() / 4, R.drawable.ic_backlight, "#ffffff");
    }

    private void drawCtrlArcRight(Canvas canvas) {
        // ctrl arc right
        float x = getWidth() / 2;
        float y = getHeight() / 2;
        float r = Math.min(getWidth(), getHeight()) / 4;
        mRectF.set(x - r, y - r, x + r, y + r);
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#35a4c8"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mSpace);
        canvas.drawArc(mRectF, -45, 90, false, mPaint);
        // wi-fi icon
        drawBitmap(canvas, getWidth() / 4 * 3, getHeight() / 2, R.drawable.ic_menu_wifi, "#ffffff");
        // wi-fi sub icons
        float radius = Math.min(getWidth(), getHeight()) / 2 / 32 * 25;
        PointF pointF = new PointF();
        pointF.set(calculateCirclePoint(-30, radius));
        drawText(canvas, pointF.x, pointF.y, "ON", -30);
        pointF.set(calculateCirclePoint(0, radius));
        drawText(canvas, pointF.x, pointF.y, "SCAN", 0);
        pointF.set(calculateCirclePoint(30, radius));
        drawText(canvas, pointF.x, pointF.y, "OTHER", 30);
    }

    private void drawCtrlArcBottom(Canvas canvas) {
        // ctrl arc bottom
        float x = getWidth() / 2;
        float y = getHeight() / 2;
        float r = Math.min(getWidth(), getHeight()) / 4;
        mRectF.set(x - r, y - r, x + r, y + r);
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#35a4c8"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mSpace);
        canvas.drawArc(mRectF, 45, 90, false, mPaint);
        // standby icon
        drawBitmap(canvas, getWidth() / 2, getHeight() / 4 * 3, R.drawable.ic_standby, "#ffffff");
        // standby sub icons
        float radius = Math.min(getWidth(), getHeight()) / 2 / 32 * 23;
        PointF pointF = new PointF();
        pointF.set(calculateCirclePoint(60, radius));
        drawBitmap(canvas, pointF.x, pointF.y, R.drawable.ic_turbine, "#ffffff");
        pointF.set(calculateCirclePoint(90, radius));
        drawBitmap(canvas, pointF.x, pointF.y, R.drawable.ic_picture, "#ffffff");
        pointF.set(calculateCirclePoint(120, radius));
        drawBitmap(canvas, pointF.x, pointF.y, R.drawable.ic_clock, "#ffffff");
    }

    private void drawCtrlArcLeft(Canvas canvas) {
        // ctrl arc left
        float cx = getWidth() / 2;
        float cy = getHeight() / 2;
        float cr = Math.min(getWidth(), getHeight()) / 4;
        mRectF.set(cx - cr, cy - cr, cx + cr, cy + cr);
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#35a4c8"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mSpace);
        canvas.drawArc(mRectF, 135, 90, false, mPaint);
        // speaker icon
        drawBitmap(canvas, getWidth() / 4, getHeight() / 2, R.drawable.ic_speaker, "#ffffff");
        // speaker seek arc
        drawSpeakerSeeArc(canvas, mTouchDegree - 90);
    }

    private PointF calculateCirclePoint(float degree, float radius) {
        float cx = getWidth() / 2;
        float cy = getHeight() / 2;
        double radians = degree * (Math.PI / 180);
        float x = cx + (float) (radius * Math.cos(radians));
        float y = cy + (float) (radius * Math.sin(radians));
        return new PointF(x, y);
    }

    private void drawSpeakerSeeArc(Canvas canvas, float degree) {
        float cx = getWidth() / 2;
        float cy = getHeight() / 2;
        float cr = Math.min(getWidth(), getHeight()) / 4 + mSpace;
        mRectF.set(cx - cr, cy - cr, cx + cr, cy + cr);
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#ffffff"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 2,
            getResources().getDisplayMetrics()));
        if (degree < 135) {
            degree = 135;
        }
        if (degree > 225) {
            degree = 225;
        }
        // total
        canvas.drawArc(mRectF, 135, 90, false, mPaint);
        // percentage
        mPaint.setColor(Color.parseColor("#35a4c8"));
        canvas.drawArc(mRectF, 135, degree - 135, false, mPaint);
        // thumb
        PointF pointF = calculateCirclePoint(degree, cr);
        float radius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 4,
            getResources().getDisplayMetrics());
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#35a4c8"));
        canvas.drawCircle(pointF.x, pointF.y, radius, mPaint);
    }

    private void drawText(Canvas canvas, float x, float y, String text, float degree) {
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12,
            getResources().getDisplayMetrics()));
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setColor(Color.parseColor("#ffffff"));
        canvas.save();
        canvas.rotate(degree, x, y);
        canvas.drawText(text, x, y, mPaint);
        canvas.restore();
    }

    private void drawBitmap(Canvas canvas, float x, float y, int resId, String colorString) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resId);
        float w = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8,
            getResources().getDisplayMetrics());
        float h = w / bmp.getWidth() * bmp.getHeight();
        Rect src = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
        RectF dst = new RectF(x - w, y - h, x + w, y + h);
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);
        mPaint.setDither(true);
        mPaint.setColorFilter(
            new PorterDuffColorFilter(Color.parseColor(colorString), PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bmp, src, dst, mPaint);
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isTouchCtrlArc(event.getX(), event.getY())) {
                    updateState(event.getX(), event.getY());
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mState == STATE_LEFT) {
                    float degree = getTouchDegree(event.getX(), event.getY());
                    if (Math.abs(mTouchDegree - degree) < 30) {
                        mTouchDegree = degree;
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private boolean isTouchCtrlArc(float x, float y) {
        float cx = getWidth() / 2;
        float cy = getHeight() / 2;
        float minR = Math.min(getWidth(), getHeight()) / 4 - mSpace / 2;
        float maxR = Math.min(getWidth(), getHeight()) / 4 + mSpace / 2;
        double dis = Math.sqrt((x - cx) * (x - cx) + (y - cy) * (y - cy));
        return dis > minR && dis < maxR;
    }

    private void updateState(float x, float y) {
        double degree = getTouchDegree(x, y);
        if (degree > 315 || degree <= 45) {
            mState = (mState == STATE_TOP) ? STATE_NONE : STATE_TOP;
        } else if (degree > 45 && degree <= 135) {
            mState = (mState == STATE_RIGHT) ? STATE_NONE : STATE_RIGHT;
        } else if (degree > 135 && degree <= 225) {
            mState = (mState == STATE_BOTTOM) ? STATE_NONE : STATE_BOTTOM;
        } else if (degree > 225 && degree <= 315) {
            mState = (mState == STATE_LEFT) ? STATE_NONE : STATE_LEFT;
        }
    }

    private float getTouchDegree(float x, float y) {
        float cx = getWidth() / 2;
        float cy = getHeight() / 2;
        double degree = Math.toDegrees(Math.atan2(y - cy, x - cx) + (Math.PI / 2));
        if (degree < 0) {
            degree = 360 + degree;
        }
        return (float) degree;
    }
}
