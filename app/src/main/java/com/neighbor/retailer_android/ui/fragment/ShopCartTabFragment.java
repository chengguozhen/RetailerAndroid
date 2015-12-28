package com.neighbor.retailer_android.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.ShopCartInfo;
import com.neighbor.retailer_android.bean.ShopInfo;
import com.neighbor.retailer_android.ui.activity.shopcart.SubmitOrderActivity;
import com.neighbor.retailer_android.ui.adapter.OrderShopListAdapter;
import com.neighbor.retailer_android.ui.adapter.ShopCartAdapter;
import com.neighbor.retailer_android.ui.view.pulltorefresh.XListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SuppressLint("NewApi")
public class ShopCartTabFragment extends Fragment {

    private View rootView;
    /**
     * 店铺列表listview
     */
    private ExpandableListView shopcartListView;
    /**
     * 店铺列表适配器
     */
    private ShopCartAdapter shopCartAdapter;
    /**
     * 所需适配的数据列表
     */
    private List<ShopCartInfo> list = new ArrayList<ShopCartInfo>();
    /**
     * 分页值
     */
    private int mRefreshIndex = 0;
    /**
     * 未登录提示布局
     */
    private LinearLayout noNetWork;
    /**
     * 网络出错，重新加载按钮
     */
    private Button doLoddingBtn;
    /* 确认支付按钮 */
    private Button submit;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.main_tab_03, container, false);
        shopcartListView = (ExpandableListView) rootView.findViewById(R.id.shop_cart_listview);
        noNetWork = (LinearLayout) rootView.findViewById(R.id.shop_cart_not_network);
        doLoddingBtn = (Button) rootView.findViewById(R.id.ms_tab_do_lodding_btn);
        shopcartListView.setGroupIndicator(null);
        noNetWork.setVisibility(View.GONE);
        submit = (Button) rootView.findViewById(R.id.submit_pay);
        submit.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击支付逻辑
                Intent intent = new Intent(getActivity(), SubmitOrderActivity.class);
                startActivity(intent);
            }
        });
        doLoddingBtn.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新加载逻辑
                //getMsListData(1);
            }
        });
        shopCartAdapter = new ShopCartAdapter(getActivity(), list, mHandler);
        shopcartListView.setAdapter(shopCartAdapter);
        if (list != null || !list.isEmpty()) {
            list.clear();
        }
        for (int i = 0; i < 5; i++) {
            List<ShopInfo> data = new ArrayList<ShopInfo>();
            for (int j = 0; j < 3; j++) {
                ShopInfo shopInfo = new ShopInfo();
                shopInfo.setName("商品名称" + i + j);
                shopInfo.setCount(i + 1);
                shopInfo.setPrice(10.0 + j);
                data.add(shopInfo);
            }
            ShopCartInfo shopCartInfo = new ShopCartInfo();
            shopCartInfo.setName("批发商" + i);
            shopCartInfo.setCategory("二级类目" + i);
            shopCartInfo.setList(data);
            list.add(shopCartInfo);
        }
        shopCartAdapter.notifyDataSetChanged();
        return rootView;
    }

}
