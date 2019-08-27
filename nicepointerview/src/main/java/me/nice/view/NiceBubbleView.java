package me.nice.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class NiceBubbleView extends RelativeLayout {

    private View rootView;

    public NiceBubbleView(Context context) {
        this(context, null);
    }

    public NiceBubbleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        rootView = inflate(context, R.layout.nice_bubble_view, this);

    }



}
