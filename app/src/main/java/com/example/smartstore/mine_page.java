package com.example.smartstore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import in_out_rcd.in_out_rcd;

public class mine_page extends AppCompatActivity  {


    private TextView tv;
    private int uer_experience;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_page);

        ProgressBar progressBar = findViewById(R.id.exp_progress);
        uer_experience = 40;
        int progress = (int) (((double) uer_experience / 100) * 100); // 计算百分比进度
        progressBar.setProgress(progress);

        findViewById(R.id.my_record_btn).setOnClickListener(v->{
            Intent intent = new Intent(this, in_out_rcd.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
    }

}
