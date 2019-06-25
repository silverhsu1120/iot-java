package org.demo.iot;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import jp.wasabeef.blurry.Blurry;
import org.demo.iot.view.ConsoleLayout;
import org.demo.iot.view.SettingsView;

public class HomeActivity extends AppCompatActivity implements ConsoleLayout.Events {

    private ConsoleLayout mConsoleLayout;
    private GestureDetector mGestureDetector;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        doBlur();

        mConsoleLayout = (ConsoleLayout) findViewById(R.id.console_layout);
        mConsoleLayout.setEvents(this);
        mGestureDetector = new GestureDetector(this, new HomeActivityGestureListener());
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        return mGestureDetector.onTouchEvent(event);
    }

    @Override public void onPower() {
        startActivity(new Intent().setClass(HomeActivity.this, MainActivity.class));
    }

    @Override public void onSchedule() {

    }

    @Override public void onSettings() {
        startActivity(new Intent().setClass(HomeActivity.this, SettingsActivity.class));
    }

    @Override public void onStatistics() {
        startActivity(new Intent().setClass(HomeActivity.this, StatisticsActivity.class));
    }

    @Override public void onNotification() {

    }

    private void doBlur() {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                ImageView iv = (ImageView) findViewById(R.id.iv_blur);
                Blurry.with(HomeActivity.this)
                    //.radius(25)
                    //.sampling(1)
                    //.color(Color.argb(66, 0, 255, 255))
                    .async()
                    .capture(iv)
                    .into(iv);
                iv.setVisibility(View.VISIBLE);
            }
        }, 100);
    }

    private class HomeActivityGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            //return super.onScroll(e1, e2, distanceX, distanceY);
            float threshold = 30;
            if (distanceY > threshold) {
                mConsoleLayout.animate().translationY(0).setDuration(500).start();
            }
            if (distanceY < -threshold) {
                mConsoleLayout.animate()
                    .translationY(mConsoleLayout.getHeight())
                    .setDuration(500)
                    .start();
            }
            return false;
        }
    }
}
