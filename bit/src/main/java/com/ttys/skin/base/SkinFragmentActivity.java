package com.ttys.skin.base;

import android.content.res.Resources;
import android.os.Bundle;

import com.ttys.skin.source.SkinSource;

import androidx.fragment.app.FragmentActivity;

/**
 * tomas modify for SkinFragmentActivity at 2021
 */
public class SkinFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        //SkinSource.getInstance().finish(this);
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
