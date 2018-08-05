package com.example.whc.changeshin;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.whc.changeshin.Skin.Base.BaseSkinActivity;

/**
 * Created by WSY on 2018/6/8.
 */

public class TestFactoryActivity extends BaseSkinActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

//        LayoutInflater mInflate= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        LayoutInflaterCompat.setFactory(mInflate, new LayoutInflaterFactory() {
//            @Override
//            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
//
//                Log.e("TAG",name);
//
//                for (int i=0,n=attrs.getAttributeCount();i<n;i++){
//                    String atttName=attrs.getAttributeName(i);
//                    String attrVal=attrs.getAttributeValue(i);
//                    Log.e("TAG",atttName+"="+attrVal);
//                }
//
//                if (name.equals("TextView")){
//                    return new EditText(context,attrs);
//                }
//
//                return null;
//            }
//        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.testview);
    }
}
