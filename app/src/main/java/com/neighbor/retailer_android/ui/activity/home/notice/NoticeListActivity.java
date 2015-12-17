package com.neighbor.retailer_android.ui.activity.home.notice;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.NoticeItemBean;
import com.neighbor.retailer_android.ui.adapter.NoticeListItemAdapter;
import com.neighbor.retailer_android.ui.view.pulltorefresh.XListView;

import java.util.ArrayList;

public class NoticeListActivity extends Activity implements XListView.IXListViewListener{

    /**
     * 公告列表控件
     */
    private XListView noticeListview;

    /**
     * 公告列表数据list
     */
    private ArrayList<NoticeItemBean> noticeList;
    private NoticeListItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_list);
        initView();
        initListview();
        initAdapterData();
    }

    /**
     * 初始化其他控件
     */
    private void initView()
    {

    }
    /**
     * 初始化ListView控件
     */
    private void initListview()
    {
        //title info
        noticeListview = (XListView)findViewById(R.id.notice_list);
        noticeListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        noticeListview.setPullLoadEnable(true);
        noticeListview.setXListViewListener(this);
    }

    /**
     * 刷新数据函数
     */
    @Override
    public void onRefresh() {

    }

    /**
     * 加载更多数据函数
     */
    @Override
    public void onLoadMore() {

    }

    /**
     * 初始化适配器数据
     */
    private void initAdapterData()
    {
        for(int i=0;i <10; i++)
        {
            NoticeItemBean bean = new NoticeItemBean();
            bean.setNoticeTitle("title "+i);
            bean.setNoticeTime("9:05:"+i);
            noticeList.add(bean);
        }
        adapter = new NoticeListItemAdapter(this,noticeList);
        noticeListview.setAdapter(adapter);

    }
}
