package com.superpichen.mainlibrary.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.superpichen.mainlibrary.MyView.TopBar.StatusBarUtil;
import com.superpichen.mainlibrary.R;

public class DuihuanActivity extends AppCompatActivity {
    private LinearLayout LlDuihuanDialog;
    private TextView TvDuihuanButton;
    private ImageView IvDuihuanPintu6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duihuan);
        StatusBarUtil.setTranslucentForImageViewAndDarkFont(this,0,null);
        LlDuihuanDialog = findViewById(R.id.LlDuihuanDialog);
        TvDuihuanButton = findViewById(R.id.TvDuihuanButton);
        IvDuihuanPintu6 = findViewById(R.id.IvDuihuanPintu6);
        setAnimation();
        setDialog();
        setOnClick();
        handler.sendEmptyMessageDelayed(1,1000);
    }

    /**
     * 设置兑换的Dialog
     */
    AlertDialog dialog;
    private void setDialog() {
        View view=View.inflate(this,R.layout.dialog_duihuan,null);
        setDialogView(view);
        dialog=new AlertDialog.Builder(this,R.style.Translucent_NoTitle)
                .setView(view)
                .create();
    }

    /**
     * @param view
     * 设置Dialog的控件
     */
    private TextView TvDuihuanDialogNumber;
    private TextView TvDuihuanDialogCopy;
    private TextView TvDuihuanDialogCancel;
    private void setDialogView(View view) {
        TvDuihuanDialogNumber= view.findViewById(R.id.TvDuihuanDialogNumber);
        TvDuihuanDialogCopy = view.findViewById(R.id.TvDuihuanDialogCopy);
        TvDuihuanDialogCancel = view.findViewById(R.id.TvDuihuanDialogCancel);
    }

    /**
     * 设置监听
     */
    private void setOnClick() {
        TvDuihuanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessageDelayed(2,50);
            }
        });
        TvDuihuanDialogCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取剪贴板管理器
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", TvDuihuanDialogNumber.getText().toString());
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                Toast.makeText(DuihuanActivity.this,"已复制,到宠物图鉴去兑换吧",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        TvDuihuanDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    /**
     * 设置动画
     */
    private AlphaAnimation comeAlphaAnimation=new AlphaAnimation(0,1);
    private AlphaAnimation backAlphaAnimation=new AlphaAnimation(1,0);
    private AlphaAnimation comeAlphaAnimation1=new AlphaAnimation(0,1);
    private AlphaAnimation backAlphaAnimation1=new AlphaAnimation(1,0);
    private void setAnimation() {
        comeAlphaAnimation.setDuration(500);
        comeAlphaAnimation.setFillAfter(true);
        backAlphaAnimation.setDuration(500);
        backAlphaAnimation.setFillAfter(true);
        comeAlphaAnimation1.setDuration(500);
        comeAlphaAnimation1.setFillAfter(true);
        backAlphaAnimation1.setDuration(500);
        backAlphaAnimation1.setFillAfter(true);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    LlDuihuanDialog.setVisibility(View.VISIBLE);
                    LlDuihuanDialog.startAnimation(comeAlphaAnimation);
                    break;
                case 2:
                    LlDuihuanDialog.startAnimation(backAlphaAnimation);
                    handler.sendEmptyMessageDelayed(3,500);
                    break;
                case 3:
                    LlDuihuanDialog.setVisibility(View.GONE);
                    handler.sendEmptyMessage(4);
                    break;
                case 4:
                    IvDuihuanPintu6.startAnimation(backAlphaAnimation1);
                    handler.sendEmptyMessageDelayed(5,500);
                    break;
                case 5:
                    IvDuihuanPintu6.setBackgroundResource(R.drawable.yuanjiaojuxing_yellow_yinying_shape1);
                    IvDuihuanPintu6.startAnimation(comeAlphaAnimation1);
                    handler.sendEmptyMessageDelayed(6,1000);
                    break;
                case 6:
                    dialog.show();
            }
        }
    };
}
