package com.neighbor.retailer_android.ui.fragment.newmerchandise;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.MerchandiseItemBean;
import com.neighbor.retailer_android.ui.adapter.MerchandiseDiscountCountAdapter;
import com.neighbor.retailer_android.ui.view.pulltorefresh.XListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vicky on 2015/12/18.
 * Retailer_android
 * contact way: 317461087@qq.com
 */
public class MerchandiseNewCountFragment extends Fragment implements XListView.IXListViewListener{

    private View count;
    /**
     * 显示商品列表组件
     */
    private XListView countListview;

    private List<MerchandiseItemBean> merchandiseList;
    private MerchandiseDiscountCountAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        count = inflater.inflate(R.layout.fragment_merchandise_new_count,container,false);
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
            bean.setMerchandiseName("这是新品区商品 销量排序");
            bean.setSpecifications("新品新品哈哈哈哈");
            bean.setInitNumber(i);
            bean.setInventoryCounts(i + "0");
            List<String> image = new ArrayList<String>();
            image.add("http://images.99pet.com/InfoImages/wm600_450/1d770941f8d44c6e85ba4c0eb736ef69.jpg");
            bean.setMerchandiseUrl(image);
            bean.setWholesaler("批发商"+i);
            merchandiseList.add(bean);
        }
        if(adapter == null)
        {
            adapter = new MerchandiseDiscountCountAdapter(getActivity(),merchandiseList);
        }
        else {
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 初始化组件
     */
    private void initView()
    {
        countListview = (XListView)count.findViewById(R.id.new_count_list);
        countListview.setAdapter(adapter);
        countListview.setPullLoadEnable(true);
        countListview.setXListViewListener(this);
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
        countListview.stopRefresh();
        countListview.stopLoadMore();
        countListview.setRefreshTime("none");
    }
}
