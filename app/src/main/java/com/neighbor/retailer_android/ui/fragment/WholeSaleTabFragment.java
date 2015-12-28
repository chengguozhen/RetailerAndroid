package com.neighbor.retailer_android.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.WholeSale;
import com.neighbor.retailer_android.ui.activity.wholesale.WholeSaleDetailActivity;
import com.neighbor.retailer_android.ui.activity.wholesale.WholeSaleListActivity;
import com.neighbor.retailer_android.ui.adapter.WholeSaleAdapter;
import com.neighbor.retailer_android.ui.view.FtLoadingDialog;
import com.neighbor.retailer_android.ui.view.pulltorefresh.XListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SuppressLint("NewApi")
public class WholeSaleTabFragment extends Fragment implements XListView.IXListViewListener,View.OnClickListener{

    private View rootView;
    /* listview */
    private XListView saleListView = null;
    /* 数据源 */
    private List<WholeSale> mList = new ArrayList<WholeSale>();
    private WholeSaleAdapter adapter;
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
    /* 搜索按钮 */
    private ImageButton searchBtn;
    /* 搜索输入框 */
    private EditText searchEdit;
    /**
     * 分页值
     */
    private int mRefreshIndex = 0;
    /**
     *  loading 对话框
     *  */
    private FtLoadingDialog dialog;

    /**
     * show loading 对话框
     * @param loadingText
     */
    private void showDialog(String loadingText){
        dialog = new FtLoadingDialog(getActivity(),loadingText);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    /**
     * 关闭 loading对话框
     */
    private void dismissDialog(){
        if(dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.main_tab_mid, container, false);

        saleListView = (XListView) rootView.findViewById(R.id.sale_listview);
        listviewLayout = (LinearLayout)rootView.findViewById(R.id.lv_layout);
        noNetWork=(LinearLayout)rootView.findViewById(R.id.sale_not_network);
        doLoddingBtn=(Button)rootView.findViewById(R.id.ms_tab_do_lodding_btn);
        noNetWork.setVisibility(View.GONE);
        searchBtn = (ImageButton)rootView.findViewById(R.id.search_btn);
        doLoddingBtn.setOnClickListener(this);
        searchEdit = (EditText)rootView.findViewById(R.id.search_name_edit);
        searchBtn.setOnClickListener(this);
        adapter = new WholeSaleAdapter(getActivity(),mList);
        saleListView.setAdapter(adapter);
        for(int i = 0;i < 10;i++){
            WholeSale info = new WholeSale();
            info.setName("幸福便利店" + i);
            mList.add(info);
        }
        adapter.notifyDataSetChanged();
        saleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String wsId = mList.get(position).getId();
                String name = mList.get(position).getName();
                Intent intent = new Intent(getActivity(), WholeSaleDetailActivity.class);
                intent.putExtra("ID",wsId);
                intent.putExtra("NAME",name);
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.search_btn:
                //搜索逻辑跳转
                Intent intent = new Intent(getActivity(), WholeSaleListActivity.class);
                intent.putExtra("KEY",searchEdit.getText().toString());
                startActivity(intent);
                break;
            case R.id.ms_tab_do_lodding_btn:
                //重新加载逻辑
                getMsListData(1);
                break;
        }
    }

    /* 加载数据，更新数据源（mode=1重新加载，mode=0上拉加载） */
    private void getMsListData(int mode){

    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
//        mRefreshIndex = 0;
//        if(list != null || !list.isEmpty()){
//            list.clear();
//        }
//        getMsListData(0);
    }

    /**
     * 上拉加载
     */
    @Override
    public void onLoadMore() {
        if(mList.size() >= 10) {
            getMsListData(0);
        }else{
            saleListView.stopLoadMore();
        }
    }


    /**
     * 结束刷新或加载
     */
    private void onLoad() {
        saleListView.stopRefresh();
        saleListView.stopLoadMore();
        saleListView.setRefreshTime(getTime());
    }

    /**
     * @return 时间日期
     */
    private String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }
}
