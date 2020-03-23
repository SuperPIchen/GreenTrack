package com.superpichen.mainlibrary.Fragments;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nightonke.jellytogglebutton.JellyToggleButton;
import com.superpichen.mainlibrary.R;
import com.superpichen.mainlibrary.Tools.JavaTools.BaseFragment;
import com.superpichen.mainlibrary.Tools.JavaTools.SocialFamilyChengyuanListViewAdapter;
import com.superpichen.mainlibrary.Tools.JavaTools.SocialFriendInFamilyListViewAdapter;
import com.superpichen.mainlibrary.Tools.JavaTools.SocialFriendInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SocialFamilyFragment extends BaseFragment {
    private RelativeLayout RlSocialFamilyContainer;
    private TextView TvSocialFamilyPoint;
    private RelativeLayout RlSocialFamilyAdd;
    private EditText EtSocialFamily;
    private ImageView IvSocialFamilySearch;
    private ListView LvSocialFriendInFamily;
    private RelativeLayout RlSocialQuanxian;
    private JellyToggleButton TbSocialSharePoint;
    private JellyToggleButton TbSocialShareAction;
    private ListView LvSocialChengyaun;
    private ImageView IvSocialAddFamily;
    private ImageView IvSocialQuanxian;
    private ImageView IvSocialFamily;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-03-23 09:20:34 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(View view) {
        RlSocialFamilyContainer = view.findViewById( R.id.RlSocialFamilyContainer );
        TvSocialFamilyPoint = view.findViewById( R.id.TvSocialFamilyPoint );
        RlSocialFamilyAdd = view.findViewById( R.id.RlSocialFamilyAdd );
        EtSocialFamily = view.findViewById( R.id.EtSocialFamily );
        IvSocialFamilySearch = view.findViewById( R.id.IvSocialFamilySearch );
        LvSocialFriendInFamily = view.findViewById( R.id.LvSocialFriendInFamily );
        RlSocialQuanxian = view.findViewById( R.id.RlSocialQuanxian );
        TbSocialSharePoint = view.findViewById( R.id.TbSocialSharePoint );
        TbSocialShareAction = view.findViewById( R.id.TbSocialShareAction );
        LvSocialChengyaun = view.findViewById( R.id.LvSocialChengyaun );
        IvSocialAddFamily = view.findViewById( R.id.IvSocialAddFamily );
        IvSocialQuanxian = view.findViewById( R.id.IvSocialQuanxian );
        IvSocialFamily = view.findViewById( R.id.IvSocialFamily );
    }



    public SocialFamilyFragment() {
        // Required empty public constructor
    }

    @Override
    protected View initView() {
        View view=View.inflate(getContext(),R.layout.fragment_social_family,null);
        findViews(view);
        return view;
    }

    private List<SocialFriendInfo> socialFriendInfoList;
    private List<SocialFriendInfo> familyChengyuanList;
    @Override
    protected void initData() {
        super.initData();
        setAnimation();
        socialFriendInfoList=new ArrayList<>();
        socialFriendInfoList.add(new SocialFriendInfo(1,R.drawable.touxiang1,"冰块",18,1312.1));
        socialFriendInfoList.add(new SocialFriendInfo(2,R.drawable.touxiang2,"张天天",6,699.5));
        socialFriendInfoList.add(new SocialFriendInfo(3,R.drawable.touxiang3,"Klay",12,600.6));
        socialFriendInfoList.add(new SocialFriendInfo(4,R.drawable.touxiang4,"茶叶蛋欧巴",3,504.4));
        socialFriendInfoList.add(new SocialFriendInfo(5,R.drawable.touxiang5,"我就是闹心",15,404.4));
        socialFriendInfoList.add(new SocialFriendInfo(6,R.drawable.touxiang6,"Noah",1,330.3));socialFriendInfoList.add(new SocialFriendInfo(4,R.drawable.touxiang4,"茶叶蛋欧巴",3,504.4));
        socialFriendInfoList.add(new SocialFriendInfo(7,R.drawable.touxiang3,"心上",11,232.1));
        socialFriendInfoList.add(new SocialFriendInfo(8,R.drawable.touxiang5,"潮水",10,199.0));socialFriendInfoList.add(new SocialFriendInfo(4,R.drawable.touxiang4,"茶叶蛋欧巴",3,504.4));
        socialFriendInfoList.add(new SocialFriendInfo(9,R.drawable.touxiang1,"做梦",1,120.1));
        socialFriendInfoList.add(new SocialFriendInfo(10,R.drawable.touxiang2,"啦啦啦",2,55.5));
        socialFriendInfoList.add(new SocialFriendInfo(11,R.drawable.touxiang4,"Mo",1,30.3));
        socialFriendInfoList.add(new SocialFriendInfo(12,R.drawable.touxiang6,"二哈",3,10.1));

        familyChengyuanList=new ArrayList<>();
        familyChengyuanList.add(new SocialFriendInfo(3,R.drawable.touxiang3,"女儿",12,600.6));
        familyChengyuanList.add(new SocialFriendInfo(4,R.drawable.touxiang4,"爸爸",3,504.4));
        familyChengyuanList.add(new SocialFriendInfo(5,R.drawable.touxiang5,"老婆",15,404.4));
        setFriendInFamilyListView();
        setChengyuanListView();
        setOnClick();
    }

    /**
     * 设置监听
     */
    private List<View> familyViews;
    private void setOnClick() {
        familyViews=new ArrayList<>();
        familyViews.add(RlSocialFamilyAdd);
        familyViews.add(RlSocialFamilyContainer);
        familyViews.add(RlSocialQuanxian);
        IvSocialAddFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to=0;
                switchView();
            }
        });
        IvSocialFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to=1;
                switchView();
            }
        });
        IvSocialQuanxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to=2;
                switchView();
            }
        });
    }

    /**
     * 选择显示哪个界面
     */
    private int pre=1;
    private int to;
    void switchView(){
        if(to!=pre){
            familyViews.get(pre).setVisibility(View.INVISIBLE);
            familyViews.get(to).setVisibility(View.VISIBLE);
            pre=to;
        }
    }

    /**
     * 设置权限管理界面的ListView
     */
    private void setChengyuanListView() {
        SocialFamilyChengyuanListViewAdapter adapter=new SocialFamilyChengyuanListViewAdapter(getContext(),familyChengyuanList);
        LvSocialChengyaun.setAdapter(adapter);
    }

    /**
     * 设置搜索里的ListView
     */
    private void setFriendInFamilyListView() {
        SocialFriendInFamilyListViewAdapter adapter=new SocialFriendInFamilyListViewAdapter(getContext(),socialFriendInfoList);
        LvSocialFriendInFamily.setAdapter(adapter);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    TvSocialFamilyPoint.startAnimation(PaoPaoTranslateAnimationSet);
                    handler.sendEmptyMessageDelayed(1,2000);
                    break;
            }
        }
    };

    /**
     * 设置动画
     */
    //设置背包浮动动画
    private AnimationSet PaoPaoTranslateAnimationSet =new AnimationSet(true);
    private void setAnimation() {
        TranslateAnimation PaoPaoTranslateAnimationUp1 =new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.02f);
        TranslateAnimation PaoPaoTranslateAnimationDown =new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.02f,Animation.RELATIVE_TO_PARENT,-0.02f);
        TranslateAnimation PaoPaoTranslateAnimationUp2 =new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,-0.02f,Animation.RELATIVE_TO_PARENT,0);
        PaoPaoTranslateAnimationUp1.setDuration(500);
        PaoPaoTranslateAnimationUp1.setFillAfter(true);
        PaoPaoTranslateAnimationDown.setStartOffset(500);
        PaoPaoTranslateAnimationDown.setDuration(1000);
        PaoPaoTranslateAnimationDown.setFillAfter(true);
        PaoPaoTranslateAnimationUp2.setStartOffset(1500);
        PaoPaoTranslateAnimationUp2.setDuration(500);
        PaoPaoTranslateAnimationUp2.setFillAfter(true);
        PaoPaoTranslateAnimationSet.addAnimation(PaoPaoTranslateAnimationUp1);
        PaoPaoTranslateAnimationSet.addAnimation(PaoPaoTranslateAnimationDown);
        PaoPaoTranslateAnimationSet.addAnimation(PaoPaoTranslateAnimationUp2);
        PaoPaoTranslateAnimationSet.setFillAfter(true);
        PaoPaoTranslateAnimationSet.setInterpolator(new LinearInterpolator());
        handler.sendEmptyMessage(1);
    }


}
