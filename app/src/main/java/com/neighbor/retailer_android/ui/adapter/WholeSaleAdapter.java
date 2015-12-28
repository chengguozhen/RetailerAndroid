package com.neighbor.retailer_android.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.WholeSale;
import com.neighbor.retailer_android.util.AnimateFirstDisplayListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

/**
 * Created by NS on 2015/3/9.
 */
public class WholeSaleAdapter extends BaseAdapter {

    /**
     * 所要适配的数据list
     */
    private List<WholeSale> mList;
    /**
     * 上下文
     */
    private Context mContext;
    private LayoutInflater mInflater = null;
    DisplayImageOptions options;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    public WholeSaleAdapter(Context context, List<WholeSale> list){
        this.mContext = context;
        this.mList = list;
        this.mInflater = LayoutInflater.from(context);
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.ic_launcher) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象

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
            convertView = mInflater.inflate(R.layout.wholesale_list_item, null);
            holder.name = (TextView)convertView.findViewById(R.id.sale_name);
            holder.img = (ImageView)convertView.findViewById(R.id.sale_img);
            holder.tag = (TextView)convertView.findViewById(R.id.sale_tag);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder)convertView.getTag();
        }
//        holder.name.setText(mList.get(position).getMidName());
//        holder.tag.setText(mList.get(position).getMidSpec());
//        imageLoader.displayImage(mList.get(position).getPicUrl(), holder.img, options,
//                animateFirstListener);
        return convertView;
    }

    public final class ViewHolder{
        /**
         * 批发商名称
         */
        public TextView name;
        public ImageView img;
        public TextView tag;
    }
}
