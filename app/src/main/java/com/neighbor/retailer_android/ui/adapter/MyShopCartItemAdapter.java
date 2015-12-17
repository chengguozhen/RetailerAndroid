package com.neighbor.retailer_android.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.ShopInfo;

import java.util.List;

/**
 * Created by xu on 2015/12/9.
 */
public class MyShopCartItemAdapter extends BaseAdapter {

    private String TAG = "nihao";
    private List<ShopInfo> data;
    private Context mContext;
    private LayoutInflater mInflater = null;

    public MyShopCartItemAdapter(Context context, List<ShopInfo> data){
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
            convertView = mInflater.inflate(R.layout.shopcart_list_list_item, null);
            holder.img = (ImageView)convertView.findViewById(R.id.shop_img);
            holder.name = (TextView)convertView.findViewById(R.id.shop_name);
            holder.price = (TextView)convertView.findViewById(R.id.shop_price);
            holder.total = (TextView)convertView.findViewById(R.id.shop_total_price);
            holder.count = (TextView)convertView.findViewById(R.id.shop_count);
            holder.del = (ImageButton)convertView.findViewById(R.id.shop_count_del);
            holder.add = (ImageButton)convertView.findViewById(R.id.shop_count_add);
            holder.category = (TextView)convertView.findViewById(R.id.shop_category);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.name.setText(data.get(position).getName());
        holder.price.setText(data.get(position).getPrice() + "");
        holder.total.setText(data.get(position).getSum() + "");
        holder.count.setText(data.get(position).getCount() + "");
        holder.category.setText(data.get(position).getSpec());
        return convertView;
    }

    public final class ViewHolder{
        /**
         * 搜索出的信息
         */
        public ImageView img;
        public TextView name;
        public TextView price;
        public TextView total;
        public TextView count;
        public ImageButton del;
        public ImageButton add;
        public TextView category;
    }

}
