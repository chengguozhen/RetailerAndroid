package com.neighbor.retailer_android.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.Dish;
import com.neighbor.retailer_android.ui.adapter.KindOrderLeftAdapter;
import com.neighbor.retailer_android.ui.adapter.TestKindSectionedAdapter;
import com.neighbor.retailer_android.ui.view.KindPinnedHeaderListView;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("NewApi")
public class KindTabFragment extends Fragment {

    private String TAG = "nihao";
    /**
     * 滚动轴是否可滚
     */
    private boolean isScroll = true;
    /**
     * 左侧listview
     */
    private ListView leftListView = null;
    /**
     * 右侧listview
     */
    private KindPinnedHeaderListView rightListview = null;
    /**
     * 右侧listview的适配器adapter
     */
    private TestKindSectionedAdapter sectionedAdapter = null;
    /**
     * 左侧listview的适配器adapter
     */
    private KindOrderLeftAdapter leftAdapter = null;
    /**
     * 左侧listview的值
     */
    private List<String> leftStr = new ArrayList<String>();
    /**
     * 右侧listview的值
     */
    private List<List<Dish>> rightStr = new ArrayList<List<Dish>>();
    /**
     * volley监听队列
     */
    //private RequestQueue mQueue;
    /**
     * loading 对话框
     */
    //private FtLoadingDialog dialog;
    /**
     * 未连接网络提示布局
     */
    private LinearLayout noNetWork;
    /**
     * listview布局
     */
    private LinearLayout listviewLayout;
    /**
     * 网络错误，重新加载按钮
     */
    private Button doLoddingBtn;
    private ImageButton searchBtn;
    /**
     * Handler MSG 饭店列表数据更新
     */
    public static final int MS_ORDER_DATA_NOTIFY_DATA=0;
    public static final int MS_ORDER_DATA_NOTIFY_LEFT=5;
    /**
     * 网络未连接
     */
    public static final int MS_NO_NETWORK=2;
    private View rootView;
    /**
     * handler处理
     */
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case MS_ORDER_DATA_NOTIFY_DATA:
//                    if(listviewLayout.getVisibility() == View.GONE){
//                        listviewLayout.setVisibility(View.VISIBLE);
//                    }
//                    List<DishsOfCategory> dishList = (List<DishsOfCategory>)msg.obj;
//                    for(int i = 0;i < dishList.size();i++){
//                        Log.v(TAG,"dish--->" + dishList.get(i));
//                        leftStr.add(dishList.get(i).getCategory().getName());
//                        rightStr.add(dishList.get(i).getDish());
//                    }
//                    leftAdapter.notifyDataSetChanged();
//                    sectionedAdapter.notifyDataSetChanged();
//                    if(noNetWork.getVisibility()==View.VISIBLE){
//                        noNetWork.setVisibility(View.GONE);
//                    }
//                    dismissDialog();
                    break;
                case MS_NO_NETWORK :
                    noNetWork.setVisibility(View.VISIBLE);
                    if(listviewLayout.getVisibility() == View.VISIBLE){
                        listviewLayout.setVisibility(View.GONE);
                    }
                    break;
                case MS_ORDER_DATA_NOTIFY_LEFT:
                    leftAdapter.setIndex(msg.arg1);
                    leftAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.main_tab_02, container, false);
        rightListview = (KindPinnedHeaderListView) rootView.findViewById(R.id.pinnedListView);
        listviewLayout = (LinearLayout)rootView.findViewById(R.id.listview_layout);
        //mQueue = Volley.newRequestQueue(this);
        //getMsListData(1);
        for(int i = 0;i < 10;i++){
            leftStr.add("日用商品" + i);
            List<Dish> list1 = new ArrayList<Dish>();
            for(int j = 0;j < 3;j++) {
                Dish dish = new Dish();
                dish.setName("方便即食" + i + j);
                List<String> list = new ArrayList<String>();
                list.add("牙刷");
                list.add("牙刷");
                list.add("牙刷");
                list.add("牙刷");
                list.add("牙刷");
                list.add("牙刷");
                list.add("牙刷");
                list.add("牙刷");
                list.add("牙刷");
                list.add("牙刷");
                list.add("牙刷");
                list.add("牙刷");
                list.add("牙刷");
                list.add("牙刷");
                list.add("牙刷");
                list.add("牙刷");
                dish.setList(list);
                list1.add(dish);
            }
            rightStr.add(list1);
        }
        sectionedAdapter = new TestKindSectionedAdapter(getActivity(), leftStr, rightStr,mHandler);
        rightListview.setAdapter(sectionedAdapter);
        leftListView = (ListView) rootView.findViewById(R.id.left_listview);
        leftAdapter = new KindOrderLeftAdapter(getActivity(),leftStr);
        leftListView.setAdapter(leftAdapter);
        noNetWork=(LinearLayout)rootView.findViewById(R.id.ms_not_network);
        doLoddingBtn=(Button)rootView.findViewById(R.id.ms_tab_do_lodding_btn);
        noNetWork.setVisibility(View.GONE);
        searchBtn = (ImageButton)rootView.findViewById(R.id.search_btn);
        doLoddingBtn.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新加载逻辑
                //getMsListData(1);
            }
        });
        searchBtn.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                //搜索逻辑跳转
            }
        });
        leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,
                                    long arg3) {
                isScroll = false;

                leftAdapter.setIndex(position);
                leftAdapter.notifyDataSetChanged();

                int rightSection = 0;
                for (int i = 0; i < position; i++) {
                    rightSection += sectionedAdapter.getCountForSection(i) + 1;
                }
                rightListview.setSelection(rightSection);

            }

        });

        rightListview.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView arg0, int arg1) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if (isScroll) {
                    Message msg = new Message();
                    msg.what = MS_ORDER_DATA_NOTIFY_LEFT;
                    msg.arg1 = sectionedAdapter.getSectionForPosition(firstVisibleItem);
                    mHandler.sendMessage(msg);
                    isScroll = false;
                    //leftListView.setSelection(sectionedAdapter.getSectionForPosition(firstVisibleItem));
                } else {
                    isScroll = true;
                }
            }
        });
        Log.v(TAG, "right-->" + rightStr.toString());
        return rootView;
    }
}
