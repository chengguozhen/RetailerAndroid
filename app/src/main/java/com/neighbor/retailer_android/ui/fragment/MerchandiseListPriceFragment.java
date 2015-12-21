package com.neighbor.retailer_android.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.MerchandiseItemBean;
import com.neighbor.retailer_android.ui.activity.kind.MerchandiseDetailActivity;
import com.neighbor.retailer_android.ui.adapter.MerchandiseCountAdapter;
import com.neighbor.retailer_android.ui.adapter.MerchandisePriceAdapter;
import com.neighbor.retailer_android.ui.view.pulltorefresh.XListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vicky on 2015/12/17.
 * Retailer_android
 * contact way: 317461087@qq.com
 */
public class MerchandiseListPriceFragment extends Fragment implements XListView.IXListViewListener,AdapterView.OnItemClickListener{

    private View count;
    /**
     * 显示商品列表组件
     */
    private XListView priceListview;

    private List<MerchandiseItemBean> merchandiseList;
    private MerchandisePriceAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        count = inflater.inflate(R.layout.fragment_merchandise_price,container,false);
        initAdapterData();
        initView();
        return count;
    }

    /**
     * 初始化商品列表适配器数据
     */
    private void initAdapterData()
    {
        merchandiseList = new ArrayList<MerchandiseItemBean>();
        MerchandiseItemBean bean;
        for(int i=0; i<10; i++)
        {
            bean = new MerchandiseItemBean();
            bean.setUnitPrice(i + "元");
            bean.setMerchandiseName("奥尔良鸡块 啊喵");
            bean.setSpecifications("500g/袋");
            bean.setInitNumber(i);
            bean.setInventoryCounts(i + "10");
            List<String> image = new ArrayList<String>();
            image.add("http://pic.ffpic.com/files/2012/1221/1206pic1205we188.jpg");
            bean.setMerchandiseUrl(image);
            bean.setWholesaler("批发商"+i);
            merchandiseList.add(bean);
        }
        adapter = new MerchandisePriceAdapter(getActivity(),merchandiseList);
    }

    /**
     * 初始化组件
     */
    private void initView()
    {
        priceListview = (XListView)count.findViewById(R.id.price_list);
        priceListview.setAdapter(adapter);
        priceListview.setPullLoadEnable(true);
        priceListview.setXListViewListener(this);
        priceListview.setOnItemClickListener(this);
    }

    /**
     * 刷新数据函数
     */
    @Override
    public void onRefresh() {

        onLoad();
    }

    /**
     * 加载更多数据函数
     */
    @Override
    public void onLoadMore() {

        onLoad();
    }

    private void onLoad() {
        priceListview.stopRefresh();
        priceListview.stopLoadMore();
        priceListview.setRefreshTime("none");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), MerchandiseDetailActivity.class);
        startActivity(intent);
    }
}
