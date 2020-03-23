package com.superpichen.mainlibrary.Activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.superpichen.mainlibrary.MyView.TopBar.StatusBarUtil;
import com.superpichen.mainlibrary.R;
import com.superpichen.unitycontactlib.ContactTool;
import com.unity3d.player.UnityPlayerActivity;

public class PaokuActivity extends UnityPlayerActivity {
    private ImageView IvPaoKuBack;
    private FrameLayout FlPaoKuUnityContainer;

    private void findViews() {
        IvPaoKuBack = findViewById(R.id.IvPaoKuBack);
        FlPaoKuUnityContainer = findViewById(R.id.FlPaoKuUnityContainer);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paoku);
        StatusBarUtil.setTranslucentForImageView(this,0,null);
        findViews();
        selectScn();
    }

    /**
     * 选择unity场景
     */
    private void selectScn() {
        ContactTool.TargetUnitySecen="PaoKu";
        IvPaoKuBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUnityPlayer.quit();
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
