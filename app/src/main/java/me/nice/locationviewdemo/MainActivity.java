package me.nice.locationviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.nice.view.NiceBubbleView;
import me.nice.view.NicePointerView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button start;

    private Button jump;
    private NicePointerView locationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationView = findViewById(R.id.locationView);
        jump = findViewById(R.id.jump);
        jump.setOnClickListener(this);
//        start = findViewById(R.id.start);
//        start.setOnClickListener(this);
//        NiceBubbleView niceBubbleView = new NiceBubbleView(this);
//        locationView.setBubbleView(niceBubbleView);
    }

    @Override
    public void onClick(View v) {
//        locationView.startOutAnimation();
//        locationView.cancelWaveAnimation();
//        locationView.cancelWaveColorAnimation();
//        locationView.startWaveAnimation();
        if (v.getId() == R.id.jump) {
            Intent intent = new Intent(this, DotViewDemoActivity.class);
            startActivity(intent);
        }
    }

    //    /**
//     * 讲view转换成bitmap
//     *
//     * @param addViewContent
//     * @return
//     */
//    private Bitmap getViewBitmap(View addViewContent) {
//        addViewContent.setDrawingCacheEnabled(true);
//        addViewContent.measure(
//                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//        addViewContent.layout(0, 0,
//                addViewContent.getMeasuredWidth(),
//                addViewContent.getMeasuredHeight());
//        addViewContent.buildDrawingCache();
//
//        Bitmap cacheBitmap = addViewContent.getDrawingCache();
//        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
//        return bitmap;
//    }
}
