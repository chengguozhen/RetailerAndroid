package com.neighbor.retailer_android.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Vicky on 2015/12/21.
 * Retailer_android
 * contact way: 317461087@qq.com
 */
public class MerPagerAdapter extends PagerAdapter {
    private Context context;
    private List<View> advList;

    public MerPagerAdapter(Context context, List<View> advList) {
        this.context =context;
        this.advList =advList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager)container).addView(advList.get(position),0);
        return advList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView(advList.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object ;
    }

    @Override
    public int getCount() {
        return advList.size();
    }
}
