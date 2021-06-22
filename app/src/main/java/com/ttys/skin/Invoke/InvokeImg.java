package com.ttys.skin.Invoke;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import java.lang.reflect.Method;

import static com.ttys.skin.source.SkinAttr.SKIN_DRAWABLEALPHA_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_SETSCALETYPE_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_SRC_TYPE;

// tomas modify for img
@SuppressLint("UseCompatLoadingForDrawables")
class InvokeImg implements Invoke.Callbacks {

    @Override
    public boolean parse(View view, String attrName, int ids, Resources res, String entry, String type) {
        if (null == view || null == res) return false;
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
                    Method method = clazz.getDeclaredMethod("setScaleType", ImageView.ScaleType.class);
                    method.invoke(view, res.getInteger(ids));
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
