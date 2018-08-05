package com.example.whc.changeshin;


import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whc.changeshin.Skin.Base.BaseSkinActivity;
import com.example.whc.changeshin.Skin.CallBack.ISkinChangeCallBack;
import com.example.whc.changeshin.Skin.SkinManager;

import java.io.File;

public class MainActivity extends BaseSkinActivity {

    private DrawerLayout drawerLayout;
    private ListView listView;
    private String[] mDates = new String[]{"item1", "item2", "item3", "item4", "item5", "item6"};

    private String mSkinPluginPath = Environment.getExternalStorageDirectory() + File.separator + "skin_plugin.apk";
    private String mSkinPluginPkg = "com.example.whc.skinapp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 1) {
                    SkinManager.getInstance().changeSkin("blue");
                }else if (i==0){
                    Intent intent=new Intent(MainActivity.this,TestFactoryActivity.class);
                    startActivity(intent);
                } else {
                    SkinManager.getInstance().changeSkin(mSkinPluginPath, mSkinPluginPkg, new ISkinChangeCallBack() {

                        @Override
                        public void onStart() {

                        }

                        @Override
                        public void onError(Exception e) {
//                        Toast.makeText(MainActivity.this,"换肤失败",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onComplete() {
                            Toast.makeText(MainActivity.this, "换肤成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }


        });
    }


    private void initView() {
        drawerLayout = findViewById(R.id.id_drawerlayout);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.id_left_menu_container);
        if (fragment == null) {
            fm.beginTransaction().add(R.id.id_left_menu_container, new MenuLeftFragment()).commit();
        }
        listView = findViewById(R.id.id_listview);
        listView.setAdapter(new ArrayAdapter<String>(this, -1, mDates) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, parent, false);
                }
                TextView textView = convertView.findViewById(R.id.id_tv_title);
                textView.setText(getItem(position));
                return convertView;
            }
        });
    }


}
