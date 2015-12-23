package com.neighbor.retailer_android.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.WholeSale;

import java.util.List;

/**
 * Created by NS on 2015/3/9.
 */
public class OrderTab01Adapter extends BaseAdapter {

    /**
     * 所要适配的数据list
     */
    private List<WholeSale> mList;
    /**
     * 上下文
     */
    private Context mContext;
    private LayoutInflater mInflater = null;

    public OrderTab01Adapter(Context context, List<WholeSale> list){
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
            convertView = mInflater.inflate(R.layout.order_tab_01_item, null);
            holder.code = (TextView)convertView.findViewById(R.id.order_tab_01_code);
            holder.wsImg = (ImageView)convertView.findViewById(R.id.order_tab_01_ws_img);
            holder.wsName = (TextView)convertView.findViewById(R.id.order_tab_01_ws);
            holder.total = (TextView)convertView.findViewById(R.id.order_tab_01_total);
            holder.quit = (Button)convertView.findViewById(R.id.quit_order);
            holder.pay = (Button)convertView.findViewById(R.id.go_to_pay);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        return convertView;
    }

    public final class ViewHolder{
        public TextView code;
        public ImageView wsImg;
        public TextView wsName;
        public TextView total;
        public Button quit;
        public Button pay;
    }
}
