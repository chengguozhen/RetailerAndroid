package com.neighbor.retailer_android.ui.fragment.order;

/**
 * Created by john
 * Date 2015/6/3
 * Time 13:49
 * 该接作用 ： 在viewPager滑动过程中，滑到哪一页，哪一页调用该方法
 *            在onPageSelected 中调用改方法
 *            在viewPager中的想要刷新当前页面的fragment重写该方法
 */
public interface FragmentState {
    void fragmentVisible();
}
