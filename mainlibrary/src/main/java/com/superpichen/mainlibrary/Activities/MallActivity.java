package com.superpichen.mainlibrary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.superpichen.mainlibrary.MyView.TopBar.StatusBarUtil;
import com.superpichen.mainlibrary.R;
import com.superpichen.mainlibrary.Tools.JavaTools.FinalValue;

public class MallActivity extends AppCompatActivity {

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);
        StatusBarUtil.setTranslucentForImageView(this,0,null);
        intent=getIntent();
        setResult(FinalValue.GETPOINTFORMALLACTIVITY);
    }
}
