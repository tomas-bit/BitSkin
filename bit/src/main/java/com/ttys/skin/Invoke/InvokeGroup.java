package com.ttys.skin.Invoke;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.view.View;

import com.ttys.skin.R;
import com.ttys.skin.source.SkinSource;

import static com.ttys.skin.source.Config.INVALID_ID;
import static com.ttys.skin.source.SkinAttr.SKIN_BACKGROUND_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_STYLE_TYPEFACE_TYPE;

// tomas modify for group but no use
@SuppressLint("UseCompatLoadingForDrawables")
class InvokeGroup implements Invoke.Callbacks {

    @Override
    public boolean parse(View view, String attrName, int ids, Resources res) {
        if (null == view || null == res || ids <= 0) return false;
        try {
            if (attrName.equals(SKIN_BACKGROUND_TYPE)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    view.setBackground(res.getDrawable(ids, view.getContext().getTheme()));
                } else {
                    view.setBackground(res.getDrawable(ids));
                }
                return true;
            } else if (attrName.equals(SKIN_STYLE_TYPEFACE_TYPE)) {
                final Resources.Theme theme = view.getContext().getTheme();
                TypedArray a = theme.obtainStyledAttributes(ids, R.styleable.BitBackground);
                if (a.hasValue(R.styleable.BitBackground_android_background)) {
                    int id = a.getResourceId(R.styleable.BitBackground_android_background, INVALID_ID);
                    int itp = SkinSource.getInstance().getId(id);
                    return parse(view, SKIN_BACKGROUND_TYPE, itp, res);
                }
                a.recycle();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
