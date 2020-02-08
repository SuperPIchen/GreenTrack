package com.superpichen.mainlibrary.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.superpichen.mainlibrary.Fragments.OfflineShopFragment;
import com.superpichen.mainlibrary.Fragments.OnlineShopFragment;
import com.superpichen.mainlibrary.MyView.BlurringView;
import com.superpichen.mainlibrary.MyView.TopBar.StatusBarUtil;
import com.superpichen.mainlibrary.R;
import com.superpichen.mainlibrary.Tools.JavaTools.BaseFragment;
import com.superpichen.mainlibrary.Tools.JavaTools.FinalValue;

import java.util.ArrayList;
import java.util.List;

public class MallActivity extends FragmentActivity {
    private RelativeLayout RlMallTopContainer;
    private TextView TvMallCode;
    private FrameLayout FlMallContainer;
    private ImageView IvMallChange;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-02-08 15:12:40 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        RlMallTopContainer = (RelativeLayout)findViewById( R.id.RlMallTopContainer );
        TvMallCode = (TextView)findViewById( R.id.TvMallCode );
        FlMallContainer = (FrameLayout)findViewById( R.id.FlMallContainer );
        IvMallChange = findViewById(R.id.IvMallChange);
    }

    private Intent intent;
    private FragmentTransaction ft;
    //    选中对用Fragment的对应的位置
    private int position;
    //    上次切换的Fragment
    private Fragment mContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);
        findViews();
        intent=getIntent();
        setResult(FinalValue.GETPOINTFORMALLACTIVITY);
        setBar();
        initFragment();
        setOnclick();
    }

    /**
     * 设置监听
     */
    private void setOnclick() {
        IvMallChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(defaultPage==0){
                    defaultPage=1;
                    switchFragment(onlineShopFragment,offlineShopFragment);
                }else {
                    defaultPage=0;
                    switchFragment(offlineShopFragment,onlineShopFragment);
                }
            }
        });
    }


    /**
     * 初始化碎片
     */
    private int defaultPage=0;
    private List<BaseFragment> baseFragments;
    OnlineShopFragment onlineShopFragment;
    OfflineShopFragment offlineShopFragment;
    private void initFragment() {
        onlineShopFragment=new OnlineShopFragment();
        offlineShopFragment=new OfflineShopFragment();
        baseFragments=new ArrayList<>();
        baseFragments.add(onlineShopFragment);
        baseFragments.add(offlineShopFragment);
        ft=getSupportFragmentManager().beginTransaction();
        for(int i=0;i<baseFragments.size();i++){
            if(!baseFragments.get(i).isAdded())
                ft.add(R.id.FlMallContainer,baseFragments.get(i),"MyFragmentTag"+i);
        }
        switchFragment(offlineShopFragment,onlineShopFragment);
    }
    /**
     * @param from 要隐藏的Fragment
     * @param to 要切换的Fragment
     */
    private void switchFragment(Fragment from,Fragment to){
        if(from!=to){
            mContent=to;
            ft=getSupportFragmentManager().beginTransaction();
            //才切换
            //判断有没有被添加
            if(!to.isAdded()){
                //没有被添加
                //from隐藏
                if(from!=null){
                    ft.hide(from);
                }
                //添加to
                ft.add(R.id.FlMallContainer,to).commit();

            }else{
                if(from!=null){
                    ft.hide(from);
                }
                ft.show(to).commit();
            }

        }
    }
    /**
     * @return
     * 根据位置得到响应的Fragment
     */
    private BaseFragment getFragment() {
        BaseFragment fragment=baseFragments.get(position);
        return fragment;
    }


    /**
     * 设置状态栏
     */
    private void setBar() {
        StatusBarUtil.setTranslucentForImageViewAndDarkFont(this,0,null);
    }
}
