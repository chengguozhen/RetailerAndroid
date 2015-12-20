package com.neighbor.retailer_android.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.util.AnimateFirstDisplayListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

/**
 * Created by VickyYang on 12/20/15.
 */
public class AdvPagerAdapter extends PagerAdapter {

    private Context context;
    //图片的url地址
    private List<String> advList;

    DisplayImageOptions options;
    public ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    protected ImageLoader imageLoader= ImageLoader.getInstance();

    public AdvPagerAdapter(Context context, List<String> advList) {
        this.context =context;
        this.advList =advList;
        options =new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.ic_launcher) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String url = advList.get(position);
        if(null!=url && !url.equals(""))
        {
            ((ViewPager)container).addView(initImageview(url),0);
            return initImageview(url);
        }else {
            return null;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        String url = advList.get(position);
        if(null!=url && !url.equals(""))
        {
            ((ViewPager)container).removeView(initImageview(url));
        }else {
        }
        //((ViewPager)container).removeView(advList.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object ;
    }

    @Override
    public int getCount() {
        return advList.size();
    }

    /**
     * 加载网络图片
     *
     * @param url
     * @return
     */
    private View initImageview(String url)
    {
        ImageView view = new ImageView(context);
        view.setScaleType(ImageView.ScaleType.CENTER);
        imageLoader.displayImage(url, view, options, animateFirstListener);
        view.setTag(url);
        return view;
    }
}
