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
    }


    private View view;
    public OnlineShopFragment() {
        // Required empty public constructor
    }

    private List<FraOnlineXbanner1Info> data1=new ArrayList<>();
    @Override
    protected View initView() {
        View view=View.inflate(getContext(),R.layout.fragment_online_shop,null);
        findViews(view);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        data1.add(new FraOnlineXbanner1Info("限时特惠","哆啦A梦地铁卡：带你梦回童年","每周免费乘车一次",R.drawable.fraonlinebanner1));
        data1.add(new FraOnlineXbanner1Info("宠物上新","碳宠物需要你的照顾","陪伴您的旅程",R.drawable.fraonlinebanner2));
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
