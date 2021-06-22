package com.ttys.skin.source;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import com.ttys.skin.SkinCallBack;
import com.ttys.skin.SkinStatus;

import java.io.File;
import java.lang.reflect.Method;

import static com.ttys.skin.source.Config.ACTION;
import static com.ttys.skin.source.Config.ACTIONCALL;

class SkinHandle {

    private final SkinSource mSkin;
    private final Context mContext;
    BroadcastReceiver broadcastReceiver = null;

    public SkinHandle(Context context, final SkinSource skin) {
        mContext = context;
        mSkin = skin;
        initSkinHandle(context);
    }

    private void initSkinHandle(final Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        if (broadcastReceiver == null) {
            final SkinCallBack callBack = new SkinCallBack() {
                @Override
                public void updateSkinStatus(SkinStatus status, boolean result) {
                    Intent intent = new Intent();
                    intent.setAction(ACTIONCALL);
                    intent.putExtra("result", status.ordinal());
                    context.sendBroadcast(intent);
                }
            };

            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String path = intent.getStringExtra("path");
                    String font = intent.getStringExtra("font_path");
                    mSkin.updateSkin(path, callBack);
                }
            };
            context.registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    public void destroy() {
        if (broadcastReceiver != null && mContext != null) {
            mContext.unregisterReceiver(broadcastReceiver);
        }
    }

    public String getPackagePath() {
        SharedPreferences spf = mContext.getSharedPreferences("skin_name", Context.MODE_PRIVATE);
        return spf.getString("packname", "");
    }

    public void updatePackagePath(String packageName) {
        SharedPreferences spf = mContext.getSharedPreferences("skin_name", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = spf.edit();
        ed.putString("packname", packageName);
        ed.apply();
    }

    public Resources getPackSource(String path) {
        Resources res = null;
        try {
            if (!new File(path).exists()) return res;
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.setAccessible(true);
            addAssetPath.invoke(assetManager, path);

            Resources superRes = mContext.getResources();
            res = new Resources(assetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public String getPackName(String path) {
        if (path.length() <= 0) {
            return mContext.getPackageName();
        } else {
            PackageInfo info = mContext.getPackageManager().getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
            return (info != null) ? info.packageName : "";
        }
    }
}
