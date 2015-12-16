package com.neighbor.retailer_android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by Administrator on 2015/3/17.
 */
public class WelcomeActivity extends Activity {
    /**
     * WelcomeActivity Context
     */
    private Context context = WelcomeActivity.this;
    /**
     * 判断是否是第一次打开饭团App
     */
    private String ISOPENED = "open";
    /**
     * 延迟跳转线程Handler
     */
    private Handler handler;
    /**
     * Welcome画面停留时间
     */
    private int DELAYED_TIME = 500;


    private TextView versionTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        try {
            initWidget();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        handler = new Handler();
        //MobclickAgent.updateOnlineConfig(this);
        //openDelayedThread();
    }

//    /**
//     * Welcome延迟跳转线程
//     */
//    private void openDelayedThread() {
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (spUtilCity.get(LOC_CITY).equals("")) {
//                    //用户第一次打开App，那么跳转到CityListActivity
//                    setIsOpened(spUtilCity);
//                    Intent intent = new Intent();
//                    intent.setClass(WelcomeActivity.this, CityListActivity.class);
//                    startActivity(intent);
//                    WelcomeActivity.this.finish();
//
//                } else {
//                    //已获取到之前的城市数据，那么直接跳转到MainActivity上
//                    Intent intent = new Intent();
//                    intent.setClass(WelcomeActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    WelcomeActivity.this.finish();
//
//                }
//            }
//        }, DELAYED_TIME);
//    }

    private void initWidget() throws PackageManager.NameNotFoundException {
        PackageManager packageManager = getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        versionTV = (TextView) findViewById(R.id.welcome_version_id);
        versionTV.setText("Version"+packInfo.versionName);
    }

}
