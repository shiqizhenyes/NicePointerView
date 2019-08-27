package me.nice.locationviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import me.nice.view.NiceDotView;

public class DotViewDemoActivity extends AppCompatActivity implements View.OnClickListener {



    private NiceDotView niceDotView;
    private Button startDotAnimator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dot_view_demo);

        niceDotView = findViewById(R.id.niceDotView);
        startDotAnimator = findViewById(R.id.startDotAnimator);
        startDotAnimator.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.startDotAnimator) {
            niceDotView.startColorAnimator();
        }


    }
}
