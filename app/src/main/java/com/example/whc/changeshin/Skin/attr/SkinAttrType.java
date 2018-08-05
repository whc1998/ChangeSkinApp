package com.example.whc.changeshin.Skin.attr;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whc.changeshin.Skin.ResourceManager;
import com.example.whc.changeshin.Skin.SkinManager;

/**
 * Created by WSY on 2018/6/8.
 */

public enum SkinAttrType {

    BACKGROUND("background"){
        @Override
        public void apply(View view, String resName) {
            Drawable drawable= getResourceManager().getDrawableByResName(resName);
            if (drawable!=null)
            view.setBackground(drawable);
        }
    },SRC("src") {
        @Override
        public void apply(View view, String resName) {
            Drawable drawable= getResourceManager().getDrawableByResName(resName);
            if (view instanceof ImageView){
                ImageView imageView= (ImageView) view;
                if (drawable!=null)
                    imageView.setBackground(drawable);
            }
        }
    },TEXT_COLOR("textColor") {
        @Override
        public void apply(View view, String resName) {
            ColorStateList colorStateList= getResourceManager().getColorByResName(resName);
            if (view instanceof TextView){
                TextView textView= (TextView) view;
                if (colorStateList!=null)
                    textView.setTextColor(colorStateList);
            }
        }
    };

    public String getResType() {
        return resType;
    }

    String resType;

    SkinAttrType(String type){
        resType=type;
    }

   public abstract void apply(View view,String resName);

    public ResourceManager getResourceManager(){
        return SkinManager.getInstance().getResourceManager();
    }

}
