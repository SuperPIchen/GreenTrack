package com.superpichen.mainlibrary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dzaitsev.android.widget.RadarChartView;
import com.jarvislau.destureviewbinder.GestureViewBinder;
import com.nightonke.jellytogglebutton.JellyToggleButton;
import com.nightonke.jellytogglebutton.State;
import com.superpichen.mainlibrary.MyView.TopBar.StatusBarUtil;
import com.superpichen.mainlibrary.R;
import com.superpichen.mainlibrary.Tools.JavaTools.AchievementInfo;
import com.superpichen.mainlibrary.Tools.JavaTools.FinalValue;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import me.zhouzhuo.zzhorizontalprogressbar.ZzHorizontalProgressBar;

import static android.graphics.Paint.Style.FILL;

public class AchievementActivity extends AppCompatActivity {
    private JellyToggleButton TbAchievementButton;
    private RadarChartView CvAchievement;
    private ZzHorizontalProgressBar PbAchievementPetCount;
    private TextView TvAchievementPetCountPercent;
    private ZzHorizontalProgressBar PbAchievementJiantanCount;
    private TextView TvAchievementJiantanCountPercent;
    private ZzHorizontalProgressBar PbAchievementXiaofeiCount;
    private TextView TvAchievementXiaofeiCountPercent;
    private ZzHorizontalProgressBar PbAchievementYouxiCount;
    private TextView TvAchievementYouxiCountPercent;
    private ZzHorizontalProgressBar PbAchievementShejiaoCount;
    private TextView TvAchievementShejiaoCountPercent;
    private ZzHorizontalProgressBar PbAchievementChuxingCount;
    private TextView TvAchievementChuxingCountPercent;
    private ScrollView SvAchievementZonglanContainer;
    private RelativeLayout RlAchievementViewgroup;
    private RelativeLayout RlAchievementView;
    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-02-25 18:36:13 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        StatusBarUtil.setTranslucentForImageView(this,0,null);
        TbAchievementButton = findViewById( R.id.TbAchievementButton );
        CvAchievement = findViewById( R.id.CvAchievement );
        PbAchievementPetCount = findViewById( R.id.PbAchievementPetCount );
        TvAchievementPetCountPercent = findViewById( R.id.TvAchievementPetCountPercent );
        PbAchievementJiantanCount = findViewById( R.id.PbAchievementJiantanCount );
        TvAchievementJiantanCountPercent = findViewById( R.id.TvAchievementJiantanCountPercent );
        PbAchievementXiaofeiCount = findViewById( R.id.PbAchievementXiaofeiCount );
        TvAchievementXiaofeiCountPercent = findViewById( R.id.TvAchievementXiaofeiCountPercent );
        PbAchievementYouxiCount = findViewById( R.id.PbAchievementYouxiCount );
        TvAchievementYouxiCountPercent = findViewById( R.id.TvAchievementYouxiCountPercent );
        PbAchievementShejiaoCount = findViewById( R.id.PbAchievementShejiaoCount );
        TvAchievementShejiaoCountPercent = findViewById( R.id.TvAchievementShejiaoCountPercent );
        PbAchievementChuxingCount = findViewById( R.id.PbAchievementChuxingCount );
        TvAchievementChuxingCountPercent = findViewById( R.id.TvAchievementChuxingCountPercent );
        SvAchievementZonglanContainer = findViewById(R.id.SvAchievementZonglanContainer);
        RlAchievementViewgroup = findViewById(R.id.RlAchievementViewgroup);
        RlAchievementView = findViewById(R.id.RlAchievementView);    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2020-02-25 18:36:13 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);
        findViews();
        setData();
        setRadarChartView();
        setProgress();
        setGestureViewBinder();
        setOnclick();
    }

    /**
     * 设置缩放视图
     */
    private void setGestureViewBinder() {
        GestureViewBinder bind=GestureViewBinder.bind(this,RlAchievementViewgroup,RlAchievementView);
        bind.setFullGroup(true);
    }

    /**
     * 设置监听
     */
    private void setOnclick() {
        TbAchievementButton.setOnStateChangeListener(new JellyToggleButton.OnStateChangeListener() {
            @Override
            public void onStateChange(float process, State state, JellyToggleButton jtb) {
                if(state.equals(State.RIGHT))
                    SvAchievementZonglanContainer.setVisibility(View.INVISIBLE);
                if(state.equals(State.LEFT))
                    SvAchievementZonglanContainer.setVisibility(View.VISIBLE);
            }
        });
    }

    List<AchievementInfo> data=new ArrayList<>();
    int chongwuTrue=0;
    int jiantanTrue=0;
    int xiaofeiTrue=0;
    int chuxingTrue=0;
    int youxiTrue=0;
    int shejiaoTrue=0;
    int chongwuF;
    int jiantanF;
    int xiaofeiF;
    int chuxingF;
    int youxiF;
    int shejiaoF;
    /**
     * 设置数据
     */
    private void setData() {
        data.add(new AchievementInfo("新主人",true, FinalValue.ACHIEVEMENTTYPEOFCHONGWU));
        data.add(new AchievementInfo("爱心懵懂",true, FinalValue.ACHIEVEMENTTYPEOFCHONGWU));
        data.add(new AchievementInfo("爱心满屋",false, FinalValue.ACHIEVEMENTTYPEOFCHONGWU));
        data.add(new AchievementInfo("负责的主人",false, FinalValue.ACHIEVEMENTTYPEOFCHONGWU));
        data.add(new AchievementInfo("日理万机的主人",true, FinalValue.ACHIEVEMENTTYPEOFCHONGWU));
        data.add(new AchievementInfo("浪漫的主人",false, FinalValue.ACHIEVEMENTTYPEOFCHONGWU));
        data.add(new AchievementInfo("居家小能手",false, FinalValue.ACHIEVEMENTTYPEOFCHONGWU));
        data.add(new AchievementInfo("动物管理员",true, FinalValue.ACHIEVEMENTTYPEOFCHONGWU));
        data.add(new AchievementInfo("一颗小草",true, FinalValue.ACHIEVEMENTTYPEOFJIANTAN));
        data.add(new AchievementInfo("一缕阳光",false, FinalValue.ACHIEVEMENTTYPEOFJIANTAN));
        data.add(new AchievementInfo("香草扑鼻",true, FinalValue.ACHIEVEMENTTYPEOFJIANTAN));
        data.add(new AchievementInfo("灌木丛中",true, FinalValue.ACHIEVEMENTTYPEOFJIANTAN));
        data.add(new AchievementInfo("桃花朵朵开",false, FinalValue.ACHIEVEMENTTYPEOFJIANTAN));
        data.add(new AchievementInfo("满面春风",false, FinalValue.ACHIEVEMENTTYPEOFJIANTAN));
        data.add(new AchievementInfo("地球卫士",false, FinalValue.ACHIEVEMENTTYPEOFJIANTAN));
        data.add(new AchievementInfo("杨树林",false, FinalValue.ACHIEVEMENTTYPEOFJIANTAN));
        data.add(new AchievementInfo("爱宠消费主义",false, FinalValue.ACHIEVEMENTTYPEOFXIAOFEI));
        data.add(new AchievementInfo("生活小能手",true, FinalValue.ACHIEVEMENTTYPEOFXIAOFEI));
        data.add(new AchievementInfo("勤俭持家",false, FinalValue.ACHIEVEMENTTYPEOFXIAOFEI));
        data.add(new AchievementInfo("三位一体",false, FinalValue.ACHIEVEMENTTYPEOFXIAOFEI));
        data.add(new AchievementInfo("单车小能手",true, FinalValue.ACHIEVEMENTTYPEOFCHUXING));
        data.add(new AchievementInfo("公交常驻户",false, FinalValue.ACHIEVEMENTTYPEOFCHUXING));
        data.add(new AchievementInfo("地铁冲冲冲",false, FinalValue.ACHIEVEMENTTYPEOFCHUXING));
        data.add(new AchievementInfo("10公里冲刺",true, FinalValue.ACHIEVEMENTTYPEOFCHUXING));
        data.add(new AchievementInfo("30公里长跑",false, FinalValue.ACHIEVEMENTTYPEOFCHUXING));
        data.add(new AchievementInfo("50公里坚持",false, FinalValue.ACHIEVEMENTTYPEOFCHUXING));
        data.add(new AchievementInfo("落地成盒",true, FinalValue.ACHIEVEMENTTYPEOFYOUXI));
        data.add(new AchievementInfo("持之以恒",false, FinalValue.ACHIEVEMENTTYPEOFYOUXI));
        data.add(new AchievementInfo("一飞冲天",false, FinalValue.ACHIEVEMENTTYPEOFYOUXI));
        data.add(new AchievementInfo("目不转睛",true, FinalValue.ACHIEVEMENTTYPEOFYOUXI));
        data.add(new AchievementInfo("命悬一线",true, FinalValue.ACHIEVEMENTTYPEOFYOUXI));
        data.add(new AchievementInfo("决策大师",false, FinalValue.ACHIEVEMENTTYPEOFYOUXI));
        data.add(new AchievementInfo("见钱眼开",true, FinalValue.ACHIEVEMENTTYPEOFYOUXI));
        data.add(new AchievementInfo("风驰电掣",true, FinalValue.ACHIEVEMENTTYPEOFYOUXI));
        data.add(new AchievementInfo("爱的家庭",true, FinalValue.ACHIEVEMENTTYPEOFYOUXI));
        data.add(new AchievementInfo("不亦说乎",false, FinalValue.ACHIEVEMENTTYPEOFSHEJIAO));
        data.add(new AchievementInfo("社交小能手",false, FinalValue.ACHIEVEMENTTYPEOFSHEJIAO));
        data.add(new AchievementInfo("蜘蛛网",true, FinalValue.ACHIEVEMENTTYPEOFSHEJIAO));
        data.add(new AchievementInfo("爱的供养",false, FinalValue.ACHIEVEMENTTYPEOFSHEJIAO));
        int chongwuCount=0;
        int jiantanCount=0;
        int xiaofeiCount=0;
        int chuxingCount=0;
        int youxiCount=0;
        int shejiaoCount=0;
        for(int i=0;i<data.size();i++){
            AchievementInfo info=data.get(i);
            switch (info.getType()){
                case FinalValue.ACHIEVEMENTTYPEOFCHONGWU:
                    chongwuCount++;
                    if(info.isGet())
                        chongwuTrue++;
                    break;
                case FinalValue.ACHIEVEMENTTYPEOFJIANTAN:
                    jiantanCount++;
                    if(info.isGet())
                        jiantanTrue++;
                    break;
                case FinalValue.ACHIEVEMENTTYPEOFXIAOFEI:
                    xiaofeiCount++;
                    if(info.isGet())
                        xiaofeiTrue++;
                    break;
                case FinalValue.ACHIEVEMENTTYPEOFCHUXING:
                    chuxingCount++;
                    if(info.isGet())
                        chuxingTrue++;
                    break;
                case FinalValue.ACHIEVEMENTTYPEOFYOUXI:
                    youxiCount++;
                    if(info.isGet())
                        youxiTrue++;
                    break;
                case FinalValue.ACHIEVEMENTTYPEOFSHEJIAO:
                    shejiaoCount++;
                    if(info.isGet())
                        shejiaoTrue++;
                    break;
            }
        }
        chongwuF=100*chongwuTrue/chongwuCount;
        jiantanF=100*jiantanTrue/jiantanCount;
        xiaofeiF=100*xiaofeiTrue/xiaofeiCount;
        chuxingF=100*chuxingTrue/chuxingCount;
        youxiF=100*youxiTrue/youxiCount;
        shejiaoF=100*shejiaoTrue/shejiaoCount;
    }

    /**
     * 设置饼状图
     */
    private void setRadarChartView() {
        final Map<String, Float> axis = new LinkedHashMap<>(6);
        axis.put("碳宠成就",(float)chongwuTrue);
        axis.put("减碳成就",(float)jiantanTrue);
        axis.put("消费成就",(float)xiaofeiTrue);
        axis.put("出行成就",(float)chuxingTrue);
        axis.put("游戏成就",(float)youxiTrue);
        axis.put("社交成就",(float)shejiaoTrue);
        CvAchievement.setAxis(axis);
        CvAchievement.setAutoSize(true);
        CvAchievement.setAxisWidth(6);
        CvAchievement.setAxisTick(1);
        CvAchievement.setChartStyle(FILL);
    }

    /**
     * 设置进度条
     */
    private void setProgress() {
        PbAchievementPetCount.setProgress(chongwuF);
        TvAchievementPetCountPercent.setText(chongwuF+"%");
        PbAchievementJiantanCount.setProgress(jiantanF);
        TvAchievementJiantanCountPercent.setText(jiantanF+"%");
        PbAchievementXiaofeiCount.setProgress(xiaofeiF);
        TvAchievementXiaofeiCountPercent.setText(xiaofeiF+"%");
        PbAchievementChuxingCount.setProgress(chuxingF);
        TvAchievementChuxingCountPercent.setText(chuxingF+"%");
        PbAchievementYouxiCount.setProgress(youxiF);
        TvAchievementYouxiCountPercent.setText(youxiF+"%");
        PbAchievementShejiaoCount.setProgress(shejiaoF);
        TvAchievementShejiaoCountPercent.setText(shejiaoF+"%");
    }
}
