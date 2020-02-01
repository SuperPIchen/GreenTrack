package com.superpichen.mainlibrary.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
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
import java.util.HashMap;
import java.util.Map;

import pl.droidsonroids.gif.GifImageView;

public class PetMain extends ModelActivity {
    MimicPageTurnView turnView;
    private MimicPageTurnView PtMainTip;
    private GifImageView GvMainFeng;
    private TextView TvMainId;
    private CangerjinkaiFont TvMainCode;
    private RelativeLayout RlMainContainer;

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
        RlMainContainer = (RelativeLayout) findViewById(R.id.RlMainContainer);
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
        myhandler.sendEmptyMessage(1);
    }

    //设置3D模型
    private Map<String, Object> loadModelParameters = new HashMap<>();
    private void set3DModel() {
        ContentUtils.provideAssets(this);
        loadModelParameters.put("type",2);
        launchModelRendererActivity(Uri.parse("assets://" + getPackageName() + "/" + "models/cowboy.dae"));
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
    private  Handler myhandler=new Handler(){
        @Override
        public void  handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    turnView.startAnimation(rotateAnimation1);
                    myhandler.sendEmptyMessageDelayed(2,1000);
                    break;
                case 2:
                    turnView.startAnimation(rotateAnimation2);
                    myhandler.sendEmptyMessageDelayed(3,1500);
                    break;
                case 3:
                    turnView.startAnimation(rotateAnimation3);
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
        loadModelParameters.put("model", uri);
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
