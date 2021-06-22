package com.ttys.skin.Invoke;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ttys.skin.R;
import com.ttys.skin.SkinCallBack;
import com.ttys.skin.SkinStatus;
import com.ttys.skin.source.Bean;

import java.util.ArrayList;
import java.util.List;

import static com.ttys.skin.source.Config.mPackname;

//tomas modify for Invoke
public class Invoke {

    private InvokeText mText;
    private InvokeImg mImg;
    private InvokeProgress mProgress;
    private InvokeSurface mSurface;
    private InvokeGroup mGroup;
    private InvokeView mView;
    private View mTemp;
    final List<Bean> attrs = new ArrayList<>();
    private static final int TYPE = R.id.id_type;

    public Invoke() {
        mText = new InvokeText();
        mImg = new InvokeImg();
        mProgress = new InvokeProgress();
        mSurface = new InvokeSurface();
        mGroup = new InvokeGroup();
        mView = new InvokeView();
    }

    interface Callbacks {
        boolean parse(View view, String attrName, int ids, Resources res, String entry, String type);
    }

    public void parseAuto(Context context, View view, String attr, int id, Resources res) {
        if (null != view && null != res && null != context) {
            final String entry = context.getResources().getResourceEntryName(id);
            final String name = context.getResources().getResourceTypeName(id);
            if (ifNeed(view, entry, attr)) return;
            final int ids = res.getIdentifier(entry, name, mPackname);
            if (ids > 0) parse(view, attr, ids, res, entry, name);
        }
    }

    public void finish(Context context) {
        mTemp = null;
        Log.e("TAG", "onAttachedToWindow onCreateView finish:::" + context);
        final List<Bean> temps = new ArrayList<>();
        for (Bean bean : attrs) {
            if (!isValue(bean)) temps.add(bean);
        }
        attrs.removeAll(temps);
        Log.w("TAG", "onAttachedToWindow onCreateView bean  finish:::" + attrs.size());
    }

    private boolean isValue(Bean bean) {
        Object obj = bean.mView.getTag(TYPE);
        if (obj instanceof Bean) {
            final Bean tp = (Bean) obj;
            Log.w("TAG", "onAttachedToWindow onCreateView bean:::" + attrs.size() + "    " + tp.toString());
            return tp.mActive;
        }
        return true;
    }

    public void parse(View view, String attrName, int ids, Resources res, String entry, String type) {
        final boolean result = getParse(view, attrName, ids, res, entry, type);
        if (result) {
            final Bean bean = new Bean(view, attrName, entry, type);
            if (null == view.getTag(TYPE)) view.setTag(TYPE, bean);
            attrs.add(bean);
            attachStateChange(view);
        }
    }

    private synchronized void attachStateChange(View view) {
        if (mTemp == view) return;
        mTemp = view;
        view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                Log.w("TAG", "onAttachedToWindow onCreateView onViewAttachedToWindow bean:::" + v);
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                Object obj = v.getTag(TYPE);
                if (obj instanceof Bean) {
                    Bean tp = (Bean) obj;
                    tp.mActive = false;
                    attrs.remove(tp);
                }
                Log.e("TAG", "onAttachedToWindow onCreateView onViewDetachedFromWindow:::" + v);
            }
        });
    }

    private boolean ifNeed(View view, String name, String attr) {
        for (Bean bean : attrs) {
            if (view == bean.mView && name.equals(bean.mEntry)) {
                if (attr.equals(bean.mName)) return true;
                attrs.remove(bean);
                break;
            }
        }
        return false;
    }

    private boolean getParse(View view, String attrName, int ids, Resources res, String entry, String type) {
        if (view == null) return false;
        if (attrName.equals("background") || attrName.equals("foreground")) {
            if (mView != null) {
                return mView.parse(view, attrName, ids, res, entry, type);
            }
        }
        if (view instanceof ViewGroup) {
            if (mGroup != null) {
                return mGroup.parse(view, attrName, ids, res, entry, type);
            }
        } else if (view instanceof TextView) {
            if (mText != null) {
                return mText.parse(view, attrName, ids, res, entry, type);
            }
        } else if (view instanceof ImageView) {
            if (mImg != null) {
                return mImg.parse(view, attrName, ids, res, entry, type);
            }
        } else if (view instanceof ProgressBar) {
            if (mProgress != null) {
                return mProgress.parse(view, attrName, ids, res, entry, type);
            }
        } else if (view instanceof SurfaceView) {
            if (mSurface != null) {
                return mSurface.parse(view, attrName, ids, res, entry, type);
            }
        }
        return false;
    }

    public void updateSkin(Resources res, SkinCallBack callBack) {
        for (Bean bean : attrs) {
            if (!isValue(bean)) continue;
            int ids = res.getIdentifier(bean.mEntry, bean.mType, mPackname);
            if (ids == 0) continue;
            final boolean result = getParse(bean.mView, bean.mName, ids, res, bean.mEntry, bean.mType);
            if (callBack != null) {
                //callBack.updateSkinStatus(SkinStatus.LOADING, result);
            }
        }
        if (callBack != null) {
            callBack.updateSkinStatus(SkinStatus.SUCCESS, true);
        }
    }

    public void destroy() {
        attrs.clear();
        mText = null;
        mImg = null;
        mGroup = null;
        mProgress = null;
        mSurface = null;
        mView = null;
    }
}

