package org.demo.iot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.demo.iot.view.StatisticsView;

public class StatisticsActivity extends AppCompatActivity implements StatisticsView.Events {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        ((StatisticsView) findViewById(R.id.statistics_view)).setEvents(this);
    }

    @Override public void onAirQuality() {
        Intent intent = new Intent();
        intent.setClass(StatisticsActivity.this, StatisticsAirQualityActivity.class);
        startActivity(intent);
    }

    @Override public void onPowerConsumption() {
        Intent intent = new Intent();
        intent.setClass(StatisticsActivity.this, StatisticsPowerConsumptionActivity.class);
        startActivity(intent);
    }

    @Override public void onFilterRemains() {
        Intent intent = new Intent();
        intent.setClass(StatisticsActivity.this, StatisticsFilterRemainsActivity.class);
        startActivity(intent);
    }
}
