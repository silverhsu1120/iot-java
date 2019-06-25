package org.demo.iot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;
import org.demo.iot.R;

/**
 * Created by silver on 2017/7/28.
 */

public class ConsoleLayout extends LinearLayout {

    private ImageView ivCollapse;
    private ViewPager vpConsole;
    private PageIndicator piConsole;
    private Events mEvents;

    public interface Events {
        void onPower();
        void onSchedule();
        void onSettings();
        void onStatistics();
        void onNotification();
    }

    public ConsoleLayout(Context context) {
        super(context);
        init();
    }

    public ConsoleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setEvents(Events events) {
        mEvents = events;
    }

    private void init() {
        setOrientation(LinearLayout.VERTICAL);
        setBackgroundColor(Color.parseColor("#70ffffff"));
        LayoutParams lp;

        ivCollapse = new ImageView(getContext());
        ivCollapse.setImageResource(R.drawable.ic_collapse);
        lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        addView(ivCollapse, lp);

        vpConsole = new ViewPager(getContext());
        vpConsole.setAdapter(new ConsoleLayoutPagerAdapter(getContext()));
        vpConsole.addOnPageChangeListener(new ConsoleLayoutPageChangeListener());
        lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        lp.weight = 1;
        addView(vpConsole, lp);

        piConsole = new PageIndicator(getContext());
        lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        lp.weight = 1;
        addView(piConsole, lp);
    }

    private class ConsoleLayoutPagerAdapter extends PagerAdapter {

        private List<View> mPages;

        ConsoleLayoutPagerAdapter(Context context) {
            mPages = new ArrayList<>();
            mPages.add(LayoutInflater.from(context).inflate(R.layout.page_console_1, null));
            mPages.add(LayoutInflater.from(context).inflate(R.layout.page_console_2, null));
            mPages.get(0).findViewById(R.id.iv_power).setOnClickListener(new OnClickListener() {
                @Override public void onClick(View v) {
                    if (mEvents != null) {
                        mEvents.onPower();
                    }
                }
            });
            mPages.get(0).findViewById(R.id.iv_schedule).setOnClickListener(new OnClickListener() {
                @Override public void onClick(View v) {
                    if (mEvents != null) {
                        mEvents.onSchedule();
                    }
                }
            });
            mPages.get(0).findViewById(R.id.iv_settings).setOnClickListener(new OnClickListener() {
                @Override public void onClick(View v) {
                    if (mEvents != null) {
                        mEvents.onSettings();
                    }
                }
            });
            mPages.get(1).findViewById(R.id.iv_statistics).setOnClickListener(new OnClickListener() {
                @Override public void onClick(View v) {
                    if (mEvents != null) {
                        mEvents.onStatistics();
                    }
                }
            });
            mPages.get(1).findViewById(R.id.iv_notification).setOnClickListener(new OnClickListener() {
                @Override public void onClick(View v) {
                    if (mEvents != null) {
                        mEvents.onNotification();
                    }
                }
            });
        }

        @Override public int getCount() {
            return mPages.size();
        }

        @Override public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }

        @Override public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mPages.get(position));
            return mPages.get(position);
        }

        @Override public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private class ConsoleLayoutPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override public void onPageSelected(int position) {
            piConsole.setIndex(position);
            piConsole.invalidate();
        }

        @Override public void onPageScrollStateChanged(int state) {

        }
    }

    private class PageIndicator extends View {

        private final float GAP = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12,
            getResources().getDisplayMetrics());

        private final float RADIUS = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2,
            getResources().getDisplayMetrics());

        private List<CircleView> mCircleViews = new ArrayList<>();
        private int mIndex = 0;

        public PageIndicator(Context context) {
            super(context);
            init();
        }

        public PageIndicator(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        private void init() {
            for (int i = 0; i < 2; i++) {
                mCircleViews.add(new CircleView(getContext()));
            }
        }

        @Override protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            drawIndicator(canvas);
        }

        private void drawIndicator(Canvas canvas) {
            if (mCircleViews.size() > 0 && mCircleViews.size() > mIndex) {
                float shift =
                    (mCircleViews.size() % 2 == 0) ? (mCircleViews.size() / 2 - 1) * GAP + GAP / 2
                        : (mCircleViews.size() / 2) * GAP;
                float start = getWidth() / 2 - shift;
                for (int i = 0; i < mCircleViews.size(); i++) {
                    CircleView circleView = mCircleViews.get(i);
                    circleView.setCenter(start + GAP * i, getHeight() / 2);
                    circleView.setColor((mIndex == i) ? "#35a4c8" : "#ffffff");
                    circleView.setRadius(RADIUS);
                    circleView.draw(canvas);
                }
            }
        }

        private void setIndex(int index) {
            mIndex = index;
        }
    }
}
