package com.neighbor.retailer_android.ui.activity.wholesale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.neighbor.retailer_android.R;
import com.neighbor.retailer_android.bean.WholeSale;
import com.neighbor.retailer_android.ui.adapter.WholeSaleAdapter;
import com.neighbor.retailer_android.ui.view.FtLoadingDialog;
import java.util.ArrayList;
import java.util.List;

public class WholeSaleListActivity extends Activity implements View.OnClickListener{

    /* 返回键 */
    private ImageButton back;
    /**
     * listview
     */
    private ListView saleListView = null;
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
    /**
     *  loading 对话框
     *  */
    private FtLoadingDialog dialog;
    /* 搜索关键词 */
    private String key;
    /* 标题 */
    private TextView title;

    /**
     * show loading 对话框
     * @param loadingText
     */
    private void showDialog(String loadingText){
        dialog = new FtLoadingDialog(this,loadingText);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole_sale_list);

        key = getIntent().getStringExtra("KEY");
        back = (ImageButton)findViewById(R.id.wholesaler_back);
        title = (TextView)findViewById(R.id.wholesaler_title);
        title.setText("批发商列表");
        saleListView = (ListView) findViewById(R.id.whole_listview);
        listviewLayout = (LinearLayout)findViewById(R.id.whole_lv_layout);
        noNetWork=(LinearLayout)findViewById(R.id.whole_not_network);
        doLoddingBtn=(Button)findViewById(R.id.ms_tab_do_lodding_btn);
        noNetWork.setVisibility(View.GONE);
        doLoddingBtn.setOnClickListener(this);
        back.setOnClickListener(this);
        adapter = new WholeSaleAdapter(this,mList);
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
                String name = mList.get(position).getName();
                Intent intent = new Intent(WholeSaleListActivity.this, WholeSaleDetailActivity.class);
                intent.putExtra("NAME", name);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
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
