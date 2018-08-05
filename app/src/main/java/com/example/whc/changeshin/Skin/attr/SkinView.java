package com.example.whc.changeshin.Skin.attr;

import android.view.View;

import java.util.List;

/**
 * Created by WSY on 2018/6/8.
 */

public class SkinView {

    public View getView() {
        return mView;
    }

    public void setView(View view) {
        this.mView = view;
    }

    public List<SkinAttr> getAttrs() {
        return mAttrs;
    }

    public void setAttrs(List<SkinAttr> attrs) {
        this.mAttrs = attrs;
    }

    private View mView;
    private List<SkinAttr> mAttrs;

    public SkinView(View mView, List<SkinAttr> mAttrs) {
        this.mView = mView;
        this.mAttrs = mAttrs;
    }

    public void apply(){
        for(SkinAttr attr:mAttrs){
            attr.apply(mView);
        }
    }

}
