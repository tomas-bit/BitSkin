package com.ttys.skin.source;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.ttys.skin.Invoke.Invoke;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

// tomas modify for SkinInflater
class SkinInflater implements LayoutInflater.Factory2 {
    SkinSource mRes;
    private final Invoke mInvoke;

    private static SkinInflater mSkinInflater;

    private SkinInflater() {
        mRes = SkinSource.getInstance();
        mInvoke = new Invoke();
        mRes.setInvoke(mInvoke, this);
    }

    protected synchronized static SkinInflater getInstance() {
        if (null == mSkinInflater) {
            mSkinInflater = new SkinInflater();
        }
        return mSkinInflater;
    }

    private View getView(Context context, String name, AttributeSet attrs) {
        View view = null;
        try {
            if (-1 == name.indexOf('.')) {
                if ("View".contains(name) || "ViewStub".contains(name)) {
                    view = LayoutInflater.from(context).createView(name, "android.view.", attrs);
                }
                if (view == null) {
                    view = LayoutInflater.from(context).createView(name, "android.widget.", attrs);
                }
                if (view == null) {
                    view = LayoutInflater.from(context).createView(name, "android.webkit.", attrs);
                }
            } else {
                view = LayoutInflater.from(context).createView(name, "", attrs);
            }
        } catch (Exception e) {
            Log.e("TAG", "SkinInflater error: " + name + "::" + e);
        }
        return view;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        View view = getView(context, name, attrs);
        if (view == null) return null;
        parseContext(context, attrs, view);
        return view;
    }

    protected void onDestroy() {
        if (null != mRes) mRes.destroy();
        if (null != mInvoke) mInvoke.destroy();
    }

    private void parseContext(Context context, AttributeSet attrs, View view) {
        if (null == mRes || null == mInvoke) return;
        Resources res = mRes.source();
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            String attrName = attrs.getAttributeName(i);
            if (attrName.equals("id")) continue;
            String attrValue = attrs.getAttributeValue(i);
            if (attrValue.startsWith("@")) {
                try {
                    int id = Integer.parseInt(attrValue.substring(1));
                    if (id <= 0) continue;
                    String typeName = context.getResources().getResourceTypeName(id);
                    if (typeName.equals("id")) continue;
                    String entryName = context.getResources().getResourceEntryName(id);
                    int ids = res.getIdentifier(entryName, typeName, Config.mPackname);
                    if (ids <= 0) continue;
                    mInvoke.parse(view, attrName, ids, res, entryName, typeName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        View view = getView(context, name, attrs);
        if (view == null) return null;
        parseContext(context, attrs, view);
        return view;
    }
}
