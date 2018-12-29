package com.aroutertest.plugindemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.aroutertest.communication.BeanManager;


public class PluginActivity extends AppCompatActivity {

    TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);

        String ceshi = BeanManager.getInstance().getName();

        tv_text = findViewById(R.id.tv_text);

        tv_text.setText(tv_text.getText() + ceshi);
    }
}
