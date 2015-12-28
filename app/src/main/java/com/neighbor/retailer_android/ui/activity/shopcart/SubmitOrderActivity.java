package com.neighbor.retailer_android.ui.activity.shopcart;

import android.app.Activity;
import android.content.Intent;
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
import com.neighbor.retailer_android.ui.activity.my.AddressListActivity;
import com.neighbor.retailer_android.ui.adapter.OrderShopListAdapter;

import java.util.ArrayList;
import java.util.List;

public class SubmitOrderActivity extends Activity implements View.OnClickListener{

    /* 返回键 */
    private ImageButton back;
    /* 标题 */
    private TextView title;
    /* listview */
    private ListView listView;
    /* 确认按钮 */
    private Button submit;
    /* 画布 */
    private LayoutInflater mInflater;
    /* header */
    private View header;
    /* footer */
    private View footer;
    /* 切换地址按钮 */
    private ImageButton addressImg;
    /* 收货人姓名 */
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
    /* 总价确认 */
    private TextView totalSubmit;
    /* 在线支付 */
    private CheckBox online;
    /* 货到付款 */
    private CheckBox outline;
    /* 适配器 */
    private OrderShopListAdapter adpater;
    /* 数据源 */
    private List<ShopInfo> mList = new ArrayList<ShopInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_order);

        back = (ImageButton)findViewById(R.id.wholesaler_back);
        title = (TextView)findViewById(R.id.wholesaler_title);
        title.setText("确认订单");
        listView = (ListView)findViewById(R.id.order_submit_lv);
        submit = (Button)findViewById(R.id.submit);
        totalSubmit = (TextView)findViewById(R.id.total_submit);
        mInflater = LayoutInflater.from(SubmitOrderActivity.this);
        header = mInflater.inflate(R.layout.submit_order_lv_header, null);
        footer = mInflater.inflate(R.layout.submit_order_lv_footer, null);
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
        online = (CheckBox)footer.findViewById(R.id.online_pay);
        outline = (CheckBox)footer.findViewById(R.id.outline_pay);
        back.setOnClickListener(this);
        submit.setOnClickListener(this);
        addressImg.setOnClickListener(this);
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
            case R.id.submit:
                //跳转至结算页面
//                Intent intent = new Intent(this,OrderDetailActivity.class);
//                startActivity(intent);
                break;
            case R.id.chance_address:
                //更改地址
                Intent intent = new Intent(this, AddressListActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
                String address = data.getStringExtra("ADDRESS");
                if(!address.equals("")){
                    recAddress.setText(address);
                }
                break;
            default:
                break;
        }
    }
}
