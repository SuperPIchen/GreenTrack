package com.superpichen.mainlibrary.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shehuan.niv.NiceImageView;
import com.stx.xhb.androidx.XBanner;
import com.stx.xhb.androidx.transformers.Transformer;
import com.superpichen.mainlibrary.R;
import com.superpichen.mainlibrary.Tools.JavaTools.BaseFragment;
import com.superpichen.mainlibrary.Tools.JavaTools.FraOnlineXbanner1Info;
import com.superpichen.mainlibrary.Tools.JavaTools.FraOnlineXbanner2Info;
import com.superpichen.mainlibrary.Tools.JavaTools.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OnlineShopFragment extends BaseFragment {
    private ImageView IvFraOnlineChange;
    private NiceImageView NvFraOnlineUser;
    private XBanner XbFraOnlineFirst;
    private XBanner XbFraOnlineSecond;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-02-08 18:04:59 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(View view) {
        IvFraOnlineChange = (ImageView)view.findViewById( R.id.IvFraOnlineChange );
        NvFraOnlineUser = (NiceImageView)view.findViewById( R.id.NvFraOnlineUser );
        XbFraOnlineFirst = (XBanner)view.findViewById( R.id.XbFraOnlineFirst );
        XbFraOnlineSecond = view.findViewById(R.id.XbFraOnlineSecond);
    }


    public OnlineShopFragment() {
        // Required empty public constructor
    }

    @Override
    protected View initView() {
        View view=View.inflate(getContext(),R.layout.fragment_online_shop,null);
        findViews(view);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        setBanner1();
        setBanner2();
    }

    /**
     * 设置第二条Banner
     */
    private void setBanner2() {
        List<FraOnlineXbanner2Info> data2=new ArrayList<>();
        data2.add(new FraOnlineXbanner2Info("绿萝-附有灵气的植物","让家园水灵灵",1.2,R.drawable.fraonlinebanner2_1,
                "樱桃蛋糕-体力恢复×2","款待你的碳宠物",2,R.drawable.fraonlinebanner2_2,
                "哈士奇-顽皮的碳宠物","让你的家园充满活力",19.9,R.drawable.fraonlinebanner2_3));
        data2.add(new FraOnlineXbanner2Info("甜甜圈-小心宠物蛀牙","甜蜜的味道",1.5,R.drawable.fraonlinebanner2_4,
                "小野花-庄园中的一抹红","点缀你的家园",3,R.drawable.fraonlinebanner2_5,
                "小白猫-冷库的温暖","不小心酷到你",29.9,R.drawable.fraonlinebanner2_6));
        data2.add(new FraOnlineXbanner2Info("仙人掌-不用太操心他","顽强的植物",1.9,R.drawable.fraonlinebanner2_7,
                "小恐龙-绿油油的\"猛兽\"","可爱的侏罗纪动物",49.9,R.drawable.fraonlinebanner2_8,
                "冰淇淋-酷暑一夏","最好的甜品",1.8,R.drawable.fraonlinebanner2_9));
        XbFraOnlineSecond.setPageTransformer(Transformer.Default);
        XbFraOnlineSecond.setBannerData(R.layout.item_fraonline_xbanner_second,data2);
        XbFraOnlineSecond.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                NiceImageView NvItemFraOnline1= view.findViewById( R.id.NvItemFraOnline1 );
                TextView TvItemFraOnlineBanner2Title1= view.findViewById( R.id.TvItemFraOnlineBanner2Title1 );
                TextView TvItemFraOnlineBanner2Tag1= view.findViewById( R.id.TvItemFraOnlineBanner2Tag1 );
                TextView TvFraOnlineBanner2Money1= view.findViewById( R.id.TvFraOnlineBanner2Money1 );
                NiceImageView NvItemFraOnline2= view.findViewById( R.id.NvItemFraOnline2 );;
                TextView TvItemFraOnlineBanner2Title2= view.findViewById( R.id.TvItemFraOnlineBanner2Title2 );;
                TextView TvItemFraOnlineBanner2Tag2= view.findViewById( R.id.TvItemFraOnlineBanner2Tag2 );
                TextView TvFraOnlineBanner2Money2= view.findViewById( R.id.TvFraOnlineBanner2Money2 );
                NiceImageView NvItemFraOnline3= view.findViewById( R.id.NvItemFraOnline3 );
                TextView TvItemFraOnlineBanner2Title3= view.findViewById( R.id.TvItemFraOnlineBanner2Title3 );
                TextView TvItemFraOnlineBanner2Tag3= view.findViewById( R.id.TvItemFraOnlineBanner2Tag3 );
                TextView TvFraOnlineBanner2Money3= view.findViewById( R.id.TvFraOnlineBanner2Money3 );
                NvItemFraOnline1.setImageResource(((FraOnlineXbanner2Info)model).getImg1());
                TvItemFraOnlineBanner2Title1.setText(((FraOnlineXbanner2Info)model).getTitle1());
                TvItemFraOnlineBanner2Tag1.setText(((FraOnlineXbanner2Info)model).getTag1());
                TvFraOnlineBanner2Money1.setText(((FraOnlineXbanner2Info)model).getMoney1()+"币");
                NvItemFraOnline2.setImageResource(((FraOnlineXbanner2Info)model).getImg2());
                TvItemFraOnlineBanner2Title2.setText(((FraOnlineXbanner2Info)model).getTitle2());
                TvItemFraOnlineBanner2Tag2.setText(((FraOnlineXbanner2Info)model).getTag2());
                TvFraOnlineBanner2Money2.setText(((FraOnlineXbanner2Info)model).getMoney2()+"币");
                NvItemFraOnline3.setImageResource(((FraOnlineXbanner2Info)model).getImg3());
                TvItemFraOnlineBanner2Title3.setText(((FraOnlineXbanner2Info)model).getTitle3());
                TvItemFraOnlineBanner2Tag3.setText(((FraOnlineXbanner2Info)model).getTag3());
                TvFraOnlineBanner2Money3.setText(((FraOnlineXbanner2Info)model).getMoney3()+"币");

            }
        });
        XbFraOnlineSecond.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {

            }
        });
    }

    /**
     * 设置第一条Banner
     */
    private void setBanner1() {
        List<FraOnlineXbanner1Info> data1=new ArrayList<>();
        data1.add(new FraOnlineXbanner1Info("限时特惠","哆啦A梦地铁卡：带你梦回童年","每周免费乘车一次",R.drawable.fraonlinebanner1));
        data1.add(new FraOnlineXbanner1Info("宠物上新","碳宠物需要你的照顾","陪伴您的旅程",R.drawable.fraonlinebanner2));
        data1.add(new FraOnlineXbanner1Info("生活用品","更多用品等你探索","简约而不简单",R.drawable.fraonlinebanner3));
        data1.add(new FraOnlineXbanner1Info("帮你省钱","Green鲜果店代金券","维C你的生活",R.drawable.fraonlinebanner4));
        XbFraOnlineFirst.setPageTransformer(Transformer.Default);
        XbFraOnlineFirst.setBannerData(R.layout.item_fraonline_xbanner_first,data1);
        XbFraOnlineFirst.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                TextView TvItemFraOnlineBanner1Tag= view.findViewById( R.id.TvItemFraOnlineBanner1Tag );
                TextView TvItemFraOnlineBanner1Title= view.findViewById( R.id.TvItemFraOnlineBanner1Title );
                TextView TvItemFraOnlineBanner1Extratext= view.findViewById( R.id.TvItemFraOnlineBanner1Extratext );
                NiceImageView NvFraOnlineBanner1= view.findViewById( R.id.NvFraOnlineBanner1 );
                TvItemFraOnlineBanner1Tag.setText(((FraOnlineXbanner1Info)model).getTag());
                TvItemFraOnlineBanner1Title.setText(((FraOnlineXbanner1Info)model).getTitle());
                TvItemFraOnlineBanner1Extratext.setText(((FraOnlineXbanner1Info)model).getExtratext());
                NvFraOnlineBanner1.setImageResource(((FraOnlineXbanner1Info)model).getImg());
            }
        });
        XbFraOnlineFirst.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {

            }
        });
    }
}
