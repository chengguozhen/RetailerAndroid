package com.neighbor.retailer_android.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.MerchandiseItemBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

/**
 * Created by Vicky on 2015/12/17.
 * Retailer_android
 * contact way: 317461087@qq.com
 */
public class MerchandiseDiscountCountAdapter extends ParentBaseAdapter implements View.OnClickListener{
    private List<MerchandiseItemBean> list;
    private ViewHolder viewHolder;
    private LayoutInflater layoutInflater;
    private Context context;
    private int purchasePosition;

    public MerchandiseDiscountCountAdapter(Context context, List<MerchandiseItemBean> list)
    {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    static class ViewHolder
    {
        TextView merchandiseName,specification,inventory,unitPrice;
        TextView purchaseNum;
        ImageView add,substract,merchandiseImage;
        Button pruchase;
        TextView wholesaler;
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
            v = layoutInflater.inflate(R.layout.fragment_merchandise_discount_countitem, null);
            viewHolder = new ViewHolder();
            viewHolder.merchandiseName = (TextView)v.findViewById(R.id.merchandise_name);
            viewHolder.merchandiseImage = (ImageView)v.findViewById(R.id.merchandise_url);
            viewHolder.unitPrice = (TextView)v.findViewById(R.id.merchandise_unit_price);
            viewHolder.specification = (TextView)v.findViewById(R.id.merchandise_specification);
            viewHolder.inventory = (TextView)v.findViewById(R.id.merchandise_inventory);
            viewHolder.wholesaler = (TextView)v.findViewById(R.id.wholesaler_name);
            viewHolder.purchaseNum = (TextView)v.findViewById(R.id.purchase_number);
            viewHolder.pruchase = (Button)v.findViewById(R.id.purchase_btn);
            viewHolder.add = (ImageView)v.findViewById(R.id.add_merchandise);
            viewHolder.substract = (ImageView)v.findViewById(R.id.substract_merchandise);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }
        purchasePosition = position;

        viewHolder.substract.setOnClickListener(this);
        viewHolder.add.setOnClickListener(this);
        viewHolder.pruchase.setOnClickListener(this);

        viewHolder.merchandiseName.setText(list.get(position).getMerchandiseName());
        String url = list.get(position).getMerchandiseUrl().get(0);
        if(null != url && !url.equals(""))
        {
            imageLoader.displayImage(url, viewHolder.merchandiseImage, options, animateFirstListener);
        }
        else {
            viewHolder.merchandiseImage.setImageResource(R.mipmap.merchandise_default);
        }
        viewHolder.unitPrice.setText(list.get(position).getUnitPrice());
        viewHolder.specification.setText(list.get(position).getSpecifications());
        viewHolder.inventory.setText(list.get(position).getInventoryCounts());
        viewHolder.purchaseNum.setText(String.valueOf(list.get(position).getPurchaseNumber()));
        viewHolder.wholesaler.setText(list.get(position).getWholesaler());

        return v;
    }

    /**
     * 点击修改购买数量
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        int number = Integer.valueOf(viewHolder.purchaseNum.getText().toString());
        switch (v.getId())
        {
            case R.id.add_merchandise:
                number += 1;
                break;
            case R.id.substract_merchandise:
                number -= 1;
                break;
            case R.id.purchase_btn:
                //加入购物车操作

                break;
            default:
                break;
        }
        //通知修改数据  传递number和purchasePosition数据

    }
}
