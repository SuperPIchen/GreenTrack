package com.superpichen.mainlibrary.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.superpichen.mainlibrary.R;
import com.superpichen.mainlibrary.Tools.BaiduMap.MyLocationListener;
import com.superpichen.mainlibrary.Tools.BaiduMap.PoiOverlay;
import com.superpichen.mainlibrary.Tools.BaiduMap.TransitRouteOverlay;
import com.superpichen.mainlibrary.Tools.JavaTools.Dip2px;
import com.superpichen.mainlibrary.Tools.JavaTools.FinalValue;
import com.superpichen.mainlibrary.Tools.JavaTools.PointsInfo;
import com.superpichen.unitycontactlib.ContactTool;
import com.unity3d.player.UnityPlayerActivity;

public class DaoHangActivity extends UnityPlayerActivity {
    private FrameLayout FlDaoHangPekemonContainer;
    private ImageView IvDaoHangPeKemonBack;
    private MapView MvDaoHangMap;
    private BoomMenuButton BbDaoHangMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dao_hang);
        FlDaoHangPekemonContainer = findViewById(R.id.FlDaoHangPekemonContainer);
        IvDaoHangPeKemonBack = findViewById(R.id.IvDaoHangPeKemonBack);
        MvDaoHangMap = findViewById(R.id.MvDaoHangMap);
        BbDaoHangMenu = findViewById(R.id.BbDaoHangMenu);
        selectScn();
        initMap();
        initPOI();
        inttGuihua();
        setMenu();
    }

    /**
     * 设置公交路线规划
     */
    String startDidian="浙江大学城市学院";
    String endDidian="杭州东站";
    PlanNode stNode;
    PlanNode enNode;
    RoutePlanSearch mSearch;
    private void inttGuihua() {
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(listener);
        stNode = PlanNode.withCityNameAndPlaceName("杭州", startDidian);
        enNode = PlanNode.withCityNameAndPlaceName("杭州", endDidian);
    }
    OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
        @Override
        public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

        }

        @Override
        public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
            //创建TransitRouteOverlay实例
            TransitRouteOverlay overlay = new TransitRouteOverlay(baiduMap);
            //获取路径规划数据,(以返回的第一条数据为例)
            //为TransitRouteOverlay实例设置路径数据
            if (transitRouteResult.getRouteLines().size() > 0) {
                overlay.setData(transitRouteResult.getRouteLines().get(0));
                //在地图上绘制TransitRouteOverlay
                overlay.addToMap();
            }
        }

        @Override
        public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

        }

        @Override
        public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

        }

        @Override
        public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

        }

        @Override
        public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

        }
    };

    /**
     * 设置POI
     */
    PoiSearch mPoiSearch;
    private void initPOI() {
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(listener1);

    }
    OnGetPoiSearchResultListener listener1 = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                baiduMap.clear();

                //创建PoiOverlay对象
                PoiOverlay poiOverlay = new PoiOverlay(baiduMap);

                //设置Poi检索数据
                poiOverlay.setData(poiResult);

                //将poiOverlay添加至地图并缩放至合适级别
                poiOverlay.addToMap();
                poiOverlay.zoomToSpan();
                Toast.makeText(DaoHangActivity.this,"成功",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(DaoHangActivity.this,"失败",Toast.LENGTH_SHORT).show();
                Log.e("错误",poiResult.error.toString());
            }
        }
        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

        }
        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }
        //废弃
        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

        }
    };


    /**
     * 设置菜单
     */
    private void setMenu() {
        TextOutsideCircleButton.Builder builderBall = new TextOutsideCircleButton.Builder()
                .normalImageRes(R.drawable.daohang_ball)
                .normalText("AR宠物")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        MvDaoHangMap.setVisibility(View.GONE);
                        FlDaoHangPekemonContainer.setVisibility(View.VISIBLE);
                    }
                });
        BbDaoHangMenu.addBuilder(builderBall);
        TextOutsideCircleButton.Builder builderJijian = new TextOutsideCircleButton.Builder()
                .normalImageRes(R.drawable.daohang_jijian)
                .normalText("简易模式")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        MvDaoHangMap.setVisibility(View.VISIBLE);
                        FlDaoHangPekemonContainer.setVisibility(View.GONE);
                    }
                });
        final AlertDialog[] dialog = new AlertDialog[2];
        BbDaoHangMenu.addBuilder(builderJijian);
        TextOutsideCircleButton.Builder builderGuihua = new TextOutsideCircleButton.Builder()
                .normalImageRes(R.drawable.daohang_guihua)
                .normalText("公交规划")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        View view=View.inflate(DaoHangActivity.this,R.layout.dialog_daohang_guihua,null);
                        InitGuiHuaView(view);
                        dialog[1]=new AlertDialog.Builder(DaoHangActivity.this,R.style.Translucent_NoTitle)
                                .setView(view)
                                .create();
                        dialog[1].show();
                        android.view.WindowManager.LayoutParams p = dialog[1].getWindow().getAttributes();  //获取对话框当前的参数值
                        Dip2px dip2px=new Dip2px(DaoHangActivity.this);
                        p.height = dip2px.dip2px(300);
                        p.width = dip2px.dip2px(230);
                        dialog[1].getWindow().setAttributes(p);     //设置生效
                    }

                    private void InitGuiHuaView(View view) {
                        view.findViewById(R.id.IvDaoHangGuiHuaYes).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //设置定位属性
                                myLocationConfiguration=new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL,true, BitmapDescriptorFactory.fromResource(R.drawable.yuanjiaojuxing_blue_shape));
                                baiduMap.setMyLocationConfiguration(myLocationConfiguration);
                                EditText EtDaoHangStart = view.findViewById(R.id.EtDaoHangStart);
                                EditText EtDaoHangEnd = view.findViewById(R.id.EtDaoHangEnd);
                                startDidian=EtDaoHangStart.getText().toString();
                                endDidian=EtDaoHangEnd.getText().toString();
                                mSearch.transitSearch((new TransitRoutePlanOption())
                                        .from(stNode)
                                        .to(enNode)
                                        .city("杭州"));
                                dialog[1].dismiss();
                            }
                        });
                        view.findViewById(R.id.IvDaoHangGuiHuaCancel).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog[1].dismiss();
                            }
                        });
                    }
                });
        BbDaoHangMenu.addBuilder(builderGuihua);

        TextOutsideCircleButton.Builder builderPoi = new TextOutsideCircleButton.Builder()
                .normalImageRes(R.drawable.daohang_poi)
                .normalText("POI")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        View view=View.inflate(DaoHangActivity.this,R.layout.dialog_daohang_poi,null);
                        Init(view);
                        dialog[0] =new AlertDialog.Builder(DaoHangActivity.this,R.style.Translucent_NoTitle)
                                .setView(view)
                                .create();
                        dialog[0].show();
                        android.view.WindowManager.LayoutParams p = dialog[0].getWindow().getAttributes();  //获取对话框当前的参数值
                        Dip2px dip2px=new Dip2px(DaoHangActivity.this);
                        p.height = dip2px.dip2px(450);
                        p.width = dip2px.dip2px(170);
                        dialog[0].getWindow().setAttributes(p);     //设置生效
                    }

                    /**
                     *设置dialog视图
                     * @param view
                     */

                    private void Init(View view) {
                        view.findViewById(R.id.TvDaoHangFood).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPoiSearch.searchInCity(new PoiCitySearchOption()
                                        .city("新昌") //必填
                                        .keyword("美食") //必填
                                        .pageNum(10));
                                dialog[0].dismiss();
                            }
                        });
                        view.findViewById(R.id.TvDaoHangFood).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPoiSearch.searchInCity(new PoiCitySearchOption()
                                        .city("新昌") //必填
                                        .keyword("美食") //必填
                                        .pageNum(10));
                            }
                        });
                        view.findViewById(R.id.TvDaoHangFood).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //设置定位属性
                                myLocationConfiguration=new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL,true, BitmapDescriptorFactory.fromResource(R.drawable.yuanjiaojuxing_blue_shape));
                                baiduMap.setMyLocationConfiguration(myLocationConfiguration);
                                mPoiSearch.searchInCity(new PoiCitySearchOption()
                                        .city("新昌") //必填
                                        .keyword("美食") //必填
                                        .pageNum(10));
                                dialog[0].dismiss();
                            }
                        });
                        view.findViewById(R.id.TvDaoHangJingdian).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPoiSearch.searchInCity(new PoiCitySearchOption()
                                        .city("新昌") //必填
                                        .keyword("景点") //必填
                                        .pageNum(10));
                                dialog[0].dismiss();
                            }
                        });
                    }
                });
        BbDaoHangMenu.addBuilder(builderPoi);
    }

    /**
     * 加载地图
     */
    BaiduMap baiduMap;
    LocationClient mLocationClient;
    MyLocationConfiguration myLocationConfiguration;
    private void initMap() {
        baiduMap=MvDaoHangMap.getMap();

        //设置显示级别
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.zoom(18.0f);
        baiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

        //开启交通图
        baiduMap.setTrafficEnabled(true);
        //开启定位
        baiduMap.setMyLocationEnabled(true);
//        MyLocationListener myLocationListener=new MyLocationListener(MvDaoHangMap,baiduMap);
        //定位初始化
        mLocationClient = new LocationClient(this);
        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        //设置locationClientOption
        mLocationClient.setLocOption(option);
        //注册LocationListener监听器
//        MyLocationListener1 myLocationListener = new MyLocationListener1();
        MyLocationListener myLocationListener = new MyLocationListener(MvDaoHangMap,baiduMap);
        mLocationClient.registerLocationListener(myLocationListener);
        //开启地图定位图层
        mLocationClient.start();

        //设置定位属性
        myLocationConfiguration=new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING,true, BitmapDescriptorFactory.fromResource(R.drawable.yuanjiaojuxing_blue_shape));
        baiduMap.setMyLocationConfiguration(myLocationConfiguration);
    }

    /**
     * 选择unity场景
     */
    private void selectScn() {
        ContactTool.TargetUnitySecen="Pekemon";
        IvDaoHangPeKemonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUnityPlayer.quit();
            }
        });
        int glesMode = mUnityPlayer.getSettings().getInt("gles_mode", 1);
        mUnityPlayer.init(glesMode, false);
        FlDaoHangPekemonContainer.addView(mUnityPlayer.getView());
    }

//    @Override
//    protected void onResume() {
//        mUnityPlayer.resume();
//        super.onResume();
//    }



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        mUnityPlayer.configurationChanged(newConfig);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        mUnityPlayer.windowFocusChanged(hasFocus);
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_MULTIPLE)
            return mUnityPlayer.onKeyMultiple(event.getKeyCode(), event.getRepeatCount(), event);
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onPause() {
        mUnityPlayer.pause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        MvDaoHangMap.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        MvDaoHangMap.onResume();
    }


    @Override
    protected void onDestroy() {
        mLocationClient.stop();
        baiduMap.setMyLocationEnabled(false);
        MvDaoHangMap.onDestroy();
        MvDaoHangMap = null;
        mUnityPlayer.quit();
        mPoiSearch.destroy();
        mSearch.destroy();
        super.onDestroy();
    }

    public class MyLocationListener1 extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            //mapView 销毁后不在处理新接收的位置
            if (bdLocation == null || MvDaoHangMap == null){
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(bdLocation.getDirection()).latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();
            baiduMap.setMyLocationData(locData);
        }
    }

}
