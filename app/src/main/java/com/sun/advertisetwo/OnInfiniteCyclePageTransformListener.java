package com.sun.advertisetwo;

import android.view.View;

/**
 * Created by sun on 2017/9/27.
 */

public interface OnInfiniteCyclePageTransformListener {
    void onPreTransform(final View page, final float position);

    void onPostTransform(final View page, final float position);
}