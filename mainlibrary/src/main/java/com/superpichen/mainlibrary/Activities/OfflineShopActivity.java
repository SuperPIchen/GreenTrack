package com.superpichen.mainlibrary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.Point;
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

        MapStatus.Builder builder = new MapStatus.Builder();
        builder.zoom(21.0f);
        baiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

        //定义Maker坐标点
        LatLng point = new LatLng(30.167317, 120.251778);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_marka);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        baiduMap.addOverlay(option);
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
