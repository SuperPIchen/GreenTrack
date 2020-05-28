package com.superpichen.mainlibrary.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.superpichen.mainlibrary.MyView.MyFonts.HanbiaoshuangjiancutiFont;
import com.superpichen.mainlibrary.MyView.TopBar.StatusBarUtil;
import com.superpichen.mainlibrary.R;
import com.superpichen.mainlibrary.Tools.JavaTools.HomelandBagAdapater;
import com.superpichen.mainlibrary.Tools.JavaTools.HomelandGoodsTool;
import com.superpichen.mainlibrary.Tools.JavaTools.HomelandWallInfo;

import net.frakbot.jumpingbeans.JumpingBeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.crypto.spec.IvParameterSpec;

import cn.bingoogolapple.progressbar.BGAProgressBar;
import pl.droidsonroids.gif.GifImageView;

public class HomelandActivity extends AppCompatActivity {
    private GifImageView GvHomelandPet;
    private ImageView IvHomelandPen;
    private ImageView IvHomelandBag;
    private HanbiaoshuangjiancutiFont TvHomelandLoad;
    private BGAProgressBar PbHomelandLixi;
    private FrameLayout FlHomelandAwardContainer;
    private FrameLayout FlHomelandGoodsContainer;
    private BGAProgressBar PbHomelandFood;
    private BGAProgressBar PbHomelandHealth;
    private BGAProgressBar PbHomelandMood;
    private ImageView IvHomelandBall;
    private RelativeLayout RlHomelandGift;
    private HanbiaoshuangjiancutiFont TvHomelandPointSum;
    private HanbiaoshuangjiancutiFont TvHomelandPointJieShao;
    private FrameLayout FlHomelandBagContainer;
    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-03-19 21:12:50 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        GvHomelandPet = findViewById( R.id.GvHomelandPet );
        IvHomelandPen = findViewById( R.id.IvHomelandPen );
        IvHomelandBag = findViewById( R.id.IvHomelandBag );
        TvHomelandLoad = findViewById( R.id.TvHomelandLoad );
        PbHomelandLixi = findViewById( R.id.PbHomelandLixi );
        FlHomelandAwardContainer = findViewById( R.id.FlHomelandAwardContainer );
        FlHomelandGoodsContainer = findViewById( R.id.FlHomelandGoodsContainer );
        PbHomelandFood = findViewById( R.id.PbHomelandFood );
        PbHomelandHealth = findViewById( R.id.PbHomelandHealth );
        PbHomelandMood = findViewById( R.id.PbHomelandMood );
        IvHomelandBall = findViewById(R.id.IvHomelandBall);
        RlHomelandGift = findViewById(R.id.RlHomelandGift);
        TvHomelandPointSum = findViewById(R.id.TvHomelandPointSum);
        TvHomelandPointJieShao = findViewById(R.id.TvHomelandPointJieShao);
        FlHomelandBagContainer = findViewById(R.id.FlHomelandBagContainer);
    }


    /**
     * 载入数据
     */
    private List<HomelandGoodsTool> bagGoods;
    private List<HomelandGoodsTool> bagGoods_use;
    private List<HomelandGoodsTool> awardGoods;
    private List<HomelandGoodsTool> goodsGoods;
    private List<HomelandGoodsTool> foodGoods;
    private HomelandWallInfo awardWall;
    private HomelandWallInfo goodsWall;
    private int foodPercent;
    private int healthPercent;
    private int moodPercent;
    private int producePercent;
    private int jichuPoint;
    private boolean[] awardWallisApply;
    private int[] awardWallNum;
    private boolean[] goodsWallisApply;
    private int[] goodsWallNum;
    private void initData() {
        foodPercent=8;
        healthPercent=43;
        moodPercent=79;
        producePercent=89;
        setPercent();
        if(foodPercent<10||healthPercent<10||moodPercent<10){
            TvHomelandLoad.setText("碳宠物罢工啦!!!");
            GvHomelandPet.setImageResource(R.drawable.homeland_pet_rabbit_unhealth);
            handler.removeMessages(2);
        }
        jichuPoint=4;
        bagGoods =new ArrayList<>();
        bagGoods_use=new ArrayList<>();
        awardGoods=new ArrayList<>();
        goodsGoods=new ArrayList<>();
        foodGoods=new ArrayList<>();
        int i=0;
        bagGoods.add(new HomelandGoodsTool(R.drawable.achieventment_zhizhuwang,"蜘蛛网",0.02,"award",++i));
        bagGoods.add(new HomelandGoodsTool(R.drawable.achieventment_aidejiating,"爱的家庭",0.05,"award",++i));
        bagGoods.add(new HomelandGoodsTool(R.drawable.achieventment_fengchidianche,"风驰电掣",0.01,"award",++i));
        bagGoods.add(new HomelandGoodsTool(R.drawable.achieventment_luodichenghe,"落地成盒",0.01,"award",++i));
        bagGoods.add(new HomelandGoodsTool(R.drawable.achieventment_mubuzhuangjing,"目不转睛",0.04,"award",++i));
        bagGoods.add(new HomelandGoodsTool(R.drawable.achieventment_shigonglichongci,"10公里冲刺",0.03,"award",++i));
        bagGoods.add(new HomelandGoodsTool(R.drawable.achieventment_danchexiaonengshou,"单车小能手",0.03,"award",++i));
        bagGoods.add(new HomelandGoodsTool(R.drawable.achieventment_shenghuoxiaonengshou,"生活小能手",0.02,"award",++i));
        bagGoods.add(new HomelandGoodsTool(R.drawable.achieventment_xiangcaopubi,"香草扑鼻",0.01,"award",++i));
        bagGoods.add(new HomelandGoodsTool(R.drawable.achieventment_dongwuguanliyuan,"动物管理员",0.04,"award",++i));
        bagGoods.add(new HomelandGoodsTool(R.drawable.achieventment_aixinmengdong,"爱心萌动",0.02,"award",++i));
        bagGoods.add(new HomelandGoodsTool(R.drawable.achieventment_yikexiaocao,"一颗小草",0.01,"award",++i));
        bagGoods.add(new HomelandGoodsTool(R.drawable.goods_bangbangtang,"棒棒糖",0.02,"goods",++i));
        bagGoods.add(new HomelandGoodsTool(R.drawable.goods_xiangquan,"项圈",0.01,"goods",++i));
        bagGoods.add(new HomelandGoodsTool(R.drawable.food_coke1,"蛋糕",0.2,"food",++i));
        bagGoods.add(new HomelandGoodsTool(R.drawable.food_coke2,"蛋糕",0.3,"food",++i));
        bagGoods.add(new HomelandGoodsTool(R.drawable.food_xuegao,"雪糕",0.3,"food",++i));
        for(int j=0;j<bagGoods.size();j++){
            switch (bagGoods.get(j).getType()){
                case "award":
                    awardGoods.add(bagGoods.get(j));
                    break;
                case "food":
                    foodGoods.add(bagGoods.get(j));
                    break;
                case "goods":
                    goodsGoods.add(bagGoods.get(j));
            }
        }
        awardWallisApply=new boolean[]{true,false,true,false,false,false};
        awardWallNum=new int[]{2,0,1,0,0,0};
        awardWall=new HomelandWallInfo("award",awardWallisApply,awardWallNum);
        goodsWallisApply=new boolean[]{false,false,false,true,false,false};
        goodsWallNum=new int[]{0,0,0,14,0,0};
        goodsWall=new HomelandWallInfo("good",goodsWallisApply,goodsWallNum);
    }

    /**
     * 改变RecycleView数据
     */
    private void ReSetBagData() {
        for(int i=0;i<bagGoods_use.size();i++){
            bagGoods_use.remove(i);
            i--;
        }
        bagGoods_use.addAll(bagGoods);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeland);
        setExtraView();
        findViews();
        initData();
        changeData();
        setGoodsViews();
        setAwardViews();
        setBag();
        setAnimation();
        setOnClick();

    }


    /**
     * 设置背包
     */
    private boolean isBagShow=false;
    private RecyclerView goodsRecycleView;
    private HomelandBagAdapater homelandBagAdapater;
    private ImageView IvHomelandBagAward;
    private ImageView IvHomelandBagGoods;
    private ImageView IvHomelandBagFood;
    private boolean buttonAwardIspressed=true;
    private boolean buttonFoodIspressed=false;
    private boolean buttonGoodsIspressed=false;
    private void setBag() {
        goodsRecycleView =new RecyclerView(this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        goodsRecycleView.setLayoutManager(staggeredGridLayoutManager);
        ReSetBagData();
        homelandBagAdapater=new HomelandBagAdapater(this,bagGoods_use);
        UpdateAdapter("award");
        goodsRecycleView.setAdapter(homelandBagAdapater);
        View view=View.inflate(this,R.layout.item_homeland_bag,FlHomelandBagContainer);
        FrameLayout FlItenRecycleViewContainer;
        FlItenRecycleViewContainer = view.findViewById(R.id.FlItenRecycleViewContainer);
        IvHomelandBagAward = view.findViewById(R.id.IvHomelandBagAward);
        IvHomelandBagGoods = view.findViewById(R.id.IvHomelandBagGoods);
        IvHomelandBagFood = findViewById(R.id.IvHomelandBagFood);
        FlItenRecycleViewContainer.addView(goodsRecycleView);
    }

    private void UpdateAdapter(String type) {
        for(int i=0;i<bagGoods_use.size();i++){
            if(!bagGoods_use.get(i).getType().equals(type)){
                bagGoods_use.remove(bagGoods_use.get(i));
                i--;
            }
        }
        homelandBagAdapater.notifyDataSetChanged();
        homelandBagAdapater.setOnItemClickListener(new HomelandBagAdapater.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(type.equals("award")){
                    int i;
                    for(i=0;i<6;i++){
                        if(awardWallisApply[i])
                            if(awardGoods.get(position).getIndex()==awardWallNum[i])
                                break;
                    }
                    if(i==6){
                        FlHomelandBagContainer.setVisibility(View.INVISIBLE);
                        changeEnable(true);
                        for(int j=0;j<6;j++){
                            awardImgs.get(j).setVisibility(View.VISIBLE);
                            int finalJ = j;
                            awardImgs.get(j).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(awardWallisApply[finalJ]){
                                        AlertDialog dialog=new AlertDialog
                                                .Builder(HomelandActivity.this)
                                                .setMessage("该位置已经有荣誉了，是否进行替换。")
                                                .setPositiveButton("替换", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        awardWallNum[finalJ]=awardGoods.get(position).getIndex();
                                                        awardImgs.get(finalJ).setImageResource(awardGoods.get(position).getImg());
                                                        changeEnable(false);
                                                    }
                                                })
                                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                        changeEnable(false);
                                                    }
                                                })
                                                .show();
                                    }
                                }
                            });
                        }
                    }else {
                        AlertDialog dialog=new AlertDialog
                                .Builder(HomelandActivity.this)
                                .setMessage("此荣誉已经装饰上啦！")
                                .show();
                    }
                }
                if(type.equals("goods")){

                }
                if(type.equals("food")){
                    FlHomelandBagContainer.setVisibility(View.INVISIBLE);
                    isBagShow=false;
                }
            }

            void changeEnable(boolean b){
                for(int i=0;i<6;i++){
                    awardImgs.get(i).setEnabled(b);
                    if(!awardWallisApply[i])
                        awardImgs.get(i).setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //点击完返回键，执行的动作
            if(isBagShow){
                FlHomelandBagContainer.setVisibility(View.INVISIBLE);
                isBagShow=false;
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 设置监听
     */
    private void setOnClick() {
        IvHomelandPen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlHomelandBagContainer.setVisibility(View.VISIBLE);
                isBagShow=true;
                IvHomelandPen.setImageResource(R.drawable.homeland_pen_full);
                GvHomelandPet.setImageResource(R.drawable.homeland_pet_rabbit_eat);
                TvHomelandLoad.setText("拼命产出中...");
                foodPercent+=20;
                if(foodPercent>100)
                    foodPercent=100;
                Random random=new Random();
                int add=random.nextInt(15);
                moodPercent+=add;
                if(moodPercent>100)
                    moodPercent=100;
                PbHomelandFood.setProgress(foodPercent);
                PbHomelandMood.setProgress(moodPercent);
                handler.sendEmptyMessage(2);
            }
        });

        IvHomelandBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GvHomelandPet.setImageResource(R.drawable.homeland_pet_cat_play);
                TvHomelandLoad.setText("拼命产出中...");
                healthPercent+=20;
                if(healthPercent>100)
                    healthPercent=100;
                Random random=new Random();
                int add=random.nextInt(15);
                moodPercent+=add;
                if(moodPercent>100)
                    moodPercent=100;
                PbHomelandHealth.setProgress(healthPercent);
                PbHomelandMood.setProgress(moodPercent);
                handler.sendEmptyMessage(2);
            }
        });

        PbHomelandLixi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PbHomelandLixi.getProgress()==100){
                    producePercent=0;
                    PbHomelandLixi.setProgress(0);
                    RlHomelandGift.setVisibility(View.VISIBLE);
                    isBagShow=true;

                }
            }
        });

        IvHomelandBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlHomelandBagContainer.setVisibility(View.VISIBLE);
                isBagShow=true;
            }
        });

        IvHomelandBagAward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!buttonAwardIspressed){
                    buttonAwardIspressed=true;
                    buttonFoodIspressed=false;
                    buttonGoodsIspressed=false;
                    IvHomelandBagAward.setImageResource(R.drawable.awardbutton_pressed);
                    IvHomelandBagGoods.setImageResource(R.drawable.goodsbutton_unpressed);
                    IvHomelandBagFood.setImageResource(R.drawable.foodbutton_unpressed);
                    ReSetBagData();
                    UpdateAdapter("award");
                }
            }
        });

        IvHomelandBagGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!buttonGoodsIspressed){
                    buttonAwardIspressed=false;
                    buttonFoodIspressed=false;
                    buttonGoodsIspressed=true;
                    IvHomelandBagAward.setImageResource(R.drawable.awardbutton_unpressed);
                    IvHomelandBagGoods.setImageResource(R.drawable.goodsbutton_pressed);
                    IvHomelandBagFood.setImageResource(R.drawable.foodbutton_unpressed);
                    ReSetBagData();
                    UpdateAdapter("goods");
                }
            }
        });

        IvHomelandBagFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!buttonFoodIspressed){
                    buttonAwardIspressed=false;
                    buttonFoodIspressed=true;
                    buttonGoodsIspressed=false;
                    IvHomelandBagAward.setImageResource(R.drawable.awardbutton_unpressed);
                    IvHomelandBagGoods.setImageResource(R.drawable.goodsbutton_unpressed);
                    IvHomelandBagFood.setImageResource(R.drawable.foodbutton_pressed);
                    ReSetBagData();
                    UpdateAdapter("food");
                }
            }
        });
    }

    /**
     * 多线程改变数据
     */
    private void changeData() {
//        progressThread=new Thread(){
//            @Override
//            public void run() {
//                super.run();
//
//            }
//        };
//        progressThread.start();
//        if(foodPercent>0)
//            foodPercent--;
//        if(healthPercent>0)
//            healthPercent--;
//        if(moodPercent>0)
//            moodPercent--;
        if(producePercent<100)
            producePercent++;
        PbHomelandLixi.setProgress(producePercent);
        handler.sendEmptyMessageDelayed(2,5000);
    }



    /**
     * 设置进度条
     */
    private void setPercent() {
        PbHomelandFood.setProgress(foodPercent);
        PbHomelandHealth.setProgress(healthPercent);
        PbHomelandMood.setProgress(moodPercent);
        PbHomelandLixi.setProgress(producePercent);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    IvHomelandBag.startAnimation(BagTranslateAnimationSet);
                    handler.sendEmptyMessageDelayed(1,2000);
                    break;
                case 2:
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            changeData();
                        }
                    });

            }
        }
    };
    /**
     * 添加动画（包括文字跳动）
     */
    //设置背包浮动动画
    TranslateAnimation BagTranslateAnimationUp1 =new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.02f);
    TranslateAnimation BagTranslateAnimationDown =new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.02f,Animation.RELATIVE_TO_PARENT,-0.02f);
    TranslateAnimation BagTranslateAnimationUp2 =new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,-0.02f,Animation.RELATIVE_TO_PARENT,0);
    AnimationSet BagTranslateAnimationSet =new AnimationSet(true);
    private void setAnimation() {
        JumpingBeans.with(TvHomelandLoad)
                .makeTextJump(TvHomelandLoad.getText().toString().length()-3,TvHomelandLoad.getText().toString().length())
                .build();
        BagTranslateAnimationUp1.setDuration(500);
        BagTranslateAnimationUp1.setFillAfter(true);
        BagTranslateAnimationDown.setStartOffset(500);
        BagTranslateAnimationDown.setDuration(1000);
        BagTranslateAnimationDown.setFillAfter(true);
        BagTranslateAnimationUp2.setStartOffset(1500);
        BagTranslateAnimationUp2.setDuration(500);
        BagTranslateAnimationUp2.setFillAfter(true);
        BagTranslateAnimationSet.addAnimation(BagTranslateAnimationUp1);
        BagTranslateAnimationSet.addAnimation(BagTranslateAnimationDown);
        BagTranslateAnimationSet.addAnimation(BagTranslateAnimationUp2);
        BagTranslateAnimationSet.setFillAfter(true);
        BagTranslateAnimationSet.setInterpolator(new LinearInterpolator());
        handler.sendEmptyMessage(1);
    }


    /**
     * 设置勋章栏
     */
    ImageView IvAwardItemHomelandPic1;
    ImageView IvAwardItemHomelandPic2;
    ImageView IvAwardItemHomelandPic3;
    ImageView IvAwardItemHomelandPic4;
    ImageView IvAwardItemHomelandPic5;
    ImageView IvAwardItemHomelandPic6;
    private List<ImageView> awardImgs;
    private void setAwardViews() {
        View view=View.inflate(this,R.layout.item_homeland_container,FlHomelandAwardContainer);
        IvAwardItemHomelandPic1 = view.findViewById( R.id.IvItemHomelandPic1 );
        IvAwardItemHomelandPic2 = view.findViewById( R.id.IvItemHomelandPic2 );
        IvAwardItemHomelandPic3 = view.findViewById( R.id.IvItemHomelandPic3 );
        IvAwardItemHomelandPic4 = view.findViewById( R.id.IvItemHomelandPic4 );
        IvAwardItemHomelandPic5 = view.findViewById( R.id.IvItemHomelandPic5 );
        IvAwardItemHomelandPic6 = view.findViewById( R.id.IvItemHomelandPic6 );
        awardImgs=new ArrayList<>();
        awardImgs.add(IvAwardItemHomelandPic1);
        awardImgs.add(IvAwardItemHomelandPic2);
        awardImgs.add(IvAwardItemHomelandPic3);
        awardImgs.add(IvAwardItemHomelandPic4);
        awardImgs.add(IvAwardItemHomelandPic5);
        awardImgs.add(IvAwardItemHomelandPic6);
        for(int i=0;i<6;i++){
            if(awardWallisApply[i]){
                awardImgs.get(i).setVisibility(View.VISIBLE);
                for(int j=0;j<awardGoods.size();j++){
                    if(awardGoods.get(j).getIndex()==awardWallNum[i]){
                        awardImgs.get(i).setImageResource(awardGoods.get(i).getImg());
                        break;
                    }
                }
            }
        }

    }

    /**
     * 设置物品栏
     */
    private void setGoodsViews() {
        View view=View.inflate(this,R.layout.item_homeland_container,FlHomelandGoodsContainer);
        HanbiaoshuangjiancutiFont TvItemHomelandTitle;
        ImageView IvItemHomelandPic1;
        ImageView IvItemHomelandPic2;
        ImageView IvItemHomelandPic3;
        ImageView IvItemHomelandPic4;
        ImageView IvItemHomelandPic5;
        ImageView IvItemHomelandPic6;
        TvItemHomelandTitle = view.findViewById( R.id.TvItemHomelandTitle );
        IvItemHomelandPic1 = view.findViewById( R.id.IvItemHomelandPic1 );
        IvItemHomelandPic2 = view.findViewById( R.id.IvItemHomelandPic2 );
        IvItemHomelandPic3 = view.findViewById( R.id.IvItemHomelandPic3 );
        IvItemHomelandPic4 = view.findViewById( R.id.IvItemHomelandPic4 );
        IvItemHomelandPic5 = view.findViewById( R.id.IvItemHomelandPic5 );
        IvItemHomelandPic6 = view.findViewById( R.id.IvItemHomelandPic6 );
        TvItemHomelandTitle.setText("物品栏");
        if(goodsWallisApply[0]){
            IvItemHomelandPic1.setVisibility(View.VISIBLE);
            for(int i=0;i<goodsGoods.size();i++){
                if(goodsGoods.get(i).getIndex()==goodsWallNum[0]){
                    IvItemHomelandPic1.setImageResource(goodsGoods.get(i).getImg());
                    break;
                }
            }
        }
        if(goodsWallisApply[1]){
            IvItemHomelandPic2.setVisibility(View.VISIBLE);
            for(int i=0;i<goodsGoods.size();i++){
                if(goodsGoods.get(i).getIndex()==goodsWallNum[1]){
                    IvItemHomelandPic2.setImageResource(goodsGoods.get(i).getImg());
                    break;
                }
            }
        }
        if(goodsWallisApply[2]){
            IvItemHomelandPic3.setVisibility(View.VISIBLE);
            for(int i=0;i<goodsGoods.size();i++){
                if(goodsGoods.get(i).getIndex()==goodsWallNum[2]){
                    IvItemHomelandPic3.setImageResource(goodsGoods.get(i).getImg());
                    break;
                }
            }
        }
        if(goodsWallisApply[3]){
            IvItemHomelandPic4.setVisibility(View.VISIBLE);
            for(int i=0;i<goodsGoods.size();i++){
                if(goodsGoods.get(i).getIndex()==goodsWallNum[3]){
                    IvItemHomelandPic4.setImageResource(goodsGoods.get(i).getImg());
                    break;
                }
            }
        }
        if(goodsWallisApply[4]){
            IvItemHomelandPic5.setVisibility(View.VISIBLE);
            for(int i=0;i<goodsGoods.size();i++){
                if(goodsGoods.get(i).getIndex()==goodsWallNum[4]){
                    IvItemHomelandPic5.setImageResource(goodsGoods.get(i).getImg());
                    break;
                }
            }
        }
        if(goodsWallisApply[5]){
            IvItemHomelandPic6.setVisibility(View.VISIBLE);
            for(int i=0;i<goodsGoods.size();i++){
                if(goodsGoods.get(i).getIndex()==goodsWallNum[5]){
                    IvItemHomelandPic6.setImageResource(goodsGoods.get(i).getImg());
                    break;
                }
            }
        }


    }

    /**
     * 横屏状态下填充状态栏
     */
    private void setExtraView() {
        //设置状态栏
        StatusBarUtil.setTranslucent(this, 0);
        Window window = getWindow();
        // 延伸显示区域到刘海
        WindowManager.LayoutParams lp = window.getAttributes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            window.setAttributes(lp);
            // 设置页面全屏显示
            final View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
