package com.ttys.skin.Invoke;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import com.ttys.skin.R;
import com.ttys.skin.source.SkinSource;

import java.lang.reflect.Method;

import static com.ttys.skin.source.Config.INVALID_ID;
import static com.ttys.skin.source.SkinAttr.SKIN_BACKGROUND_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_INDETERMINATEDRAWABLE_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_PROGRESSDRAWABLE_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_STYLE_TYPEFACE_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_THUMB_TYPE;

// tomas modify for progress
class InvokeProgress implements Invoke.Callbacks {

    @Override
    @SuppressLint("UseCompatLoadingForDrawables")
    public boolean parse(View view, String attrName, int ids, Resources res) {
        if (null == view || null == res || ids <= 0) return false;
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
                case SKIN_BACKGROUND_TYPE: {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        view.setBackground(res.getDrawable(ids, view.getContext().getTheme()));
                    } else {
                        view.setBackground(res.getDrawable(ids));
                    }
                    return true;
                }
                case SKIN_STYLE_TYPEFACE_TYPE: {
                    boolean result = false;
                    final Resources.Theme theme = view.getContext().getTheme();
                    TypedArray a = theme.obtainStyledAttributes(ids, R.styleable.BitProgressBar);
                    if (a.hasValue(R.styleable.BitProgressBar_android_progressDrawable)) {
                        int id = a.getResourceId(R.styleable.BitProgressBar_android_progressDrawable, INVALID_ID);
                        int itp = SkinSource.getInstance().getId(id);
                        result = parse(view, SKIN_PROGRESSDRAWABLE_TYPE, itp, res);
                    }
                    if (a.hasValue(R.styleable.BitProgressBar_android_indeterminateDrawable)) {
                        int id = a.getResourceId(R.styleable.BitProgressBar_android_indeterminateDrawable, INVALID_ID);
                        int itp = SkinSource.getInstance().getId(id);
                        result = parse(view, SKIN_INDETERMINATEDRAWABLE_TYPE, itp, res) || result;
                    }
                    if (a.hasValue(R.styleable.BitProgressBar_android_thumb)) {
                        int id = a.getResourceId(R.styleable.BitProgressBar_android_thumb, INVALID_ID);
                        int itp = SkinSource.getInstance().getId(id);
                        result = parse(view, SKIN_THUMB_TYPE, itp, res) || result;
                    }
                    a.recycle();

                    TypedArray a1 = theme.obtainStyledAttributes(ids, R.styleable.BitBackground);
                    if (a1.hasValue(R.styleable.BitBackground_android_background)) {
                        int id = a1.getResourceId(R.styleable.BitBackground_android_background, INVALID_ID);
                        int itp = SkinSource.getInstance().getId(id);
                        result = parse(view, SKIN_BACKGROUND_TYPE, itp, res) || result;
                    }
                    a1.recycle();
                    return result;
                }
                /*add some others case**/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
