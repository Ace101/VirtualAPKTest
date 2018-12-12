package com.aroutertest.virtualapktest;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aroutertest.communication.BeanManager;
import com.aroutertest.communication.entitys.Bean;
import com.aroutertest.communication.entitys.UserInfo;
import com.didi.virtualapk.PluginManager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    TextView tv_jump, tv_jump2;
    RxPermissions rxPermission;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_jump = findViewById(R.id.tv_jump);
        tv_jump2 = findViewById(R.id.tv_jump2);

        rxPermission = new RxPermissions(MainActivity.this);

        rxPermission.request(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            loadPlugin();
                        }
                    }
                });

        Bean bean = new Bean();
        bean.setName("ceshi");
        BeanManager.init(bean);

        UserInfo info = new UserInfo();
        info.setUserAge("18");
        BeanManager.initUserInfo(info);

        tv_jump.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.aroutertest.virtualapktest", "com.aroutertest.plugindemo.PluginActivity");
                startActivity(intent);
            }
        });

        tv_jump2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.aroutertest.virtualapktest", "com.aroutertest.plugindemo2.PluginActivity2");
                startActivity(intent);
            }
        });
    }

    public void loadPlugin() {
        PluginManager pluginManager = PluginManager.getInstance(this);
        //此处是当查看插件apk是否存在,如果存在就去加载(比如修改线上的bug,把插件apk下载到sdcard的根目录下取名为plugin-release.apk)
        File apk = new File(Environment.getExternalStorageDirectory(), "plugin-release1.apk");
        if (apk.exists()) {
            try {
                pluginManager.loadPlugin(apk);
                Toast.makeText(this, "插件加载成功", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "插件加载异常！", Toast.LENGTH_SHORT).show();
            }
        } else {
//            if (copyFile("F:/AsTest/VirtualAPKTest/plugindemo/build/outputs/apk/release/plugindemo-release-unsigned.apk", Environment.getExternalStorageDirectory() + "plugin-release1.apk")) {
//
//            }
        }

        PluginManager pluginManager2 = PluginManager.getInstance(this);
        //此处是当查看插件apk是否存在,如果存在就去加载(比如修改线上的bug,把插件apk下载到sdcard的根目录下取名为plugin-release.apk)
        File apk2 = new File(Environment.getExternalStorageDirectory(), "plugin-release2.apk");
        if (apk2.exists()) {
            try {
                pluginManager2.loadPlugin(apk2);
                Toast.makeText(this, "插件2加载成功", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "插件2加载异常！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean copyFile(String oldPath, String newPath) {
        try {
            File oldFile = new File(oldPath);
            if (!oldFile.exists()) {
                Log.e("--Method--", "copyFile:  oldFile not exist.");
                return false;
            } else if (!oldFile.isFile()) {
                Log.e("--Method--", "copyFile:  oldFile not file.");
                return false;
            } else if (!oldFile.canRead()) {
                Log.e("--Method--", "copyFile:  oldFile cannot read.");
                return false;
            }

        /* 如果不需要打log，可以使用下面的语句
        if (!oldFile.exists() || !oldFile.isFile() || !oldFile.canRead()) {
            return false;
        }
        */

            FileInputStream fileInputStream = new FileInputStream(oldPath);    //读入原文件
            FileOutputStream fileOutputStream = new FileOutputStream(newPath);
            byte[] buffer = new byte[1024];
            int byteRead;
            while ((byteRead = fileInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, byteRead);
            }
            fileInputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
