package com.ttys.skin.source;

import android.view.View;

// tomas modify for bean
public class Bean {
    public int mId;
    public View mView;
    public String mName;
    public String mEntry;
    public String mType;
    public boolean mActive = true;

    public Bean(View view, int id,String name, String entry, String type) {
        this.mId = id;
        this.mView = view;
        this.mName = name;
        this.mEntry = entry;
        this.mType = type;
    }
}
