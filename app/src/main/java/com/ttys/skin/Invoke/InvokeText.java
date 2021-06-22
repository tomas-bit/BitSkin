package com.ttys.skin.Invoke;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;

import java.lang.reflect.Method;

import static com.ttys.skin.source.SkinAttr.SKIN_DRAWABLEBOTTOM_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_DRAWABLELEFT_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_DRAWABLERIGHT_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_DRAWABLETOP_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_FONTFAMILY_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_HINT_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_TEXTCOLORHIGHLIGHT_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_TEXTCOLORHINT_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_TEXTCOLORLINK_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_TEXTCOLOR_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_TEXTSIZE_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_TEXT_TYPE;

// tomas modify for text
class InvokeText implements Invoke.Callbacks {

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public boolean parse(View view, String attrName, int ids, Resources res, String entry, String type) {
        if (null == view || null == res) return false;
        try {
            Class<?> clazz = Class.forName("android.widget.TextView");
            switch (attrName) {
                case SKIN_TEXT_TYPE: {
                    Method method = clazz.getDeclaredMethod("setText", CharSequence.class);
                    method.invoke(view, res.getString(ids));
                    return true;
                }
                case SKIN_HINT_TYPE: {
                    Method method = clazz.getDeclaredMethod("setHint", CharSequence.class);
                    method.invoke(view, res.getString(ids));
                    return true;
                }
                case SKIN_TEXTCOLOR_TYPE: {
                    Method method = clazz.getDeclaredMethod("setTextColor", Integer.TYPE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        method.invoke(view, res.getColor(ids, view.getContext().getTheme()));
                    } else {
                        method.invoke(view, res.getColor(ids));
                    }
                    return true;
                }
                case SKIN_TEXTCOLORHIGHLIGHT_TYPE: {
                    Method method = clazz.getDeclaredMethod("setHighlightColor", Integer.TYPE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        method.invoke(view, res.getColor(ids, view.getContext().getTheme()));
                    } else {
                        method.invoke(view, res.getColor(ids));
                    }
                    return true;
                }
                case SKIN_TEXTCOLORHINT_TYPE: {
                    Method method = clazz.getDeclaredMethod("setHintTextColor", Integer.TYPE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        method.invoke(view, res.getColor(ids, view.getContext().getTheme()));
                    } else {
                        method.invoke(view, res.getColor(ids));
                    }
                    return true;
                }
                case SKIN_TEXTSIZE_TYPE: {
                    Method method = clazz.getDeclaredMethod("setTextSize", Integer.TYPE, Float.TYPE);
                    res.getDimension(ids);
                    method.invoke(view, TypedValue.COMPLEX_UNIT_PX, res.getDimension(ids));
                    return true;
                }
                case SKIN_TEXTCOLORLINK_TYPE: {
                    Method method = clazz.getDeclaredMethod("setLinkTextColor", Integer.TYPE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        method.invoke(view, res.getColor(ids, view.getContext().getTheme()));
                    } else {
                        method.invoke(view, res.getColor(ids));
                    }
                    return true;
                }
                case SKIN_FONTFAMILY_TYPE: {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        Method method = clazz.getDeclaredMethod("setTypeface", Typeface.class);
                        method.invoke(view, res.getFont(ids));
                        return true;
                    }
                }
                case SKIN_DRAWABLEBOTTOM_TYPE: {
                    Method method = clazz.getDeclaredMethod("setCompoundDrawablesWithIntrinsicBounds", Drawable.class,
                            Drawable.class, Drawable.class, Drawable.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        method.invoke(view, null, null, null, res.getDrawable(ids, view.getContext().getTheme()));
                    } else {
                        method.invoke(view, null, null, null, res.getDrawable(ids));
                    }
                    return true;
                }
                case SKIN_DRAWABLELEFT_TYPE: {
                    Method method = clazz.getDeclaredMethod("setCompoundDrawablesWithIntrinsicBounds", Drawable.class,
                            Drawable.class, Drawable.class, Drawable.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        method.invoke(view, res.getDrawable(ids, view.getContext().getTheme()), null, null, null);
                    } else {
                        method.invoke(view, res.getDrawable(ids), null, null, null);
                    }
                    return true;
                }
                case SKIN_DRAWABLETOP_TYPE: {
                    Method method = clazz.getDeclaredMethod("setCompoundDrawablesWithIntrinsicBounds", Drawable.class,
                            Drawable.class, Drawable.class, Drawable.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        method.invoke(view, null, res.getDrawable(ids, view.getContext().getTheme()), null, null);
                    } else {
                        method.invoke(view, null, res.getDrawable(ids), null, null);
                    }
                    return true;
                }
                case SKIN_DRAWABLERIGHT_TYPE: {
                    Method method = clazz.getDeclaredMethod("setCompoundDrawablesWithIntrinsicBounds", Drawable.class,
                            Drawable.class, Drawable.class, Drawable.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        method.invoke(view, null, null, res.getDrawable(ids, view.getContext().getTheme()), null);
                    } else {
                        method.invoke(view, null, null, res.getDrawable(ids), null);
                    }
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
