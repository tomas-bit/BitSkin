package com.ttys.skin.source;

import android.view.View;

// tomas modify for bean
public class Bean {
    public View mView;
    public String mName;
    public String mEntry;
    public String mType;
    public boolean mActive = true;

    public Bean(View view, String name, String entry, String type) {
        this.mView = view;
        this.mName = name;
        this.mEntry = entry;
        this.mType = type;
    }
}
