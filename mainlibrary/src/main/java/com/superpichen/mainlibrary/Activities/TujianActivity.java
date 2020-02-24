package com.superpichen.mainlibrary.Activities;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;

import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.OnBoomListener;
import com.superpichen.mainlibrary.MyView.PileLayout;
import com.superpichen.mainlibrary.MyView.TopBar.StatusBarUtil;
import com.superpichen.mainlibrary.R;
import com.superpichen.mainlibrary.Tools.JavaTools.TujianPileLayoutAdapter;
import com.superpichen.mainlibrary.Tools.JavaTools.TujianPileLayoutInfo;
import com.superpichen.mainlibrary.Tools.ThreeD.demo.ExampleSceneLoader;
import com.superpichen.mainlibrary.Tools.ThreeD.demo.SceneLoader;
import com.superpichen.mainlibrary.Tools.ThreeD.engine.util.android.ContentUtils;
import com.superpichen.mainlibrary.Tools.ThreeD.view.ModelActivity;
import com.superpichen.mainlibrary.Tools.ThreeD.view.ModelSurfaceView;

import java.util.ArrayList;
import java.util.List;

public class TujianActivity extends ModelActivity {

    private PileLayout PlTujianContainer;
    private RelativeLayout RlTujian3DContainer;
    private BoomMenuButton BbTujianButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tujian);
        StatusBarUtil.setTranslucentForImageView(this,0,null);
        PlTujianContainer = findViewById(R.id.PlTujianContainer);
        RlTujian3DContainer = findViewById(R.id.RlTujian3DContainer);
        BbTujianButton = findViewById(R.id.BbTujianButton);
        setPileLayout();
        setBoomMenuButton();
    }

    Uri returnUri;
    AlertDialog dialog;
    private void setBoomMenuButton() {
        BbTujianButton.setAlpha(0.8f);
        BbTujianButton.setOnBoomListener(new OnBoomListener() {
            @Override
            public void onClicked(int index, BoomButton boomButton) {

            }

            @Override
            public void onBackgroundClick() {

            }

            @Override
            public void onBoomWillHide() {
                RlTujian3DContainer.setVisibility(View.VISIBLE);
            }

            @Override
            public void onBoomDidHide() {

            }

            @Override
            public void onBoomWillShow() {
                RlTujian3DContainer.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onBoomDidShow() {

            }
        });
        HamButton.Builder builderShow=new HamButton.Builder()
                .normalImageRes(R.drawable.tujianshow)
                .normalText("出场")
                .subNormalText("皇上您要雨露均沾啊")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        returnUri=data.get(position).getUri();
                        PetMain.uri=returnUri;
                    }
                });
        BbTujianButton.addBuilder(builderShow);
        HamButton.Builder builderName=new HamButton.Builder()
                .normalImageRes(R.drawable.tujianname)
                .normalText("更改昵称")
                .subNormalText("赋予碳宠物有趣的灵魂")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        View view=View.inflate(TujianActivity.this,R.layout.dialog_tujian_changname,null);
                        EditText EtTujianChangename =view.findViewById(R.id.EtTujianChangename);
                        ImageView IvTujianChangenameYes =view.findViewById(R.id.IvTujianChangenameYes);
                        ImageView IvTujianChangenameCancel = view.findViewById(R.id.IvTujianChangenameCancel);
                        Typeface tf = Typeface.createFromAsset(TujianActivity.this.getAssets(), "fonts/星辰在唱歌.ttf");
                        EtTujianChangename.setTypeface(tf);
                        IvTujianChangenameYes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String name=EtTujianChangename.getText().toString();
                                if(!name.equals("")){
                                    data.get(position).setName(name);
                                    dialog.dismiss();
                                    PlTujianContainer.notifyDataSetChanged();
                                }else
                                    Toast.makeText(TujianActivity.this,"请先输入昵称！",Toast.LENGTH_SHORT).show();
                            }
                        });
                        IvTujianChangenameCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        dialog=new AlertDialog.Builder(TujianActivity.this,R.style.Translucent_NoTitle)
                                .setView(view)
                                .show();
                    }
                });
        BbTujianButton.addBuilder(builderName);
        HamButton.Builder builderDuihuan=new HamButton.Builder()
                .normalImageRes(R.drawable.tujianduihuan)
                .normalText("兑换碳宠物")
                .subNormalText("让漂泊的萌宠有个家")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        View view=View.inflate(TujianActivity.this,R.layout.dialog_tujian_duihuan,null);
                        EditText EtDuihuanNumber = view.findViewById(R.id.EtDuihuanNumber);
                        ImageView IvDuihuanButton = view.findViewById(R.id.IvDuihuanButton);
                        Typeface tf = Typeface.createFromAsset(TujianActivity.this.getAssets(), "fonts/汉标双剑粗体.ttf");
                        EtDuihuanNumber.setTypeface(tf);
                        dialog=new AlertDialog.Builder(TujianActivity.this,R.style.Translucent_NoTitle)
                                .setView(view)
                                .show();
                    }
                });
        BbTujianButton.addBuilder(builderDuihuan);
        HamButton.Builder builderGift=new HamButton.Builder()
                .normalImageRes(R.drawable.tujiangift)
                .normalText("赠送给朋友")
                .subNormalText("赠人玫瑰，手有余香");
        BbTujianButton.addBuilder(builderGift);
    }

    private int position=0;

    public void setPosition(int position) {
        this.position = position;
    }

    List<TujianPileLayoutInfo> data=new ArrayList<>();
    TujianPileLayoutAdapter adapter;
    /**
     * 设置图鉴卡片
     */
    private void setPileLayout() {
        ContentUtils.provideAssets(this);
        data.add(new TujianPileLayoutInfo("牛仔很忙","231",R.drawable.tujianniuzai, Uri.parse("assets://" + getPackageName() + "/" + "models/cowboy.dae")));
        data.add(new TujianPileLayoutInfo("云烟鸟鸟","234",R.drawable.tujianbird,Uri.parse("assets://" + getPackageName() + "/" + "models/bird.obj")));
        data.add(new TujianPileLayoutInfo("熊猫贝贝","634",R.drawable.tujianpanda,Uri.parse("assets://" + getPackageName() + "/" + "models/panda.DAE")));
        data.add(new TujianPileLayoutInfo("鹿鹿无为","532",R.drawable.tujianlu,Uri.parse("assets://" + getPackageName() + "/" + "models/fawn.obj")));
        data.add(new TujianPileLayoutInfo("柴犬拉卡","262",R.drawable.tujiandog,Uri.parse("assets://" + getPackageName() + "/" + "models/dog.obj")));
        data.add(new TujianPileLayoutInfo("猫爷祖宗","263",R.drawable.tujiancat,null));
        data.add(new TujianPileLayoutInfo("火狐娇娇","522",R.drawable.tujianfox,null));
        adapter=new TujianPileLayoutAdapter(data,this);
        PlTujianContainer.setAdapter(adapter);
        PlTujianContainer.bringToFront();
    }

    public void remove3DModel(){
        RlTujian3DContainer.removeView(gLView);
    }


    /**
     * 以下为3D模型内容
     */
    private Uri uri;
    public void launchModelRendererActivity(Uri uri,int type) {
        this.uri=uri;
        // content provider case
        startShow(type);
    }

    private void startShow(int type){
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

        threeDHandler = new Handler(getMainLooper());

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
            gLView = new ModelSurfaceView(this,type);
//            gLView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
//            gLView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
            RlTujian3DContainer.addView(gLView);
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

    private Handler threeDHandler;


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setupOnSystemVisibilityChangeListener() {

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
        threeDHandler.removeCallbacksAndMessages(null);

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
        threeDHandler.removeCallbacksAndMessages(null);
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
}

