package com.superpichen.mainlibrary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.superpichen.mainlibrary.MyView.TopBar.StatusBarUtil;
import com.superpichen.mainlibrary.R;

public class OfflineShopActivity extends AppCompatActivity {
    private MapView MvOfflineShopMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_shop);
        StatusBarUtil.setTranslucentForImageViewAndDarkFont(this,0,null);
        MvOfflineShopMap = findViewById(R.id.MvOfflineShopMap);
        setMap();
    }

    /**
     * 设置地图
     */
    BaiduMap baiduMap;
    private void setMap() {
        baiduMap = MvOfflineShopMap.getMap();
        //普通地图 ,mBaiduMap是地图控制器对象
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        baiduMap.setTrafficEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        MvOfflineShopMap.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        MvOfflineShopMap.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        MvOfflineShopMap.onDestroy();
    }
}
