package com.ttys.skin.base;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.ttys.skin.source.SkinSource;

/**
 * tomas modify for Activity at 2021 06 10
 */
public class SkinActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        SkinSource.create(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Resources getResources() {
        return super.getResources();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        SkinSource.getInstance().finish(this);
    }
}
