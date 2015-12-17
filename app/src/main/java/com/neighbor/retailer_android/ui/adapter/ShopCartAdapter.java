package com.neighbor.retailer_android.ui.adapter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.ShopCartInfo;

import java.util.List;

/**
 * Created by NS on 2015/3/9.
 */
public class ShopCartAdapter extends BaseExpandableListAdapter {

    /**
     * 所要适配的数据list
     */
    private List<ShopCartInfo> mList;
    /**
     * 上下文
     */
    private Context mContext;
    private LayoutInflater mInflater = null;
    private Handler mHandler;
    private String TAG = "nihao";

    public ShopCartAdapter(Context context, List<ShopCartInfo> mList,Handler mHandler){
        this.mContext = context;
        this.mList = mList;
        this.mHandler = mHandler;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mList.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mList.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //如果缓存convertView为空，则需要创建View
        if(convertView == null)
        {
            holder = new ViewHolder();
            //根据自定义的Item布局加载布局
            convertView = mInflater.inflate(R.layout.shop_cart_list_item, null);
            holder.saleName = (TextView)convertView.findViewById(R.id.textItem);
            holder.openImg = (ImageView)convertView.findViewById(R.id.open_img);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.saleName.setText(mList.get(groupPosition).getName() + "  " + mList.get(groupPosition).getCategory());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder1 holder = null;
        //如果缓存convertView为空，则需要创建View
        if(convertView == null)
        {
            holder = new ViewHolder1();
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
            holder = (ViewHolder1)convertView.getTag();
        }
        holder.name.setText(mList.get(groupPosition).getList().get(childPosition).getName());
        holder.price.setText(mList.get(groupPosition).getList().get(childPosition).getPrice() + "");
        holder.total.setText(mList.get(groupPosition).getList().get(childPosition).getSum() + "");
        holder.count.setText(mList.get(groupPosition).getList().get(childPosition).getCount() + "");
        holder.category.setText(mList.get(groupPosition).getList().get(childPosition).getSpec());
        return convertView;
    }


    public final class ViewHolder{
        /**
         * 菜品分类
         */
        public TextView saleName;
        public ImageView openImg;
    }

    public final class ViewHolder1{
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
