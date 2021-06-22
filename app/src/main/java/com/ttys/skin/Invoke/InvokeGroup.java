package com.ttys.skin.Invoke;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.view.View;

// tomas modify for group but no use
@SuppressLint("UseCompatLoadingForDrawables")
class InvokeGroup implements Invoke.Callbacks {

    @Override
    public boolean parse(View view, String attrName, int ids, Resources res, String entry, String type) {
        if (null == view || null == res) return false;
        try {
            switch (attrName) {/*no use**/

            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return false;
    }
}
