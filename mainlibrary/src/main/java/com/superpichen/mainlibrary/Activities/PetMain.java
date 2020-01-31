package com.superpichen.mainlibrary.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.superpichen.mainlibrary.MyView.MyFonts.CangerjinkaiFont;
import com.superpichen.mainlibrary.MyView.PageTurn.MimicPageTurnView;
import com.superpichen.mainlibrary.MyView.PageTurn.TextPageAdapter;
import com.superpichen.mainlibrary.MyView.TopBar.StatusBarUtil;
import com.superpichen.mainlibrary.R;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class PetMain extends AppCompatActivity {
    MimicPageTurnView turnView;
    private MimicPageTurnView PtMainTip;
    private GifImageView GvMainFeng;
    private TextView TvMainId;
    private CangerjinkaiFont TvMainCode;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-01-31 16:24:46 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        PtMainTip = (MimicPageTurnView)findViewById( R.id.PtMainTip );
        GvMainFeng = (GifImageView)findViewById( R.id.GvMainFeng );
        TvMainId = (TextView)findViewById( R.id.TvMainId );
        TvMainCode = (CangerjinkaiFont)findViewById( R.id.TvMainCode );
        turnView=findViewById(R.id.PtMainTip);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_mian);
        StatusBarUtil.setTranslucentForImageView(this,0,null);
        findViews();
        setTip();
        setAnimations();
        handler.sendEmptyMessage(1);
    }



    //设置便利贴内容
    private void setTip() {
        TextPageAdapter adapter = new TextPageAdapter(this);
        adapter.setText("低碳意指较低（更低）的温室气体（二氧化碳为主）的排放，低碳生活可以理解为：减少二氧化碳的排放，低能量、低消耗、低开支的生活方式。如今，这股风潮逐渐在我国一些大城市兴起，潜移默化地改变着人们的生活。低碳生活代表着更健康、更自然、更安全，返璞归真地去进行人与自然的活动。当今社会，随着人类生活发展，生活物质条件的提高，随之也对人类周围环境带来了影响与改变。对于普通人来说是一种生活态度，低碳生活既是一种生活方式，同时更是一种可持续发展的环保责任。");
        turnView.setPageTurnAdapter(adapter);
    }

    //设置便利贴动画
    RotateAnimation rotateAnimation1=new RotateAnimation(0,15, Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0);
    RotateAnimation rotateAnimation2=new RotateAnimation(15,-5, Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0);
    RotateAnimation rotateAnimation3=new RotateAnimation(-5,0, Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0);
    private void setAnimations() {
        rotateAnimation1.setDuration(1000);
        rotateAnimation1.setFillAfter(true);
        rotateAnimation2.setDuration(1500);
        rotateAnimation2.setFillAfter(true);
        rotateAnimation3.setDuration(500);
        rotateAnimation3.setFillAfter(true);
    }

    //动画控制器
    @SuppressLint("HandlerLeak")
    private  Handler handler=new Handler(){
        @Override
        public void  handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    turnView.startAnimation(rotateAnimation1);
                    handler.sendEmptyMessageDelayed(2,1000);
                    break;
                case 2:
                    turnView.startAnimation(rotateAnimation2);
                    handler.sendEmptyMessageDelayed(3,1500);
                    break;
                case 3:
                    turnView.startAnimation(rotateAnimation3);
                    if(!toStop){
                        handler.sendEmptyMessageDelayed(1,1000);
                    }
                    else {
                        toStop=false;
                        handler.removeCallbacksAndMessages(null);
                        handler.sendEmptyMessageDelayed(4,4000);
                    }
                    break;
                case 4:
                    if(!toStop){
                        handler.sendEmptyMessageDelayed(1,1000);
                    }
                    else {
                        toStop=false;
                        handler.removeCallbacksAndMessages(null);
                        handler.sendEmptyMessageDelayed(4,4000);
                    }
                    break;
            }
        }
    };
    private static boolean toStop=false;
    public static void isClick(){
        toStop=true;
    }
}
