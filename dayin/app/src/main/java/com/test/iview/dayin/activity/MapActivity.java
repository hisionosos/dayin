package com.test.iview.dayin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.mapsdk.raster.model.BitmapDescriptorFactory;
import com.tencent.mapsdk.raster.model.Circle;
import com.tencent.mapsdk.raster.model.CircleOptions;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;
import com.test.iview.dayin.R;
import com.test.iview.dayin.util.DemoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018\5\5 0005.
 */

public class MapActivity extends com.tencent.tencentmap.mapsdk.map.MapActivity implements TencentLocationListener {
    @BindView(R.id.mapview)
    MapView mapview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_map);
        ButterKnife.bind(this);
//        获取TencentMap实例
        mTencentMap = mapview.getMap();
//        设置卫星底图
        mTencentMap.setSatelliteEnabled(false);
        //设置实时路况开启
        mTencentMap.setTrafficEnabled(false);
        //设置地图中心点
        double lat = 39;
        double lng = 116;
        lat = Double.valueOf(getIntent().getStringExtra("Map_Lat"));
        lng = Double.valueOf(getIntent().getStringExtra("Map_Lng"));
        mTencentMap.setCenter(new LatLng(lat, lng));
//        设置缩放级别
        mTencentMap.setZoom(11);
        LatLng latLng = new LatLng(lat, lng);

        Marker marker = mTencentMap.addMarker(new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory
                        .defaultMarker())
                .draggable(true));

//        marker.showInfoWindow();// 设置默认显示一个infoWindow
        TencentLocationRequest request = TencentLocationRequest.create();
        request.setInterval(10000);
        request.setAllowCache(true);
        request.setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_NAME);
        TencentLocationManager locationManager = TencentLocationManager.getInstance(this);
        error = locationManager.requestLocationUpdates(request,this);

        locationManager.removeUpdates(this);
        mLocationManager = TencentLocationManager.getInstance(this);
        // 设置坐标系为 gcj-02, 缺省坐标为 gcj-02, 所以通常不必进行如下调用
        mLocationManager.setCoordinateType(TencentLocationManager.COORDINATE_TYPE_GCJ02);
    }


    private int error;

    @OnClick(R.id.map_fanhui)
    public void onViewClicked() {
        finish();
    }

    private TencentLocation mLocation;
    private String mRequestParams;
    private Marker mLocationMarker;
    private TencentMap mTencentMap;
    private Circle mAccuracyCircle;
    private TencentLocationManager mLocationManager;

    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
        if (i == TencentLocation.ERROR_OK) {
            mLocation = tencentLocation;

            // 定位成功
            StringBuilder sb = new StringBuilder();
            sb.append("定位参数=").append(mRequestParams).append("\n");
            sb.append("(纬度=").append(tencentLocation.getLatitude()).append(",经度=")
                    .append(tencentLocation.getLongitude()).append(",精度=")
                    .append(tencentLocation.getAccuracy()).append("), 来源=")
                    .append(tencentLocation.getProvider()).append(", 地址=")
                    .append(tencentLocation.getAddress());
            LatLng latLngLocation = new LatLng(tencentLocation.getLatitude(), tencentLocation.getLongitude());


            // 更新 location Marker
            if (mLocationMarker == null) {
                mLocationMarker =
                        mTencentMap.addMarker(new MarkerOptions().
                                position(latLngLocation).
                                icon(BitmapDescriptorFactory.fromResource(R.drawable.mark_location)));
            } else {
                mLocationMarker.setPosition(latLngLocation);
            }

            if (mAccuracyCircle == null) {
                mAccuracyCircle = mTencentMap.addCircle(new CircleOptions().
                        center(latLngLocation).
                        radius(tencentLocation.getAccuracy()).
                        fillColor(0x884433ff).
                        strokeColor(0xaa1122ee).
                        strokeWidth(1));
            } else {
                mAccuracyCircle.setCenter(latLngLocation);
                mAccuracyCircle.setRadius(tencentLocation.getAccuracy());
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        TencentLocationRequest request = TencentLocationRequest.create();
        request.setInterval(5000);
        mLocationManager.requestLocationUpdates(request, this);

        mRequestParams = request.toString() + ", 坐标系="
                + DemoUtils.toString(mLocationManager.getCoordinateType());
        Log.e("weizhi",mLocationManager.requestLocationUpdates(request,this)+"");
    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {
        Log.e("weizhi1", s + "---" + i + "----" + s1);
    }
}
