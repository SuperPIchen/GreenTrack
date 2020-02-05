package com.superpichen.mainlibrary.Tools.JavaTools;

import android.app.Activity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import net.frakbot.jumpingbeans.JumpingBeans;

import pl.droidsonroids.gif.GifImageView;

import static java.lang.Thread.sleep;

public class SetGetPointsGifOnClick {
    public static void  onClick(Activity activity, GifImageView gifImageView, TextView addText,TextView textView,PointsInfo pointsInfo){
        if(!pointsInfo.isReceive()){
            pointsInfo.setReceive(true);
            addText.setVisibility(View.VISIBLE);
            setAnimation();
            gifImageView.startAnimation(backAlphaAnimation);
            addText.startAnimation(comeAnimationSet);
            setText(activity,textView,pointsInfo);
        }
    }

    private static void setText(Activity activity,TextView text,PointsInfo pointsInfo) {
        Double fenshu=Double.parseDouble(text.getText().toString())+pointsInfo.getCount();
        text.setText(fenshu+"");
        JumpingBeans jumpingBeans=JumpingBeans.with(text)
                .makeTextJump(0,text.getText().toString().length())
                .setIsWave(false)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        jumpingBeans.stopJumping();
                    }
                });
            }
        }).start();
    }

    private static AlphaAnimation comeAlphaAnimation;
    private static AlphaAnimation backAlphaAnimation;
    private static TranslateAnimation comeTranslateAnimation;
    private static AlphaAnimation backAlphaAnimation1;
    private static AnimationSet comeAnimationSet;
    private static void setAnimation() {
        comeAlphaAnimation=new AlphaAnimation(0,1);
        backAlphaAnimation=new AlphaAnimation(1,0);
        comeTranslateAnimation =new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,-0.2f);
        backAlphaAnimation1=new AlphaAnimation(1,0);
        comeAnimationSet =new AnimationSet(true);

        comeAlphaAnimation.setDuration(500);
        comeAlphaAnimation.setFillAfter(true);
        backAlphaAnimation.setDuration(500);
        backAlphaAnimation1.setStartOffset(500);
        backAlphaAnimation.setFillAfter(true);
        backAlphaAnimation1.setDuration(500);
        backAlphaAnimation1.setFillAfter(true);
        comeTranslateAnimation.setStartOffset(500);
        comeTranslateAnimation.setDuration(500);
        comeTranslateAnimation.setFillAfter(true);
        comeAnimationSet.addAnimation(comeAlphaAnimation);
        comeAnimationSet.addAnimation(comeTranslateAnimation);
        comeAnimationSet.addAnimation(backAlphaAnimation1);
        comeAnimationSet.setFillAfter(true);
    }
}
