package com.superpichen.mainlibrary.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.superpichen.mainlibrary.MyView.TopBar.StatusBarUtil;
import com.superpichen.mainlibrary.R;

public class JiJianActivity extends AppCompatActivity {
    private ImageView IvJijianYinbo;
    private TextView TvJiJianYuyin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ji_jian);
        TvJiJianYuyin = findViewById(R.id.TvJiJianYuyin);
        StatusBarUtil.setTranslucentForImageView(this,0,null);
        IvJijianYinbo = findViewById(R.id.IvJijianYinbo);
        TvJiJianYuyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IvJijianYinbo.setVisibility(View.VISIBLE);
                handler.sendEmptyMessageDelayed(1,2000);
            }
        });
    }
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(JiJianActivity.this,DaoHangActivity.class));
        }
    };
}
