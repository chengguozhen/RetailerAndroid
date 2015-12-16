package com.neighbor.retailer_android.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.NoticeItemBean;

import java.util.ArrayList;

/**
 * Created by Vicky on 2015/12/16.
 * Retailer_android
 * contact way: 317461087@qq.com
 */
public class NoticeListItemAdapter extends BaseAdapter {

    /*
    options =new DisplayImageOptions.Builder()
            .showStubImage(R.mipmap.loading_image) // 设置图片下载期间显示的图片
    .showImageForEmptyUri(R.mipmap.loading_image) // 设置图片Uri为空或是错误的时候显示的图片
    .showImageOnFail(R.mipmap.loading_image) // 设置图片加载或解码过程中发生错误显示的图片
    .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
    .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
    .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
            .build(); // 创建配置过得DisplayImageOption对象
    */
    public NoticeListItemAdapter(final Context context,Handler handler,ArrayList<NoticeItemBean> list)
    {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);

    }

    private ArrayList<NoticeItemBean> list;
    private ViewHolder viewHolder;
    private LayoutInflater layoutInflater;
    private Context context;

    static class ViewHolder
    {

    }

    @Override
    public int getCount() {
        if(list == null)
        {
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        if(position >= getCount())
        {
            return null;
        }
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.activity_notice_listitem, null);
            viewHolder = new ViewHolder();

            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        return v;
    }
}
