package org.demo.iot;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import java.util.ArrayList;
import java.util.List;

public class StatisticsFilterRemainsActivity extends AppCompatActivity {

    private List<Entry> mEntries;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_filter_remains);

        initData();
        drawLineChart();
    }

    private void initData() {
        mEntries = new ArrayList<>();
        mEntries.add(new Entry(10, 68));
        mEntries.add(new Entry(11, 62));
        mEntries.add(new Entry(12, 61));
        mEntries.add(new Entry(13, 55));
        mEntries.add(new Entry(14, 52));
        mEntries.add(new Entry(15, 50));
        mEntries.add(new Entry(16, 46));
        mEntries.add(new Entry(17, 42));
        mEntries.add(new Entry(18, 40));
        mEntries.add(new Entry(19, 39));
    }

    private void drawLineChart() {
        LineChart lineChart = (LineChart) findViewById(R.id.line_chart);

        LineDataSet lineDataSet = new LineDataSet(mEntries, "");
        lineDataSet.setColor(Color.parseColor("#813d9b")); //折線顏色
        lineDataSet.setCircleColor(Color.parseColor("#813d9b")); //折線峰值外圈顏色
        lineDataSet.setCircleColorHole(Color.parseColor("#813d9b"));//折線峰值內圈顏色
        lineDataSet.setDrawValues(false); //顯示折線峰值文字
        lineDataSet.setDrawFilled(true); //支持折線下方填滿
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.bg_gradient_purple);
        lineDataSet.setFillDrawable(drawable); //填滿折線下方

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(lineDataSet);
        LineData lineData = new LineData(dataSets);

        lineChart.getDescription().setText(""); //去除描述標籤
        lineChart.setScaleEnabled(false); //縮放圖表功能
        lineChart.setTouchEnabled(false); //點擊圖表功能

        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM); //X軸置底
        lineChart.getXAxis().setAxisLineColor(Color.parseColor("#505050")); //X軸顏色
        lineChart.getXAxis().setGridColor(Color.TRANSPARENT); //直格線顏色
        lineChart.getXAxis().setTextColor(Color.parseColor("#ffc7c7cc")); //X軸文字顏色
        lineChart.getXAxis().setTextSize(10); //X軸文字大小
        lineChart.getXAxis().setSpaceMin(1); //X軸左邊空間
        lineChart.getXAxis().setSpaceMax(1); //X軸右邊空間

        lineChart.getAxisLeft().setTextColor(Color.parseColor("#ffc7c7cc")); //左側文字顏色
        lineChart.getAxisLeft().setTextSize(10); //左側文字大小
        lineChart.getAxisLeft().setSpaceTop(50); //上線比例
        lineChart.getAxisLeft().setSpaceBottom(50); //下限比例
        lineChart.getAxisRight().setAxisLineColor(Color.TRANSPARENT); //右邊框顏色
        lineChart.getAxisRight().setTextColor(Color.TRANSPARENT); //右側文字顏色
        lineChart.getAxisRight().setGridColor(Color.TRANSPARENT); //橫格線顏色
        lineChart.getAxisRight().setGridLineWidth(1.0f); //橫格線寬度

        lineChart.setData(lineData);
        lineChart.animateY(1000);

        lineChart.getLegend().setEnabled(false); //格式設定
    }
}
