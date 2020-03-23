package com.superpichen.mainlibrary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.superpichen.mainlibrary.R;
import com.superpichen.unitycontactlib.ContactTool;
import com.unity3d.player.UnityPlayerActivity;

public class DaoHangActivity extends UnityPlayerActivity {
    private FrameLayout FlDaoHangPekemonContainer;
    private ImageView IvDaoHangPeKemonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dao_hang);
        FlDaoHangPekemonContainer = findViewById(R.id.FlDaoHangPekemonContainer);
        IvDaoHangPeKemonBack = findViewById(R.id.IvDaoHangPeKemonBack);
        selectScn();
    }

    /**
     * 选择unity场景
     */
    private void selectScn() {
        ContactTool.TargetUnitySecen="Pekemon";
        IvDaoHangPeKemonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUnityPlayer.quit();
            }
        });
        int glesMode = mUnityPlayer.getSettings().getInt("gles_mode", 1);
        mUnityPlayer.init(glesMode, false);
        FlDaoHangPekemonContainer.addView(mUnityPlayer.getView());
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
