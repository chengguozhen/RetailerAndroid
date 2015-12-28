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

    /* 返回键 */
    private ImageButton back;
    /* 标题 */
    private TextView title;
    /* 订单中商品listview */
    private ListView listView;
    /* 删除订单 */
    private Button delete;
    /* header footer 布局画布 */
    private LayoutInflater mInflater;
    /* listview 头 */
    private View header;
    /* listview 尾 */
    private View footer;
    /* 切换地址按钮 */
    private ImageButton addressImg;
    /* 收货人 */
    private TextView recName;
    /* 收货人联系方式 */
    private TextView recPhone;
    /* 收货地址 */
    private TextView recAddress;
    /* 批发商名称 */
    private TextView wsName;
    /* 总价 */
    private TextView totalPrice;
    /* 订单编码 */
    private TextView orderCode;
    /* 下单时间 */
    private TextView orderTime;
    /* 分为两种（一是已完成订单的付款时间，二是订单状态） */
    private TextView orderStateTag;
    /* 上面Tag值（一为时间，二为状态） */
    private TextView orderStateValue;
    /* 适配器 */
    private OrderShopListAdapter adpater;
    /* 数据源 */
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
        orderStateTag = (TextView)footer.findViewById(R.id.order_state_tag);
        orderStateValue = (TextView)footer.findViewById(R.id.order_state_tag_value);
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
