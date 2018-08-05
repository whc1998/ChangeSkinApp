package com.example.whc.changeshin.Skin.CallBack;

/**
 * Created by WSY on 2018/6/9.
 */

public interface ISkinChangeCallBack {

    void onStart();
    void onError(Exception e);
    void onComplete();

    public static DefaultSkinChangingCallback  DEFAULT_CALLBACK=new DefaultSkinChangingCallback();

    public class DefaultSkinChangingCallback implements ISkinChangeCallBack{

        @Override
        public void onStart() {

        }

        @Override
        public void onError(Exception e) {

        }

        @Override
        public void onComplete() {

        }
    }

}
