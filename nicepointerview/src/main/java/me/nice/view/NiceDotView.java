package me.nice.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import me.nice.view.util.DpUtil;

public class NiceDotView extends View {


    private final String TAG =  NiceDotView.class.getSimpleName();


    private TypedArray typedArray;
    private Context context;

    public NiceDotView(Context context) {
        this(context, null);
    }

    public NiceDotView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NiceDotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.NiceDotView,
                defStyleAttr, 0);
        init(typedArray);
        initPaint();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NiceDotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.NiceDotView,
                defStyleAttr, defStyleRes);
        init(typedArray);
        initPaint();
    }

    private int DEFAULT_COUNT = 3;

    private int dotRadius;
    private int dotColor;
    private int dotAnimationColor;
    private int dotCount;
    private int dotSpace;

    private void init(TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.NiceDotView_dotRadius) {
                dotRadius = typedArray.getDimensionPixelSize(i, dotRadius);
            } else if (attr == R.styleable.NiceDotView_dotColor) {
                dotColor = typedArray.getColor(i,
                        ContextCompat.getColor(context, R.color.nv_colorPrimary));
            } else if (attr == R.styleable.NiceDotView_dotAnimationColor) {
                dotAnimationColor = typedArray.getColor(i, ContextCompat.getColor(context,
                        R.color.nv_colorAccent));
            } else if (attr == R.styleable.NiceDotView_dotCount) {
                dotCount = typedArray.getInteger(i, DEFAULT_COUNT);
            } else if (attr == R.styleable.NiceDotView_dotSpace) {
                dotSpace = typedArray.getDimensionPixelSize(i, context.getResources()
                        .getDimensionPixelSize(R.dimen.nv_dp_5));
            }
        }
    }

    private List<Paint> paints;

    private void initPaint() {
        paints = new ArrayList<>();
        for (int i = 0; i < dotCount; i++) {
            Paint paint = new Paint();
            paint.setColor(dotColor);
            paint.setStyle(Paint.Style.FILL);
            paints.add(paint);
        }
    }


    private int viewHeight;
    private int viewWidth;


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        w = (dotRadius * 2) * dotCount + dotSpace * (dotCount - 1)
                + getPaddingLeft() + getPaddingRight();
        h = dotRadius * 2 + getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(w, h);
        viewWidth = w;
        viewHeight = h;
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int withMode = MeasureSpec.getMode(widthMeasureSpec);
        int withSpecSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (withMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            width = (dotRadius * 2) * dotCount + dotSpace * (dotCount - 1)
                    + getPaddingLeft() + getPaddingRight();

            height = dotRadius * 2 + getPaddingTop() + getPaddingBottom();

        }else if (withMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.EXACTLY){
            height = heightSpecSize;
            width = (dotRadius * 2) * dotCount + dotSpace * (dotCount - 1)
                    + getPaddingLeft() + getPaddingRight();
        }else if (heightMode == MeasureSpec.AT_MOST && withMode == MeasureSpec.EXACTLY) {
            width = withSpecSize;
            height = dotRadius * 2 + getPaddingTop() + getPaddingBottom();
        }else {
            width = withSpecSize;
            height = heightSpecSize;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (dotCount < 2) {
            canvas.drawCircle((getPaddingLeft() + dotRadius * 2 + getPaddingRight()) >> 1,
                    viewHeight >> 1, dotRadius, paints.get(0));
        } else {

            canvas.drawCircle((getPaddingLeft() + dotRadius * 2 + getPaddingRight()) >> 1,
                    viewHeight >> 1, dotRadius, paints.get(0));

            for (int i = 1; i < dotCount; i++) {

                canvas.drawCircle(getPaddingLeft() + (dotRadius * 2 + dotSpace) * (i) + dotRadius,
                        viewHeight >>1, dotRadius, paints.get(i));

            }

        }
    }

    private static class ColorHandler extends Handler {

        private WeakReference<NiceDotView> dotViewWeakReference;
        private int dotCount;
        private List<Paint> paints;
        private int i;
        private int dotAnimationColor;
        private int dotColor;

        ColorHandler(NiceDotView niceDotView) {
            dotViewWeakReference = new WeakReference<>(niceDotView);
            if (dotViewWeakReference.get() != null) {
                dotCount = dotViewWeakReference.get().dotCount;
                paints = dotViewWeakReference.get().paints;
                dotColor= dotViewWeakReference.get().dotColor;
                dotAnimationColor = dotViewWeakReference.get().dotAnimationColor;
            }
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x11) {
                if ( i < dotCount) {

                    if (i == 0) {
                        paints.get(i).setColor(dotAnimationColor);
                    }else if (i > 0) {
                        paints.get(i -1).setColor(dotColor);
                        paints.get(i).setColor(dotAnimationColor);
                    }
                    if (i == (dotCount - 1)) {
                        paints.get(0).setColor(dotAnimationColor);
                    }

                    if (dotViewWeakReference.get() != null) {
                        dotViewWeakReference.get().invalidate();
                    }

                    i++;
                } else {
                    i = 0;
                    paints.get(dotCount - 1).setColor(dotColor);
//                    paints.get(0).setColor(dotAnimationColor);
                    if (dotViewWeakReference.get() != null) {
                        dotViewWeakReference.get().invalidate();
                    }
                }
                colorHandler.sendEmptyMessageDelayed(0x11, 100);
            }
        }
    }

    private static ColorHandler colorHandler;

    public void startColorAnimator() {
        cancelColorAnimator();
        colorHandler = new ColorHandler(this);
        colorHandler.sendEmptyMessageDelayed(0x11, 100);
    }

    public void cancelColorAnimator() {
        if (colorHandler != null) {
            colorHandler.removeMessages(0x11);
        }
    }


}
