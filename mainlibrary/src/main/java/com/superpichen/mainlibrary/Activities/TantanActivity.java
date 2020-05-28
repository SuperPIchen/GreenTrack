package com.superpichen.mainlibrary.Activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.superpichen.mainlibrary.MyView.TopBar.StatusBarUtil;
import com.superpichen.mainlibrary.R;
import com.superpichen.unitycontactlib.ContactTool;
import com.unity3d.player.UnityPlayerActivity;

import java.util.ArrayList;
import java.util.List;

public class TantanActivity extends UnityPlayerActivity {

    private ImageView IvTantanBack;
    private TextView TvTantanNextStation;
    private FrameLayout FlTantanUnityContainer;
    private LinearLayout LlTantanZhandian;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-05-11 18:03:50 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        StatusBarUtil.setTranslucentForImageView(this,0,null);
        IvTantanBack = findViewById( R.id.IvTantanBack );
        TvTantanNextStation = findViewById( R.id.TvTantanNextStation );
        FlTantanUnityContainer = findViewById( R.id.FlTantanUnityContainer );
        LlTantanZhandian = findViewById( R.id.LlTantanZhandian );
        initData();
    }

    /**
     * 初始化数据
     */
    List<String> zhanS=new ArrayList<>();
    private void initData() {
        zhanS.add("蓝海中学");
        zhanS.add("珊瑚馆");
        zhanS.add("中心花园");
        zhanS.add("大剧院");
        zhanS.add("万达购物");
        zhanS.add("我的位置！");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tantan);
        findViews();
        selectScn();
        handler.sendEmptyMessageDelayed(1,2000);
    }

    private void selectScn() {
        ContactTool.TargetUnitySecen="Tower";
        IvTantanBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUnityPlayer.quit();
                handler.removeCallbacksAndMessages(null);
            }
        });
        int glesMode = mUnityPlayer.getSettings().getInt("gles_mode", 1);
        mUnityPlayer.init(glesMode, false);
        FlTantanUnityContainer.addView(mUnityPlayer.getView());
    }

    int count=0;
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    TvTantanNextStation.setText("最近车辆："+zhanS.get(count));
                    if(!zhanS.get(count).equals("我的位置！")){
                        count++;
                        handler.sendEmptyMessageDelayed(1,8000);
                    }else {
                        View view=View.inflate(TantanActivity.this,R.layout.dialog_paoku,null);
                        AlertDialog dialog=new AlertDialog.Builder(TantanActivity.this,R.style.Translucent_NoTitle)
                                .setView(view)
                                .create();
                        view.findViewById(R.id.TvPaoKuDialogYes).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mUnityPlayer.quit();
                                handler.removeCallbacksAndMessages(null);
                            }
                        });
                        view.findViewById(R.id.TvDuihuanDialogCancel).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                    }

            }
        }
    };
}
