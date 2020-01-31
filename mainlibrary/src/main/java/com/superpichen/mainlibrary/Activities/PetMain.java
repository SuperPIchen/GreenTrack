package com.superpichen.mainlibrary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.superpichen.mainlibrary.MyView.PageTurn.MimicPageTurnView;
import com.superpichen.mainlibrary.MyView.PageTurn.TextPageAdapter;
import com.superpichen.mainlibrary.MyView.TopBar.StatusBarUtil;
import com.superpichen.mainlibrary.R;

public class PetMain extends AppCompatActivity {
    MimicPageTurnView turnView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_mian);
        StatusBarUtil.setTranslucentForImageView(this,0,null);
        turnView=findViewById(R.id.PtMainTip);
        TextPageAdapter adapter = new TextPageAdapter(this);
        adapter.setText("低碳意指较低（更低）的温室气体（二氧化碳为主）的排放，低碳生活可以理解为：减少二氧化碳的排放，低能量、低消耗、低开支的生活方式。如今，这股风潮逐渐在我国一些大城市兴起，潜移默化地改变着人们的生活。低碳生活代表着更健康、更自然、更安全，返璞归真地去进行人与自然的活动。当今社会，随着人类生活发展，生活物质条件的提高，随之也对人类周围环境带来了影响与改变。对于普通人来说是一种生活态度，低碳生活既是一种生活方式，同时更是一种可持续发展的环保责任。");
        turnView.setPageTurnAdapter(adapter);
    }
}
