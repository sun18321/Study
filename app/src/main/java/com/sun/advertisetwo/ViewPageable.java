package com.sun.advertisetwo;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by sun on 2017/9/27.
 */

interface ViewPageable {

    boolean hasWindowFocus();
    int getCurrentItem();
    int getChildCount();
    View getChildAt(final int index);
    PagerAdapter getAdapter();

    boolean post(final Runnable runnable);

    void addOnPageChangeListener(final ViewPager.OnPageChangeListener onPageChangeListener);
    void setClipChildren(final boolean clipChildren);
    void setDrawingCacheEnabled(final boolean drawingCacheEnabled);
    void setWillNotCacheDrawing(final boolean willNotCacheDrawing);
    void setOverScrollMode(final int overScrollMode);
    void setCurrentItem(final int item);
    void setPageTransformer(final boolean reverseDrawingOrder, final ViewPager.PageTransformer transformer);
    void setPageMargin(final int pageMargin);
    void setOffscreenPageLimit(final int offscreenPageLimit);

    boolean isFakeDragging();
    boolean beginFakeDrag();
    void fakeDragBy(final float dragBy);
    void endFakeDrag();

}
