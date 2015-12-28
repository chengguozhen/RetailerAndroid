package com.neighbor.retailer_android.util.cascade.activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.util.widget.OnWheelChangedListener;
import com.neighbor.retailer_android.util.widget.WheelView;
import com.neighbor.retailer_android.util.widget.adapters.ArrayWheelAdapter;

public class ModifyLocationActivity extends BaseActivity implements OnClickListener, OnWheelChangedListener {
    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;
    private Button mBtnConfirm;
    private Button mBtnCancel;
    private int code;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_modify_location);

        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 为获取屏幕宽、高
        android.view.WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = (int) (d.getWidth() * 0.95); // 宽度设置为屏幕的0.7
        getWindow().setAttributes(p);
        code = getIntent().getExtras().getInt("key");
        data = getIntent().getExtras().getString("data");
        Log.d("ModifyLocationActivity", data);
        setUpViews();
        setUpListener();
        setUpData();
    }

    private void setUpViews() {
        mViewProvince = (WheelView) findViewById(R.id.id_province);
        mViewCity = (WheelView) findViewById(R.id.id_city);
        mViewDistrict = (WheelView) findViewById(R.id.id_district);
        mBtnConfirm = (Button) findViewById(R.id.btn_confirm);
        mBtnCancel = (Button) findViewById(R.id.btn_cancel);
    }

    private void setUpListener() {
        // 添加change事件
        mViewProvince.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
        // 添加change事件
        mViewDistrict.addChangingListener(this);
        // 添加onclick事件
        mBtnConfirm.setOnClickListener(this);
        // 添加onclick事件
        mBtnCancel.setOnClickListener(this);
    }

    private void setUpData() {
        initProvinceDatas();





        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(ModifyLocationActivity.this, mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
//        mViewProvince.setCurrentItem(provinceIndex);
        updateCities();


//        updateAreas();
        String[] address = data.split("\\|");
        Log.i("ModifyLocationActivity", "address.length:" + address.length);
        if (address.length==3){
            initIndex(address[0], address[1], address[2]);
        }





    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        // TODO Auto-generated method stub
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
//            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
        }
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[]{""};
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
        mViewDistrict.setCurrentItem(0);
        mCurrentDistrictName = areas[mViewDistrict.getCurrentItem()];
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[]{""};
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                backModifyInfo();
//                showSelectedResult();
                break;
            case R.id.btn_cancel:
                finish();
                break;
            default:
                break;
        }
    }

    private void initIndex(String provincename, String cityName, String areaName) {
        int provinceIndex = getProvinceIndex(provincename);
        if(provinceIndex==-1){
            return;
        }
        int cityIndex = getCityIndex(provincename, cityName);
        if(cityIndex==-1){
            return;
        }
        int areaIndex = getAreaIndex(cityName, areaName);
        if(areaIndex==-1){
            return;
        }
        mViewProvince.setCurrentItem(provinceIndex);
        mViewCity.setCurrentItem(cityIndex);
        mViewDistrict.setCurrentItem(areaIndex);
    }
    public void backModifyInfo()
    {
        Intent intent = new Intent();
        intent.putExtra("back", mCurrentProviceName + "|" + mCurrentCityName + "|" + mCurrentDistrictName);
        setResult(code, intent);
        finish();
    }
    private void showSelectedResult() {
        Toast.makeText(ModifyLocationActivity.this, "当前选中:" + mCurrentProviceName + "," + mCurrentCityName + ","
                + mCurrentDistrictName, Toast.LENGTH_SHORT).show();
        /*Toast.makeText(ModifyLocationActivity.this, "当前选中:" + mCurrentProviceName + "," + mCurrentCityName + ","
                + mCurrentDistrictName + "," + mCurrentZipCode, Toast.LENGTH_SHORT).show();*/
    }

    private int getProvinceIndex(String provinceName) {
        int provinceIndex = -1;
        for (int i = 0; i < mProvinceDatas.length; i++) {
            if (mProvinceDatas[i].equals(provinceName)) {
                provinceIndex = i;
                break;
            }
        }
        return provinceIndex;
    }

    private int getCityIndex(String provinceName, String cityName) {
        int cityIndex = -1;
        String[] cities = mCitisDatasMap.get(provinceName);
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].equals(cityName)) {
                cityIndex = i;
                break;
            }
        }
        return cityIndex;
    }

    private int getAreaIndex(String cityName, String areaName) {
        int areaIndex = -1;
        String[] areas = mDistrictDatasMap.get(cityName);
        for (int i = 0; i < areas.length; i++) {
            if (areas[i].equals(areaName)) {
                areaIndex = i;
                break;
            }
        }
        return areaIndex;
    }


}
