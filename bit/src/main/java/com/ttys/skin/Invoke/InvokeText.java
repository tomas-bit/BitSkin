package com.ttys.skin.Invoke;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;

import com.ttys.skin.R;
import com.ttys.skin.source.SkinSource;

import java.lang.reflect.Method;

import static com.ttys.skin.source.Config.INVALID_ID;
import static com.ttys.skin.source.SkinAttr.SKIN_BACKGROUND_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_BUTTONHIT_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_BUTTON_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_DRAWABLEBOTTOM_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_DRAWABLELEFT_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_DRAWABLERIGHT_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_DRAWABLETOP_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_FONTFAMILY_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_HINT_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_STYLE_TYPEFACE_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_TEXTCOLORHIGHLIGHT_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_TEXTCOLORHINT_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_TEXTCOLORLINK_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_TEXTCOLOR_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_TEXTSIZE_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_TEXT_TYPE;

// tomas modify for text 2021 6 27
class InvokeText implements Invoke.Callbacks {

    @SuppressLint({"UseCompatLoadingForColorStateLists", "UseCompatLoadingForDrawables"})
    @Override
    public boolean parse(final View view, final String attrName, int ids, final Resources res) {
        if (null == view || null == res || ids <= 0) return false;
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
                case SKIN_BUTTON_TYPE: {
                    Class<?> claz = Class.forName("android.widget.CompoundButton");
                    Method method = claz.getDeclaredMethod("setButtonDrawable", Drawable.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        method.invoke(view, res.getDrawable(ids, view.getContext().getTheme()));
                    } else {
                        method.invoke(view, res.getDrawable(ids));
                    }
                    return true;
                }
                case SKIN_BUTTONHIT_TYPE: {
                    Class<?> claz = Class.forName("android.widget.CompoundButton");
                    Method method = claz.getDeclaredMethod("setButtonTintList", ColorStateList.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        method.invoke(view, res.getColorStateList(ids, view.getContext().getTheme()));
                    } else {
                        method.invoke(view, res.getColorStateList(ids));
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
                    TypedArray a = theme.obtainStyledAttributes(ids, R.styleable.BitTextAppearance);
                    if (a.hasValue(R.styleable.BitTextAppearance_android_text)) {
                        int id = a.getResourceId(R.styleable.BitTextAppearance_android_text, INVALID_ID);
                        int itp = SkinSource.getInstance().getId(id);
                        result = parse(view, SKIN_TEXT_TYPE, itp, res);
                    }
                    if (a.hasValue(R.styleable.BitTextAppearance_android_textSize)) {
                        int id = a.getResourceId(R.styleable.BitTextAppearance_android_textSize, INVALID_ID);
                        int itp = SkinSource.getInstance().getId(id);
                        result = parse(view, SKIN_TEXTSIZE_TYPE, itp, res);
                    }
                    if (a.hasValue(R.styleable.BitTextAppearance_android_textColor)) {
                        int id = a.getResourceId(R.styleable.BitTextAppearance_android_textColor, INVALID_ID);
                        int itp = SkinSource.getInstance().getId(id);
                        result = result || parse(view, SKIN_TEXTCOLOR_TYPE, itp, res);
                    }
                    if (a.hasValue(R.styleable.BitTextAppearance_android_textColorLink)) {
                        int id = a.getResourceId(R.styleable.BitTextAppearance_android_textColorLink, INVALID_ID);
                        int itp = SkinSource.getInstance().getId(id);
                        result = result || parse(view, SKIN_TEXTCOLORLINK_TYPE, itp, res);
                    }
                    if (a.hasValue(R.styleable.BitTextAppearance_android_textColorHint)) {
                        int id = a.getResourceId(R.styleable.BitTextAppearance_android_textColorHint, INVALID_ID);
                        int itp = SkinSource.getInstance().getId(id);
                        result = result || parse(view, SKIN_TEXTCOLORHINT_TYPE, itp, res);
                    }
                    if (a.hasValue(R.styleable.BitTextAppearance_android_fontFamily)) {
                        int id = a.getResourceId(R.styleable.BitTextAppearance_android_fontFamily, INVALID_ID);
                        int itp = SkinSource.getInstance().getId(id);
                        result = result || parse(view, SKIN_FONTFAMILY_TYPE, itp, res);
                    }
                    if (a.hasValue(R.styleable.BitTextAppearance_android_drawableTop)) {
                        int id = a.getResourceId(R.styleable.BitTextAppearance_android_drawableTop, INVALID_ID);
                        int itp = SkinSource.getInstance().getId(id);
                        result = result || parse(view, SKIN_DRAWABLETOP_TYPE, itp, res);
                    }
                    if (a.hasValue(R.styleable.BitTextAppearance_android_drawableBottom)) {
                        int id = a.getResourceId(R.styleable.BitTextAppearance_android_drawableBottom, INVALID_ID);
                        int itp = SkinSource.getInstance().getId(id);
                        result = result || parse(view, SKIN_DRAWABLEBOTTOM_TYPE, itp, res);
                    }
                    if (a.hasValue(R.styleable.BitTextAppearance_android_drawableLeft)) {
                        int id = a.getResourceId(R.styleable.BitTextAppearance_android_drawableLeft, INVALID_ID);
                        int itp = SkinSource.getInstance().getId(id);
                        result = result || parse(view, SKIN_DRAWABLELEFT_TYPE, itp, res);
                    }
                    if (a.hasValue(R.styleable.BitTextAppearance_android_drawableRight)) {
                        int id = a.getResourceId(R.styleable.BitTextAppearance_android_drawableRight, INVALID_ID);
                        int itp = SkinSource.getInstance().getId(id);
                        result = result || parse(view, SKIN_DRAWABLERIGHT_TYPE, itp, res);
                    }
                    if (a.hasValue(R.styleable.BitTextAppearance_android_button)) {
                        int id = a.getResourceId(R.styleable.BitTextAppearance_android_button, INVALID_ID);
                        int itp = SkinSource.getInstance().getId(id);
                        result = result || parse(view, SKIN_BUTTON_TYPE, itp, res);
                    }
                    if (a.hasValue(R.styleable.BitTextAppearance_android_buttonTint)) {
                        int id = a.getResourceId(R.styleable.BitTextAppearance_android_buttonTint, INVALID_ID);
                        int itp = SkinSource.getInstance().getId(id);
                        result = result || parse(view, SKIN_BUTTONHIT_TYPE, itp, res);
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
