package com.superpichen.mainlibrary.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.superpichen.mainlibrary.MyView.MyFonts.HanbiaoshuangjiancutiFont;
import com.superpichen.mainlibrary.MyView.TopBar.StatusBarUtil;
import com.superpichen.mainlibrary.R;
import com.superpichen.mainlibrary.Tools.JavaTools.Dip2px;
import com.superpichen.mainlibrary.Tools.JavaTools.FinalValue;
import com.superpichen.mainlibrary.Tools.JavaTools.PointsInfo;
import com.superpichen.mainlibrary.Tools.JavaTools.SetGetPointsGifOnClick;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class GetPointsActivity extends AppCompatActivity {
    private ImageView IvGetPointsBike;
    private ImageView IvGetPointsBus;
    private ImageView IvGetPointsSubway;
    private RelativeLayout RlGetPointsCoinContainer;
    private TextView TvGetPointsCode;
    private ImageView IvGetPoints404;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-02-04 14:15:00 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        StatusBarUtil.setTranslucentForImageView(this,0,null);
        IvGetPointsBike = (ImageView)findViewById( R.id.IvGetPointsBike );
        IvGetPointsBus = (ImageView)findViewById( R.id.IvGetPointsBus );
        IvGetPointsSubway = (ImageView)findViewById( R.id.IvGetPointsSubway );
        RlGetPointsCoinContainer = (RelativeLayout)findViewById( R.id.RlGetPointsCoinContainer );
        TvGetPointsCode = (TextView) findViewById(R.id.TvGetPointsCode);
        IvGetPoints404 = (ImageView) findViewById(R.id.IvGetPoints404);

        TvGetPointsCode.setText(intent.getExtras().getString("getPoint").toString());
    }

    private List<PointsInfo> pointsInfoList=new ArrayList<>();
    private List<PointsInfo> bikepointsInfoList=new ArrayList<>();
    private List<PointsInfo> buspointsInfoList=new ArrayList<>();
    private List<PointsInfo> subwaypointsInfoList=new ArrayList<>();
    private int bikeCoinCount=0;
    private int bikeSum=0;
    private int busCoinCount=0;
    private int busSum=0;
    private int subwayCoinCount=0;
    private int subwaySum=0;
    private Intent intent=new Intent();
    private Dip2px dip2px;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_points);
        intent=getIntent();
        findViews();
        setData();
        setAnimation();
        loadBikeCoins();
    }

    //加载单车金币
    private void loadBikeCoins() {
        if(bikeCoinCount!=0){
            IvGetPointsBike.startAnimation(bikeTranslateAnimation);
            for(int i=0;i<bikeCoinCount;i++){
                GifImageView gifImageView=new GifImageView(this);
                HanbiaoshuangjiancutiFont codeText=new HanbiaoshuangjiancutiFont(getApplicationContext(),null);
                String text="+"+bikepointsInfoList.get(i).getCount();
                setCoinContainer(i, gifImageView, codeText,text);
                int finalI = i;
                gifImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SetGetPointsGifOnClick.onClick(GetPointsActivity.this,gifImageView,codeText,TvGetPointsCode,bikepointsInfoList.get(finalI));
                        RlGetPointsCoinContainer.removeView(gifImageView);
                        RlGetPointsCoinContainer.removeView(codeText);
                        intent.putExtra("backCode",TvGetPointsCode.getText().toString());
                        setResult(FinalValue.GETPOINTFORGETPOINTSACTIVITY,intent);
                        if(--bikeSum==0){
                            loadBusCoins();
                            IvGetPointsBike.startAnimation(bikeBackAlphaAnimation);
                        }
                    }
                });
            }
        }else
            loadBusCoins();
    }



    //加载公交车金币
    private void loadBusCoins() {
        if(busCoinCount!=0){
            IvGetPointsBus.startAnimation(busTranslateAnimation);
            for(int i=0;i<busCoinCount;i++){
                GifImageView gifImageView=new GifImageView(this);
                HanbiaoshuangjiancutiFont codeText=new HanbiaoshuangjiancutiFont(getApplicationContext(),null);
                String text="+"+buspointsInfoList.get(i).getCount();
                setCoinContainer(i, gifImageView, codeText,text);
                int finalI = i;
                gifImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SetGetPointsGifOnClick.onClick(GetPointsActivity.this,gifImageView,codeText,TvGetPointsCode,buspointsInfoList.get(finalI));
                        RlGetPointsCoinContainer.removeView(gifImageView);
                        RlGetPointsCoinContainer.removeView(codeText);
                        intent.putExtra("backCode",TvGetPointsCode.getText().toString());
                        setResult(FinalValue.GETPOINTFORGETPOINTSACTIVITY,intent);
                        if(--busSum==0){
                            loadSubwayCoins();
                            IvGetPointsBus.startAnimation(busBackAlphaAnimation);
                        }
                    }
                });
            }
        }else
            loadSubwayCoins();
    }

    //加载地铁金币
    private void loadSubwayCoins() {
        if(subwayCoinCount!=0){
            IvGetPointsSubway.startAnimation(subwayScaleAnimation);
            for(int i=0;i<subwayCoinCount;i++){
                GifImageView gifImageView=new GifImageView(this);
                HanbiaoshuangjiancutiFont codeText=new HanbiaoshuangjiancutiFont(getApplicationContext(),null);
                String text="+"+subwaypointsInfoList.get(i).getCount();
                setCoinContainer(i, gifImageView, codeText,text);
                int finalI = i;
                gifImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SetGetPointsGifOnClick.onClick(GetPointsActivity.this,gifImageView,codeText,TvGetPointsCode,subwaypointsInfoList.get(finalI));
                        RlGetPointsCoinContainer.removeView(gifImageView);
                        RlGetPointsCoinContainer.removeView(codeText);
                        intent.putExtra("backCode",TvGetPointsCode.getText().toString());
                        setResult(FinalValue.GETPOINTFORGETPOINTSACTIVITY,intent);
                        if(--subwaySum==0){
                            load404();
                            IvGetPointsSubway.startAnimation(subwayBackAlphaAnimation);
                        }
                    }
                });
            }
        }else{
            load404();
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            IvGetPoints404.startAnimation(iv404AnimationSet);
            handler.sendEmptyMessageDelayed(0,4000);
        }
    };
    private void load404() {
        IvGetPoints404.startAnimation(comeAlphaAnimation);
        handler.sendEmptyMessageDelayed(0,500);
    }

    private void setCoinContainer(int i, GifImageView gifImageView, HanbiaoshuangjiancutiFont codeText,String text) {
        gifImageView.setImageResource(R.drawable.getpointscoin);
        int gifLength=dip2px.dip2px(80);
        codeText.setText(text);
        codeText.setTextColor(0xfff59f1b);
        codeText.setTextSize(dip2px.dip2px(16));
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        RlGetPointsCoinContainer.measure(w, h);
        int height = RlGetPointsCoinContainer.getMeasuredHeight();
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int x = new Random().nextInt(width-gifLength);
        int y = new Random().nextInt(height-gifLength);
        RelativeLayout.LayoutParams gifParams = new RelativeLayout.LayoutParams(gifLength,gifLength);
        RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, gifLength);
        gifParams.leftMargin=x;
        gifParams.topMargin=y;
        textParams.leftMargin=x;
        textParams.topMargin=y+gifLength/4;
        gifImageView.setLayoutParams(gifParams);
        codeText.setLayoutParams(textParams);
        RlGetPointsCoinContainer.addView(gifImageView);
        RlGetPointsCoinContainer.addView(codeText);
        gifImageView.startAnimation(comeAlphaAnimation);
        codeText.setVisibility(View.INVISIBLE);
    }

    //设置动画
    TranslateAnimation bikeTranslateAnimation=new TranslateAnimation(Animation.RELATIVE_TO_SELF,-1f,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0);
    TranslateAnimation busTranslateAnimation=new TranslateAnimation(Animation.RELATIVE_TO_SELF,1f,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0);
    ScaleAnimation subwayScaleAnimation=new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
    AlphaAnimation bikeBackAlphaAnimation =new AlphaAnimation(1,0);
    AlphaAnimation busBackAlphaAnimation =new AlphaAnimation(1,0);
    AlphaAnimation subwayBackAlphaAnimation =new AlphaAnimation(1,0);
    AlphaAnimation comeAlphaAnimation=new AlphaAnimation(0,1);
    TranslateAnimation iv404TranslateAnimationUp1 =new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.02f);
    TranslateAnimation iv404TranslateAnimationDown =new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.02f,Animation.RELATIVE_TO_PARENT,-0.02f);
    TranslateAnimation iv404TranslateAnimationUp2 =new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,-0.02f,Animation.RELATIVE_TO_PARENT,0);
    AnimationSet iv404AnimationSet=new AnimationSet(true);
    private void setAnimation() {
        bikeTranslateAnimation.setDuration(2000);
        bikeTranslateAnimation.setFillAfter(true);
        busTranslateAnimation.setDuration(2000);
        busTranslateAnimation.setFillAfter(true);
        subwayScaleAnimation.setDuration(2000);
        subwayScaleAnimation.setFillAfter(true);
        bikeBackAlphaAnimation.setDuration(1000);
        bikeBackAlphaAnimation.setFillAfter(true);
        busBackAlphaAnimation.setDuration(1000);
        busBackAlphaAnimation.setFillAfter(true);
        subwayBackAlphaAnimation.setDuration(1000);
        subwayBackAlphaAnimation.setFillAfter(true);
        comeAlphaAnimation.setDuration(1000);
        comeAlphaAnimation.setFillAfter(true);

        iv404TranslateAnimationUp1.setDuration(1000);
        iv404TranslateAnimationUp1.setFillAfter(true);
        iv404TranslateAnimationDown.setStartOffset(1000);
        iv404TranslateAnimationDown.setDuration(2000);
        iv404TranslateAnimationDown.setFillAfter(true);
        iv404TranslateAnimationUp2.setStartOffset(3000);
        iv404TranslateAnimationUp2.setDuration(1000);
        iv404TranslateAnimationUp2.setFillAfter(true);
        iv404AnimationSet.addAnimation(iv404TranslateAnimationUp1);
        iv404AnimationSet.addAnimation(iv404TranslateAnimationDown);
        iv404AnimationSet.addAnimation(iv404TranslateAnimationUp2);
        iv404AnimationSet.setInterpolator(new LinearInterpolator());
    }

    //设置数据
    private void setData() {
        dip2px=new Dip2px(this);
        pointsInfoList.add(new PointsInfo(2.1,"bike", "2020-01-01",false));
        pointsInfoList.add(new PointsInfo(1.9,"bike", "2020-01-02",false));
        pointsInfoList.add(new PointsInfo(3.3,"bus", "2020-01-03",false));
        pointsInfoList.add(new PointsInfo(0.6,"bus", "2020-01-02",false));
        pointsInfoList.add(new PointsInfo(2.3,"bus", "2020-01-06",false));
        pointsInfoList.add(new PointsInfo(2.9,"bus", "2020-01-23",false));
        pointsInfoList.add(new PointsInfo(4,"subway", "2020-02-01",false));
        pointsInfoList.add(new PointsInfo(2.1,"subway", "2020-01-31",false));
        pointsInfoList.add(new PointsInfo(2.1,"subway", "2020-02-02",false));
        for(int i=0;i<pointsInfoList.size();i++){
            if(pointsInfoList.get(i).getType().equals("bike")){
                bikepointsInfoList.add(pointsInfoList.get(i));
                bikeCoinCount++;
                bikeSum=bikeCoinCount;
            }
            else if(pointsInfoList.get(i).getType().equals("bus")){
                buspointsInfoList.add(pointsInfoList.get(i));
                busCoinCount++;
                busSum=busCoinCount;
            }
            else{
                subwaypointsInfoList.add(pointsInfoList.get(i));
                subwayCoinCount++;
                subwaySum=subwayCoinCount;
            }
        }
        intent.putExtra("backCode",TvGetPointsCode.getText().toString());
        setResult(FinalValue.GETPOINTFORGETPOINTSACTIVITY,intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
