package com.neighbor.retailer_android.ui.fragment.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.WholeSale;
import com.neighbor.retailer_android.ui.activity.shopcart.OrderDetailActivity;
import com.neighbor.retailer_android.ui.adapter.OrderTab02Adapter;
import com.neighbor.retailer_android.ui.view.pulltorefresh.XListView;
import com.nostra13.universalimageloader.utils.L;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrderTab02 extends Fragment implements XListView.IXListViewListener,FragmentState
{
    /**
     *父activity布局
     */
	private View rootView;
    /**
     * 列表listview
     */
	private XListView listview;
    /**
     * 数据源适配器
     */
	private OrderTab02Adapter adapter = null;
    /**
     * 数据源
     */
	private List<WholeSale> mList = new ArrayList<WholeSale>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		rootView = inflater.inflate(R.layout.order_tab_01, container, false);
		listview = (XListView)rootView.findViewById(R.id.listview);
        listview.setPullRefreshEnable(true);
        listview.setPullLoadEnable(false);
        listview.setAutoLoadEnable(true);
        listview.setXListViewListener(this);
        listview.setRefreshTime(getTime());
        getMsListData(1);
		adapter = new OrderTab02Adapter(getActivity(),mList);
		listview.setAdapter(adapter);
        for(int i = 0;i < 10;i++){
            WholeSale info = new WholeSale();
            info.setMidName("幸福便利店" + i);
            mList.add(info);
        }
        adapter.notifyDataSetChanged();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //根据订单id跳订单详情页面
                Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                startActivity(intent);
            }
        });
		return  rootView;
	}

    /**
     * 下拉刷新完毕
     */
    private void onLoadComplete() {
        L.i("stop");
        listview.stopRefresh();
        listview.stopLoadMore();
        listview.setRefreshTime(getTime());
    }

    @Override
    public void onRefresh() {
        //下拉请求数据方法
        getMsListData(1);
    }

    @Override
    public void onLoadMore() {
        //上拉加载
    }

    /**
     * @return 时间日期
     */
    private String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }

    /**
     *
     * 获取饭订单列表
     */
    private void getMsListData(int mode){
//        Common.getOrderList(getActivity(), mQueue, orderListListener, errorListener, resId, "0");
//        if(mode==1)
//        showDialog("正在加载订单列表...");
    }

    @Override
    public void fragmentVisible() {
        Log.i("OrderTab01", "----------------");
        getMsListData(1);
    }
}
