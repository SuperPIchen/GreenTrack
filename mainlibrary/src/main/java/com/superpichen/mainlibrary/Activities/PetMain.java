package com.superpichen.mainlibrary.Activities;

import androidx.annotation.NonNull;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.view.animation.AnimationSet;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.mingle.sweetpick.SweetSheet;
import com.nightonke.boommenu.Animation.BoomEnum;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceAlignmentEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.superpichen.mainlibrary.MyView.MyFonts.CangerjinkaiFont;
import com.superpichen.mainlibrary.MyView.MyFonts.HanbiaoshuangjiancutiFont;
import com.superpichen.mainlibrary.MyView.PageTurn.MimicPageTurnView;
import com.superpichen.mainlibrary.MyView.PageTurn.TextPageAdapter;
import com.superpichen.mainlibrary.MyView.TopBar.StatusBarUtil;
import com.superpichen.mainlibrary.R;
import com.superpichen.mainlibrary.Tools.JavaTools.Dip2px;
import com.superpichen.mainlibrary.Tools.JavaTools.FinalValue;
import com.superpichen.mainlibrary.Tools.ThreeD.demo.ExampleSceneLoader;
import com.superpichen.mainlibrary.Tools.ThreeD.demo.SceneLoader;
import com.superpichen.mainlibrary.Tools.ThreeD.engine.util.android.AndroidURLStreamHandlerFactory;
import com.superpichen.mainlibrary.Tools.ThreeD.engine.util.android.ContentUtils;
import com.superpichen.mainlibrary.Tools.ThreeD.view.ModelActivity;
import com.superpichen.mainlibrary.Tools.ThreeD.view.ModelSurfaceView;
import com.superpichen.mainlibrary.Tools.Yuyin.JsonParser;

import net.frakbot.jumpingbeans.JumpingBeans;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class PetMain extends ModelActivity {
    private MimicPageTurnView turnView;
    private TextView TvMainId;
    private CangerjinkaiFont TvMainCode;
    private RelativeLayout RlMainContainer;
    private RelativeLayout RlMainYouxiButton;
    private RelativeLayout RlMainShopButton;
    private RelativeLayout RlMainZhuangyaunButton;
    private RelativeLayout RlMainDaohangButton;
    private RelativeLayout RlMainPaopaoContainer;
    private TextView TvMainBiaoqian;
    private BoomMenuButton BbPetZhuangyuan;
    private BoomMenuButton BbPetYouxi;
    private ImageView IvMainYuyinButton;
    private HanbiaoshuangjiancutiFont TvMainYuyin;
    private ImageView IvMainGongyiButton;
    private TextView TvMainBiaoqian2;
    private TextView TvMainBiaoqian3;
    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-01-31 16:24:46 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        TvMainId = findViewById( R.id.TvMainId );
        TvMainCode = findViewById( R.id.TvMainCode );
        turnView=findViewById(R.id.PtMainTip);
        RlMainContainer =  findViewById(R.id.RlMainContainer);
        RlMainYouxiButton =  findViewById(R.id.RlMainYouxiButton);
        RlMainShopButton =  findViewById(R.id.RlMainShopButton);
        RlMainZhuangyaunButton =  findViewById(R.id.RlMainZhuangyaunButton);
        RlMainDaohangButton =  findViewById(R.id.RlMainDaohangButton);
        RlMainPaopaoContainer =  findViewById(R.id.RlMainPaopaoContainer);
        TvMainBiaoqian =  findViewById(R.id.TvMainBiaoqian);
        BbPetZhuangyuan = findViewById(R.id.BbPetZhuangyuan);
        BbPetYouxi = findViewById(R.id.BbPetYouxi);
        IvMainYuyinButton = findViewById(R.id.IvMainYuyinButton);
        TvMainYuyin = findViewById(R.id.TvMainYuyin);
        IvMainGongyiButton = findViewById(R.id.IvMainGongyiButton);
        TvMainBiaoqian2 = findViewById(R.id.TvMainBiaoqian2);
        TvMainBiaoqian3 = findViewById(R.id.TvMainBiaoqian3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_main);
        StatusBarUtil.setTranslucentForImageView(this,0,null);
        findViews();
        setTip();
        setAnimations();
        set3DModel();
        setBoomMenuButton();
        setYuyin();
        setOnClick();
        /**
         * 置顶一些控件，防止被挡住，无法触发点击事件
         */
        RlMainPaopaoContainer.bringToFront();
        TvMainBiaoqian.bringToFront();
        TvMainBiaoqian2.bringToFront();
    }

    /**
     * 设置公益界面
     */
    private void startGongyi() {
        View view=View.inflate(this,R.layout.dialog_pet_gongyi,null);
        TextView TvGOngyiJisuan = view.findViewById(R.id.TvGOngyiJisuan);
        TvGOngyiJisuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TvGOngyiJisuan.setText("6.3G");
                TvGOngyiJisuan.setTextSize(20);
            }
        });
        dialogSetView(view);
        setDialogData();
        dialogOnClick();
        AlertDialog dialog=new AlertDialog.Builder(this)
                .setView(view)
                .create();
        dialog.show();
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        Dip2px dip2px=new Dip2px(this);
        p.height = dip2px.dip2px(550);
        p.width = dip2px.dip2px(360);
        dialog.getWindow().setAttributes(p);     //设置生效
    }

    /**
     * 设置Dialog数据
     */
    private ArrayList<String> wayList;
    SweetSheet mSweetSheet;
    private void setDialogData() {
        wayList=new ArrayList<>();
        wayList.add("公交车");
        wayList.add("地铁");
        wayList.add("共享单车");
    }

    /**
     * dialog里的点击事件
     */
    private void dialogOnClick() {
        TvGongyiStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    /**
     * 设置Dialog的View
     * @param view
     */
    ImageView IvGongyiTree;
    ImageView IvGongyiBus;
    ImageView IvGongyiBike;
    ImageView IvGongyiSubway;
    TextView TvGongyiSubway;
    TextView TvGongyiBus;
    TextView TvGongyiBike;
    RelativeLayout RlGongyiTabContainer;
    HanbiaoshuangjiancutiFont TvGOngyiToday;
    HanbiaoshuangjiancutiFont TvGOngyiSum;
    HanbiaoshuangjiancutiFont TvGOngyiJisuan;
    TextView TvGongyiStart;
    TextView TvGongyiEnd;
    TextView TvGongyiWay;
    private void dialogSetView(View view) {
        IvGongyiTree = view.findViewById( R.id.IvGongyiTree );
        IvGongyiBus = view.findViewById( R.id.IvGongyiBus );
        IvGongyiBike = view.findViewById( R.id.IvGongyiBike );
        IvGongyiSubway = view.findViewById( R.id.IvGongyiSubway );
        TvGongyiSubway = view.findViewById( R.id.TvGongyiSubway );
        TvGongyiBus = view.findViewById( R.id.TvGongyiBus );
        TvGongyiBike = view.findViewById( R.id.TvGongyiBike );
        RlGongyiTabContainer = view.findViewById( R.id.RlGongyiTabContainer );
        TvGOngyiToday = view.findViewById( R.id.TvGOngyiToday );
        TvGOngyiSum = view.findViewById( R.id.TvGOngyiSum );
        TvGOngyiJisuan = view.findViewById( R.id.TvGOngyiJisuan );
        TvGongyiStart = view.findViewById( R.id.TvGongyiStart );
        TvGongyiEnd = view.findViewById( R.id.TvGongyiEnd );
        TvGongyiWay = view.findViewById( R.id.TvGongyiWay );
    }

    /**
     * 设置语音
     */
    // 语音听写对象
    private SpeechRecognizer mIat;
    private Toast mToast;
    // 引擎类型
    private String mEngineType = SpeechConstant.TYPE_CLOUD;
    private String language="zh_cn";
    private String resultType = "json";
    private StringBuffer buffer = new StringBuffer();
    int ret = 0; // 函数调用返回值
    private static int flg=0;
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();
    private void setYuyin() {
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5d7dd0e4");
        // 初始化识别无UI识别对象
        // 使用SpeechRecognizer对象，可根据回调消息自定义界面；
        mIat = SpeechRecognizer.createRecognizer(PetMain.this, mInitListener);
        mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
    }


    /**
     * 设置二级菜单按钮
     */
    private void setBoomMenuButton() {
        //庄园按钮
        BbPetZhuangyuan.setEnabled(false);
        BbPetZhuangyuan.setButtonPlaceAlignmentEnum(ButtonPlaceAlignmentEnum.Bottom);
        BbPetZhuangyuan.setButtonBottomMargin(1000);
        BbPetZhuangyuan.setBoomEnum(BoomEnum.HORIZONTAL_THROW_2);
        TextOutsideCircleButton.Builder builderTujian = new TextOutsideCircleButton.Builder()
                .normalImageRes(R.drawable.maintujianbutton)
                .normalText("宠物图鉴")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        startActivityForResult(new Intent(PetMain.this,TujianActivity.class),FinalValue.GETPOINTFORTUJIANACTIVITY);
                        RlMainContainer.removeView(gLView);
                    }
                });
        BbPetZhuangyuan.addBuilder(builderTujian);
        TextOutsideCircleButton.Builder builderJiayuan = new TextOutsideCircleButton.Builder()
                .normalImageRes(R.drawable.mainjiayuanbutton)
                .normalText("宠物家园")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        startActivity(new Intent(PetMain.this,HomelandActivity.class));
                    }
                });
        BbPetZhuangyuan.addBuilder(builderJiayuan);
        TextOutsideCircleButton.Builder builderChengjiu = new TextOutsideCircleButton.Builder()
                .normalImageRes(R.drawable.mainchengjiubutton)
                .normalText("用户成就")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        startActivity(new Intent(PetMain.this,AchievementActivity.class));
                    }
                });
        BbPetZhuangyuan.addBuilder(builderChengjiu);
        TextOutsideCircleButton.Builder builderShejiao = new TextOutsideCircleButton.Builder()
                .normalImageRes(R.drawable.mainshejiaobutton)
                .normalText("社交区")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        startActivity(new Intent(PetMain.this,SocialActivity.class));
                    }
                });
        BbPetZhuangyuan.addBuilder(builderShejiao);

        //游戏按钮
        BbPetYouxi.setEnabled(false);
        BbPetYouxi.setButtonPlaceAlignmentEnum(ButtonPlaceAlignmentEnum.Bottom);
        BbPetYouxi.setButtonBottomMargin(1000);
        BbPetYouxi.setBoomEnum(BoomEnum.HORIZONTAL_THROW_2);
        TextOutsideCircleButton.Builder builderTanTan = new TextOutsideCircleButton.Builder()
                .normalImageRes(R.drawable.maintantanbutton)
                .normalText("碳碳大作战")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
//                        startActivityForResult(new Intent(PetMain.this,TantanActivity.class),FinalValue.GETPOINTFORTUJIANACTIVITY);
//                        RlMainContainer.removeView(gLView);
                        View view=View.inflate(PetMain.this,R.layout.dialog_main_tantan,null);
                        TextView TvPaoKuDialogYus = view.findViewById(R.id.TvPaoKuDialogYus);
                        TextView TvMainDialogCancel = view.findViewById(R.id.TvMainDialogCancel);
                        TextView TvTantan6lu = view.findViewById(R.id.TvTantan6lu);
                        TextView TvTantanTo1 = view.findViewById(R.id.TvTantanTo1);
                        TextView TvTantanTo2 = view.findViewById(R.id.TvTantanTo2);
                        TvTantan6lu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                TvTantan6lu.setBackgroundResource(R.drawable.yuanjiaojuxing_yellow_yinying_shape1);
                                TvTantanTo1.setText("蓝海中学");
                                TvTantanTo2.setText("客运中心");
                            }
                        });
                        TvTantanTo2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                TvTantanTo2.setBackgroundResource(R.drawable.yuanjiaojuxing_yellow_yinying_shape1);
                            }
                        });
                        AlertDialog dialog=new AlertDialog.Builder(PetMain.this,R.style.Translucent_NoTitle)
                                .setView(view)
                                .create();
                        TvPaoKuDialogYus.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(PetMain.this,TantanActivity.class));
                            }
                        });
                        TvMainDialogCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
                        Dip2px dip2px=new Dip2px(PetMain.this);
                        p.height = dip2px.dip2px(350);
                        p.width = dip2px.dip2px(350);
                        dialog.getWindow().setAttributes(p);
                    }
                });
        BbPetYouxi.addBuilder(builderTanTan);
        TextOutsideCircleButton.Builder builderPaoku = new TextOutsideCircleButton.Builder()
                .normalImageRes(R.drawable.mainpaokubutton)
                .normalText("精灵跑酷")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        View view=View.inflate(PetMain.this,R.layout.dialog_main_paoku,null);
                        TextView TvPaoKuDialogYus = view.findViewById(R.id.TvPaoKuDialogYus);
                        TextView TvMainDialogCancel = view.findViewById(R.id.TvMainDialogCancel);
                        AlertDialog dialog=new AlertDialog.Builder(PetMain.this,R.style.Translucent_NoTitle)
                                .setView(view)
                                .create();
                        TvPaoKuDialogYus.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(PetMain.this,PaokuActivity.class));
                            }
                        });
                        TvMainDialogCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
                        Dip2px dip2px=new Dip2px(PetMain.this);
                        p.height = dip2px.dip2px(310);
                        p.width = dip2px.dip2px(350);
                        dialog.getWindow().setAttributes(p);
                    }
                });
        BbPetYouxi.addBuilder(builderPaoku);
    }

    private boolean isPaopaoVisible =false;
    //设置监听
    private void setOnClick() {
        RlMainShopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PetMain.this,MallActivity.class);
                intent.putExtra("getPoint",TvMainCode.getText().toString());
                startActivityForResult(intent,FinalValue.GETPOINTFORMALLACTIVITY);
                RlMainContainer.removeView(gLView);
            }
        });
        TvMainBiaoqian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PetMain.this,GetPointsActivity.class);
                intent.putExtra("getPoint",TvMainCode.getText().toString());
                startActivityForResult(intent, FinalValue.GETPOINTFORGETPOINTSACTIVITY);
                RlMainContainer.removeView(gLView);
            }
        });

        RlMainDaohangButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PetMain.this,DaoHangActivity.class));
            }
        });
        IvMainYuyinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( null == mIat ){
                    // 创建单例失败，与 21001 错误为同样原因，参考 http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=9688
                    PetMain.this.showTip( "创建对象失败，请确认 libmsc.so 放置正确，且有调用 createUtility 进行初始化" );
                    return;
                }
                TvMainYuyin.setVisibility(View.VISIBLE);
                startRec();
            }
        });
        IvMainGongyiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGongyi();
            }
        });
        TvMainBiaoqian2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PetMain.this,JiJianActivity.class));
            }
        });
        TvMainBiaoqian3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PetMain.this,YoukeActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FinalValue.GETPOINTFORGETPOINTSACTIVITY:
                String text=data.getStringExtra("backCode");
                TvMainCode.setText(text);
                break;
            case FinalValue.GETPOINTFORMALLACTIVITY:
                break;
            case FinalValue.GETPOINTFORTUJIANACTIVITY:
                break;
        }
        startShow(0);
    }
    //监听单击模型
    public void clickModel(){
        if(!isPaopaoVisible){
            RlMainPaopaoContainer.setVisibility(View.VISIBLE);
            BbPetZhuangyuan.setEnabled(true);
            BbPetYouxi.setEnabled(true);
            RlMainYouxiButton.startAnimation(youxiPaopaoAnimatorSet);
            RlMainShopButton.startAnimation(shopPaopaoAnimatorSet);
            RlMainZhuangyaunButton.startAnimation(zhuangyuanPaopaoAnimatorSet);
            RlMainDaohangButton.startAnimation(daohangPaopaoAnimatorSet);
            handler.sendEmptyMessageDelayed(5,500);
            handler.sendEmptyMessageDelayed(6,700);
            handler.sendEmptyMessageDelayed(7,900);
            handler.sendEmptyMessageDelayed(8,1100);
        }else {
            BbPetZhuangyuan.setEnabled(false);
            BbPetYouxi.setEnabled(false);
            RlMainYouxiButton.startAnimation(youxiPaopaoAnimatorSetBack);
            RlMainShopButton.startAnimation(shopPaopaoAnimatorSetBack);
            RlMainZhuangyaunButton.startAnimation(zhuangyuanPaopaoAnimatorSetBack);
            RlMainDaohangButton.startAnimation(daohangPaopaoAnimatorSetBack);
            handler.removeMessages(5);
            handler.removeMessages(6);
            handler.removeMessages(7);
            handler.removeMessages(8);
        }
        isPaopaoVisible=!isPaopaoVisible;

    }


    //设置3D模型
    private void set3DModel() {
        ContentUtils.provideAssets(this);
        launchModelRendererActivity(Uri.parse("assets://" + getPackageName() + "/" + "models/cowboy.dae"),0);
    }


    //设置便利贴内容
    private void setTip() {
        TextPageAdapter adapter = new TextPageAdapter(this);
        adapter.setText("低碳意指较低（更低）的温室气体（二氧化碳为主）的排放，低碳生活可以理解为：减少二氧化碳的排放，低能量、低消耗、低开支的生活方式。如今，这股风潮逐渐在我国一些大城市兴起，潜移默化地改变着人们的生活。低碳生活代表着更健康、更自然、更安全，返璞归真地去进行人与自然的活动。当今社会，随着人类生活发展，生活物质条件的提高，随之也对人类周围环境带来了影响与改变。对于普通人来说是一种生活态度，低碳生活既是一种生活方式，同时更是一种可持续发展的环保责任。");
        turnView.setPageTurnAdapter(adapter);
        handler.sendEmptyMessage(1);
    }

    //设置便利贴动画
    RotateAnimation tipRotateAnimation1 =new RotateAnimation(0,10, Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0);
    RotateAnimation tipRotateAnimation2 =new RotateAnimation(0,-15, Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0);
    RotateAnimation tipRotateAnimation3 =new RotateAnimation(0,5, Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0);
    AnimationSet tipAnimationSet=new AnimationSet(true);
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
    TranslateAnimation youxiPaopaoTranslateAnimationUp1 =new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.02f);
    TranslateAnimation youxiPaopaoTranslateAnimationDown =new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.02f,Animation.RELATIVE_TO_PARENT,-0.02f);
    TranslateAnimation youxiPaopaoTranslateAnimationUp2 =new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,-0.02f,Animation.RELATIVE_TO_PARENT,0);
    AnimationSet youxiPaopaoTranslateAnimationSet =new AnimationSet(true);
    TranslateAnimation shopPaopaoTranslateAnimationUp1 =new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.02f);
    TranslateAnimation shopPaopaoTranslateAnimationDown =new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.02f,Animation.RELATIVE_TO_PARENT,-0.02f);
    TranslateAnimation shopPaopaoTranslateAnimationUp2 =new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,-0.02f,Animation.RELATIVE_TO_PARENT,0);
    AnimationSet shopPaopaoTranslateAnimationSet =new AnimationSet(true);
    TranslateAnimation daohangPaopaoTranslateAnimationUp1 =new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.02f);
    TranslateAnimation daohangPaopaoTranslateAnimationDown =new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.02f,Animation.RELATIVE_TO_PARENT,-0.02f);
    TranslateAnimation daohangPaopaoTranslateAnimationUp2 =new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,-0.02f,Animation.RELATIVE_TO_PARENT,0);
    AnimationSet daohangPaopaoTranslateAnimationSet =new AnimationSet(true);
    TranslateAnimation zhuangyuanPaopaoTranslateAnimationUp1 =new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.02f);
    TranslateAnimation zhuangyuanPaopaoTranslateAnimationDown =new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.02f,Animation.RELATIVE_TO_PARENT,-0.02f);
    TranslateAnimation zhuangyuanPaopaoTranslateAnimationUp2 =new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,-0.02f,Animation.RELATIVE_TO_PARENT,0);
    AnimationSet zhuangyuanPaopaoTranslateAnimationSet =new AnimationSet(true);
    AlphaAnimation paopaoAlphaTranslateAnimation=new AlphaAnimation(0.8f,0.8f);
    private void setAnimations() {
        //便利贴
        tipRotateAnimation1.setDuration(1000);
        tipRotateAnimation1.setFillAfter(true);
        tipRotateAnimation2.setStartOffset(1000);
        tipRotateAnimation2.setDuration(1500);
        tipRotateAnimation2.setFillAfter(true);
        tipRotateAnimation3.setStartOffset(2500);
        tipRotateAnimation3.setDuration(500);
        tipRotateAnimation3.setFillAfter(true);
        tipAnimationSet.addAnimation(tipRotateAnimation1);
        tipAnimationSet.addAnimation(tipRotateAnimation2);
        tipAnimationSet.addAnimation(tipRotateAnimation3);
        tipAnimationSet.setFillAfter(true);
        tipAnimationSet.setInterpolator(new LinearInterpolator());
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
        paopaoAlphaTranslateAnimation.setFillAfter(true);
        paopaoAlphaTranslateAnimation.setDuration(0);
        youxiPaopaoTranslateAnimationUp1.setDuration(500);
        youxiPaopaoTranslateAnimationUp1.setFillAfter(true);
        youxiPaopaoTranslateAnimationDown.setStartOffset(500);
        youxiPaopaoTranslateAnimationDown.setDuration(1000);
        youxiPaopaoTranslateAnimationDown.setFillAfter(true);
        youxiPaopaoTranslateAnimationUp2.setStartOffset(1500);
        youxiPaopaoTranslateAnimationUp2.setDuration(500);
        youxiPaopaoTranslateAnimationUp2.setFillAfter(true);
        youxiPaopaoTranslateAnimationSet.addAnimation(youxiPaopaoTranslateAnimationUp1);
        youxiPaopaoTranslateAnimationSet.addAnimation(youxiPaopaoTranslateAnimationDown);
        youxiPaopaoTranslateAnimationSet.addAnimation(youxiPaopaoTranslateAnimationUp2);
        youxiPaopaoTranslateAnimationSet.addAnimation(paopaoAlphaTranslateAnimation);
        youxiPaopaoTranslateAnimationSet.setFillAfter(true);
        youxiPaopaoTranslateAnimationSet.setInterpolator(new LinearInterpolator());
        shopPaopaoTranslateAnimationUp1.setDuration(500);
        shopPaopaoTranslateAnimationUp1.setFillAfter(true);
        shopPaopaoTranslateAnimationDown.setStartOffset(500);
        shopPaopaoTranslateAnimationDown.setDuration(1000);
        shopPaopaoTranslateAnimationDown.setFillAfter(true);
        shopPaopaoTranslateAnimationUp2.setStartOffset(1500);
        shopPaopaoTranslateAnimationUp2.setDuration(500);
        shopPaopaoTranslateAnimationUp2.setFillAfter(true);
        shopPaopaoTranslateAnimationSet.addAnimation(shopPaopaoTranslateAnimationUp1);
        shopPaopaoTranslateAnimationSet.addAnimation(shopPaopaoTranslateAnimationDown);
        shopPaopaoTranslateAnimationSet.addAnimation(shopPaopaoTranslateAnimationUp2);
        shopPaopaoTranslateAnimationSet.addAnimation(paopaoAlphaTranslateAnimation);
        shopPaopaoTranslateAnimationSet.setFillAfter(true);
        shopPaopaoTranslateAnimationSet.setInterpolator(new LinearInterpolator());
        daohangPaopaoTranslateAnimationUp1.setDuration(500);
        daohangPaopaoTranslateAnimationUp1.setFillAfter(true);
        daohangPaopaoTranslateAnimationDown.setStartOffset(500);
        daohangPaopaoTranslateAnimationDown.setDuration(1000);
        daohangPaopaoTranslateAnimationDown.setFillAfter(true);
        daohangPaopaoTranslateAnimationUp2.setStartOffset(1500);
        daohangPaopaoTranslateAnimationUp2.setDuration(500);
        daohangPaopaoTranslateAnimationUp2.setFillAfter(true);
        daohangPaopaoTranslateAnimationSet.addAnimation(daohangPaopaoTranslateAnimationUp1);
        daohangPaopaoTranslateAnimationSet.addAnimation(daohangPaopaoTranslateAnimationDown);
        daohangPaopaoTranslateAnimationSet.addAnimation(daohangPaopaoTranslateAnimationUp2);
        daohangPaopaoTranslateAnimationSet.addAnimation(paopaoAlphaTranslateAnimation);
        daohangPaopaoTranslateAnimationSet.setFillAfter(true);
        daohangPaopaoTranslateAnimationSet.setInterpolator(new LinearInterpolator());
        zhuangyuanPaopaoTranslateAnimationUp1.setDuration(500);
        zhuangyuanPaopaoTranslateAnimationUp1.setFillAfter(true);
        zhuangyuanPaopaoTranslateAnimationDown.setStartOffset(500);
        zhuangyuanPaopaoTranslateAnimationDown.setDuration(1000);
        zhuangyuanPaopaoTranslateAnimationDown.setFillAfter(true);
        zhuangyuanPaopaoTranslateAnimationUp2.setStartOffset(1500);
        zhuangyuanPaopaoTranslateAnimationUp2.setDuration(500);
        zhuangyuanPaopaoTranslateAnimationUp2.setFillAfter(true);
        zhuangyuanPaopaoTranslateAnimationSet.addAnimation(zhuangyuanPaopaoTranslateAnimationUp1);
        zhuangyuanPaopaoTranslateAnimationSet.addAnimation(zhuangyuanPaopaoTranslateAnimationDown);
        zhuangyuanPaopaoTranslateAnimationSet.addAnimation(zhuangyuanPaopaoTranslateAnimationUp2);
        zhuangyuanPaopaoTranslateAnimationSet.addAnimation(paopaoAlphaTranslateAnimation);
        zhuangyuanPaopaoTranslateAnimationSet.setFillAfter(true);
        zhuangyuanPaopaoTranslateAnimationSet.setInterpolator(new LinearInterpolator());
        //设置字的跳动
        JumpingBeans.with(TvMainBiaoqian)
                .makeTextJump(0,2)
                .setIsWave(false)
                .build();
        JumpingBeans.with(TvMainBiaoqian)
                .makeTextJump(6,9)
                .build();
    }

    //动画控制器
    @SuppressLint("HandlerLeak")
    private  Handler handler =new Handler(){
        @Override
        public void  handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    if(!toStop){
                        turnView.startAnimation(tipAnimationSet);
                        handler.sendEmptyMessageDelayed(1,3000);
                    }else {
                        toStop=false;
                        handler.removeMessages(1);
                        handler.sendEmptyMessageDelayed(1,4000);
                    }
                    break;
                case 5:
                    if(isPaopaoVisible){
                        RlMainYouxiButton.startAnimation(youxiPaopaoTranslateAnimationSet);
                        handler.sendEmptyMessageDelayed(5,2000);
                    }
                    break;
                case 6:
                    if(isPaopaoVisible){
                        RlMainShopButton.startAnimation(shopPaopaoTranslateAnimationSet);
                        handler.sendEmptyMessageDelayed(6,2000);
                    }
                    break;
                case 7:
                    if(isPaopaoVisible){
                        RlMainDaohangButton.startAnimation(daohangPaopaoTranslateAnimationSet);
                        handler.sendEmptyMessageDelayed(7,2000);
                    }
                    break;
                case 8:
                    if(isPaopaoVisible){
                        RlMainZhuangyaunButton.startAnimation(zhuangyuanPaopaoTranslateAnimationSet);
                        handler.sendEmptyMessageDelayed(8,2000);
                    }
                    break;
                case 9:
//                    TvMainYuyin.setVisibility(View.INVISIBLE);
//                    startActivity(new Intent(PetMain.this,DaoHangActivity.class));
                    TvMainYuyin.setText("一棵松树树一年可以吸收二氧化碳18千克!");
                    break;
            }
        }
    };
    //便利贴是否被浏览
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
    public static Uri uri;
    private void launchModelRendererActivity(Uri uri,int type) {
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

    private Handler threeDHandler;


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




    /**
     * 以下内容为语音模块
     */



    /**
     * 开始识别
     */
    private void startRec() {
        buffer.setLength(0);
        TvMainYuyin.setText(null);// 清空显示内容
        mIatResults.clear();
        // 设置参数
        setParam();
        boolean isShowDialog =false;
        // 不显示听写对话框
        ret = mIat.startListening(mRecognizerListener);
        if (ret != ErrorCode.SUCCESS) {
            showTip("听写失败,错误码：" + ret+",请点击网址https://www.xfyun.cn/document/error-code查询解决方案");
        } else {
            showTip("请开始说话...");
        }
    }

    private void showTip(final String str) {
        mToast.setText(str);
        mToast.show();
    }

    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
//            Log.d(TAG, "SpeechRecognizer init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败，错误码：" + code+",请点击网址https://www.xfyun.cn/document/error-code查询解决方案");
            }
        }
    };

    /**
     * 参数设置
     *
     * @return
     */
    public void setParam() {
        // 清空参数
        mIat.setParameter(SpeechConstant.PARAMS, null);

        // 设置听写引擎
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);
        // 设置返回结果格式
        mIat.setParameter(SpeechConstant.RESULT_TYPE, resultType);


        if(language.equals("zh_cn")) {
            String lag ="mandarin";
            mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
            // 设置语言区域
            mIat.setParameter(SpeechConstant.ACCENT, lag);
        }else {

            mIat.setParameter(SpeechConstant.LANGUAGE, language);
        }

        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mIat.setParameter(SpeechConstant.VAD_BOS, "4000");

        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mIat.setParameter(SpeechConstant.VAD_EOS, "1000");

        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mIat.setParameter(SpeechConstant.ASR_PTT, "1");

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        mIat.setParameter(SpeechConstant.AUDIO_FORMAT,"wav");
        mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory()+"/msc/iat.wav");
    }

    /**
     * 听写监听器。
     */
    private RecognizerListener mRecognizerListener = new RecognizerListener() {

        @Override
        public void onBeginOfSpeech() {
            // 此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入
            showTip("开始说话");
        }

        @Override
        public void onError(SpeechError error) {
            // Tips：
            // 错误码：10118(您没有说话)，可能是录音机权限被禁，需要提示用户打开应用的录音权限。

            showTip(error.getPlainDescription(true));

        }

        @Override
        public void onEndOfSpeech() {
            // 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
            showTip("结束说话");
            handler.sendEmptyMessageDelayed(9,1000);
        }

        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            System.out.println(flg++);
            if (resultType.equals("json")) {

                printResult(results);

            }else if(resultType.equals("plain")) {
                buffer.append(results.getResultString());
                TvMainYuyin.setText(buffer.toString());
            }

        }

        @Override
        public void onVolumeChanged(int volume, byte[] data) {
            showTip("当前正在说话，音量大小：" + volume);
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {

        }
    };

    private void printResult(RecognizerResult results) {
        String text = JsonParser.parseIatResult(results.getResultString());

        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mIatResults.put(sn, text);

        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }

        TvMainYuyin.setText(resultBuffer.toString());
    }
}
