package com.neighbor.retailer_android.ui.activity.home.notice;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.support.v7.widget.Toolbar;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.NoticeItemBean;
import com.neighbor.retailer_android.common.Constants;
import com.neighbor.retailer_android.ui.adapter.NoticeListItemAdapter;
import com.neighbor.retailer_android.ui.view.MyToolBar.MyToolbarHeader;
import com.neighbor.retailer_android.ui.view.MyToolBar.MyToolbarListener;
import com.neighbor.retailer_android.ui.view.pulltorefresh.XListView;

import java.util.ArrayList;

public class NoticeListActivity extends ActionBarActivity implements XListView.IXListViewListener{

    /**
     * 公告列表控件
     */
    private XListView noticeListview;

    /**
     * 公告列表数据list
     */
    private ArrayList<NoticeItemBean> noticeList;
    private NoticeListItemAdapter adapter;

    /**
     * toolbar
     */
    private View view;

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
        /**
         * toolbar title
         */
        view = findViewById(R.id.header);
        if(view != null)
        {
            Toolbar toolbar = (Toolbar)view;
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            MyToolbarHeader toolbarHeader = new MyToolbarHeader(this,toolbar);
            toolbarHeader.setHeaderTitle(getString(R.string.noticelist_title));
            MyToolbarListener listener = new MyToolbarListener() {
                @Override
                public void addNavigation() {
                    finish();
                }
            };
            toolbarHeader.setNavigation(R.mipmap.back,listener);
        }

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
                Intent intent = new Intent(NoticeListActivity.this,NoticeDetailActivity.class);
                intent.putExtra(Constants.NOTICEID,noticeList.get(position).getId());
                startActivity(intent);
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
        noticeListview.stopRefresh();
        noticeListview.stopLoadMore();
        noticeListview.setRefreshTime("none");
    }

    /**
     * 初始化适配器数据
     */
    private void initAdapterData()
    {
        noticeList = new ArrayList<NoticeItemBean>();
        for(int i=0;i <10; i++)
        {
            NoticeItemBean bean = new NoticeItemBean();
            bean.setId(i+"");
            bean.setNoticeTitle("title "+i);
            bean.setNoticeTime("9:05:"+i);
            noticeList.add(bean);
        }
        adapter = new NoticeListItemAdapter(this,noticeList);
        noticeListview.setAdapter(adapter);

    }
}
