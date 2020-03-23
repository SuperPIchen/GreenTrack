package com.superpichen.mainlibrary.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.superpichen.mainlibrary.R;
import com.superpichen.mainlibrary.Tools.JavaTools.BaseFragment;
import com.superpichen.mainlibrary.Tools.JavaTools.SocialFriendInfo;
import com.superpichen.mainlibrary.Tools.JavaTools.SocialFriendListViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SocialFriendFragment extends BaseFragment {
    private FrameLayout FlSocialFriendContainer;
    private ImageView IvSocialAddFriend;
    private ImageView IvSocialMarry;
    private ImageView IvSocialFriend;
    private ListView LvSocialFriend;
    private RelativeLayout RlSocialSearchContainer;
    private RelativeLayout RlSocialMarryContainer;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-03-22 21:53:35 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(View view) {
        FlSocialFriendContainer = view.findViewById( R.id.FlSocialFriendContainer );
        IvSocialAddFriend = view.findViewById( R.id.IvSocialAddFriend );
        IvSocialMarry = view.findViewById( R.id.IvSocialMarry );
        IvSocialFriend = view.findViewById( R.id.IvSocialFriend );
        LvSocialFriend = view.findViewById(R.id.LvSocialFriend);
        RlSocialSearchContainer = view.findViewById(R.id.RlSocialSearchContainer);
        RlSocialMarryContainer = view.findViewById(R.id.RlSocialMarryContainer);
    }

    public SocialFriendFragment() {
        // Required empty public constructor
    }

    @Override
    protected View initView() {
        View view=View.inflate(getContext(),R.layout.fragment_social_friend,null);
        findViews(view);
        return view;
    }

    private List<SocialFriendInfo> socialFriendInfoList;
    @Override
    protected void initData() {
        super.initData();

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

        setFriendListView();
        setSearchView();
        setMarryView();

        setOnClick();
    }

    /**
     * 设置结婚界面
     */
    private void setMarryView() {

    }

    /**
     * 设置搜索视图
     */
    private void setSearchView() {
        View view=View.inflate(getContext(),R.layout.item_social_addfriend,RlSocialSearchContainer);

    }

    /**
     * 设置朋友排名ListView
     */
    private void setFriendListView() {
        SocialFriendListViewAdapter socialFriendListViewAdapter=new SocialFriendListViewAdapter(getContext(),socialFriendInfoList);
        LvSocialFriend.setAdapter(socialFriendListViewAdapter);
    }

    /**
     * 设置监听
     */
    private List<View> friendViews;
    private void setOnClick() {
        friendViews=new ArrayList<>();
        friendViews.add(RlSocialSearchContainer);
        friendViews.add(LvSocialFriend);
        friendViews.add(RlSocialMarryContainer);
        IvSocialAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to=0;
                switchView();
            }
        });

        IvSocialFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to=1;
                switchView();
            }
        });
        IvSocialMarry.setOnClickListener(new View.OnClickListener() {
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
            friendViews.get(pre).setVisibility(View.INVISIBLE);
            friendViews.get(to).setVisibility(View.VISIBLE);
            pre=to;
        }
    }
}
