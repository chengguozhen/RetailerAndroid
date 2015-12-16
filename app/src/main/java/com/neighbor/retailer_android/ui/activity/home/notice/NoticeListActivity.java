package com.neighbor.retailer_android.ui.activity.home.notice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.NoticeItemBean;
import com.neighbor.retailer_android.ui.adapter.NoticeListItemAdapter;
import com.neighbor.retailer_android.ui.view.KindPinnedHeaderListView;
import com.neighbor.retailer_android.ui.view.XListView;

import java.util.ArrayList;

public class NoticeListActivity extends AppCompatActivity {

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
        initData();
    }

    private void initView()
    {
        //title info
        noticeListview = (XListView)findViewById(R.id.notice_list);
        noticeListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

    private void initData()
    {

    }
}
