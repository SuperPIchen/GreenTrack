package com.superpichen.mainlibrary.Fragments;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.shehuan.niv.NiceImageView;
import com.stx.xhb.androidx.XBanner;
import com.stx.xhb.androidx.transformers.Transformer;
import com.superpichen.mainlibrary.Activities.MallActivity;
import com.superpichen.mainlibrary.R;
import com.superpichen.mainlibrary.Tools.JavaTools.BaseFragment;
import com.superpichen.mainlibrary.Tools.JavaTools.FraOfflineXbannerInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfflineShopFragment extends BaseFragment {

    private ImageView IvFraOfflineChange;
    private NiceImageView NvFraOnlineUser;
    private HorizontalScrollView wuyong4;
    private TextView TvFraOfflineShouye;
    private TextView TvFraOfflineBaihuo;
    private TextView TvFraOfflineFuzhuang;
    private TextView TvFraOfflineShengxian;
    private TextView TvFraOfflineShipin;
    private TextView TvFraOfflineDianqi;
    private TextView TvFraOfflineXiangbao;
    private TextView TvFraOfflineShuma;
    private TextView TvFraOfflineJiazhuang;
    private TextView TvFraOfflineChepin;
    private XBanner XbFraOfflineContainer;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-02-20 01:31:59 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(View view) {
        IvFraOfflineChange = view.findViewById( R.id.IvFraOfflineChange );
        NvFraOnlineUser = view.findViewById( R.id.NvFraOnlineUser );
        wuyong4 = view.findViewById( R.id.wuyong4 );
        TvFraOfflineShouye = view.findViewById( R.id.TvFraOfflineShouye );
        TvFraOfflineBaihuo = view.findViewById( R.id.TvFraOfflineBaihuo );
        TvFraOfflineFuzhuang = view.findViewById( R.id.TvFraOfflineFuzhuang );
        TvFraOfflineShengxian = view.findViewById( R.id.TvFraOfflineShengxian );
        TvFraOfflineShipin = view.findViewById( R.id.TvFraOfflineShipin );
        TvFraOfflineDianqi = view.findViewById( R.id.TvFraOfflineDianqi );
        TvFraOfflineXiangbao = view.findViewById( R.id.TvFraOfflineXiangbao );
        TvFraOfflineShuma = view.findViewById( R.id.TvFraOfflineShuma );
        TvFraOfflineJiazhuang = view.findViewById( R.id.TvFraOfflineJiazhuang );
        TvFraOfflineChepin = view.findViewById( R.id.TvFraOfflineChepin );
        XbFraOfflineContainer = view.findViewById( R.id.XbFraOfflineContainer );
    }


    public OfflineShopFragment() {
        // Required empty public constructor
    }

    private MallActivity mallActivity;
    public void inputData(MallActivity mallActivity){
        this.mallActivity=mallActivity;
    }

    @Override
    protected View initView() {
        View view=View.inflate(getContext(),R.layout.fragment_offline_shop,null);
        findViews(view);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        setText(null,TvFraOfflineShouye);
        setOnClick();
        setBanner();
    }

    /**
     * 设置Banner条
     */
    private void setBanner() {
        List<FraOfflineXbannerInfo> data=new ArrayList<>();
        List<Integer> advertisrImg=new ArrayList<>();
        advertisrImg.add(R.drawable.fraonlinebanner1);
        advertisrImg.add(R.drawable.fraonlinebanner2);
        data.add(new FraOfflineXbannerInfo(advertisrImg));
        XbFraOfflineContainer.setPageTransformer(Transformer.Default);
        XbFraOfflineContainer.setBannerData(R.layout.item_fraonline_xbanner_second,data);
        XbFraOfflineContainer.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                if(position==0){

                }
            }
        });
    }

    private int nowPosition=0;
    /**
     * 设置监听
     */
    private void setOnClick() {
        List<TextView> textViewList=new ArrayList<>();
        textViewList.add(TvFraOfflineShouye);
        textViewList.add(TvFraOfflineBaihuo);
        textViewList.add(TvFraOfflineFuzhuang);
        textViewList.add(TvFraOfflineShengxian);
        textViewList.add(TvFraOfflineShipin);
        textViewList.add(TvFraOfflineDianqi);
        textViewList.add(TvFraOfflineXiangbao);
        textViewList.add(TvFraOfflineShuma);
        textViewList.add(TvFraOfflineJiazhuang);
        textViewList.add(TvFraOfflineChepin);
        for(int i=0;i<textViewList.size();i++){
            int finalI = i;
            textViewList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setText(textViewList.get(nowPosition), (TextView) v);
                    nowPosition= finalI;
                }
            });
        }

        IvFraOfflineChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mallActivity.switchFragmentInFragment(1);
            }
        });
    }

    /**
     * @param from
     * @param to
     * 设置选择栏字体样式
     */
    private void setText(TextView from,TextView to){
        if(from!=null){
            from.setTextSize(20);
            from.setTextColor(Color.BLACK);
            from.setTypeface(Typeface.SANS_SERIF,Typeface.NORMAL);
        }
        to.setTextSize(25);
        to.setTextColor(0xff3D84FD);
        to.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);
    }
}
