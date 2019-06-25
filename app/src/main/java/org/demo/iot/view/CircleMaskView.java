package org.demo.iot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by silver on 2017/7/28.
 */

public class CircleMaskView extends View {

    private Paint mPaint;
    private Path mPath;

    public CircleMaskView(Context context) {
        super(context);
        init();
    }

    public CircleMaskView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPath = new Path();
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircleMask(canvas);
    }

    private void drawCircleMask(Canvas canvas) {
        float radius = Math.min(getWidth(), getHeight()) / 2;
        mPaint.setColor(Color.parseColor("#000000"));
        mPath.reset();
        mPath.setFillType(Path.FillType.EVEN_ODD);
        mPath.addRect(0, 0, getWidth(), getHeight(), Path.Direction.CW);
        mPath.addCircle(radius, radius, radius, Path.Direction.CW);
        canvas.drawPath(mPath, mPaint);
    }
}
