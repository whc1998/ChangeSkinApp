package com.example.whc.changeshin.Skin.attr;

import android.view.View;

/**
 * Created by WSY on 2018/6/8.
 */

public class SkinAttr {

    public String getResName() {
        return mResName;
    }

    public void setResName(String resName) {
        this.mResName = resName;
    }

    public SkinAttrType getType() {
        return mType;
    }

    public void setType(SkinAttrType type) {
        this.mType = type;
    }

    private String mResName;
    private SkinAttrType mType;

    public SkinAttr(String mResName, SkinAttrType mType) {
        this.mResName = mResName;
        this.mType = mType;
    }

    public void apply(View view) {
        mType.apply(view,mResName);
    }

}
