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
import static com.ttys.skin.source.SkinAttr.SKIN_DRAWABLEALPHA_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_SETSCALETYPE_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_SRC_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_STYLE_TYPEFACE_TYPE;

// tomas modify for img
@SuppressLint("UseCompatLoadingForDrawables")
class InvokeImg implements Invoke.Callbacks {

    @Override
    public boolean parse(View view, String attrName, int ids, Resources res) {
        if (null == view || null == res || ids <= 0) return false;
        try {
            Class<?> clazz = Class.forName("android.widget.ImageView");
            switch (attrName) {
                case SKIN_SRC_TYPE: {
                    Method method = clazz.getDeclaredMethod("setImageDrawable", Drawable.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        method.invoke(view, res.getDrawable(ids, view.getContext().getTheme()));
                    } else {
                        method.invoke(view, res.getDrawable(ids));
                    }
                    return true;
                }
                case SKIN_DRAWABLEALPHA_TYPE: {
                    Method method = clazz.getDeclaredMethod("setImageAlpha", Integer.TYPE);
                    method.invoke(view, res.getInteger(ids));
                    return true;
                }
                case SKIN_SETSCALETYPE_TYPE: {//no check todo
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
                    TypedArray a = theme.obtainStyledAttributes(ids, R.styleable.BitImageView);
                    if (a.hasValue(R.styleable.BitImageView_android_src)) {
                        int id = a.getResourceId(R.styleable.BitImageView_android_src, INVALID_ID);
                        int itp = SkinSource.getInstance().getId(id);
                        result = parse(view, SKIN_SRC_TYPE, itp, res);
                    }
                    if (a.hasValue(R.styleable.BitImageView_android_alpha)) {
                        int id = a.getResourceId(R.styleable.BitImageView_android_alpha, INVALID_ID);
                        int itp = SkinSource.getInstance().getId(id);
                        result = result || parse(view, SKIN_DRAWABLEALPHA_TYPE, itp, res);
                    }
                    a.recycle();

                    TypedArray a1 = theme.obtainStyledAttributes(ids, R.styleable.BitBackground);
                    if (a1.hasValue(R.styleable.BitBackground_android_background)) {
                        int id = a1.getResourceId(R.styleable.BitBackground_android_background, INVALID_ID);
                        int itp = SkinSource.getInstance().getId(id);
                        result = result || parse(view, SKIN_BACKGROUND_TYPE, itp, res);
                    }
                    a1.recycle();
                    return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
