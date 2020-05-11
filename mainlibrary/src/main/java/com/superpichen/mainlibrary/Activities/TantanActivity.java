package com.superpichen.mainlibrary.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.superpichen.mainlibrary.MyView.TopBar.StatusBarUtil;
import com.superpichen.mainlibrary.R;
import com.superpichen.unitycontactlib.ContactTool;
import com.unity3d.player.UnityPlayerActivity;

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
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tantan);
        findViews();
        selectScn();
    }

    private void selectScn() {
        ContactTool.TargetUnitySecen="Tower";
        IvTantanBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUnityPlayer.quit();
            }
        });
        int glesMode = mUnityPlayer.getSettings().getInt("gles_mode", 1);
        mUnityPlayer.init(glesMode, false);
        FlTantanUnityContainer.addView(mUnityPlayer.getView());
    }
}
