package com.neighbor.retailer_android.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.ShopCartInfo;
import com.neighbor.retailer_android.bean.ShopInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NS on 2015/3/9.
 */
public class ShopCartAdapter extends BaseAdapter {

    /**
     * 所要适配的数据list
     */
    private List<ShopCartInfo> mList;
    /**
     * 上下文
     */
    private Context mContext;
    private MyShopCartItemAdapter myAdapter;
    private List<ShopInfo> list = new ArrayList<ShopInfo>();
    private LayoutInflater mInflater = null;
    private Handler mHandler;
    private String TAG = "nihao";

    public ShopCartAdapter(Context context, List<ShopCartInfo> mList,Handler mHandler){
        this.mContext = context;
        this.mList = mList;
        this.mHandler = mHandler;
        this.mInflater = LayoutInflater.from(context);
        myAdapter = new MyShopCartItemAdapter(mContext,list);
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
            convertView = mInflater.inflate(R.layout.shop_cart_list_item, null);
            holder.saleName = (TextView)convertView.findViewById(R.id.textItem);
            holder.shopInfoList = (ListView)convertView.findViewById(R.id.shop_cart_info_ls);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.saleName.setText(mList.get(position).getName() + "  " + mList.get(position).getCategory());
        holder.shopInfoList.setAdapter(myAdapter);
        if(list != null || !list.isEmpty()){
            list.clear();
        }
        for(int i = 0;i < mList.get(position).getList().size();i++){
            list.add((ShopInfo)mList.get(position).getList().get(i));
        }
        myAdapter.notifyDataSetChanged();
        return convertView;
    }

    public final class ViewHolder{
        /**
         * 菜品分类
         */
        public TextView saleName;
        public ListView shopInfoList;
    }
}
