package com.neighbor.retailer_android.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.MerchandiseItemBean;
import com.neighbor.retailer_android.ui.adapter.MerchandiseCountAdapter;
import com.neighbor.retailer_android.ui.view.pulltorefresh.XListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vicky on 2015/12/17.
 * Retailer_android
 * contact way: 317461087@qq.com
 */
public class MerchandiseListCountFragment extends Fragment {

    private View count;
    /**
     * 显示商品列表组件
     */
    private XListView countListview;

    private List<MerchandiseItemBean> merchandiseList;
    private MerchandiseCountAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        count = inflater.inflate(R.layout.fragment_merchandise_count,container,false);
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
            bean.setMerchandiseName("乐事薯片 美国经典");
            bean.setSpecifications("300g/袋");
            bean.setInitNumber(i);
            bean.setInventoryCounts(i + "0");
            List<String> image = new ArrayList<String>();
            image.add("http://pic.ffpic.com/files/2012/1221/1206pic1205we188.jpg");
            bean.setMerchandiseUrl(image);
            bean.setWholesaler("批发商"+i);
            merchandiseList.add(bean);
        }
        adapter = new MerchandiseCountAdapter(getActivity(),merchandiseList);
    }

    /**
     * 初始化组件
     */
    private void initView()
    {
        countListview = (XListView)count.findViewById(R.id.count_list);
        countListview.setAdapter(adapter);
    }
}
