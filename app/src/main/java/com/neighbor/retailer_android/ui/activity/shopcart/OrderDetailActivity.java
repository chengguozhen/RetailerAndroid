package com.neighbor.retailer_android.ui.activity.shopcart;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.ShopInfo;
import com.neighbor.retailer_android.ui.adapter.OrderShopListAdapter;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends Activity implements View.OnClickListener{

    private ImageButton back;
    private TextView title;
    private ListView listView;
    private Button delete;
    private LayoutInflater mInflater;
    private View header;
    private View footer;
    private ImageButton addressImg;
    private TextView recName;
    private TextView recPhone;
    private TextView recAddress;
    private TextView wsName;
    private TextView totalPrice;
    private TextView orderCode;
    private TextView orderTime;
    private OrderShopListAdapter adpater;
    private List<ShopInfo> mList = new ArrayList<ShopInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        back = (ImageButton)findViewById(R.id.wholesaler_back);
        title = (TextView)findViewById(R.id.wholesaler_title);
        title.setText("订单详情");
        listView = (ListView)findViewById(R.id.order_submit_lv);
        delete = (Button)findViewById(R.id.delete_order);
        mInflater = LayoutInflater.from(OrderDetailActivity.this);
        header = mInflater.inflate(R.layout.submit_order_lv_header, null);
        footer = mInflater.inflate(R.layout.order_detail_lv_footer, null);
        listView.addHeaderView(header);
        listView.addFooterView(footer);
        addressImg = (ImageButton)header.findViewById(R.id.chance_address);
        recName = (TextView)header.findViewById(R.id.recive_name);
        recPhone = (TextView)header.findViewById(R.id.recive_phone);
        recAddress = (TextView)header.findViewById(R.id.recive_address);
        wsName = (TextView)header.findViewById(R.id.ws_name);
        totalPrice = (TextView)footer.findViewById(R.id.order_total_price);
        orderCode = (TextView)footer.findViewById(R.id.order_code);
        orderTime = (TextView)footer.findViewById(R.id.order_create_time);
        back.setOnClickListener(this);
        delete.setOnClickListener(this);
        addressImg.setClickable(false);
        adpater = new OrderShopListAdapter(this,mList);
        listView.setAdapter(adpater);
        for(int i = 0;i < 10;i++){
            ShopInfo info = new ShopInfo();
            mList.add(info);
        }
        adpater.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.wholesaler_back:
                finish();
                break;
            case R.id.delete_order:
                //删除订单
                break;
        }
    }
}
