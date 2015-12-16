package com.neighbor.retailer_android.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.neighbor.retailer_android.R;

import java.util.List;

/**
 * Created by NS on 2015/3/9.
 */
public class KindOrderLeftAdapter extends BaseAdapter {

    /**
     * 所要适配的数据list
     */
    private List<String> mList;
    /**
     * 上下文
     */
    private Context mContext;
    private LayoutInflater mInflater = null;
    /**
     * 当前选中项的index
     */
    private int index = 0;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public KindOrderLeftAdapter(Context context, List<String> list){
        this.mContext = context;
        this.mList = list;
        this.mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        //如果缓存convertView为空，则需要创建View
        if(convertView == null)
        {
            holder = new ViewHolder();
            //根据自定义的Item布局加载布局
            convertView = mInflater.inflate(R.layout.kind_order_header_item, null);
            holder.msResName = (TextView)convertView.findViewById(R.id.textItem);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.msResName.setText(mList.get(position));
        if(index == position){
            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            holder.msResName.setTextColor(Color.rgb(255, 97, 0));
        }else{
            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.gray_background_color));
            holder.msResName.setTextColor(Color.rgb(76, 76, 76));
        }
        return convertView;
    }

    public final class ViewHolder{
        /**
         * 菜品分类
         */
        public TextView msResName;
    }
}
