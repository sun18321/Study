package com.sun.advertise;

import android.content.Context;
import android.view.View;

import java.io.Serializable;

/**
 * Created by sun on 2017/9/27.
 */

public interface CardHandler<T> extends Serializable {

    View onBind(Context context, T data, int position, @CardViewPager.TransformerMode int mode);
}
