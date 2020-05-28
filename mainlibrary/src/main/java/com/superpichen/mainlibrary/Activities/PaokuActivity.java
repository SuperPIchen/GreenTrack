package com.superpichen.mainlibrary.Activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.superpichen.mainlibrary.MyView.TopBar.StatusBarUtil;
import com.superpichen.mainlibrary.R;
import com.superpichen.unitycontactlib.ContactTool;
import com.unity3d.player.UnityPlayerActivity;

import java.util.ArrayList;
import java.util.List;

public class PaokuActivity extends UnityPlayerActivity {
    private ImageView IvPaoKuBack;
    private FrameLayout FlPaoKuUnityContainer;
    private TextView TvPaoKuSpeed;
    private TextView TvPaoKuNextStation;

    private void findViews() {
        IvPaoKuBack = findViewById(R.id.IvPaoKuBack);
        FlPaoKuUnityContainer = findViewById(R.id.FlPaoKuUnityContainer);
        TvPaoKuSpeed = findViewById(R.id.TvPaoKuSpeed);
        TvPaoKuNextStation = findViewById(R.id.TvPaoKuNextStation);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paoku);
        StatusBarUtil.setTranslucentForImageView(this,0,null);
        findViews();
        selectScn();
        initData();
        handler.sendEmptyMessageDelayed(1,4000);
    }

    /**
     * 初始化数据
     */
    List<String> zhans=new ArrayList<>();
    List<Integer> speeds=new ArrayList<>();
    private void initData() {
        zhans.add("中心花园");
        zhans.add("大剧院");
        zhans.add("万达购物中心");
        zhans.add("目的地！");
        speeds.add(65);
        speeds.add(43);
        speeds.add(76);
        speeds.add(49);
    }

    int count=0;
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    TvPaoKuNextStation.setText("下一站："+zhans.get(count));
                    TvPaoKuSpeed.setText("当前时速："+speeds.get(count)+"Km/h");
                    if(count!=2){
                        count++;
                        handler.sendEmptyMessageDelayed(1,8000);
                    }else {
                        View view=View.inflate(PaokuActivity.this,R.layout.dialog_paoku,null);
                        AlertDialog dialog=new AlertDialog.Builder(PaokuActivity.this,R.style.Translucent_NoTitle)
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
    /**
     * 选择unity场景
     */
    private void selectScn() {
        ContactTool.TargetUnitySecen="PaoKu";
        IvPaoKuBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUnityPlayer.quit();
                handler.removeCallbacksAndMessages(null);
            }
        });
        int glesMode = mUnityPlayer.getSettings().getInt("gles_mode", 1);
        mUnityPlayer.init(glesMode, false);
        FlPaoKuUnityContainer.addView(mUnityPlayer.getView());
    }

    @Override
    protected void onDestroy() {
        mUnityPlayer.quit();
        super.onDestroy();
    }

//    @Override
//    protected void onResume() {
//        mUnityPlayer.resume();
//        super.onResume();
//    }

    @Override
    protected void onPause() {
        mUnityPlayer.pause();
        handler.removeCallbacksAndMessages(null);
        super.onPause();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        mUnityPlayer.configurationChanged(newConfig);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        mUnityPlayer.windowFocusChanged(hasFocus);
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_MULTIPLE)
            return mUnityPlayer.onKeyMultiple(event.getKeyCode(), event.getRepeatCount(), event);
        return super.dispatchKeyEvent(event);
    }
}
