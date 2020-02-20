package com.superpichen.mainlibrary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.superpichen.mainlibrary.MyView.PileLayout;
import com.superpichen.mainlibrary.MyView.TopBar.StatusBarUtil;
import com.superpichen.mainlibrary.R;
import com.superpichen.mainlibrary.Tools.JavaTools.TujianPileLayoutAdapter;
import com.superpichen.mainlibrary.Tools.JavaTools.TujianPileLayoutInfo;

import java.util.ArrayList;
import java.util.List;

public class TujianActivity extends AppCompatActivity {

    private PileLayout PlTujianContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tujian);
        StatusBarUtil.setTranslucentForImageView(this,0,null);
        PlTujianContainer = findViewById(R.id.PlTujianContainer);
        setPileLayout();
    }

    /**
     * 设置图鉴卡片
     */
    private void setPileLayout() {
        List<TujianPileLayoutInfo> data=new ArrayList<>();
        data.add(new TujianPileLayoutInfo("牛仔很忙","525245425",R.drawable.tujianniuzai));
        data.add(new TujianPileLayoutInfo("鹿鹿无为","25252555",R.drawable.tujianniuzai));
        data.add(new TujianPileLayoutInfo("鹿鹿无为","25252555",R.drawable.tujianniuzai));
        data.add(new TujianPileLayoutInfo("鹿鹿无为","25252555",R.drawable.tujianniuzai));
        data.add(new TujianPileLayoutInfo("鹿鹿无为","25252555",R.drawable.tujianniuzai));
        data.add(new TujianPileLayoutInfo("鹿鹿无为","25252555",R.drawable.tujianniuzai));
        data.add(new TujianPileLayoutInfo("鹿鹿无为","25252555",R.drawable.tujianniuzai));
        data.add(new TujianPileLayoutInfo("鹿鹿无为","25252555",R.drawable.tujianniuzai));
        data.add(new TujianPileLayoutInfo("鹿鹿无为","25252555",R.drawable.tujianniuzai));
        data.add(new TujianPileLayoutInfo("鹿鹿无为","25252555",R.drawable.tujianniuzai));
        data.add(new TujianPileLayoutInfo("鹿鹿无为","25252555",R.drawable.tujianniuzai));
        TujianPileLayoutAdapter adapter=new TujianPileLayoutAdapter(data,this);
        PlTujianContainer.setAdapter(adapter);
    }
}
