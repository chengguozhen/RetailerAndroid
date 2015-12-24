package com.neighbor.retailer_android.ui.activity.my;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.ui.fragment.order.OrderTab01;
import com.neighbor.retailer_android.ui.fragment.order.OrderTab02;

import java.util.ArrayList;
import java.util.List;

public class MyOrderListActivity extends FragmentActivity implements View.OnClickListener{

    private ImageButton back;
    private TextView title;
    private LinearLayout noPayOrederBtn;
    private LinearLayout finishOrderBtn;
    /**
     * 左右滑动pager
     */
    private ViewPager mViewPager;
    /**
     * pager的适配器
     */
    private FragmentStatePagerAdapter mAdapter;
    /**
     * fragment数组
     */
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private TextView newTx,recTx;
    OrderTab01 tab01 = new OrderTab01();
    OrderTab02 tab02 = new OrderTab02();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_list);

        back = (ImageButton)findViewById(R.id.wholesaler_back);
        title = (TextView)findViewById(R.id.wholesaler_title);
        title.setText("我的订单");
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        newTx = (TextView)findViewById(R.id.new_order_tx);
        recTx = (TextView)findViewById(R.id.received_order_tx);
        noPayOrederBtn = (LinearLayout)findViewById(R.id.nopay_money_order);
        finishOrderBtn = (LinearLayout)findViewById(R.id.finish_order);

        mFragments.add(tab01);
        mFragments.add(tab02);
        back.setOnClickListener(this);
        noPayOrederBtn.setOnClickListener(this);
        finishOrderBtn.setOnClickListener(this);
        mAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager())
        {
            @Override
            public int getCount()
            {
                return mFragments.size();
            }
            @Override
            public Fragment getItem(int arg0)
            {
                return mFragments.get(arg0);
            }
        };

        mViewPager.setAdapter(mAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int currentIndex;

            @Override
            public void onPageSelected(int position) {
                resetTabBtn();
                switch (position) {
                    case 0:
                        newTx.setTextColor(MyOrderListActivity.this.getResources().getColor(R.color.white));
                        tab01.fragmentVisible();
                        break;
                    case 1:
                        recTx.setTextColor(MyOrderListActivity.this.getResources().getColor(R.color.white));
                        tab02.fragmentVisible();
                        break;
                }

                currentIndex = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    protected void resetTabBtn()
    {
        newTx.setTextColor(MyOrderListActivity.this.getResources().getColor(R.color.main_text_color));
        recTx.setTextColor(MyOrderListActivity.this.getResources().getColor(R.color.main_text_color));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.wholesaler_back:
                finish();
                break;
            case R.id.nopay_money_order:
                //未付款订单列表
                mViewPager.setCurrentItem(0);
                break;
            case R.id.finish_order:
                //已完成订单列表
                mViewPager.setCurrentItem(1);
                break;
        }
    }
}
