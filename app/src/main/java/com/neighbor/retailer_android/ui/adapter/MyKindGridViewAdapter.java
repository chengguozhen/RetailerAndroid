package com.neighbor.retailer_android.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.neighbor.retailer_android.R;

import java.util.List;

/**
 * Created by xu on 2015/12/9.
 */
public class MyKindGridViewAdapter extends BaseAdapter {

    private String TAG = "nihao";
    private List<String> data;
    private Context mContext;
    private LayoutInflater mInflater = null;

    public MyKindGridViewAdapter(Context context, List<String> data){
        this.data = data;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //如果缓存convertView为空，则需要创建View
        if(convertView == null)
        {
            holder = new ViewHolder();
            //根据自定义的Item布局加载布局
            convertView = mInflater.inflate(R.layout.item_grid, null);
            holder.tx = (TextView)convertView.findViewById(R.id.item_tx);
            holder.img = (ImageView)convertView.findViewById(R.id.img);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.tx.setText(data.get(position).toString());
        return convertView;
    }

    public final class ViewHolder{
        /**
         * 搜索出的信息
         */
        public TextView tx;
        public ImageView img;
    }

}
