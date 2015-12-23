package com.neighbor.retailer_android.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.ui.activity.my.AddressListActivity;
import com.neighbor.retailer_android.ui.activity.my.MyIdentityActivity;
import com.neighbor.retailer_android.ui.activity.my.MyOrderListActivity;
import com.neighbor.retailer_android.ui.activity.wholesale.WholeSaleListActivity;

@SuppressLint("NewApi")
public class MyTabFragment extends Fragment implements View.OnClickListener{

    private View rootView;
    private ImageView myHeadImg;
    private TextView myName;
    private LinearLayout myCard;
    private LinearLayout myOrder;
    private LinearLayout myWholeSale;
    private LinearLayout myIntegral;
    private LinearLayout myStockCycleCount;
    private LinearLayout myPriceChangeCount;
    private LinearLayout myPwdChange;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.main_tab_04, container, false);
        myHeadImg = (ImageView)rootView.findViewById(R.id.my_head_img);
        myName = (TextView)rootView.findViewById(R.id.my_name);
        myCard = (LinearLayout)rootView.findViewById(R.id.my_bussiness_card);
        myOrder = (LinearLayout)rootView.findViewById(R.id.my_order);
        myWholeSale = (LinearLayout)rootView.findViewById(R.id.my_whole_sale);
        myIntegral = (LinearLayout)rootView.findViewById(R.id.my_integral);
        myStockCycleCount = (LinearLayout)rootView.findViewById(R.id.my_stock_cycle_count);
        myPriceChangeCount = (LinearLayout)rootView.findViewById(R.id.my_price_change_count);
        myPwdChange = (LinearLayout)rootView.findViewById(R.id.my_pwd_change);
        myCard.setOnClickListener(this);
        myOrder.setOnClickListener(this);
        myWholeSale.setOnClickListener(this);
        myIntegral.setOnClickListener(this);
        myStockCycleCount.setOnClickListener(this);
        myPriceChangeCount.setOnClickListener(this);
        myPwdChange.setOnClickListener(this);
        myHeadImg.setImageResource(R.mipmap.merchandise_default);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.my_bussiness_card:
                //跳转到我的名片界面
                Intent intentIdentity= new Intent(getActivity(), MyIdentityActivity.class);
                startActivity(intentIdentity);
                break;
            case R.id.my_order:
                //跳转至我的订单页面
                Intent intent1 = new Intent(getActivity(), MyOrderListActivity.class);
                intent1.putExtra("KEY",myName.getText().toString());
                startActivity(intent1);
                break;
            case R.id.my_whole_sale:
                //跳转至我的批发商
                Intent intent = new Intent(getActivity(), WholeSaleListActivity.class);
                intent.putExtra("KEY",myName.getText().toString());
                startActivity(intent);
                break;
            case R.id.my_integral:
                //跳转至我的地址页面
                Intent intent2 = new Intent(getActivity(), AddressListActivity.class);
                startActivity(intent2);
                break;
            case R.id.my_stock_cycle_count:
                //跳转至进货周期统计界面
                break;
            case R.id.my_price_change_count:
                //跳转至价格变动统计界面
                break;
            case R.id.my_pwd_change:
                //跳转至修改密码页面
                break;
        }
    }
}
