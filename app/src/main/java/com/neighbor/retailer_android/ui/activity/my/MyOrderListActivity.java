package com.neighbor.retailer_android.ui.activity.my;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.neighbor.retailer_android.R;

public class MyOrderListActivity extends Activity implements View.OnClickListener{

    private ImageButton back;
    private TextView title;
    private Button noPayOrederBtn;
    private Button finishOrderBtn;
    private FrameLayout listTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_list);

        back = (ImageButton)findViewById(R.id.wholesaler_back);
        title = (TextView)findViewById(R.id.wholesaler_title);
        title.setText("我的订单");
        noPayOrederBtn = (Button)findViewById(R.id.nopay_money_order);
        finishOrderBtn = (Button)findViewById(R.id.finish_order);
        listTab = (FrameLayout)findViewById(R.id.order_tab);
        back.setOnClickListener(this);
        noPayOrederBtn.setOnClickListener(this);
        finishOrderBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.wholesaler_back:
                finish();
                break;
            case R.id.nopay_money_order:
                //未付款订单列表
                break;
            case R.id.finish_order:
                //已完成订单列表
                break;
        }
    }
}
