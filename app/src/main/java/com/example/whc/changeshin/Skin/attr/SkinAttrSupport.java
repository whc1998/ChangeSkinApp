package com.example.whc.changeshin.Skin.attr;

import android.content.Context;
import android.util.AttributeSet;

import com.example.whc.changeshin.Skin.Config.Const;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WSY on 2018/6/8.
 */

public class SkinAttrSupport {

    public static List<SkinAttr> getSkinAttrs(AttributeSet attrs, Context context) {

        List<SkinAttr> mSkinattrs = new ArrayList<>();
        SkinAttrType attrType = null;
        SkinAttr skinAttr=null;

        for (int i = 0, n = attrs.getAttributeCount(); i < n; i++) {
            String attrName = attrs.getAttributeName(i);
            String attrVal = attrs.getAttributeValue(i);

            if (attrVal.startsWith("@")) {
                int id = -1;
                try {
                    id=Integer.parseInt(attrVal.substring(1));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                if (id==-1){
                    continue;
                }

                String resName = context.getResources().getResourceEntryName(id);

                if (resName.startsWith(Const.SKIN_PREFIX)) {
                    attrType = getSupportAttrType(attrName);
                    if (attrType==null){
                        continue;
                    }
                    skinAttr=new SkinAttr(resName,attrType);
                    mSkinattrs.add(skinAttr);
                }
            }
        }
        return mSkinattrs;
    }

    private static SkinAttrType getSupportAttrType(String attrName) {
        for (SkinAttrType attrType : SkinAttrType.values()){
            if (attrType.getResType().equals(attrName)){
                return attrType;
            }
        }
            return null;
    }

}
