package com.neighbor.retailer_android.ui.activity.home.notice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.common.Constants;
import com.neighbor.retailer_android.ui.view.MyToolBar.MyToolbarHeader;
import com.neighbor.retailer_android.ui.view.MyToolBar.MyToolbarListener;

public class NoticeDetailActivity extends AppCompatActivity {

    /**
     *  公告标题 时间 详情
     */
    private TextView noticeTitle;
    private TextView noticeTime;
    private TextView noticeContent;

    /**
     * toolbar
     */
    private View view;

    /**
     * 公告Id
     */
    private String noticeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
        getIntentData();
        initView();

    }

    /**
     * 初始化控件
     */
    private void initView()
    {
        view = findViewById(R.id.noticedetail_header);
        if(view != null)
        {
            Toolbar toolbar = (Toolbar)view;
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            MyToolbarHeader toolbarHeader = new MyToolbarHeader(this,toolbar);
            toolbarHeader.setHeaderTitle(getString(R.string.notice_title));
            MyToolbarListener listener = new MyToolbarListener() {
                @Override
                public void addNavigation() {
                    finish();
                }
            };
            toolbarHeader.setNavigation(R.mipmap.back,listener);
        }
        noticeTitle = (TextView)findViewById(R.id.notice_content_title);
        noticeTime = (TextView)findViewById(R.id.notice_content_time);
        noticeContent = (TextView)findViewById(R.id.notice_content);
    }

    private void getIntentData()
    {
        noticeId = getIntent().getStringExtra(Constants.NOTICEID);
        //根据Id拉数据

    }
}
