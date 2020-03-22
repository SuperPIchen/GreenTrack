package com.superpichen.mainlibrary.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.superpichen.mainlibrary.Fragments.OfflineShopFragment;
import com.superpichen.mainlibrary.Fragments.OnlineShopFragment;
import com.superpichen.mainlibrary.Fragments.SocialFamilyFragment;
import com.superpichen.mainlibrary.Fragments.SocialFriendFragment;
import com.superpichen.mainlibrary.MyView.TopBar.StatusBarUtil;
import com.superpichen.mainlibrary.R;
import com.superpichen.mainlibrary.Tools.JavaTools.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class SocialActivity extends FragmentActivity {
    private TextView TvSocialFriend;
    private TextView TvSocialFamily;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-03-22 18:58:02 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        TvSocialFriend = (TextView)findViewById( R.id.TvSocialFriend );
        TvSocialFamily = (TextView)findViewById( R.id.TvSocialFamily );

        TvSocialFriend.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        TvSocialFriend.setTextSize(20);
        TvSocialFamily.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        TvSocialFamily.setTextSize(18);
    }

    private FragmentTransaction ft;
    //    选中对用Fragment的对应的位置
    private int position=0;
    //    上次切换的Fragment
    private Fragment mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        StatusBarUtil.setTranslucentForImageView(this,0,null);
        findViews();
        initFragment();
        setOnClick();
    }

    /**
     * 设置监听
     */
    private void setOnClick() {
        TvSocialFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(baseFragments.get(position),socialFriendFragment);
                TvSocialFriend.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                TvSocialFriend.setTextSize(20);
                TvSocialFamily.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                TvSocialFamily.setTextSize(18);
                position=0;
            }
        });
        TvSocialFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(baseFragments.get(position),socialFamilyFragment);
                TvSocialFamily.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                TvSocialFamily.setTextSize(20);
                TvSocialFriend.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                TvSocialFriend.setTextSize(18);
                position=1;
            }
        });
    }

    /**
     * 初始化碎片
     */
    private int defaultPage=0;
    private List<BaseFragment> baseFragments;
    private SocialFriendFragment socialFriendFragment;
    private SocialFamilyFragment socialFamilyFragment;
    private void initFragment() {
        socialFriendFragment=new SocialFriendFragment();
        socialFamilyFragment=new SocialFamilyFragment();
        baseFragments=new ArrayList<>();
        baseFragments.add(socialFriendFragment);
        baseFragments.add(socialFamilyFragment);
        ft=getSupportFragmentManager().beginTransaction();
        for(int i=0;i<baseFragments.size();i++){
            if(!baseFragments.get(i).isAdded())
                ft.add(R.id.FlSocialContainer,baseFragments.get(i),"MyFragmentTag"+i);
        }
        switchFragment(socialFamilyFragment,socialFriendFragment);
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
                ft.add(R.id.FlSocialContainer,to).show(to).commit();
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
}
