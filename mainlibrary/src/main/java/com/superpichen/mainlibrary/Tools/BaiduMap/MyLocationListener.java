package com.superpichen.mainlibrary.Tools.BaiduMap;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;

public class MyLocationListener extends BDAbstractLocationListener {
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    public MyLocationListener(MapView mapView, BaiduMap baiduMap) {
        mMapView=mapView;
        mBaiduMap=baiduMap;
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        //mapView 销毁后不在处理新接收的位置
        if (bdLocation == null || mMapView == null){
            return;
        }
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(bdLocation.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(bdLocation.getDirection()).latitude(bdLocation.getLatitude())
                .longitude(bdLocation.getLongitude()).build();
        mBaiduMap.setMyLocationData(locData);
    }
}
