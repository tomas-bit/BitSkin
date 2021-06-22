package com.ttys.skin.Invoke;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import java.lang.reflect.Method;

import static com.ttys.skin.source.SkinAttr.SKIN_INDETERMINATEDRAWABLE_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_PROGRESSDRAWABLE_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_THUMB_TYPE;

// tomas modify for progress
class InvokeProgress implements Invoke.Callbacks {

    @Override
    @SuppressLint("UseCompatLoadingForDrawables")
    public boolean parse(View view, String attrName, int ids, Resources res, String entry, String type) {
        if (null == view || null == res) return false;
        try {
            switch (attrName) {
                case SKIN_PROGRESSDRAWABLE_TYPE: {
                    Class<?> clazz = Class.forName("android.widget.ProgressBar");
                    Method method = clazz.getDeclaredMethod("setProgressDrawable", Drawable.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        method.invoke(view, res.getDrawable(ids, view.getContext().getTheme()));
                    } else {
                        method.invoke(view, res.getDrawable(ids));
                    }
                    return true;
                }
                case SKIN_INDETERMINATEDRAWABLE_TYPE: {
                    Class<?> clazz = Class.forName("android.widget.ProgressBar");
                    Method method = clazz.getDeclaredMethod("setIndeterminateDrawable", Drawable.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        method.invoke(view, res.getDrawable(ids, view.getContext().getTheme()));
                    } else {
                        method.invoke(view, res.getDrawable(ids));
                    }
                    return true;
                }
                case SKIN_THUMB_TYPE: {
                    Class<?> clazz = Class.forName("android.widget.AbsSeekBar");
                    Method method = clazz.getDeclaredMethod("setThumb", Drawable.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        method.invoke(view, res.getDrawable(ids, view.getContext().getTheme()));
                    } else {
                        method.invoke(view, res.getDrawable(ids));
                    }
                    return true;
                }
                /*add some others case**/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
