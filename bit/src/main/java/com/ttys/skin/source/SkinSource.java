package com.ttys.skin.source;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;

import com.ttys.skin.Invoke.Invoke;
import com.ttys.skin.SkinCallBack;
import com.ttys.skin.SkinStatus;

import static com.ttys.skin.source.Config.mPackname;
import static com.ttys.skin.source.Config.mPath;
import static com.ttys.skin.source.SkinAttr.SKIN_BACKGROUND_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_DRAWABLEBOTTOM_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_DRAWABLELEFT_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_DRAWABLERIGHT_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_DRAWABLETOP_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_FONTFAMILY_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_FOREGROUND_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_PROGRESSDRAWABLE_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_SRC_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_STYLE_TYPEFACE_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_TEXTCOLORHINT_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_TEXTCOLOR_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_TEXTSIZE_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_TEXT_TYPE;
import static com.ttys.skin.source.SkinAttr.SKIN_THUMB_TYPE;

// tomas modify for skinsource
public class SkinSource {
    @SuppressLint("StaticFieldLeak")
    private static SkinSource mSkinSource;
    private Resources mSource;
    private Context mContext;
    LayoutInflater mLayoutInf;
    SkinInflater mInflater;
    SkinHandle mHandle;
    Invoke mInvoke;

    private SkinSource() {
    }

    public synchronized static SkinSource getInstance() {
        if (null == mSkinSource) {
            mSkinSource = new SkinSource();
        }
        return mSkinSource;
    }

    public void register(@NonNull Context context) {
        mContext = context;
        mLayoutInf = from(context);
        mHandle = new SkinHandle(context, this);
        mPath = mHandle.getPackagePath();
        mPackname = mHandle.getPackName(mPath);
        this.initSource();
    }

    private void initSource() {
        mSource = mHandle.getPackSource(mPath);
        if (null == mSource) {
            mSource = mContext.getResources();
            mPackname = mContext.getPackageName();
            mPath = "";
        }
    }

    private void updateSource(Resources res) {
        mSource = res;
    }

    protected void destroy() {
        mSource = null;
        mContext = null;
        mSkinSource = null;
        mLayoutInf = null;
        if (null != mHandle) mHandle.destroy();
    }

    public static void finish(Context context) {
        if (null != getInstance().mInvoke) getInstance().mInvoke.finish(context);
    }

    public int getId(int ids) {
        if (null != mSource && null != mContext && ids > 0) {
            String name = mContext.getResources().getResourceTypeName(ids);
            String entry = mContext.getResources().getResourceEntryName(ids);
            return mSource.getIdentifier(entry, name, mPackname);
        }
        return 0;
    }

    public int getIdentifier(int id, String name, String type, String attr) {//add
        if (null == mSource || attr.equals(SKIN_STYLE_TYPEFACE_TYPE)) {
            return id;
        } else {
            return mSource.getIdentifier(name, type, Config.mPackname);
        }
    }

    protected LayoutInflater.Factory get() {
        if (null == mInflater) {
            return SkinInflater.getInstance();
        }
        return mInflater;
    }

    protected Resources source() {
        return mSource;
    }

    protected void setInvoke(Invoke invoke, SkinInflater inflater) {
        mInvoke = invoke;
        mInflater = inflater;
    }

    public static void create(Context context) {
        if (null == LayoutInflater.from(context).getFactory2()) {
            LayoutInflater.from(context).setFactory2(SkinInflater.getInstance());
        }
    }

    public static LayoutInflater from(Context context) {
        if (null == context) return null;
        create(context);
        return LayoutInflater.from(context);
    }

    public void updateSkin(String packageName, final SkinCallBack callBack) {
        if (callBack != null) {
            callBack.updateSkinStatus(SkinStatus.START, false);
        }
        if (null != packageName && !packageName.equals(mPath)) {
            if (mInvoke != null) {
                final Resources res = (packageName.length() <= 0) ?
                        mContext.getResources() : mHandle.getPackSource(packageName);
                if (res != null) {
                    final String temp = mHandle.getPackName(packageName);
                    if (!temp.equals("")) {
                        mPackname = temp;
                        mPath = packageName;
                        updateSource(res);
                        mInvoke.updateSkin(mSource, callBack);
                        mHandle.updatePackagePath(packageName);
                        return;
                    }
                }
            }
        }
        if (callBack != null) {
            callBack.updateSkinStatus(SkinStatus.ERROR, false);
        }
    }

    public void unRegister() {
        if (mInflater != null) mInflater.onDestroy();
    }

    public void setText(View view, int id) {
        if (null != mInvoke)
            mInvoke.parseAuto(mContext, view, SKIN_TEXT_TYPE, id, source());
    }

    public void setBackgroundResource(View view, int id) {
        if (null != mInvoke)
            mInvoke.parseAuto(mContext, view, SKIN_BACKGROUND_TYPE, id, source());
    }

    public void setForegroundResource(View view, int id) {
        if (null != mInvoke)
            mInvoke.parseAuto(mContext, view, SKIN_FOREGROUND_TYPE, id, source());
    }

    public void setTextColor(View view, int id) {
        if (null != mInvoke)
            mInvoke.parseAuto(mContext, view, SKIN_TEXTCOLOR_TYPE, id, source());
    }

    public void setTextColorHint(View view, int id) {
        if (null != mInvoke)
            mInvoke.parseAuto(mContext, view, SKIN_TEXTCOLORHINT_TYPE, id, source());
    }

    public void setTextSize(View view, int id) {
        if (null != mInvoke)
            mInvoke.parseAuto(mContext, view, SKIN_TEXTSIZE_TYPE, id, source());
    }

    public void setFont(View view, int id) {
        if (null != mInvoke)
            mInvoke.parseAuto(mContext, view, SKIN_FONTFAMILY_TYPE, id, source());
    }

    public void setThumb(View view, int id) {
        if (null != mInvoke)
            mInvoke.parseAuto(mContext, view, SKIN_THUMB_TYPE, id, source());
    }

    public void setTrack(View view, int id) {
        if (null != mInvoke)
            mInvoke.parseAuto(mContext, view, SKIN_THUMB_TYPE, id, source());
    }

    public void setProgressDrawable(View view, int id) {
        if (null != mInvoke)
            mInvoke.parseAuto(mContext, view, SKIN_PROGRESSDRAWABLE_TYPE, id, source());
    }

    public void setCompoundDrawables(View view, int left, int top, int right, int bottom) {
        if (null == mInvoke) return;
        if (left != 0) {
            mInvoke.parseAuto(mContext, view, SKIN_DRAWABLELEFT_TYPE, left, source());
            return;
        }
        if (right != 0) {
            mInvoke.parseAuto(mContext, view, SKIN_DRAWABLERIGHT_TYPE, right, source());
            return;
        }
        if (top != 0) {
            mInvoke.parseAuto(mContext, view, SKIN_DRAWABLETOP_TYPE, top, source());
            return;
        }
        if (bottom != 0) {
            mInvoke.parseAuto(mContext, view, SKIN_DRAWABLEBOTTOM_TYPE, bottom, source());
        }
    }

    public void setImageResource(View view, int id) {
        if (null != mInvoke)
            mInvoke.parseAuto(mContext, view, SKIN_SRC_TYPE, id, source());
    }

}
