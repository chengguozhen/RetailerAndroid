package com.neighbor.retailer_android.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.WholeSale;
import com.neighbor.retailer_android.ui.adapter.WholeSaleAdapter;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("NewApi")
public class WholeSaleTabFragment extends Fragment {

    private View rootView;
    /**
     * listview
     */
    private ListView saleListView = null;
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
    private ImageButton searchBtn;
    private EditText searchEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.main_tab_mid, container, false);

        saleListView = (ListView) rootView.findViewById(R.id.sale_listview);
        listviewLayout = (LinearLayout)rootView.findViewById(R.id.lv_layout);
        noNetWork=(LinearLayout)rootView.findViewById(R.id.sale_not_network);
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
        searchEdit = (EditText)rootView.findViewById(R.id.search_name_edit);
        searchBtn.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                //搜索逻辑跳转
            }
        });
        adapter = new WholeSaleAdapter(getActivity(),mList);
        saleListView.setAdapter(adapter);
        for(int i = 0;i < 10;i++){
            WholeSale info = new WholeSale();
            mList.add(info);
        }
        adapter.notifyDataSetChanged();
        return rootView;
    }
}
