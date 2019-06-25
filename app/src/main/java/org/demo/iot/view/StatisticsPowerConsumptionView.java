package org.demo.iot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;

/**
 * Created by silver on 2017/8/2.
 */

public class StatisticsPowerConsumptionView extends CircleView {

    private RectF mRectF;
    private Paint mPaint;

    public StatisticsPowerConsumptionView(Context context) {
        super(context);
        init();
    }

    public StatisticsPowerConsumptionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mRectF = new RectF();
        mPaint = new Paint();
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTitle(canvas);
        drawLabel(canvas);
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

    private void drawLabel(Canvas canvas) {
        float x = getWidth() / 2;
        float y = getHeight() / 8 * 7;
        drawText(canvas, x, y, 12, "Power Consumption");
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
}
