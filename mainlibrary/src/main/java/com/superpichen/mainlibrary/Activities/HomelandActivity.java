package com.superpichen.mainlibrary.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.superpichen.mainlibrary.MyView.MyFonts.HanbiaoshuangjiancutiFont;
import com.superpichen.mainlibrary.MyView.TopBar.StatusBarUtil;
import com.superpichen.mainlibrary.R;

import net.frakbot.jumpingbeans.JumpingBeans;

import java.util.Random;

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

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-03-19 21:12:50 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        GvHomelandPet = (GifImageView)findViewById( R.id.GvHomelandPet );
        IvHomelandPen = (ImageView)findViewById( R.id.IvHomelandPen );
        IvHomelandBag = (ImageView)findViewById( R.id.IvHomelandBag );
        TvHomelandLoad = (HanbiaoshuangjiancutiFont)findViewById( R.id.TvHomelandLoad );
        PbHomelandLixi = (BGAProgressBar)findViewById( R.id.PbHomelandLixi );
        FlHomelandAwardContainer = (FrameLayout)findViewById( R.id.FlHomelandAwardContainer );
        FlHomelandGoodsContainer = (FrameLayout)findViewById( R.id.FlHomelandGoodsContainer );
        PbHomelandFood = (BGAProgressBar)findViewById( R.id.PbHomelandFood );
        PbHomelandHealth = (BGAProgressBar)findViewById( R.id.PbHomelandHealth );
        PbHomelandMood = (BGAProgressBar)findViewById( R.id.PbHomelandMood );
        IvHomelandBall = findViewById(R.id.IvHomelandBall);
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
        setAnimation();
        setOnClick();
    }

    /**
     * 设置监听
     */
    private void setOnClick() {
        IvHomelandPen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
     * 载入数据
     */
    private int foodPercent;
    private int healthPercent;
    private int moodPercent;
    private int producePercent;
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
    private void setAwardViews() {
        View view=View.inflate(this,R.layout.item_homeland_container,FlHomelandAwardContainer);
        ImageView IvItemHomelandPic1;
        ImageView IvItemHomelandPic2;
        ImageView IvItemHomelandPic3;
        ImageView IvItemHomelandPic4;
        ImageView IvItemHomelandPic5;
        ImageView IvItemHomelandPic6;
        IvItemHomelandPic1 = view.findViewById( R.id.IvItemHomelandPic1 );
        IvItemHomelandPic2 = view.findViewById( R.id.IvItemHomelandPic2 );
        IvItemHomelandPic3 = view.findViewById( R.id.IvItemHomelandPic3 );
        IvItemHomelandPic4 = view.findViewById( R.id.IvItemHomelandPic4 );
        IvItemHomelandPic5 = view.findViewById( R.id.IvItemHomelandPic5 );
        IvItemHomelandPic6 = view.findViewById( R.id.IvItemHomelandPic6 );
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
