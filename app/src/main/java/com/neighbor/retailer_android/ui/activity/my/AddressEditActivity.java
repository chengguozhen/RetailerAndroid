package com.neighbor.retailer_android.ui.activity.my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neighbor.retailer_android.R;

public class AddressEditActivity extends AppCompatActivity implements View.OnClickListener{

    /**
     * 删除 保存地址按钮
     */
    private Button deleteAddress;
    private Button saveAddress;
    /**
     * 所在区域 详细地址
     */
    private TextView area,address;
    /**
     * 收货人 电话 邮编
     */
    private TextView charger,telephone,zipCode;
    /**
     * LinearLayout 点击区域
     */
    private LinearLayout areaLl,addressLl,chargerLl,telephoneLl,zipCodeLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_edit);

        initView();
    }

    private void initView()
    {
        area = (TextView)findViewById(R.id.area);
        address = (TextView)findViewById(R.id.address);
        charger = (TextView)findViewById(R.id.charger);
        telephone = (TextView)findViewById(R.id.telephone);
        zipCode = (TextView)findViewById(R.id.zip_code);

        areaLl = (LinearLayout)findViewById(R.id.area_ll);
        addressLl = (LinearLayout)findViewById(R.id.address_ll);
        chargerLl = (LinearLayout)findViewById(R.id.charger_ll);
        telephoneLl = (LinearLayout)findViewById(R.id.telephone_ll);
        zipCodeLl = (LinearLayout)findViewById(R.id.zip_code_ll);

        saveAddress = (Button)findViewById(R.id.save_address);
        deleteAddress = (Button)findViewById(R.id.delete_address);

        areaLl.setOnClickListener(this);
        addressLl.setOnClickListener(this);
        chargerLl.setOnClickListener(this);
        telephoneLl.setOnClickListener(this);
        zipCodeLl.setOnClickListener(this);
        saveAddress.setOnClickListener(this);
        deleteAddress.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.area_ll:

                break;
            case R.id.charger_ll:

                break;
            case R.id.address_ll:

                break;
            case R.id.telephone_ll:

                break;
            case R.id.zip_code_ll:

                break;
            case R.id.delete_address:

                break;
            case R.id.save_address:

                break;
            default:
                break;
        }
    }

    private void editInfoDialog()
    {

    }
}
