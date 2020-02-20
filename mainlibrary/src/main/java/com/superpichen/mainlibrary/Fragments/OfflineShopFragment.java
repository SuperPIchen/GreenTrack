package com.superpichen.mainlibrary.Fragments;


import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Typeface;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.shehuan.niv.NiceImageView;
import com.stx.xhb.androidx.XBanner;
import com.stx.xhb.androidx.transformers.Transformer;
import com.superpichen.mainlibrary.Activities.MallActivity;
import com.superpichen.mainlibrary.R;
import com.superpichen.mainlibrary.Tools.JavaTools.BaseFragment;
import com.superpichen.mainlibrary.Tools.JavaTools.FraOfflineRecycleInfo;
import com.superpichen.mainlibrary.Tools.JavaTools.FraOfflineXbannerAdvertiseInfo;
import com.superpichen.mainlibrary.Tools.JavaTools.OfflineShopRecycleAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OfflineShopFragment extends BaseFragment {

    private ImageView IvFraOfflineChange;
    private NiceImageView NvFraOnlineUser;
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
    private XBanner XbFraOfflineAdvertiseContainer;
    private RecyclerView RvOfflineInfoCotainer;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-02-20 01:31:59 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(View view) {
        IvFraOfflineChange = view.findViewById( R.id.IvFraOfflineChange );
        NvFraOnlineUser = view.findViewById( R.id.NvFraOnlineUser );
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
        XbFraOfflineAdvertiseContainer = view.findViewById( R.id.XbFraOfflineAdvertiseContainer );
        RvOfflineInfoCotainer = view.findViewById(R.id.RvOfflineInfoCotainer);
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
        setRecyclerView();
    }

    /**
     * 设置详细信息
     */
    private void setRecyclerView() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        RvOfflineInfoCotainer.setLayoutManager(staggeredGridLayoutManager);
        List<FraOfflineRecycleInfo> data1=new ArrayList<>();
        for(int i=0;i<6;i++){
            data1.add(new FraOfflineRecycleInfo(R.drawable.fraofflineshouye1,"TEA&COFF：年轻人的酒庄，年轻但不简单。轻度但不沉默。","静安区南京西路1376号"));
            data1.add(new FraOfflineRecycleInfo(R.drawable.fraofflineshouye2,"姗姗女装店：至雅蕾丝，翩翩共舞。取悦自己，美丽到底。让你做夏日的精灵。贵在品质，雅在品位。","八一南街李渔路口34号"));
            data1.add(new FraOfflineRecycleInfo(R.drawable.fraofflineshouye3,"宜家家居：沉淀经典,智慧之选。智慧沉淀,精品之选。","金发颐高数码广场"));
            data1.add(new FraOfflineRecycleInfo(R.drawable.fraofflineshouye4,"方特乐园：带上孩子，享受来之不易的亲子时光","凤凰路小新广场北门"));
            data1.add(new FraOfflineRecycleInfo(R.drawable.fraofflineshouye5,"啊皮数码城：数码产品开箱的味道，是世界上最好闻的味道。","银泰商城Bin22座34号"));
        }
        OfflineShopRecycleAdapter offlineShopRecycleAdapter=new OfflineShopRecycleAdapter(getContext(),data1);
        RvOfflineInfoCotainer.setAdapter(offlineShopRecycleAdapter);
    }

    /**
     * 设置广告条
     */
    private void setBanner() {
        List<FraOfflineXbannerAdvertiseInfo> data=new ArrayList<>();
        data.add(new FraOfflineXbannerAdvertiseInfo(R.drawable.fraofflineadvertise3));
        data.add(new FraOfflineXbannerAdvertiseInfo(R.drawable.fraofflineadvertise2));
        data.add(new FraOfflineXbannerAdvertiseInfo(R.drawable.fraofflineadvertise1));
        XbFraOfflineAdvertiseContainer.setPageTransformer(Transformer.Default);
        XbFraOfflineAdvertiseContainer.setBannerData(R.layout.item_fraoffline_advertise_xbanner,data);
        XbFraOfflineAdvertiseContainer.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                NiceImageView NvFraOfflineAdvertise = view.findViewById(R.id.NvFraOfflineAdvertise);
                NvFraOfflineAdvertise.setImageResource(((FraOfflineXbannerAdvertiseInfo)model).getImg());
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
