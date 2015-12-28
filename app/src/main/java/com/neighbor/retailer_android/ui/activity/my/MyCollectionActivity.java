package com.neighbor.retailer_android.ui.activity.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.WholeSale;
import com.neighbor.retailer_android.ui.activity.wholesale.WholeSaleDetailActivity;
import com.neighbor.retailer_android.ui.adapter.WholeSaleAdapter;
import com.neighbor.retailer_android.ui.view.pulltorefresh.XListView;

import java.util.ArrayList;
import java.util.List;

public class MyCollectionActivity extends Activity implements View.OnClickListener{

    /* 返回键 */
    private ImageButton back;
    /**
     * listview
     */
    private XListView collectListView = null;
    /* 数据源 */
    private List<WholeSale> mList = new ArrayList<WholeSale>();
    /* 适配器 */
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
    /* 标题 */
    private TextView title;
    private int mRefreshIndex = 0;
    private int page = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);

        back = (ImageButton)findViewById(R.id.wholesaler_back);
        title = (TextView)findViewById(R.id.wholesaler_title);
        title.setText("我的收藏");
        collectListView = (XListView) findViewById(R.id.collection_listview);
        listviewLayout = (LinearLayout)findViewById(R.id.collect_lv_layout);
        noNetWork=(LinearLayout)findViewById(R.id.collect_not_network);
        doLoddingBtn=(Button)findViewById(R.id.ms_tab_do_lodding_btn);
        noNetWork.setVisibility(View.GONE);
        doLoddingBtn.setOnClickListener(this);
        back.setOnClickListener(this);
        adapter = new WholeSaleAdapter(this,mList);
        collectListView.setAdapter(adapter);
        //根据key搜索出满足条件的批发商信息填充数据源
        for(int i = 0;i < 10;i++){
            WholeSale info = new WholeSale();
            info.setMidName("幸福便利店" + i);
            mList.add(info);
        }
        adapter.notifyDataSetChanged();
        //getMsListData(1);
        collectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String wsId = mList.get(position).getMidId();
                String name = mList.get(position).getMidName();
                Intent intent = new Intent(MyCollectionActivity.this, WholeSaleDetailActivity.class);
                intent.putExtra("ID", wsId);
                intent.putExtra("NAME", name);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.wholesaler_back:
                finish();
                break;
            case R.id.ms_tab_do_lodding_btn:
                //重新加载逻辑
                //getMsListData(1);
                break;
        }
    }
}
