package com.example.smartstore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import chattingCircle.Chattingcircle_recommend;
import image_submit.Upload;
import search.search;
import self_edit_layout.self_edit_layout;

public class  MainActivity extends AppCompatActivity implements View.OnClickListener {

        private TextView space_discription_btn;
        private Button search_btn;
        private static long lastClickTime;
        private SharedPreferences preferences;
        private ImageView camera_btn;
        private Button manage_btn;

    @Override
      protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        space_discription_btn = findViewById(R.id.space_discription_btn);
        search_btn = findViewById(R.id.search_btn);
        camera_btn = findViewById(R.id.camera_btn);
        manage_btn = findViewById(R.id.manage_btn);
        camera_btn.setOnClickListener(v -> {
            startActivity(new Intent(this, Upload.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        TextView goto2 = findViewById(R.id.oneTOtwo);
        TextView goto3 = findViewById(R.id.oneTOthree);

        goto2.setOnClickListener(v -> {
            Intent intent = new Intent(this, Chattingcircle_recommend.class);
            startActivity(intent);
            overridePendingTransition(0,0);
            finish();
        });

        goto3.setOnClickListener(v -> {
            Intent intent = new Intent(this, mine_page.class);
            startActivity(intent);
            overridePendingTransition(0,0);
            finish();
        });

        //备注功能
        preferences = getSharedPreferences("config",Context.MODE_PRIVATE);
        ((TextView)findViewById(R.id.layout_name)).setText(preferences.getString("current_layout_name","还没有场景哦！"));
        reload();
        space_discription_btn.setOnClickListener(this);

        //跳转到搜索界面
        search_btn.setOnClickListener(v -> {
            startActivity(new Intent(this, search.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        manage_btn.setOnClickListener(v -> {
            startActivity(new Intent(this, self_edit_layout.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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
            //参数一：默认文本
            discription_dialog dd = new discription_dialog(space_discription_btn.getText().toString(),MainActivity.this, R.style.style_dialog, new discription_dialog.PriorityListener() {
                @Override
                public void refreshPriorityUI(String string) {
                    space_discription_btn.setText(string);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("space_discription",string);
                    editor.apply();
                }
            });
            dd.onCreateDialog();
        }
    }

