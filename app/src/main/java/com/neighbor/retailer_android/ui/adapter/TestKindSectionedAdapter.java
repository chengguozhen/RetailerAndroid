package com.neighbor.retailer_android.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.Dish;
import com.neighbor.retailer_android.ui.activity.kind.MerchandiseListActivity;

import java.util.ArrayList;
import java.util.List;

public class TestKindSectionedAdapter extends KindSectionedBaseAdapter {

    private String TAG = "nihao";
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 与左边标签对应的项
     */
	private List<String> leftStr;
    /**
     * 右面listview中的项
     */
	private List<List<Dish>> rightStr;
    private MyKindGridViewAdapter adapter;
    private List<String> list = new ArrayList<String>();
    /**
     * activity处理点菜handler
     */
    private Handler mHandler;
//    DisplayImageOptions options;
//    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
//    protected ImageLoader imageLoader = ImageLoader.getInstance();

	public TestKindSectionedAdapter(Context context, List<String> leftStr, List<List<Dish>> rightStr, Handler mHandler){
		this.mContext = context;
		this.leftStr = leftStr;
		this.rightStr = rightStr;
        this.mHandler = mHandler;
        adapter = new MyKindGridViewAdapter(context,list);
//        options = new DisplayImageOptions.Builder()
//                .showStubImage(R.drawable.meishi_paigu) // 设置图片下载期间显示的图片
//                .showImageForEmptyUri(R.drawable.meishi_paigu) // 设置图片Uri为空或是错误的时候显示的图片
//                .showImageOnFail(R.drawable.meishi_paigu) // 设置图片加载或解码过程中发生错误显示的图片
//                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
//                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
//                .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
//                .build(); // 创建配置过得DisplayImageOption对象
	}

    @Override
    public Object getItem(int section, int position) {
        return rightStr.get(section).get(position);
    }

    @Override
    public long getItemId(int section, int position) {
        return position;
    }

    @Override
    public int getSectionCount() {
        return leftStr.size();
    }

    @Override
    public int getCountForSection(int section) {
        return rightStr.get(section).size();
    }

    @Override
    public View getItemView(final int section, final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder=new ViewHolder();
            LayoutInflater inflator = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.kind_order_list_item, null);
            holder.title = (TextView)convertView.findViewById(R.id.tx_title);
            holder.gridView = (GridView)convertView.findViewById(R.id.gridview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        Log.v(TAG,"right-->" + rightStr.toString());
        holder.gridView.setAdapter(adapter);
        holder.title.setText(rightStr.get(section).get(position).getName());
        if(list != null || !list.isEmpty()){
            list.clear();
        }
        for(int i = 0;i < rightStr.get(section).get(position).getList().size();i++){
            list.add(rightStr.get(section).get(position).getList().get(i));
        }
        Log.v(TAG,"list-->" + list.toString());
        adapter.notifyDataSetChanged();
        holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //handler传值到activity在跳转
                Intent intent = new Intent(mContext, MerchandiseListActivity.class);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        LinearLayout layout = null;
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (LinearLayout) inflator.inflate(R.layout.kind_order_header_item, null);
        } else {
            layout = (LinearLayout) convertView;
        }
        layout.setClickable(false);
        ((TextView) layout.findViewById(R.id.textItem)).setText(leftStr.get(section));
        return layout;
    }

    public final class ViewHolder{
        /**
         * 菜品图片
         */
        public TextView title;
        /**
         * 菜品名称
         */
        public GridView gridView;
    }

}
