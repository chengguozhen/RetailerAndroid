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
public class NoticeListItemAdapter extends ParentBaseAdapter {
    private ArrayList<NoticeItemBean> list;
    private ViewHolder viewHolder;
    private LayoutInflater layoutInflater;
    private Context context;

    public NoticeListItemAdapter(final Context context,ArrayList<NoticeItemBean> list)
    {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    static class ViewHolder
    {
        TextView noticeTitle;
        TextView noticeTime;
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
            viewHolder.noticeTitle = (TextView)v.findViewById(R.id.notice_title);
            viewHolder.noticeTime = (TextView)v.findViewById(R.id.notice_time);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.noticeTitle.setText(list.get(position).getNoticeTitle());
        viewHolder.noticeTime.setText(list.get(position).getNoticeTime());

        return v;
    }
}
