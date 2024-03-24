package com.example.smartstore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import search.search;

public class  MainActivity extends AppCompatActivity implements View.OnClickListener {

        private TextView space_discription_btn;
        private Button search_btn;
        private static long lastClickTime;
        private SharedPreferences preferences;

    @Override
          protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            space_discription_btn = findViewById(R.id.space_discription_btn);
            search_btn = findViewById(R.id.search_btn);

            //备注功能
            preferences = getSharedPreferences("config",Context.MODE_PRIVATE);
            reload();
            space_discription_btn.setOnClickListener(this);

            //跳转到搜索界面
            search_btn.setOnClickListener(v -> {
                startActivity(new Intent(this, search.class));
            });

        }









//    从sharepreference读取空间描述
    private void reload() {
        String s =  preferences.getString("space_discription",null);
        space_discription_btn.setText(s);
    }
//    设置空间标注
        public void onClick(View v) {
//        限制连续点击按钮
            long NowTime = System.currentTimeMillis();
            if(NowTime - lastClickTime < 500)
                return;
            lastClickTime = NowTime;
            discription_dialog dd = new discription_dialog(space_discription_btn.getText().toString(),MainActivity.this, R.style.style_dialog, new discription_dialog.PriorityListener() {
                @Override
                public void refreshPriorityUI(String string) {
                    space_discription_btn.setText(string);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("space_discription",string);
                    editor.commit();
                }
            });
            dd.onCreateDialog();
        }
    }

