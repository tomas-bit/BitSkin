package com.ttys.skin.Invoke;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.view.View;

// tomas modify for surface
@SuppressLint("UseCompatLoadingForDrawables")
class InvokeSurface implements Invoke.Callbacks {

    @Override
    public boolean parse(View view, String attrName, int ids, Resources res) {
        if (null == view || null == res || ids <= 0) return false;
        try {
            switch (attrName) {
                /*no use**/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
