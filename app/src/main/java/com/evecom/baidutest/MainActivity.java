package com.evecom.baidutest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;

public class MainActivity extends AppCompatActivity {
    MapView mapView;
    RadioGroup rg;
    BaiduMap baiduMap;
    Switch swTraffic;
    Button btnMyLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mapView=(MapView)findViewById(R.id.mapview);
        rg= (RadioGroup) findViewById(R.id.rgMapType);
        swTraffic= (Switch) findViewById(R.id.swTraffic);
        baiduMap=mapView.getMap();

        //地图类型
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbNORMAL:
                        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                        break;
                    case R.id.rbSATELLITE:
                        baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                        break;
                    case R.id.rbNONE:
                        baiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
                        break;
                }
                Log.i("test",checkedId+"");
            }
        });

        //实时交通
        swTraffic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    swTraffic.setText("开");
                    baiduMap.setTrafficEnabled(true);
                }else{
                    swTraffic.setText("关");
                    baiduMap.setTrafficEnabled(false);
                }
            }
        });

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
}
