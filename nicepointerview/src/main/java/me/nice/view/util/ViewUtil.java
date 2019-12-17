package me.nice.view.util;

import android.graphics.Bitmap;
import android.view.View;

public class ViewUtil {

    /**
     * 讲view转换成bitmap
     *
     * @param addViewContent
     * @return
     */
    public static Bitmap getViewBitmap(View addViewContent) {
        addViewContent.setDrawingCacheEnabled(true);
        addViewContent.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        addViewContent.layout(0, 0,
                addViewContent.getMeasuredWidth(),
                addViewContent.getMeasuredHeight());
        addViewContent.buildDrawingCache();
        Bitmap cacheBitmap = addViewContent.getDrawingCache();
        addViewContent.buildLayer();
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
        return bitmap;
    }
}
