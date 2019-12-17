package me.nice.locationviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import me.nice.view.NiceDotLoadingView;
import me.nice.view.NicePointerView;


public class DotViewDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private NicePointerView nicePointerView;

    private Button startJumpAnimator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dot_view_demo);
        nicePointerView = findViewById(R.id.nicePointerView);
        startJumpAnimator = findViewById(R.id.jumpAnimator);
        startJumpAnimator.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.jumpAnimator) {
            nicePointerView.startJumpDiDiStyleAnimation();
        }


    }
}
