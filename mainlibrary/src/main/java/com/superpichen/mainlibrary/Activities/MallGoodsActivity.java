package com.superpichen.mainlibrary.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.MediaController;
import android.widget.RelativeLayout;

import com.superpichen.mainlibrary.MyView.TopBar.StatusBarUtil;
import com.superpichen.mainlibrary.R;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class MallGoodsActivity extends AppCompatActivity {
    private RelativeLayout RlMallGoodsBuy;
    private GifImageView GvMallGoodsBuySuccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_goods);
        StatusBarUtil.setTranslucentForImageViewAndDarkFont(this,0,null);
        RlMallGoodsBuy = findViewById(R.id.RlMallGoodsBuy);
        GvMallGoodsBuySuccess = findViewById(R.id.GvMallGoodsBuySuccess);
        RlMallGoodsBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GvMallGoodsBuySuccess.setVisibility(View.VISIBLE);
                handler.sendEmptyMessageDelayed(1,2000);
            }
        });
    }

    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    startActivity(new Intent(MallGoodsActivity.this,DuihuanActivity.class));
                    finish();
            }
        }
    };
}
