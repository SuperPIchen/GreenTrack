package com.superpichen.mainlibrary.Activities;

import androidx.annotation.NonNull;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.view.animation.AnimationSet;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.superpichen.mainlibrary.MyView.MyFonts.CangerjinkaiFont;
import com.superpichen.mainlibrary.MyView.PageTurn.MimicPageTurnView;
import com.superpichen.mainlibrary.MyView.PageTurn.TextPageAdapter;
import com.superpichen.mainlibrary.MyView.TopBar.StatusBarUtil;
import com.superpichen.mainlibrary.R;
import com.superpichen.mainlibrary.Tools.ThreeD.demo.ExampleSceneLoader;
import com.superpichen.mainlibrary.Tools.ThreeD.demo.SceneLoader;
import com.superpichen.mainlibrary.Tools.ThreeD.engine.util.android.AndroidURLStreamHandlerFactory;
import com.superpichen.mainlibrary.Tools.ThreeD.engine.util.android.ContentUtils;
import com.superpichen.mainlibrary.Tools.ThreeD.view.ModelActivity;
import com.superpichen.mainlibrary.Tools.ThreeD.view.ModelSurfaceView;

import java.io.IOException;
import java.net.URL;

import pl.droidsonroids.gif.GifImageView;

public class PetMain extends ModelActivity {
    private MimicPageTurnView turnView;
    private GifImageView GvMainFeng;
    private TextView TvMainId;
    private CangerjinkaiFont TvMainCode;
    private RelativeLayout RlMainContainer;
    private RelativeLayout RlMainYouxiButton;
    private RelativeLayout RlMainShopButton;
    private RelativeLayout RlMainZhuangyaunButton;
    private RelativeLayout RlMainDaohangButton;
    private RelativeLayout RlMainPaopaoContainer;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-01-31 16:24:46 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        GvMainFeng = (GifImageView)findViewById( R.id.GvMainFeng );
        TvMainId = (TextView)findViewById( R.id.TvMainId );
        TvMainCode = (CangerjinkaiFont)findViewById( R.id.TvMainCode );
        turnView=findViewById(R.id.PtMainTip);
        RlMainContainer = (RelativeLayout) findViewById(R.id.RlMainContainer);
        RlMainYouxiButton = (RelativeLayout) findViewById(R.id.RlMainYouxiButton);
        RlMainShopButton = (RelativeLayout) findViewById(R.id.RlMainShopButton);
        RlMainZhuangyaunButton = (RelativeLayout) findViewById(R.id.RlMainZhuangyaunButton);
        RlMainDaohangButton = (RelativeLayout) findViewById(R.id.RlMainDaohangButton);
        RlMainPaopaoContainer = (RelativeLayout) findViewById(R.id.RlMainPaopaoContainer);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_mian);
        StatusBarUtil.setTranslucentForImageView(this,0,null);
        findViews();
        setTip();
        setAnimations();
        set3DModel();
        setOnClick();
        myhandler.sendEmptyMessage(1);
    }

    private boolean isPaopaoVisible =false;
    //设置监听
    private void setOnClick() {
        TvMainCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isPaopaoVisible){
                    RlMainPaopaoContainer.setVisibility(View.VISIBLE);
                    RlMainYouxiButton.startAnimation(youxiPaopaoAnimatorSet);
                    RlMainShopButton.startAnimation(shopPaopaoAnimatorSet);
                    RlMainZhuangyaunButton.startAnimation(zhuangyuanPaopaoAnimatorSet);
                    RlMainDaohangButton.startAnimation(daohangPaopaoAnimatorSet);
                }else {
                    RlMainYouxiButton.startAnimation(youxiPaopaoAnimatorSetBack);
                    RlMainShopButton.startAnimation(shopPaopaoAnimatorSetBack);
                    RlMainZhuangyaunButton.startAnimation(zhuangyuanPaopaoAnimatorSetBack);
                    RlMainDaohangButton.startAnimation(daohangPaopaoAnimatorSetBack);
                }
                isPaopaoVisible=!isPaopaoVisible;
            }
        });
    }

    //设置3D模型
    private void set3DModel() {
        ContentUtils.provideAssets(this);
        launchModelRendererActivity(Uri.parse("assets://" + getPackageName() + "/" + "models/fawn5.dae"));
    }


    //设置便利贴内容
    private void setTip() {
        TextPageAdapter adapter = new TextPageAdapter(this);
        adapter.setText("低碳意指较低（更低）的温室气体（二氧化碳为主）的排放，低碳生活可以理解为：减少二氧化碳的排放，低能量、低消耗、低开支的生活方式。如今，这股风潮逐渐在我国一些大城市兴起，潜移默化地改变着人们的生活。低碳生活代表着更健康、更自然、更安全，返璞归真地去进行人与自然的活动。当今社会，随着人类生活发展，生活物质条件的提高，随之也对人类周围环境带来了影响与改变。对于普通人来说是一种生活态度，低碳生活既是一种生活方式，同时更是一种可持续发展的环保责任。");
        turnView.setPageTurnAdapter(adapter);
    }

    //设置便利贴动画
    RotateAnimation tipRotateAnimation1 =new RotateAnimation(0,15, Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0);
    RotateAnimation tipRotateAnimation2 =new RotateAnimation(15,-5, Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0);
    RotateAnimation tipRotateAnimation3 =new RotateAnimation(-5,0, Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0);
    //设置泡泡展开动画
    TranslateAnimation youxiPaopaoTranslateAnimation=new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0.4f,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0f);
    AnimationSet youxiPaopaoAnimatorSet=new AnimationSet(true);
    TranslateAnimation shopPaopaoTranslateAnimation=new TranslateAnimation(Animation.RELATIVE_TO_PARENT,-0.4f,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0f);
    AnimationSet shopPaopaoAnimatorSet=new AnimationSet(true);
    TranslateAnimation zhuangyuanPaopaoTranslateAnimation=new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0.3f,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT,0f);
    AnimationSet zhuangyuanPaopaoAnimatorSet=new AnimationSet(true);
    TranslateAnimation daohangPaopaoTranslateAnimation=new TranslateAnimation(Animation.RELATIVE_TO_PARENT,-0.3f,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT,0f);
    AnimationSet daohangPaopaoAnimatorSet=new AnimationSet(true);
    AlphaAnimation paopaoAlphaAnimation=new AlphaAnimation(0,0.8f);
    //设置泡泡收回动画
    TranslateAnimation youxiPaopaoTranslateAnimationBack=new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.4f,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0f);
    AnimationSet youxiPaopaoAnimatorSetBack=new AnimationSet(true);
    TranslateAnimation shopPaopaoTranslateAnimationBack=new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0f,Animation.RELATIVE_TO_PARENT,-0.4f,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0f);
    AnimationSet shopPaopaoAnimatorSetBack=new AnimationSet(true);
    TranslateAnimation zhuangyuanPaopaoTranslateAnimationBack=new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0f,Animation.RELATIVE_TO_PARENT,0.3f,Animation.RELATIVE_TO_PARENT,0f,Animation.RELATIVE_TO_PARENT,0.5f);
    AnimationSet zhuangyuanPaopaoAnimatorSetBack=new AnimationSet(true);
    TranslateAnimation daohangPaopaoTranslateAnimationBack=new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0f,Animation.RELATIVE_TO_PARENT,-0.3f,Animation.RELATIVE_TO_PARENT,0f,Animation.RELATIVE_TO_PARENT,0.5f);
    AnimationSet daohangPaopaoAnimatorSetBack=new AnimationSet(true);
    AlphaAnimation paopaoAlphaAnimationBack=new AlphaAnimation(0.8f,0f);
    //设置泡泡浮动动画
    TranslateAnimation youxiPaopaoTranslateAnimationUp1=new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.05f);
    TranslateAnimation youxiPaopaoTranslateAnimationUp2=new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,-0.05f,Animation.RELATIVE_TO_PARENT,0);
    TranslateAnimation youxiPaopaoTranslateAnimationDown=new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.05f,Animation.RELATIVE_TO_PARENT,-0.05f);
    AnimationSet youxiPaopaoTranslateAnimationSet=new AnimationSet(true);
    private void setAnimations() {
        //便利贴
        tipRotateAnimation1.setDuration(1000);
        tipRotateAnimation1.setFillAfter(true);
        tipRotateAnimation2.setDuration(1500);
        tipRotateAnimation2.setFillAfter(true);
        tipRotateAnimation3.setDuration(500);
        tipRotateAnimation3.setFillAfter(true);
        //泡泡展开
        paopaoAlphaAnimation.setDuration(500);
        paopaoAlphaAnimation.setFillAfter(true);
        youxiPaopaoTranslateAnimation.setDuration(500);
        youxiPaopaoTranslateAnimation.setFillAfter(true);
        youxiPaopaoAnimatorSet.addAnimation(youxiPaopaoTranslateAnimation);
        youxiPaopaoAnimatorSet.addAnimation(paopaoAlphaAnimation);
        youxiPaopaoAnimatorSet.setFillAfter(true);
        shopPaopaoTranslateAnimation.setDuration(500);
        shopPaopaoTranslateAnimation.setFillAfter(true);
        shopPaopaoAnimatorSet.addAnimation(shopPaopaoTranslateAnimation);
        shopPaopaoAnimatorSet.addAnimation(paopaoAlphaAnimation);
        shopPaopaoAnimatorSet.setFillAfter(true);
        zhuangyuanPaopaoTranslateAnimation.setDuration(500);
        zhuangyuanPaopaoTranslateAnimation.setFillAfter(true);
        zhuangyuanPaopaoAnimatorSet.addAnimation(zhuangyuanPaopaoTranslateAnimation);
        zhuangyuanPaopaoAnimatorSet.addAnimation(paopaoAlphaAnimation);
        zhuangyuanPaopaoAnimatorSet.setFillAfter(true);
        daohangPaopaoTranslateAnimation.setDuration(500);
        daohangPaopaoTranslateAnimation.setFillAfter(true);
        daohangPaopaoAnimatorSet.addAnimation(daohangPaopaoTranslateAnimation);
        daohangPaopaoAnimatorSet.addAnimation(paopaoAlphaAnimation);
        daohangPaopaoAnimatorSet.setFillAfter(true);
        //泡泡收回
        paopaoAlphaAnimationBack.setDuration(500);
        paopaoAlphaAnimationBack.setFillAfter(true);
        youxiPaopaoTranslateAnimationBack.setDuration(500);
        youxiPaopaoTranslateAnimationBack.setFillAfter(true);
        youxiPaopaoAnimatorSetBack.addAnimation(youxiPaopaoTranslateAnimationBack);
        youxiPaopaoAnimatorSetBack.addAnimation(paopaoAlphaAnimationBack);
        youxiPaopaoAnimatorSetBack.setFillAfter(true);
        shopPaopaoTranslateAnimationBack.setDuration(500);
        shopPaopaoTranslateAnimationBack.setFillAfter(true);
        shopPaopaoAnimatorSetBack.addAnimation(shopPaopaoTranslateAnimationBack);
        shopPaopaoAnimatorSetBack.addAnimation(paopaoAlphaAnimationBack);
        shopPaopaoAnimatorSetBack.setFillAfter(true);
        zhuangyuanPaopaoTranslateAnimationBack.setDuration(500);
        zhuangyuanPaopaoTranslateAnimationBack.setFillAfter(true);
        zhuangyuanPaopaoAnimatorSetBack.addAnimation(zhuangyuanPaopaoTranslateAnimationBack);
        zhuangyuanPaopaoAnimatorSetBack.addAnimation(paopaoAlphaAnimationBack);
        zhuangyuanPaopaoAnimatorSetBack.setFillAfter(true);
        daohangPaopaoTranslateAnimationBack.setDuration(500);
        daohangPaopaoTranslateAnimationBack.setFillAfter(true);
        daohangPaopaoAnimatorSetBack.addAnimation(daohangPaopaoTranslateAnimationBack);
        daohangPaopaoAnimatorSetBack.addAnimation(paopaoAlphaAnimationBack);
        daohangPaopaoAnimatorSetBack.setFillAfter(true);
        //泡泡浮动
//        youxiPaopaoTranslateAnimationUp1.setDuration(50);
//        youxiPaopaoTranslateAnimationUp1.setFillAfter(true);
//        youxiPaopaoTranslateAnimationDown.setStartOffset(50);
//        youxiPaopaoTranslateAnimationDown.setDuration(100);
//        youxiPaopaoTranslateAnimationDown.setFillAfter(true);
//        youxiPaopaoTranslateAnimationUp2.setStartOffset(150);
//        youxiPaopaoTranslateAnimationUp2.setDuration(50);
//        youxiPaopaoTranslateAnimationUp2.setFillAfter(true);
//        youxiPaopaoTranslateAnimationSet.addAnimation(youxiPaopaoTranslateAnimationUp1);
//        youxiPaopaoTranslateAnimationSet.addAnimation(youxiPaopaoTranslateAnimationDown);
//        youxiPaopaoTranslateAnimationSet.addAnimation(youxiPaopaoTranslateAnimationUp2);
//        youxiPaopaoTranslateAnimationSet.setDuration(500);
    }

    //动画控制器
    @SuppressLint("HandlerLeak")
    private  Handler myhandler=new Handler(){
        @Override
        public void  handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    turnView.startAnimation(tipRotateAnimation1);
                    myhandler.sendEmptyMessageDelayed(2,1000);
                    break;
                case 2:
                    turnView.startAnimation(tipRotateAnimation2);
                    myhandler.sendEmptyMessageDelayed(3,1500);
                    break;
                case 3:
                    turnView.startAnimation(tipRotateAnimation3);
                    if(!toStop){
                        myhandler.sendEmptyMessageDelayed(1,1000);
                    }
                    else {
                        toStop=false;
                        myhandler.removeCallbacksAndMessages(null);
                        myhandler.sendEmptyMessageDelayed(4,4000);
                    }
                    break;
                case 4:
                    if(!toStop){
                        myhandler.sendEmptyMessageDelayed(1,1000);
                    }
                    else {
                        toStop=false;
                        myhandler.removeCallbacksAndMessages(null);
                        myhandler.sendEmptyMessageDelayed(4,4000);
                    }
                    break;
            }
        }
    };
    private static boolean toStop=false;
    public static void isClick(){
        toStop=true;
    }

    /**
     * 以下为3D模型内容
     */
    static {
        URL.setURLStreamHandlerFactory(new AndroidURLStreamHandlerFactory());
    }
    private Uri uri;
    private void launchModelRendererActivity(Uri uri) {
        this.uri=uri;
        // content provider case

        startShow();
    }

    private void startShow(){
        this.paramUri = uri;
        this.paramType =2;
        this.immersiveMode = true;
        try {
            String[] backgroundColors ={"0","0","0","0"};
            backgroundColor[0] = Float.parseFloat(backgroundColors[0]);
            backgroundColor[1] = Float.parseFloat(backgroundColors[1]);
            backgroundColor[2] = Float.parseFloat(backgroundColors[2]);
            backgroundColor[3] = Float.parseFloat(backgroundColors[3]);
        } catch (Exception ex) {
            // Assuming default background color
        }

        handler = new Handler(getMainLooper());

        // Create our 3D sceneario
        if (paramUri == null) {
            scene = new ExampleSceneLoader(this);
        } else {
            scene = new SceneLoader(this);
        }
        scene.init();

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        try {
            gLView = new ModelSurfaceView(this);
//            gLView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
//            gLView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
            RlMainContainer.addView(gLView);
        } catch (Exception e) {
        }

        // TODO: Alert user when there is no multitouch support (2 fingers). He won't be able to rotate or zoom
        ContentUtils.printTouchCapabilities(getPackageManager());

        setupOnSystemVisibilityChangeListener();
    }


    private static final int REQUEST_CODE_LOAD_TEXTURE = 1000;
    private static final int FULLSCREEN_DELAY = 10000;

    /**
     * Type of model if file name has no extension (provided though content provider)
     */
    private int paramType;
    /**
     * The file to load. Passed as input parameter
     */
    private Uri paramUri;
    /**
     * Enter into Android Immersive mode so the renderer is full screen or not
     */
    private boolean immersiveMode = true;
    /**
     * Background GL clear color. Default is light gray
     */
    private float[] backgroundColor = new float[]{1f, 1f, 1f, 1.0f};

    private ModelSurfaceView gLView;

    private SceneLoader scene;

    private Handler handler;


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setupOnSystemVisibilityChangeListener() {

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUIDelayed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.model_toggle_wireframe) {
            scene.toggleWireframe();
        } else if (itemId == R.id.model_toggle_boundingbox) {
            scene.toggleBoundingBox();
        } else if (itemId == R.id.model_toggle_textures) {
            scene.toggleTextures();
        } else if (itemId == R.id.model_toggle_animation) {
            scene.toggleAnimation();
        } else if (itemId == R.id.model_toggle_collision) {
            scene.toggleCollision();
        } else if (itemId == R.id.model_toggle_lights) {
            scene.toggleLighting();
        } else if (itemId == R.id.model_toggle_stereoscopic) {
            scene.toggleStereoscopic();
        } else if (itemId == R.id.model_toggle_blending) {
            scene.toggleBlending();
        } else if (itemId == R.id.model_toggle_immersive) {
            toggleImmersive();
        } else if (itemId == R.id.model_load_texture) {
            Intent target = ContentUtils.createGetContentIntent("image/*");
            Intent intent = Intent.createChooser(target, "Select a file");
            try {
                startActivityForResult(intent, REQUEST_CODE_LOAD_TEXTURE);
            } catch (ActivityNotFoundException e) {
                // The reason for the existence of aFileChooser
            }
        }

        hideSystemUIDelayed();
        return super.onOptionsItemSelected(item);
    }

    private void toggleImmersive() {
        this.immersiveMode = !this.immersiveMode;
        if (this.immersiveMode) {
            hideSystemUI();
        } else {
            showSystemUI();
        }
    }

    private void hideSystemUIDelayed() {
        if (!this.immersiveMode) {
            return;
        }
        handler.removeCallbacksAndMessages(null);

    }

    private void hideSystemUI() {
        if (!this.immersiveMode) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            hideSystemUIKitKat();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            hideSystemUIJellyBean();
        }
    }

    // This snippet hides the system bars.
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void hideSystemUIKitKat() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        final View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void hideSystemUIJellyBean() {
        final View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LOW_PROFILE);
    }

    // This snippet shows the system bars. It does this by removing all the flags
    // except for the ones that make the content appear under the system bars.
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void showSystemUI() {
        handler.removeCallbacksAndMessages(null);
        final View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
    }

    public Uri getParamUri() {
        return paramUri;
    }

    public int getParamType() {
        return paramType;
    }

    public float[] getBackgroundColor() {
        return backgroundColor;
    }

    public SceneLoader getScene() {
        return scene;
    }

    public ModelSurfaceView getGLView() {
        return gLView;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_CODE_LOAD_TEXTURE:
                // The URI of the selected file
                final Uri uri = data.getData();
                if (uri != null) {
                    try {
                        ContentUtils.setThreadActivity(this);
                        scene.loadTexture(null, uri);
                    } catch (IOException ex) {
                    } finally {
                        ContentUtils.setThreadActivity(null);
                    }
                }
        }
    }
}
