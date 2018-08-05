package com.example.whc.changeshin.Skin;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

/**
 * Created by WSY on 2018/6/8.
 */

public class ResourceManager {

    private Resources resources;
    private String pkgName;
    private String msuffix;

    public ResourceManager(Resources resources, String pkgName, String suffix) {
        this.resources = resources;
        this.pkgName = pkgName;

        if (suffix == null) {
            suffix = "";
        }
        msuffix = suffix;
    }

    public Drawable getDrawableByResName(String name) {

        name = appendSuffix(name);
        try {
            return resources.getDrawable(resources.getIdentifier(name, "drawable", pkgName));
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String appendSuffix(String name) {
        if (!TextUtils.isEmpty(msuffix)) {
            name += "_" + msuffix;
        }
        return name;
    }

    public ColorStateList getColorByResName(String name) {
        name = appendSuffix(name);
        try {
            return resources.getColorStateList(resources.getIdentifier(name, "color", pkgName));
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
