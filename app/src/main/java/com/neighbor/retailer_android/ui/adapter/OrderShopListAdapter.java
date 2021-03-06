package com.neighbor.retailer_android.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.ShopInfo;
import com.neighbor.retailer_android.bean.WholeSale;

import java.util.List;

/**
 * Created by NS on 2015/3/9.
 */
public class OrderShopListAdapter extends BaseAdapter {

    /**
     * 所要适配的数据list
     */
    private List<ShopInfo> mList;
    /**
     * 上下文
     */
    private Context mContext;
    private LayoutInflater mInflater = null;

    public OrderShopListAdapter(Context context, List<ShopInfo> list){
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
            convertView = mInflater.inflate(R.layout.order_shop_list_item, null);
            holder.name = (TextView)convertView.findViewById(R.id.order_ls_item_sale_name);
            holder.img = (ImageView)convertView.findViewById(R.id.order_ls_item_sale_img);
            holder.price = (TextView)convertView.findViewById(R.id.order_ls_item_sale_price);
            holder.spec = (TextView)convertView.findViewById(R.id.order_ls_item_sale_spec);
            holder.num = (TextView)convertView.findViewById(R.id.order_ls_item_count);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        return convertView;
    }

    public final class ViewHolder{
        /**
         * 批发商名称
         */
        public TextView name;
        public ImageView img;
        public TextView price;
        public TextView spec;
        public TextView num;
    }
}
