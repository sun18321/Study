//package com.sun.mystudy;
//
//import android.app.Activity;
//import android.graphics.Rect;
//import android.view.View;
//import android.view.ViewTreeObserver;
//import android.widget.FrameLayout;
//
///**
// * Created by sun on 2017/9/21.
// */
//
//public class AndroidBug5497Workaround
//{
//    private Activity activity;
//    private FrameLayout.LayoutParams frameLayoutParams;
//    private View mChildOfContent;
//    private AvActivity myAvactivity;
//    private int usableHeightPrevious;
//
//    private AndroidBug5497Workaround(Activity paramActivity)
//    {
//        this.activity = paramActivity;
//        this.mChildOfContent = ((FrameLayout)this.activity.findViewById(16908290)).getChildAt(0);
//        this.mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
//        {
//            public void onGlobalLayout()
//            {
//                AndroidBug5497Workaround.this.possiblyResizeChildOfContent();
//            }
//        });
//        this.frameLayoutParams = ((FrameLayout.LayoutParams)this.mChildOfContent.getLayoutParams());
//        if ((paramActivity instanceof AvActivity))
//            this.myAvactivity = ((AvActivity)paramActivity);
//    }
//
//    public static void assistActivity(Activity paramActivity)
//    {
//        new AndroidBug5497Workaround(paramActivity);
//    }
//
//    private int computeUsableHeight()
//    {
//        Rect localRect = new Rect();
//        this.mChildOfContent.getWindowVisibleDisplayFrame(localRect);
//        return localRect.bottom - localRect.top;
//    }
//
//    private void possiblyResizeChildOfContent()
//    {
//        int i = computeUsableHeight();
//        int j;
//        int k;
//        if (i != this.usableHeightPrevious)
//        {
//            j = this.mChildOfContent.getRootView().getHeight();
//            k = j - i;
//            if (k <= j / 4)
//                break label66;
//        }
//        for (this.frameLayoutParams.height = (j - k + BarUtils.getStatusBarHeight(this.activity)); ; this.frameLayoutParams.height = j)
//        {
//            this.mChildOfContent.requestLayout();
//            this.usableHeightPrevious = i;
//            return;
//            label66: if (this.myAvactivity == null)
//                continue;
//            this.myAvactivity.closeKeyboard();
//        }
//    }
//}
