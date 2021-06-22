package com.ttys.skin.Invoke;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Build;
import android.view.View;

import static com.ttys.skin.source.SkinAttr.SKIN_BACKGROUND_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_FOREGROUND_TYPE;

// tomas modify for view
@SuppressLint("UseCompatLoadingForDrawables")
class InvokeView implements Invoke.Callbacks {

    @Override
    public boolean parse(View view, String attrName, int ids, Resources res, String entry, String type) {
        if (null == view || null == res) return false;
        try {
            if (attrName.equals(SKIN_BACKGROUND_TYPE) || attrName.equals(SKIN_FOREGROUND_TYPE)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (attrName.equals(SKIN_BACKGROUND_TYPE))
                        view.setBackground(res.getDrawable(ids, view.getContext().getTheme()));
                    else
                        view.setForeground(res.getDrawable(ids, view.getContext().getTheme()));
                } else {
                    if (attrName.equals(SKIN_BACKGROUND_TYPE))
                        view.setBackground(res.getDrawable(ids));
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
