package com.ttys.skin.base;

import android.content.res.Resources;
import android.os.Bundle;

import com.ttys.skin.source.SkinSource;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;

// tomas modify for SkinComponentActivity at 2021
public class SkinComponentActivity extends ComponentActivity{
    /**
     * Base class for activities that enables composition of higher level components.
     * <p>
     * Rather than all functionality being built directly into this class, only the minimal set of
     * lower level building blocks are included. Higher level components can then be used as needed
     * without enforcing a deep Activity class hierarchy or strong coupling between components.
     */
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
