package me.nice.locationviewdemo;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.nice.view.NiceBubbleView;
import me.nice.view.NiceDotLoadingView;
import me.nice.view.NicePointerView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button start;
    private Button stop;

    private Button jump;

    private NiceDotLoadingView niceDotView;

    private NicePointerView locationView;
    private NiceBubbleView niceBubbleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        niceDotView = findViewById(R.id.niceDotView);
//        locationView = findViewById(R.id.locationView);
//        jump = findViewById(R.id.jump);
//        jump.setOnClickListener(this);
        start = findViewById(R.id.start);
        start.setOnClickListener(this);

        stop = findViewById(R.id.stop);
        stop.setOnClickListener(this);

        jump = findViewById(R.id.jump);
        jump.setOnClickListener(this);


//        niceDotView.setAnimatingColor(ContextCompat.getColor(this, R.color.res_md_green_A500));
//        niceDotView.setIndicatorColor(ContextCompat.getColor(this, R.color.res_md_green_A500));
//        niceDotView.setNormalColor(ContextCompat.getColor(this, R.color.res_md_white));
//        niceDotView.startAnim();
//        niceBubbleView = new NiceBubbleView(this);
//        locationView.setBubbleView(niceBubbleView);

//        locationView
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start) {
            niceDotView.startAnim();
//            locationView.setBubbleView(niceBubbleView);
        }

        if (v.getId() == R.id.stop) {
            niceDotView.stopAnim();
        }

//        locationView.startOutAnimation();
//        locationView.cancelWaveAnimation();
//        locationView.cancelWaveColorAnimation();
//        locationView.startWaveAnimation();
        if (v.getId() == R.id.jump) {
            Intent intent = new Intent(this, DotViewDemoActivity.class);
            startActivity(intent);
        }
    }



}
